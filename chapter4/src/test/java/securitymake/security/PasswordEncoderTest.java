package securitymake.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

class PasswordEncoderTest {


    @Nested
    @DisplayName("PlainTextEncoder Test")
    class PlainTextTest {
        PasswordEncoder passwordEncoder = new PlainTextPasswordEncoder();
        private final String plainText = "abcde12345!@#$";

        @DisplayName("생성한 문자열을 encode 결과가 동일하다.")
        @Test
        void test1() {
            // when
            String encoded = passwordEncoder.encode(plainText);
            // then
            assertEquals(plainText, encoded);
        }

        @DisplayName("match 결과가 true여야 한다.")
        @Test
        void test2() {
            // given
            String encoded = passwordEncoder.encode(plainText);
            // when
            boolean matches = passwordEncoder.matches(plainText, encoded);
            // then
            assertTrue(matches);
        }
    }

    @Nested
    @DisplayName("SHA-512 암호 테스트")
    class SHA512Test {
        PasswordEncoder passwordEncoder = new Sha512PasswordEncoder();
        private final String plainText = "abcde12345!@#$";

        @DisplayName("생성한 문자열을 encode 결과는 해시값이다.")
        @Test
        void test1() {
            // when
            String encoded = passwordEncoder.encode(plainText);
            // then
            Assertions.assertThat(encoded)
                .isNotEqualTo(plainText);
        }

        @DisplayName("match 결과가 true여야 한다.")
        @Test
        void test2() {
            // given
            String encoded = passwordEncoder.encode(plainText);
            // when
            boolean matches = passwordEncoder.matches(plainText, encoded);
            // then
            assertTrue(matches);
        }
    }

    @Nested
    @DisplayName("DelegatingPasswordEncoder")
    class DelegatingPasswordEncoder {
        PasswordEncoder passwordEncoder;
        String rawPassword = "1234qwer!@#$";
        PasswordEncoder scrypt;

        @BeforeEach
        void setUp() {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            scrypt = new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
        }

        @DisplayName("noop 인코딩 테스트")
        @Test
        void test1() {
            // given
            String prefix = "{noop}";
            // when
            boolean matches = passwordEncoder.matches(rawPassword, prefix + rawPassword);
            // then
            assertTrue(matches);
        }

        @DisplayName("bcrypt 인코딩 테스트")
        @Test
        void test2() {
            // given
            String encoded = passwordEncoder.encode(rawPassword);
            System.out.println(encoded);
            // when
            boolean matches = passwordEncoder.matches(rawPassword, encoded);
            // then
            assertTrue(matches);
        }

        @DisplayName("scrypt encode")
        @Test
        void test3() {
            // given
            String encoded = scrypt.encode(rawPassword);
            String prefix = "{scrypt}";
            System.out.println(encoded);
            // when
            boolean matches = passwordEncoder.matches(rawPassword, prefix+encoded);
            // then
            assertTrue(matches);
        }
    }
}
