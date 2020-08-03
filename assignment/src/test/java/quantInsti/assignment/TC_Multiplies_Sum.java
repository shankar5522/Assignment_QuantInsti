/**
 * 
 */
package quantInsti.assignment;

/**
 * @author Shankar
 *
 */
public class TC_Multiplies_Sum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TC_Multiplies_Sum obj = new TC_Multiplies_Sum();
		// TODO Auto-generated method stub
		//obj.sumOfMultipler(10);
		obj.sumOfMultipler(1000);
	}

	public void sumOfMultipler(int n) {
		int sum = 0;
		for (int i = 1; i < n; i++)
			if (i % 3 == 0 || i % 5 == 0) {
				System.out.print(i + " ");
				sum = sum + i;
			}
		System.out.println();
		System.out.println("Sum of Natuarl number between 3 & 5 that multiplies of " + n + " is = " + sum);
	}

}
