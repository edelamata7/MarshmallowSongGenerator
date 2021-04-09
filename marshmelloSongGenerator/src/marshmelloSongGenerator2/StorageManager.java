package marshmelloSongGenerator2;

import java.io.File;
import java.io.FileNotFoundException;

import wavFile.WavFile;

public class StorageManager {
	
	/**
	 * This stores the filepath to where the program's files are stored
	 */
	private String storageLocation;
	
	/**
	 * Tries to locate the program's storage folders.
	 * If one or more of the storage folders are missing then the program should inform the user and create new ones.
	 * Once the storage folders have been located/created, stores the filepath in storageLocation.
	 */
	public StorageManager() {
		
	}
	
	/**
	 * @return Returns a File array of all the sound files in the sound files folder. Should return null if there are no sound files.
	 */
	public File[] getSoundFiles() { 
		
		return null;
	}
	
	/**
	 * Returns a file object with the sound file whose name matches the given name.
	 * Throws a FileNotFoundException if the desired file cannot be found in the storage folder.
	 * @param name - The name of the desired sound file
	 * @return A File object of the desired file
	 */
	public File getSoundFile(String name) throws FileNotFoundException { //Note: This exception might need to change in the future, I'm not sure if its the best one to use here
		
		return null;
	}
	
	/**
	 * 
	 * @return Returns a File array of all the project files in the project files folder. Should return null if there are no sound files.
	 */
	public File[] getProjectFiles() {
		
		return null;
	}
	
	/**
	 * Returns a file object with the project file that matches the given name
	 * Throws a FileNotFound exception if the desired file cannot be found in the storage folder
	 * @param name - The name of the desired project file
	 * @return A File object of the desired file
	 */
	public File getProjectFile(String name) throws FileNotFoundException { //Note: This exception might need to change in the future, I'm not sure if its the best one to use here
		
		return null;
	}

}
