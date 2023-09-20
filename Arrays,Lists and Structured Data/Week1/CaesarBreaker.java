import java.util.Scanner;
import edu.duke.FileResource;

public class CaesarBreaker {
    private CaesarCipher cc = new CaesarCipher();
    private String msg = "";
    private String encryptedMsg = "";

    public void testCase() {
        // this.testDecryptMaxE();
        this.testDecryptTwoKeys();
        //System.out.println(this.cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26-2, 26-20));
    }

    public void testDecryptTwoKeys() {
        // FileResource fr = new FileResource();
        // this.msg = fr.asString();
        // System.out.println("Input message is: " + this.msg);
        // this.encryptedMsg = this.cc.encryptTwoKeys(msg, 23, 2);

        this.encryptedMsg = new FileResource().asString();

        //System.out.println("encrypted message is: " + this.encryptedMsg);
        System.out.println("Input message is: " + this.decryptTwoKeys(this.encryptedMsg));
    }

    private void testDecryptMaxE() {
        FileResource fr = new FileResource();
        this.msg = fr.asString();
        this.encryptedMsg = this.cc.encrypt(msg, 10);

        System.out.println("Input message is: " + this.msg);
        System.out.println("encrypted message is: " + this.encryptedMsg);

        int key = this.decryptMaxE(this.encryptedMsg);
        System.out.println("key is: " + key);
        System.out.println("Input message is: " + this.cc.encrypt(this.encryptedMsg, 26 - key));
    }

    private int decryptMaxE(String encryptedMsg) {
        String[] decryptedMsg = this.decrypt(encryptedMsg);
        int key = 0;
        int maxEs = 0;
        int curEs = 0;
        for (int i = 0; i < 26; i++) {
            curEs = this.numberOfEs(decryptedMsg[i]);
            if (curEs > maxEs) {
                maxEs = curEs;
                key = i;
            }
        }
        return key + 1;
    }

    private int decryptEyeball(String encryptedMsg) {
        String[] decryptedMsg = this.decrypt(encryptedMsg);
        for (int i = 0; i < 26; i++) {
            System.out.println("Decrypted key is : " + (i + " ," + decryptedMsg[0]));
        }
        System.out.print("Please check and input the most possible key: ");
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        scanner.close();
        return key;
    }

    private String[] decrypt(String encryptedMsg) {
        String[] decryptedMsg = new String[26];
        for (int i = 0; i < 26; i++) {
            decryptedMsg[i] = this.cc.encrypt(encryptedMsg, 26 - (i + 1));
        }
        return decryptedMsg;
    }

    private int numberOfEs(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.toLowerCase(input.charAt(i)) == 'e') {
                count++;
            }
        }
        return count;
    }

    private String halfOfString(String inptu, int startIdx) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIdx; i < inptu.length(); i += 2) {
            sb.append(inptu.charAt(i));
        }
        return sb.toString();
    }

    private int[] countLetters(String input) {
        String str = input.toLowerCase();
        int[] count = new int[26];
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(str.charAt(i)))
                count[str.charAt(i) - 'a']++;
        }
        return count;
    }

    private String decryptTwoKeys(String encryptedMsg) {
        String encryptedMsg1 = this.halfOfString(encryptedMsg, 0);
        String encryptedMsg2 = this.halfOfString(encryptedMsg, 1);
        int key1 = this.getKey(encryptedMsg1);
        int key2 = this.getKey(encryptedMsg2);
        String msg1 = this.cc.encrypt(encryptedMsg1, 26-key1);
        String msg2 = this.cc.encrypt(encryptedMsg2, 26-key2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encryptedMsg.length(); i++) {
            if (i % 2 == 0)
                sb.append(msg1.charAt(i / 2));
            else
                sb.append(msg2.charAt(i / 2));
        }
        return sb.toString();
    }

    private int getKey(String encryptedMsg) {
        int[] LetterCount = this.countLetters(encryptedMsg);
        int ret = 4 - this.maxIndex(LetterCount);
        if (ret < 0)
            ret += 26;
        return 26 - ret;
    }

    private int maxIndex(int[] count) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        return index;
    }
}
