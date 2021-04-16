package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//This allows classes in the tests package to access things in our main oackage, "marshmelloSongGenerator2"
import marshmelloSongGenerator2.*;

public class ExampleTest {

	/**
	 * Just an example of how to write a system test using this tests package setup and the testFiles folder
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Get a file from the testFiles folder to test
		File testFile = new File("./testFiles/testFile1_TurningObjectsIntoPercussion.wav");
		assert(testFile.exists()) : "Error: Cannot find file"; //Test if the file exists
	}

}
