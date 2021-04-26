package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import marshmelloSongGenerator2.*;
import wavFile.*;

public class CreateSongTests {

	public static void main(String[] args) {
		CreateSongTests testDriver = new CreateSongTests();
		//testDriver.testCompileSoundFiles();
		testDriver.testCreateCompleteWavFile();
	}
	
	public void testCreateCompleteWavFile() {
		ArrayList<File> soundFiles = new ArrayList<File>();
		ArrayList<WavFile> wavFiles = new ArrayList<WavFile>();
		
		try {
			wavFiles.add(WavFile.openWavFile(new File("./testFiles/testFile1_TurningObjectsIntoPercussion.wav")));
			wavFiles.add(WavFile.openWavFile(new File("./testFiles/testFile2_BassLoopsWithDrums.wav")));
			wavFiles.add(WavFile.openWavFile(new File("./testFiles/testFile3_BassLoopsNoDrums.wav")));
			wavFiles.add(WavFile.openWavFile(new File("./testFiles/testFile4_DiscoFunkLoopsNoDrums.wav")));
			wavFiles.add(WavFile.openWavFile(new File("./testFiles/testFile5_DiscoFunkLoopsWithDrums.wav")));
			
		} catch (IOException | WavFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProjectList test1 = new ProjectList(wavFiles, soundFiles);
		
		CreateSong createSongTest = new SongGenerator();
		try {
			createSongTest.createProject(test1, "RESULT_completeWavFileTest");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TEST COMPLETE");
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
