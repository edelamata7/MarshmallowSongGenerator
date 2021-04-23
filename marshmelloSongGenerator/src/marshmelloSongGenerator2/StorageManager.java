package marshmelloSongGenerator2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.*;

import wavFile.WavFile;

public class StorageManager {
	
	/**
	 * This stores the filepath to where the program's files are stored
	 */
	protected String storageLocation;
	
	/**
	 * Tries to locate the program's storage folders.
	 * If one or more of the storage folders are missing then the program should inform the user and create new ones.
	 * Once the storage folders have been located/created, stores the filepath in storageLocation.
	 */
	public StorageManager() {
		
		/*
		 * Tests:
		 * Testcase for when there is no storage location: a new one is made appropriately
		 * Testcase for when there is a storage location: it is found appropriately
		 * Tests the the master storage folder has a soundFiles and projectFiles folder in it: if 1 or more is missing create the missing folders
		 */
		
		File masterFolder = new File(storageLocation); //Create a new file object using the storageLocation path
		
		assert (storageLocation != null) : "Error: StorageLocation cannot be null"; //Checks that it actually finds/creates a storage location
		assert(masterFolder.exists()) : "Error: master storage folder doesn't exist"; //Checks that the storageLocation path actually loads a file directory/folder
		assert(masterFolder.isDirectory()) : "Error: master storage folder isn't a file directory"; //Checks that the storageLocation path actually loads a file directory/folder
		String[] masterFolderItems = masterFolder.list();
		for (int i=0; i<masterFolderItems.length; i++) {
			assert(new File(storageLocation+"\\"+masterFolderItems[i]).isDirectory()) : "Error: master folder should only have subfolders in it"; //Check each item in the master folder is a file directory/folder
		}
		assert(masterFolderItems.length == 2) : "Error: master storage folder is missing one or more subfolders"; //Checks that the master folder has at least 2 subfolders
	}
	
	/**
	 * @return Returns a File array of all the sound files in the sound files folder. Should return null if there are no sound files.
	 */
	public ArrayList<File> getSoundFiles() {
		
		ArrayList<File> soundFiles = new ArrayList<File>();
				
		
		for(int i=0; i<soundFiles.size(); i++) {
			soundFiles.add(null);
			assert(soundFiles.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every retrieved file actually exists
			assert(checkFileType(soundFiles.get(i), ".wav")) : "Error: One or more of the selected files isnt of the .wav filetype extension"; //Checks that every retrieved file is a wav file
		}
		return soundFiles;
	}
	
	/**
	 * Returns a file object with the sound file whose name matches the given name.
	 * Throws a FileNotFoundException if the desired file cannot be found in the storage folder.
	 * @param name - The name of the desired sound file
	 * @return A File object of the desired file
	 */
	public File getSoundFile(String name) throws FileNotFoundException { //Note: This exception might need to change in the future, I'm not sure if its the best one to use here
		
		File soundFile = new File("");
		
		
		
		/*
		 * Tests:
		 * testcase for searching for a specific file that does exist: file is found correctly
		 * testcase for searching for a file that doesnt exist: throws an error
		 */
		
		assert(soundFile.getName() == name) : "Error: Retrieved file doesnt match the requested file"; //Checks that the retrived file actually matches the requested file
		return soundFile;
	}
	
	/**
	 * 
	 * @return Returns a File array of all the project files in the project files folder. Should return null if there are no sound files.
	 */
	public ArrayList<File> getProjectFiles() {
		
		ArrayList<File> projectFiles = new ArrayList<File>();
		
		
		
		
		
		
		for(int i=0; i<projectFiles.size(); i++) {
			assert(projectFiles.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every retrieved file actually exists
			assert(checkFileType(projectFiles.get(i), ".txt")) : "Error: One or more of the selected files isnt of the .txt filetype extension"; //Checks that every retrieved file is a txt file
		}
		return projectFiles;
	}
	
	/**
	 * Returns a file object with the project file that matches the given name
	 * Throws a FileNotFound exception if the desired file cannot be found in the storage folder
	 * @param name - The name of the desired project file
	 * @return A File object of the desired file
	 */
	public File getProjectFile(String name) throws FileNotFoundException { //Note: This exception might need to change in the future, I'm not sure if its the best one to use here
		
		File projectFile = new File("");
		
		/*
		 * Tests:
		 * testcase for searching for a specific file that does exist: file is found correctly
		 * testcase for searching for a file that doesnt exist: throws an error
		 */
		
		
		assert(projectFile.getName() == name) : "Error: Retrieved file doesnt match the requested file"; //Checks that the retrived file actually matches the requested file
		return projectFile;
	}
	
	/**
	 * Checks the filetype of the given file. Returns true if the file is correct and false if it isn't
	 * @param file - File to check the filetype of
	 * @param fileExtension - The extension the given file should be (should be given with the . included: EX .wav, .txt)
	 * @return
	 */
	public boolean checkFileType(File file, String fileExtension) {
		assert(fileExtension.matches("\\..*")) : "Error: The value of fileExtension isnt a valid fileExtension"; //Checks that the given file extension is in the correct format (This RE may need to be changed in the future)
		String filetypeRE = ".*(\\."+fileExtension+")$";
		Pattern filetypePattern = Pattern.compile(filetypeRE);
		Matcher filetypeMatcher = filetypePattern.matcher(file.getName());
		if (filetypeMatcher.find()) {
			assert(filetypeMatcher.group() == file.getName()) : "Error: matched file doesn't equal the full file name"; //Checks the regex worked properly: it should match to then entire filename
			return true;
		} else {
			return false;
		}
	}
	
	public String getStorageLocation() {
		return storageLocation;
	}

}
