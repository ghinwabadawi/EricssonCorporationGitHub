import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

class Employee {
	public String employeeName;
	public double basePay;
	public double hoursWorked;

	public Employee() {

	}
}

public class EricssonCorporation {
	static double minBasePay = 8;
	static double maxWorkHourAllowed = 60;
	static double thresHoldHoursPerWeek = 40;
	static double overtimeTimesBasePay = 1.5;

	public EricssonCorporation() {

	}

	public static double calcTotalPay(double basePay, double hoursWorked) {
		double totalPayResult = 0.0;
		if (basePay < minBasePay)
			totalPayResult = -1;

		else if (hoursWorked > maxWorkHourAllowed)
			totalPayResult = -2;

		else {
			double totalPay = thresHoldHoursPerWeek * basePay;
			double overtimeHours = hoursWorked - thresHoldHoursPerWeek;
			if (overtimeHours > 0) {
				totalPay = totalPay + (totalPay * overtimeHours * overtimeTimesBasePay);
				totalPayResult = totalPay;
			}
		}

		return totalPayResult;

	}

	// the getTotalPay is separated from calcTotalPay to separate the user
	// friendly output from the calculation of the totalpay. and also so that we
	// can easily unit test the calcTotalPay function.

	public static String getTotalPay(double basePay, double hoursWorked) {
		double totalPay = calcTotalPay(basePay, hoursWorked);
		if (totalPay == -1)
			return "Error! Base Pay is less than " + minBasePay;
		else if (totalPay == -2)
			return "Error! Hours Worked are greater than " + maxWorkHourAllowed;
		else
			return "Total Pay = " + totalPay;
	}

	public static void main(String[] args) {

		Employee emp = new Employee();

		List<Employee> empList = new ArrayList<Employee>();

		emp.employeeName = "Employee 1";
		emp.basePay = 7.5;
		emp.hoursWorked = 35;
		empList.add(emp);

		emp = new Employee();
		emp.employeeName = "Employee 2";
		emp.basePay = 8.2;
		emp.hoursWorked = 47;
		empList.add(emp);

		emp = new Employee();
		emp.employeeName = "Employee 3";
		emp.basePay = 10;
		emp.hoursWorked = 73;
		empList.add(emp);

		for (int i = 0; i < empList.size(); i++) {

			System.out.println(empList.get(i).employeeName + " : "
					+ getTotalPay(empList.get(i).basePay, empList.get(i).hoursWorked));
		}
	}

	@Test
	public void testCalc() {
		assertEquals(3772.0, calcTotalPay(8.2, 47), 0.0);
		assertEquals(-1, calcTotalPay(7.9, 35), 0.0);
		assertEquals(-2, calcTotalPay(10, 61), 0.0);
	}
}
