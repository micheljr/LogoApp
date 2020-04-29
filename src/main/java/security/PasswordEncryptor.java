package security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordEncryptor {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RAND = new SecureRandom();

    public static Optional<String> hashPassword(String password, String salt){
        if (password == null | salt.length()<1){
            System.out.println("password = " + password);
            System.out.println("salt = " + salt);
            return Optional.empty();
        }

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();


        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars,Character.MIN_VALUE);

        try{
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex){
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();
        } finally {
            spec.clearPassword();
        }
    }

    public static Optional<String> generateSalt(final int length){
        if (length < 1){
            System.err.println("Error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public static boolean verifyPassword(String password, String hash){
        String salt = hash.substring(hash.length()-16);
        String hpw = hash.substring(0,hash.length()-16);
        return verifyHashPassword(password, hpw, salt);
    }

    public static boolean verifyHashPassword(String password, String key, String salt){
        Optional<String> optEncrypted = hashPassword(password, salt);
        return optEncrypted.map(s -> s.equals(key)).orElse(false);
    }

    public static String getEncryptedPassword(String password){
        Optional<String> gsalt = generateSalt(10);
        String salt = "";
        if (gsalt.isPresent()){
            salt = gsalt.get();
        }

        Optional<String> hpw = hashPassword(password, salt);
        String hash = "";
        if (hpw.isPresent()) hash = hpw.get();

        return hash + salt;
    }
}
