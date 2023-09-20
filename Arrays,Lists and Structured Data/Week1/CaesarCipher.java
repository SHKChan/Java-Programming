public class CaesarCipher {
    public void testCase() {
        this.testEncryptTwoKeys();
    }

    private void testEncrypt() {

        // FileResource fr = new FileResource();
        // String message = fr.asString();

        // String message = "First Legion Attack East Flank!";
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        String encrypted = this.encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    private void testEncryptTwoKeys() {
        String message = "First Legion";
        // String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 23;
        int key2 = 17;
        String encrypted = this.encryptTwoKeys(message, key1, key2);
        System.out.println("key1 is " + key1 + ", key2 is " + key2 + "\n" + encrypted);
    }

    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        StringBuilder sb = new StringBuilder(input);
        int index = -1;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            index = alphabet.indexOf(Character.toUpperCase(c));
            if (-1 == index) {
                continue;
            }

            if (Character.isUpperCase(c)) {
                sb.setCharAt(i, shiftedAlphabet.charAt(index));
            } else {
                sb.setCharAt(i, Character.toLowerCase(shiftedAlphabet.charAt(index)));
            }
        }
        return sb.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        String encrypted1 = this.encrypt(input, key1);
        String encrypted2 = this.encrypt(input, key2);
        StringBuilder sb = new StringBuilder(encrypted2);
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                sb.setCharAt(i, encrypted1.charAt(i));
            }
        }
        return sb.toString();
    }
}
