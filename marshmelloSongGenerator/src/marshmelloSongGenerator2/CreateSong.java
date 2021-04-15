package marshmelloSongGenerator2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wavFile.WavFile;
import wavFile.WavFileException;

public abstract class CreateSong {
	
	/**
	 * Allows this class to access files in storage and save/open project files
	 */
	protected ProjectManager projectManager;
	
	public CreateSong() {
		try {
			projectManager = new ProjectManager();
			assert(this.projectManager != null) : "ProjectManager cannot be null"; //Ensure that it creates a project manager
		} catch (Exception e) {
			
		}
		
	}
	
	/**
	 * Calls the methods in the createSong class to create a sound file and a project file using the information in the given projectList
	 * @param projectList - contains the list of files/wavFiles that are needed to create the sound and project files
	 * @param filename - what to save the new project as
	 */
	public void createProject(ProjectList projectList, String filename) {
		
	}
	
	/**
	 * Combines all the wavFiles in the given list into one single wavFile
	 * @param wavFileList - a list of the wavFiles to add to the new project
	 * @param filename - what to save the new wavFile as
	 */
	private void createCompleteWavFile(ArrayList<WavFile> wavFileList, String filename) {
		
		/*
		 * Tests:
		 * Check if the buffer loads each wavfile correctly?
		 * Check that it switches to the next wavFile in the correct order?
		 * 
		 */
		
		
		File newFile = new File("filepath/"+filename+".wav"); //created separately from the newWavFile to allow testing it. This technically isnt necessary
		assert (newFile.getName() == filename) : "Error: the new wavFile doesn't have the correct filename"; //check that the new wavfile has the correct name
		
		try {
			//newWavFile(new File("filepath/filename.wav"), numChannels, numFrames, validBits, sampleRate);
			/*
			 * Note: Have to ensure each given file:
			 * 	Has the same number of channels?
			 * 	has the same number of validBits?
			 * 	has the same number sampleRate?
			 * Or does this not matter? Can some be different but other have to be the same? Need to research/test further
			 */
			WavFile completeWavFile = WavFile.newWavFile(newFile, 0, 0, 0, 0); //
			
			
			int durationTest = 0;
			for (int i=0; i < wavFileList.size(); i++) {
				durationTest += (wavFileList.get(i).getNumFrames()/wavFileList.get(i).getSampleRate());
			}
			assert (durationTest == completeWavFile.getNumFrames()/completeWavFile.getSampleRate()) : "Error: The duration of the new wavFile doesn't match the duration of the combined given soundFiles"; //Check if the duration of the complete wav file equals the duration of all the given wavFiles
			
		} catch (IOException | WavFileException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Pass the given values into the projectManager to create/save a project file
	 * @param fileList
	 * @param filename
	 */
	private void createProjectFile(ArrayList<File> fileList, String filename) {
		
		for(int i=0; i<fileList.size(); i++) {
			assert(fileList.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every given file actually exists
			assert(projectManager.checkFileType(fileList.get(i), ".wav")) : "Error: One or more of the given files isnt of the .wav filetype extension"; //Checks that every given file is a wav file
		}
		
		projectManager.saveProject(fileList, filename);
	}
	
	/**
	 * Takes an arrayList of File objects to convert into an arrayList of wavFile objects
	 * @param soundFiles - The list of file objects to turn into an array of wavFile objects
	 * @return Returns an arrayList of wavFile objects created using the file objects in the given soundFiles array
	 */
	public ArrayList<WavFile> compileSoundFiles(ArrayList<File> soundFiles) { //Should throw an exception if the given file type cannot be converted in to a wavFile? need to look into that further
		for(int i=0; i<soundFiles.size(); i++) {
			assert(soundFiles.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every given file actually exists
			assert(projectManager.checkFileType(soundFiles.get(i), ".wav")) : "Error: One or more of the given files isnt of the .wav filetype extension"; //Checks that every given file is a wav file
		}
		
		ArrayList<WavFile> wavFileList = new ArrayList<WavFile>();
		
		for (int i = 0; i < soundFiles.size(); i++) {
			WavFile newWavFile;
			
			try {
				newWavFile = WavFile.openWavFile(soundFiles.get(i));
				wavFileList.add(newWavFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		assert (soundFiles.size() == wavFileList.size()) : "Error: Length of the wavFile list doesnt equal the length of the given sound files list"; //Checks that the length of the new list of wav files is the same length as the given list of file objects
		
		return wavFileList;
	}
	
	/**
	 * Takes a given arrayList of file objects and converts it into a projectList
	 * @param soundFiles
	 * @return
	 */
	protected abstract ProjectList createProjectList(ArrayList<File> soundFiles);

}
