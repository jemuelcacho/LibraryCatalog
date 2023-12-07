package main;

import java.io.IOException;
import java.time.LocalDate;

public class TestMain {

	/*
	 * You can use this method for testing. If you run it as is 
	 * you should be able to generate the same report as report/expected_report.txt
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			LibraryCatalog lc = new LibraryCatalog();
			lc.generateReport();

	}
}
