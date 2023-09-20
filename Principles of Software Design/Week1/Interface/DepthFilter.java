public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;

    DepthFilter(double min, double max) {
        this.minDep = min;
        this.maxDep = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= this.minDep && qe.getDepth() <= this.maxDep;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}
