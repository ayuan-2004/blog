package cn.lanqiao.blog.utile;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordHashUtil {

    // 推荐参数 - 可以根据硬件性能调整
    private static final int ITERATIONS = 100000;
    private static final int KEY_LENGTH = 256; // 位

    // 固定盐值 (16字节) - 你可以修改为你想要的固定值
    private static final byte[] FIXED_SALT = {
            (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78,
            (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF,
            (byte)0x11, (byte)0x22, (byte)0x33, (byte)0x44,
            (byte)0x55, (byte)0x66, (byte)0x77, (byte)0x88
    };

    /**
     * 生成固定盐值
     * @return 固定盐值
     */
    public static byte[] generateSalt() {
        return FIXED_SALT.clone(); // 返回盐值的副本以避免外部修改
    }

    /**
     * 哈希密码
     * @param password 原始密码
     * @param salt 盐值
     * @return 哈希后的密码
     */
    public static String hashPassword(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("密码哈希失败", e);
        }
    }

    /**
     * 验证密码
     * @param inputPassword 用户输入的密码
     * @param storedHash 存储的哈希值
     * @param storedSalt 存储的盐值
     * @return 是否匹配
     */
    public static boolean verifyPassword(String inputPassword, String storedHash, byte[] storedSalt) {
        String newHash = hashPassword(inputPassword, storedSalt);
        return newHash.equals(storedHash);
    }

    /**
     * 完整密码哈希流程
     * @param password 原始密码
     * @return 包含哈希值和盐值的对象
     */
    public static PasswordHashResult hashPassword(String password) {
        byte[] salt = generateSalt();
        String hash = hashPassword(password, salt);
        return new PasswordHashResult(hash, salt);
    }

    public static class PasswordHashResult {
        private final String hash;
        private final byte[] salt;

        public PasswordHashResult(String hash, byte[] salt) {
            this.hash = hash;
            this.salt = salt;
        }

        public String getHash() {
            return hash;
        }

        public byte[] getSalt() {
            return salt;
        }
    }
}