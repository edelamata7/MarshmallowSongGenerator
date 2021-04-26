package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import marshmelloSongGenerator2.*;

public class StorageManagerTest {

	public static void main(String[] args) throws FileNotFoundException {
		
//		System.out.println(System.getProperty("user.home"));
//		
//		File tmp = new File(System.getProperty("user.home")+"\\AppData\\Roaming");
//		File tmp2 = new File(System.getProperty("user.home")+"\\AppData\\Roaming\\MarshmelloSongGenerator");
//		
//		System.out.println(tmp2.exists());
//		tmp2.mkdir();
//		System.out.println(tmp2.exists());
		
		//new StorageManagerTest().testGetSoundFiles();
		//System.out.println();
		//new StorageManagerTest().testGetProjectFiles();
		//System.out.println();
		new StorageManagerTest().testGetSoundFile();
		System.out.println();
		new StorageManagerTest().testGetProjectFile();
		
		//C:\Users\bacca\AppData\Roaming
	}
	
	
	public void testGetSoundFiles() {
		StorageManager testDriver = new StorageManager();
		
		ArrayList<File> soundFiles = testDriver.getSoundFiles();
		
		for (File item : soundFiles) {
			System.out.println(item.getName());
			System.out.println(item.exists());
		}
	}
	
	public void testGetProjectFiles() {
		StorageManager testDriver = new StorageManager();
		
		ArrayList<File> projectFiles = testDriver.getProjectFiles();
		
		for (File item : projectFiles) {
			System.out.println(item.getName());
			System.out.println(item.exists());
		}
	}
	
	public void testGetSoundFile() {
		StorageManager testDriver = new StorageManager();
		
		File testFile = testDriver.getSoundFile("testFile1_TurningObjectsIntoPercussion.wav");
		System.out.println(testFile.getName());
		System.out.println(testFile.exists());
		
		testFile = testDriver.getSoundFile("testFile3_BassLoopsNoDrums.wav");
		System.out.println(testFile.getName());
		System.out.println(testFile.exists());
		
		
		
	}
	
	public void testGetProjectFile() {
		StorageManager testDriver = new StorageManager();
		
		File testFile = testDriver.getProjectFile("test1.txt");
		System.out.println(testFile.getName());
		System.out.println(testFile.exists());
		
		testFile = testDriver.getProjectFile("test2.txt");
		System.out.println(testFile.getName());
		System.out.println(testFile.exists());
	}
	
	
	

}
