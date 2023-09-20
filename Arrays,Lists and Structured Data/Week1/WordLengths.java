import edu.duke.FileResource;

public class WordLengths {
    public void testCase() {
        testCountWordLengths();
    }

    private void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        String[] wordsList = this.countWorldLengths(fr, counts);
        int maxIndex = this.indexOfMax(counts);
        for (int i = 0; i < counts.length; i++) {
            if (0 == counts[i])
                continue;
            System.out.println(counts[i] + " words of length " + (i + 1) + ": "/*  + wordsList[i] */);
        }
        System.out.println("max index is " + maxIndex);
    }

    private String[] countWorldLengths(FileResource fr, int[] counts) {
        int curWordLength = 0;
        int start = 0;
        String validWord = "";
        final  int listLength = counts.length;
        String[] wordsList = new String[listLength];
        for(String w : fr.words()){
            curWordLength = w.length();
            start = 0;
            if(!Character.isLetter(w.charAt(curWordLength-1))){
                curWordLength--;
            }
            if(!Character.isLetter(w.charAt(0))){
                start = 1;
            }
            if(curWordLength < start)
                continue;
            validWord = w.substring(start, curWordLength);
            if(listLength < curWordLength - start){
                curWordLength = listLength;
            }
            if(curWordLength-1 < 0)
                continue;
            counts[curWordLength-1] += 1;
            if(null != wordsList[curWordLength-1])
                wordsList[curWordLength-1] += validWord + " ";
            else
                wordsList[curWordLength-1] = validWord + " ";
        }
        return wordsList;
    }

    public int indexOfMax(int[] arr){
        int max = arr[0];
        int index = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
                index = i;
            }
        }
        return index+1;
    }
}
