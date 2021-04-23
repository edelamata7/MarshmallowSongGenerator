package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import marshmelloSongGenerator2.*;

public class ProjectManagerTests {
	
	public static void main(String[] args) {
		ProjectManagerTests driver = new ProjectManagerTests();
		driver.saveProjectTest();
		driver.openProjectFileTest();
	}
	
	public void saveProjectTest() {
		ArrayList<File> soundFiles = new ArrayList<File>();
		
		//Get files from the testFiles folder:
		soundFiles.add(new File("./testFiles/testFile1_TurningObjectsIntoPercussion.wav"));
		soundFiles.add(new File("./testFiles/testFile2_BassLoopsWithDrums.wav"));
		soundFiles.add(new File("./testFiles/testFile3_BassLoopsNoDrums.wav"));
		soundFiles.add(new File("./testFiles/testFile4_DiscoFunkLoopsNoDrums.wav"));
		soundFiles.add(new File("./testFiles/testFile5_DiscoFunkLoopsWithDrums.wav"));
		
		//Generates a new name for each test file... complete waste of time why did i do this again? i dont even use it...
		File test_projectFilesFolder = new File("./testFiles/projectFiles");
		String[] test_projectFileNames = test_projectFilesFolder.list();
		String testFileNameRE = ".*?(\\d+)\\.txt";
		Pattern testFileNamePattern = Pattern.compile(testFileNameRE);
		int latestTest = 0;
		for (String file : test_projectFileNames) {
			//System.out.print(file);
			Matcher testFileMatcher = testFileNamePattern.matcher(file);
			if (testFileMatcher.find()) {
				//System.out.print(" = test "+testFileMatcher.group(1));
				if (Integer.parseInt(testFileMatcher.group(1)) > latestTest) {
					latestTest = Integer.parseInt(testFileMatcher.group(1));
				}
			}
			//System.out.println();
		}
		latestTest++;
		String testName = "test"+latestTest;
		//System.out.println(testName+".txt");
		
		
		ProjectManager projectManager = new ProjectManager();
		//projectManager.saveProject(soundFiles, testName);
		projectManager.saveProject(soundFiles, "test1");
		
		System.out.println("Test Complete");
	}

	public void openProjectFileTest() {
		
		File testFile = new File("C:\\Users\\bacca\\git\\MarshmallowSongGenerator\\marshmelloSongGenerator\\testFiles\\projectFiles\\test1.txt");
		
		
		ProjectManager projectManager = new ProjectManager();
		ArrayList<File> results = projectManager.openProjectFile(testFile);
		
		for (File file : results) {
			System.out.println(file.getName() + " | Exists = "+file.exists());
		}
		
		System.out.println("Test Complete");
	}
	
}
