import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    private int[] countLetters(String input) {
        String str = input.toLowerCase();
        int[] count = new int[26];
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(str.charAt(i)))
                count[str.charAt(i) - 'a']++;
        }
        return count;
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

    private String halfOfString(String inptu, int startIdx) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIdx; i < inptu.length(); i += 2) {
            sb.append(inptu.charAt(i));
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

    private String breakCaesarCipherTwo(String encryptedMsg) {
        String encryptedMsg1 = this.halfOfString(encryptedMsg, 0);
        String encryptedMsg2 = this.halfOfString(encryptedMsg, 1);
        int key1 = this.getKey(encryptedMsg1);
        int key2 = this.getKey(encryptedMsg2);
        System.out.println("key1 is: " + key1 + ", key2 is: " + key2);
        return new CaesarCipherTwoOOP(key1, key2).decrypt(encryptedMsg);
    }

    public void simpleTests() {
        /* String input = new FileResource().asString();
        CaesarCipherTwoOOP cct = new CaesarCipherTwoOOP(23, 2); */
        /* String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwoOOP cct = new CaesarCipherTwoOOP(21, 8);
        String output = cct.encrypt(input);
        System.out.println("The encrypted message is: " + output);
        System.out.println("The decrypted message is: " + cct.decrypt(output)); */

        String output = new FileResource().asString();
        System.out.println("The breaked message is: " + this.breakCaesarCipherTwo(output));
    }
}
