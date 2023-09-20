public class StringsFirstAssignments{
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void main(String[] args) {
		clearScreen();
		part4 p = new part4();
		p.testing();
	}
}