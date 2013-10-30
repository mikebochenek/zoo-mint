package zoo.mb.fe.week1;

public class Lottery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		question2();
	}

	public static void question1() {
		double total = 0.0;
		for (int i = 0; i < 20; i++) {
			total += 500000 / Math.pow(1.1, i);
			System.out.println("at " + i + " our total is " + total);
		}
		System.out.println(total);
	}

	public static void question2() {
		double totalApt1 = 1000.0;
		double totalApt2 = 900.0;
		totalApt1 = (totalApt1 * Math.pow(1.12, 6));
		totalApt2 = (totalApt2 * Math.pow(1.12, 6));

		System.out.println("interest on  apt1=" + (totalApt1-1000) + " apt2=" + (totalApt2-900));
	}
	
	// question 3, we just try d(0, 2) = 1 / (1 + 0.069) ^ 2
	// = 0.875073616
}
