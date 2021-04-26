package tests;

import java.util.ArrayList;
import java.io.File;
import marshmelloSongGenerator2.*;
import wavFile.WavFile;

public class SongGeneratorTest {
	
	public static void main(String[] args) {
		//new SongGeneratorTest().test1();
		new SongGeneratorTest().test2();
	}
	
	public void test1() {
		SongGenerator tester = new SongGenerator();
		
		ArrayList<File> testFiles = new ArrayList<File>();
		testFiles.add(new File("./testFiles/testFile1_TurningObjectsIntoPercussion.wav"));
		testFiles.add(new File("./testFiles/testFile2_BassLoopsWithDrums.wav"));
		testFiles.add(new File("./testFiles/testFile3_BassLoopsNoDrums.wav"));
		testFiles.add(new File("./testFiles/testFile4_DiscoFunkLoopsNoDrums.wav"));
		testFiles.add(new File("./testFiles/testFile5_DiscoFunkLoopsWithDrums.wav"));
		
		ProjectList result = tester.createProjectListTest(testFiles);
		
		ArrayList<File> resultFiles = result.getFiles();
		
		for (int i = 0; i<resultFiles.size(); i++) {
			System.out.println(resultFiles.get(i).getName());
		}
	}
	
	public void test2() {
		SongGenerator tester = new SongGenerator();
		
		try {
			tester.generateSong();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
