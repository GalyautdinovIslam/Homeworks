package FirstSemester;

public class SumCalc6 {
	public static void main(String[] args) {
		if (args.length == 1) {
			int n = Integer.parseInt(args[0]);
			double s = 0.0;
			double numerator = 1.0;
			double denominator = 1.0;
			
			if (n > 0) {
				for (int m = n; m > 0; m--) {
					if (m == 1) {
						s = s + 0.5;
					} else {
						
						for (int i = 1; i <= (m - 1); i++) {
							numerator = numerator * i;
						}
						numerator = numerator*numerator;
						
						for (int j = 1; j <= (2*m); j++) {
							denominator = denominator * j;
						}
						s = s + numerator/denominator;
						
						numerator = 1.0;
						denominator = 1.0;
					}
				}
				System.out.println("The sum is " + s + ".");
			} else {
				System.out.println("The quantity of sum members must be greater than .");
			}
		} else {
			System.out.println("Please enter the quantity of sum members.");
		}
	}
}