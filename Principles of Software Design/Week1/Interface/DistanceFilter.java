public class DistanceFilter implements Filter{
    private Location myLoc;
    private double maxDis;

    DistanceFilter(Location loc, double max) {
        this.myLoc = loc;
        this.maxDis = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(this.myLoc) <= this.maxDis;
    }

    public String getName() {
        return getClass().getSimpleName();
    }
}
