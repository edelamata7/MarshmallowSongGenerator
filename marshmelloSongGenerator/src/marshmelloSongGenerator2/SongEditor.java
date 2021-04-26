package marshmelloSongGenerator2;

import java.io.File;
import java.util.ArrayList;

import wavFile.WavFile;

public class SongEditor extends CreateSong {
	
	/**
	 * An arrayList of all the sound files currently in the project
	 */
	private ArrayList<File> projectSoundFiles;
	
	/**
	 * An arrayList of all the files that can be added to the project
	 */
	private ArrayList<File> availableSoundFiles;
	
	/**
	 * Name of the project being edited
	 */
	private String projectName;
	
	/**
	 * Needs to set the list of projectSoundFiles and the list of availableSoundFiles
	 */
	public SongEditor(ArrayList<File> projectFiles, String projectName) {
		this.projectName = projectName;
		availableSoundFiles = projectManager.getSoundFiles();
		projectSoundFiles = projectFiles;
		
		
		assert (new File(projectManager.getStorageLocation()+"\\projectFiles\\"+projectName+".txt").exists()) : "Error: No file with the given filename exists in the projectFiles folder"; //Checks that a file can be found in the project files folder that matches the name of the file being edited
		for(int i=0; i<availableSoundFiles.size(); i++) {
			assert(availableSoundFiles.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every availableSoundFile actually exists
			assert(projectManager.checkFileType(availableSoundFiles.get(i), ".wav")) : "Error: One or more of the given files isnt of the .wav filetype extension"; //Checks that every availableSoundFile is a wav file
		}
		for(int i=0; i<projectSoundFiles.size(); i++) {
			assert(projectSoundFiles.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every projectSoundFile actually exists
			assert(projectManager.checkFileType(projectSoundFiles.get(i), ".wav")) : "Error: One or more of the given files isnt of the .wav filetype extension"; //Checks that every projectSoundFile is a wav file
		}
		assert(this.projectName != null) : "projectName cannot be null"; //ensure the project has a name
		assert(this.projectSoundFiles != null) : "projectSoundFiles cannot be null"; //ensure there are some project files to edit
		assert(this.availableSoundFiles != null) : "availableSoundFiles cannot be null"; //ensure there are some available sound files
	}
	
	/**
	 * Creates a new ProjectList using the createProjectList method and passes it and the projects filename to the CreateSong class to update the wavFile and project file
	 */
	public void finishProject() throws Exception {
		createProject(createProjectList(projectSoundFiles), projectName);
	}

	/**
	 * Creates an arraylist of wavFiles from the given file arraylist
	 * Creates a new ProjectList object from the wavFile and File arrayLists
	 * @param soundFiles
	 * @return Returns a projectList object
	 */
	@Override
	protected ProjectList createProjectList(ArrayList<File> soundFiles) {
		ArrayList<WavFile> wavFiles = compileSoundFiles(soundFiles);
		return new ProjectList(wavFiles, soundFiles);
	}
	
	/**
	 * Removes a specified file from the projectSoundFiles arrayList
	 * @param name - name of the file to remove from the projectSoundFiles list
	 */
	public void deleteFile(int location) {
		/*
		 * test:
		 * ensure that the deleted file is removed from the projectSoundFiles arrayList (how if file can appear multiple times? using specific test case?)
		 */
		
		int projectSoundFiles_initialSize = projectSoundFiles.size();
		
		projectSoundFiles.remove(location);
		
		assert(projectSoundFiles.size() == projectSoundFiles_initialSize-1) : "Error: projectSoundFiles size should have decreased by 1"; //Check that the size of project sound files has decreased by 1 now that a file has been removed from it
		
	}
	
	/**
	 * Adds a specified new file to the projectSoundFiles list after the specified locationFile
	 * @param newFile - the file from the availableSoundFiles list to add to the projectSoundFiles list
	 * @param locationFile - file in the projectSoundFiles list where the new file should be added
	 */
	public void addFile(int newFile, int locationFile) { //locationFile may become an int based on future implementation
		
		/*
		 * test:
		 * test that the size of the projectSoundFiles list has increased by 1
		 * test that the new file appears in the projectSoundFiles list (at the location or just in general? TBD)
		 */
		
		int projectSoundFiles_initialSize = projectSoundFiles.size();
		
		projectSoundFiles.add(locationFile, availableSoundFiles.get(newFile));
		
		assert(projectSoundFiles.size() == projectSoundFiles_initialSize+1) : "Error: projectSoundFiles size should have increased by 1"; //Check that the size of project sound files has increased by 1 now that a file has been added to it
	}
	
	/**
	 * replaces the given file in the projetSoundFiles list with the given new file
	 * @param newFile - file to replace the oldFile
	 * @param oldFile - file in the projectSoundFiles list to be replaced
	 */
	public void modify(int newFile, int oldFile) { //oldFile may become an int based on future implementation
		int projectSoundFiles_initialSize = projectSoundFiles.size();
		String oldFile_name = projectSoundFiles.get(oldFile).getName();
		
		projectSoundFiles.set(oldFile, availableSoundFiles.get(newFile));
		
		assert(projectSoundFiles.size() == projectSoundFiles_initialSize) : "Error: projectSoundFiles should have remained the same size"; //Check that the size of projectSoundFiles remains the same
		assert(projectSoundFiles.get(oldFile).getName() != oldFile_name) : "Error: the old file in projectSoundFiles should not equal the newFile"; //Checks that the oldFile has actually changed and isnt the same file
	}
	
}
