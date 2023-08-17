package project;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MideaDecryption {


        private static final String ENCRYPTION_ALGORITHM = "AES";
        private static final String SECRET_KEY = "my_secret_key_16";

        public static void main(String[] args) {
            String inputFile = "/Users/abhijeetsavale/Downloads/star1.png";
            String outputFile = "/Users/abhijeetsavale/Downloads/star2.png";

            try {
                decryptFile(inputFile, outputFile);
            } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }
        }

        private static void decryptFile(String inputFile, String outputFile) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
            // Read the input media file
            InputStream inputStream = new FileInputStream(inputFile);

            // Create the output encrypted file
            OutputStream outputStream = new FileOutputStream(outputFile);

            // Create the cipher and initialize it for encryption
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // Encrypt the media file
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close streams
            cipherInputStream.close();
            inputStream.close();
            outputStream.close();

            System.out.println("decryption completed successfully.");
        }



}
