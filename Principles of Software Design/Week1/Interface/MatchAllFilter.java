import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        this.filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }

    public String getName() {
        String ret = "";
        for (Filter filter : filters) {
            ret += filter.getName() + " ";
        }
        return ret;
    }
}
