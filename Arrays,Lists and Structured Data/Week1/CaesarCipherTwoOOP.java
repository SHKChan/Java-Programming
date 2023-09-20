import edu.duke.FileResource;

public class CaesarCipherTwoOOP {
    private int mainKey1;
    private int mainKey2;
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;

    public CaesarCipherTwoOOP(int key1, int key2) {
        this.mainKey1 = key1;
        this.mainKey2 = key2;
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = this.alphabet.substring(this.mainKey1) + this.alphabet.substring(0, this.mainKey1);
        this.shiftedAlphabet2 = this.alphabet.substring(this.mainKey2) + this.alphabet.substring(0, this.mainKey2);
    }

    public String encrypt(String input) {
        CaesarCipherOOP cc1 = new CaesarCipherOOP(this.mainKey1);
        CaesarCipherOOP cc2 = new CaesarCipherOOP(this.mainKey2);
        String encrypted1 = cc1.encrypt(input);
        String encrypted2 = cc2.encrypt(input);
        StringBuilder sb = new StringBuilder(encrypted2);
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                sb.setCharAt(i, encrypted1.charAt(i));
            }
        }
        return sb.toString();
    }

    public String decrypt(String encryptedMsg) {
        CaesarCipherOOP cc1 = new CaesarCipherOOP(26-this.mainKey1);
        CaesarCipherOOP cc2 = new CaesarCipherOOP(26+-this.mainKey2);
        String decrypted1 = cc1.encrypt(encryptedMsg);
        String decrypted2 = cc2.encrypt(encryptedMsg);
        StringBuilder sb = new StringBuilder(decrypted2);
        for (int i = 0; i < encryptedMsg.length(); i++) {
            if (i % 2 == 0) {
                sb.setCharAt(i, decrypted1.charAt(i));
            }
        }
        return sb.toString();
    }    
}
