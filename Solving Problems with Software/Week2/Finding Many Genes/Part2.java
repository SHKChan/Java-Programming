public class Part2 {
    public int howMany(String a, String b){
        int count = 0;
        a = a.toUpperCase();
        b = b.toUpperCase();
        int fIndex = a.indexOf(b);
        while(-1 != fIndex){
            count++;
            fIndex = a.indexOf(b, fIndex + b.length());
        }
        
        return count;
    }

    public void testHowMany(){
        String a = "ATGAACGAATTGAATC";
        String b = "GAA";
        System.out.println(String.format("String: \"%s\" occurs %d times in String: \"%s\"", b, howMany(a, b), a));
        a = "ATAAAA";
        b = "AA";
        System.out.println(String.format("String: \"%s\" occurs %d times in String: \"%s\"", b, howMany(a, b), a));
    }
}
