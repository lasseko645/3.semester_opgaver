package testdrevetdevelopment;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Main {

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};




    public static void main(String[] args) {

        Main main = new Main();
        main.simple_alc();

        try {
            System.out.println( main.encrypt("kasserolle"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( decrypt(main.encrypt("kasserolle")));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }




    public String encrypt(String data ) throws Exception {

        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }


    private void simple_alc(){

        String text = "skolebkkkkkkøger";

        for (int i = 0; i < text.length() ; i++ ){


            char node = text.charAt(i);
            if (node == 'k'){
                node = 's';
            }
            System.out.print(node);

            //char node = reader.next().charAt(i);

        }
        System.out.println();

    }




}
