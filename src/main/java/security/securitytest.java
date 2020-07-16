package security;

public class securitytest {
    public static void main(String[] args) {
        String paswoord = "abcdefgh";
        String secret = PasswordEncryptor.getEncryptedPassword(paswoord);

        System.out.println("secret = " + secret);

        System.out.println(PasswordEncryptor.verifyPassword("abcdefgh", secret));

    }
}
