import edu.duke.FileResource;

public class TestCaesarCipher  {
    public void simpleTests(){
        /* String input = new FileResource().asString();
        CaesarCipherOOP cc = new CaesarCipherOOP(18); */

        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherOOP cc = new CaesarCipherOOP(15);
        String output = cc.encrypt(input);
        System.out.println("The encrypted message is: " + output);
        System.out.println("The decrypted message is: " + cc.decrypt(output));

        int key = this.breakCaesarCipher(output);
        System.out.println("The key is: " + key);
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

    public int breakCaesarCipher(String encryptedMsg) {
        int[] LetterCount = this.countLetters(encryptedMsg);
        int ret = 4 - this.maxIndex(LetterCount);
        if (ret < 0)
            ret += 26;
        return 26 - ret;
    }
}
