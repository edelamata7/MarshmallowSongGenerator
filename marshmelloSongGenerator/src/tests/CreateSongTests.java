package tests;

import java.io.File;
import java.util.ArrayList;

import marshmelloSongGenerator2.*;
import wavFile.*;

public class CreateSongTests {

	public static void main(String[] args) {
		CreateSongTests testDriver = new CreateSongTests();
		testDriver.testCompileSoundFiles();
		
	}
	
	public void testCompileSoundFiles() {
		ArrayList<File> soundFiles = new ArrayList<File>();
		
		//Get files from the testFiles folder:
		soundFiles.add(new File("./testFiles/testFile1_TurningObjectsIntoPercussion.wav"));
		soundFiles.add(new File("./testFiles/testFile2_BassLoopsWithDrums.wav"));
		soundFiles.add(new File("./testFiles/testFile3_BassLoopsNoDrums.wav"));
		soundFiles.add(new File("./testFiles/testFile4_DiscoFunkLoopsNoDrums.wav"));
		soundFiles.add(new File("./testFiles/testFile5_DiscoFunkLoopsWithDrums.wav"));
		
		//Create a CreateSong object to test with
		CreateSong createSongTest = new SongGenerator(); //CreateSong is abstract so I have to use one of its child classes
		ArrayList<WavFile> wavFileList = createSongTest.compileSoundFiles(soundFiles);
		
		for (int i=0; i<wavFileList.size(); i++) {
			wavFileList.get(i).display();
			System.out.println();
		}
		
	}

}
