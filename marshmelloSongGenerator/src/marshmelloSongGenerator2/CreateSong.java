package marshmelloSongGenerator2;

import java.io.File;
import java.util.ArrayList;

import wavFile.WavFile;

public abstract class CreateSong {
	
	/**
	 * Allows this class to access files in storage and save/open project files
	 */
	protected ProjectManager projectManager;
	
	public CreateSong() {
		projectManager = new ProjectManager();
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
		
	}
	
	/**
	 * Pass the given values into the projectManager to create/save a project file
	 * @param fileList
	 * @param filename
	 */
	private void createProjectFile(ArrayList<File> fileList, String filename) {
		
	}
	
	/**
	 * Takes an arrayList of File objects to convert into an arrayList of wavFile objects
	 * @param soundFiles - The list of file objects to turn into an array of wavFile objects
	 * @return Returns an arrayList of wavFile objects created using the file objects in the given soundFiles array
	 */
	public ArrayList<WavFile> compileSoundFiles(ArrayList<File> soundFiles) { //Should throw an exception if the given file type cannot be converted in to a wavFile? need to look into that further
		return null;
	}
	
	/**
	 * Takes a given arrayList of file objects and converts it into a projectList
	 * @param soundFiles
	 * @return
	 */
	protected abstract ProjectList createProjectList(ArrayList<File> soundFiles);

}
