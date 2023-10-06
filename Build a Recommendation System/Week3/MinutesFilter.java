
public class MinutesFilter implements Filter {
	private int myMin;
	private int myMax;

	public MinutesFilter(int min, int max) {
		this.myMin = min;
		this.myMax = max;
	}

	@Override
	public boolean satisfies(String id) {
		return (MovieDatabase.getMinutes(id) >= this.myMin && MovieDatabase.getMinutes(id) <= this.myMax);
	}

}
