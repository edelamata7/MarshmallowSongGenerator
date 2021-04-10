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
	 * Needs to set the list of projectSoundFiles and the list of availableSoundFiles
	 */
	public SongEditor() {
		
	}
	
	/**
	 * Creates a new ProjectList using the createProjectList method and passes it and the projects filename to the CreateSong class to update the wavFile and project file
	 */
	public void finishProject() {
		
	}

	/**
	 * Creates an arraylist of wavFiles from the given file arraylist
	 * Creates a new ProjectList object from the wavFile and File arrayLists
	 * @param soundFiles
	 * @return Returns a projectList object
	 */
	@Override
	protected ProjectList createProjectList(ArrayList<File> soundFiles) {
		return null;
	}
	
	/**
	 * Removes a specified file from the projectSoundFiles arrayList
	 * @param name - name of the file to remove from the projectSoundFiles list
	 */
	public void deleteFile(String name) {
		
	}
	
	/**
	 * Adds a specified new file to the projectSoundFiles list after the specified locationFile
	 * @param newFile - the file from the availableSoundFiles list to add to the projectSoundFiles list
	 * @param locationFile - file in the projectSoundFiles list where the new file should be added
	 */
	public void addFile(String newFile, String locationFile) {
		
	}
	
	/**
	 * replaces the given file in the projetSoundFiles list with the given new file
	 * @param newFile - file to replace the oldFile
	 * @param oldFile - file in the projectSoundFiles list to be replaced
	 */
	public void modify(String newFile, String oldFile) {
		
	}
	
}
