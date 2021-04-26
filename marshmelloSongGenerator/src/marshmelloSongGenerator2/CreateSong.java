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
		projectManager = new ProjectManager();
		assert(this.projectManager != null) : "ProjectManager cannot be null"; //Ensure that it creates a project manager
	}
	
	/**
	 * Calls the methods in the createSong class to create a sound file and a project file using the information in the given projectList
	 * @param projectList - contains the list of files/wavFiles that are needed to create the sound and project files
	 * @param filename - what to save the new project as
	 */
	public void createProject(ProjectList projectList, String filename) throws Exception {
		createCompleteWavFile(projectList.getWavFiles(), filename);
		createProjectFile(projectList.getFiles(), filename);
	}
	
	/**
	 * Combines all the wavFiles in the given list into one single wavFile
	 * @param wavFileList - a list of the wavFiles to add to the new project
	 * @param filename - what to save the new wavFile as
	 */
	private void createCompleteWavFile(ArrayList<WavFile> wavFileList, String filename) throws Exception {
		
		//For a test later
		int durationTest = 0;
		for (int i=0; i < wavFileList.size(); i++) {
			durationTest += (wavFileList.get(i).getNumFrames()/wavFileList.get(i).getSampleRate());
		}
		
		//Every given file needs to have the same sample rate; if they dont the file cant compile correctly
		if (wavFileList.get(0).getSampleRate() == wavFileList.get(1).getSampleRate()) {
			for (int i=1; i < wavFileList.size(); i++) {
				if (wavFileList.get(i).getSampleRate() != wavFileList.get(i-1).getSampleRate()) {
					//throw new Exception();
				}
			}
		}
		
		File newFile = new File(projectManager.getStorageLocation()+"\\projectFiles\\"+filename+".wav");//created separately from the newWavFile to allow testing it. This technically isnt necessary
		assert (newFile.getName().equals(filename+".wav")) : "Error: the new wavFile doesn't have the correct filename"; //check that the new wavfile has the correct name
		
		
		try {
			/*
			 * Note: Have to ensure each given file:
			 * 	Has the same number of channels? (easy to handle
			 * 	has the same number of validBits? (doesnt matter)
			 * 	has the same number sampleRate?
			 * Or does this not matter? Can some be different but other have to be the same? Need to research/test further
			 */
			
			
			//Set the parameters for the new wav file
			int numChannels = 2;
			long numFrames = wavFileList.get(0).getNumFrames();
			for (int i=1; i<wavFileList.size(); i++) { //Sets the # of frames in the new file by added in the #frames of all given files
				numFrames += wavFileList.get(i).getNumFrames();
			}
			int validBits = 24; //16 is the most common bitDepth for wav files
			long sampleRate = 44100; //Most common sample rate
			
			//Create the new wav file
			WavFile completeWavFile = WavFile.newWavFile(newFile, numChannels, numFrames, validBits, sampleRate);
			
			//Buffer to store a block of 100 frames from the current file in the wavFileList to be added to the completeWavFile
			double[][] additiveBuffer = new double[2][100];
			//Buffer to store the block of 100 frames to write to the new wav file
			double[][] newBuffer = new double[2][100];
			
			//frameCounter ensures the number of frames written doesn't go over the maximum number of frames in the completeWavFile
			long frameCounter = 0;
			
			//Reads the frames from the current wav file and stores them in the additiveBuffer
			//the readFrames() method returns an int of how many frames were stored in the additiveBuffer
			//it returns 0 if there are no more frames to read
			int framesRead = wavFileList.get(0).readFrames(additiveBuffer, 100);
			
			
			while (frameCounter < completeWavFile.getNumFrames()) {
				//Get the number of frames left to write in the completeWavFile
				long remaining = completeWavFile.getFramesRemaining();
				//Calculate the number of frames to write this cycle
				int toWrite = (remaining > 100) ? 100 : (int)remaining;
				
				for (int i = 0; i < toWrite; i++, frameCounter++) {
					if (wavFileList.get(0).getNumChannels() == 1) {
						newBuffer[0][i] = additiveBuffer[0][i];
						newBuffer[1][i] = additiveBuffer[0][i];
					} else {
						newBuffer[0][i] = additiveBuffer[0][i];
						newBuffer[1][i] = additiveBuffer[1][i];
					}
				}
				
				//Add the values of the frames in the new buffer to the frames stored in the completeWavFile
				completeWavFile.writeFrames(newBuffer, 100);
				
				//Read the next block of 100 frames from the current wav file
				framesRead = wavFileList.get(0).readFrames(additiveBuffer, 100);
				
				if (framesRead == 0) {
					wavFileList.get(0).close(); //Closes open file handlers?
					//remove the first file in the wavFileList; shifting everything in the list by 1
					//the next file that needs to be read will now be at index 0
					wavFileList.remove(0);
					if (wavFileList.size() != 0) {
						framesRead = wavFileList.get(0).readFrames(additiveBuffer, 100);
					}
				}
			}
			
			
			assert (durationTest == completeWavFile.getNumFrames()/completeWavFile.getSampleRate()) : "Error: The duration of the new wavFile doesn't match the duration of the combined given soundFiles"; //Check if the duration of the complete wav file equals the duration of all the given wavFiles
			
			completeWavFile.close();
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
