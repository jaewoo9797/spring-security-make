package securitymake.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class KeyGenerateTest {

    @Nested
    @DisplayName("StringKeyGenerator")
    class StringKey {
        StringKeyGenerator keyGenerator = KeyGenerators.string();

        @DisplayName("생성한 String key")
        @Test
        void test1() {
            // given
            // when
            String key = keyGenerator.generateKey();
            System.out.println(key);
            // then
            Assertions.assertNotNull(key);
        }
    }

    @Nested
    @DisplayName("BytesKey")
    class BytesKey {
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(16);

        @DisplayName("bytes key generator")
        @Test
        void test1() {
            // given

            // when
            byte[] key = keyGenerator.generateKey();
            System.out.println("keyGenerator.getKeyLength() = " + keyGenerator.getKeyLength());
            System.out.println("key = " + key);
            // then
            Assertions.assertNotNull(key);
        }
    }
}
