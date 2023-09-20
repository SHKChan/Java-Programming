public class CaesarCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipherOOP(int key){
        this.mainKey = key;
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet = this.alphabet.substring(this.mainKey) + this.alphabet.substring(0, this.mainKey);
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        int index = -1;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            index = this.alphabet.indexOf(Character.toUpperCase(c));
            if (-1 == index) {
                continue;
            }

            if (Character.isUpperCase(c)) {
                sb.setCharAt(i, this.shiftedAlphabet.charAt(index));
            } else {
                sb.setCharAt(i, Character.toLowerCase(this.shiftedAlphabet.charAt(index)));
            }
        }
        return sb.toString();
    }

    public String decrypt(String encryptedMsg) {
        CaesarCipherOOP cc = new CaesarCipherOOP(26 - mainKey);
        return cc.encrypt(encryptedMsg);
    }
}
