package tests;

import java.io.FileNotFoundException;

import marshmelloSongGenerator2.*;

public class StorageManagerTest {

	public static void main(String[] args) throws FileNotFoundException {
		
		StorageManager sm = new StorageManager();
				
		sm.getSoundFile("Music.wav");
		sm.getSoundFile("Music.exe");
		sm.getSoundFile("Music.txt");
		
		sm.getSoundFiles();
				
		sm.getProjectFile("File.wav");
		sm.getProjectFile("File.exe");
		sm.getProjectFile("File.txt");
		
		sm.getProjectFiles();

		sm.checkFileType(null, ".wav");
		sm.checkFileType(null, ".exe");
		sm.checkFileType(null, ".txt");
		sm.checkFileType(null, null);
		
		sm.getStorageLocation();
	}

}
