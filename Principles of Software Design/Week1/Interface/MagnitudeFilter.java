public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;

    MagnitudeFilter(double min, double max) {
        this.minMag = min;
        this.maxMag = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= this.minMag && qe.getMagnitude() <= this.maxMag;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}
