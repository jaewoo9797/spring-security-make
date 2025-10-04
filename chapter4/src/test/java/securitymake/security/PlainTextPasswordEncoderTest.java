package securitymake.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class PlainTextPasswordEncoderTest {

    PasswordEncoder passwordEncoder = new PlainTextPasswordEncoder();

    @Nested
    @DisplayName("PlainTextEncoder Test")
    class EncodePassword {

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
}
