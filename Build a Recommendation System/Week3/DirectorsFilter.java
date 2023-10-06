
public class DirectorsFilter implements Filter {
	private String myDirectors;

	public DirectorsFilter(String directors) {
		this.myDirectors = directors;
	}

	@Override
	public boolean satisfies(String id) {
		boolean ret = false;
		String directors = MovieDatabase.getDirector(id);
		for (String director : this.myDirectors.split(",")) {
			if (directors.contains(director)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

}
