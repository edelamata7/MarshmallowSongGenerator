package tests;

import java.util.ArrayList;
import java.io.File;
import marshmelloSongGenerator2.*;

public class SongGeneratorTest {
	
	public static void main(String[] args) {
		
	}
	
	public void test1() {
		SongGenerator tester = new SongGenerator();
		
		ArrayList<File> testFiles = new ArrayList<File>();
		
		tester.createProjectListTest(testFiles);
		
	}

}
