public class part3 {
    public boolean twoOccurrences(String a, String b) {
        int count = 0;
        int tempIdx = a.indexOf(b);
        while (-1 != tempIdx) {
            count++;
            tempIdx = a.indexOf(b, tempIdx + 1);
        }
        return count >= 2;
    }

    public String lastPart (String a, String b) {
        return (-1 != a.indexOf(b)) ? a.substring(a.indexOf(b)+b.length()) : a;
    }

    public void testing() {
        // // String a = "A story by Abby Long";
        // // String b = "by";
        // // String a = "banana";
        // // String b = "a";
        // String a = "ctgtatgta";
        // String b = "atg";
        // if (twoOccurrences(a, b))
        //     System.out.println("String b: \"" + b + "\" dose occrences more than twich in String a: \"" + a + "\"");
        // else{
        //     System.out.println("String b: \"" + b + "\" dose not occrences more than twich in String a: \"" + a + "\"");
        // }

        // String a = "banana";
        // String b = "an";
        String a = "forest ";
        String b = "zoo ";
        System.out.println(lastPart(a, b));
    }
}
