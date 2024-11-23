import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class DESImp {
  private static final String ALGORITHM = "DES";

  public static String encrypt(String input, byte[] encryptionKey) throws Exception {
    Key key = new SecretKeySpec(encryptionKey, ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptedBytes = cipher.doFinal(input.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  public static String decrypt(String input, byte[] encryptionKey) throws Exception {
    Key key = new SecretKeySpec(encryptionKey, ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));
    return new String(decryptedBytes);
  }
}

