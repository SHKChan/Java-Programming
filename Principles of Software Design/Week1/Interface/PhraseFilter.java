enum SearchPos {
    START,
    END,
    ANY
}

public class PhraseFilter implements Filter {
    private SearchPos searchPos;
    private String searchPhrase;

    PhraseFilter(SearchPos pos, String phrase) {
        this.searchPos = pos;
        this.searchPhrase = phrase;
    }

    public boolean satisfies(QuakeEntry qe) {
        String phrase = qe.getInfo();
        int fIndex = phrase.indexOf(this.searchPhrase);
        boolean ret = false;
        switch (this.searchPos) {
            case START:
                if (0 == fIndex) {
                    ret = true;
                }
                break;
            case END:
                fIndex = phrase.lastIndexOf(this.searchPhrase);
                if (phrase.length() - this.searchPhrase.length() == fIndex) {
                    ret = true;
                }
                break;
            case ANY:
                if (-1 != fIndex) {
                    ret = true;
                }
                break;
            default:
                break;
        }
        return ret;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}
