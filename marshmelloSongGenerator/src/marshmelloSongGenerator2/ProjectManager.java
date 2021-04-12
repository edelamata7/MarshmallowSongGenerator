package marshmelloSongGenerator2;

import java.io.File;
import java.util.ArrayList;

public class ProjectManager extends StorageManager {
	
	/**
	 * Converts a given ArrayList<File> of soundFiles used to create a finished song project into a savable text file.
	 * Will create a text file named after the given projectName and save the contents of the ArrayList in the file.
	 * @param projectData - The given ArrayList of sound files used in a finished song project
	 * @param projectName - The name of the project to be saved
 	 */
	public void saveProject(ArrayList<File> projectData, String projectName) {
		
		/*
		 * Tests:
		 * test that it creates a new, blank text file
		 * test that the finished text file has the appropriate content in it?
		 */
	}
	
	/**
	 * Opens the given project file and reads it contents.
	 * Uses the list in the project file to create an arrayList of all the sound files listed in the project file, in the order they were listed.
	 * @param project
	 * @return Returns an ArrayList of type File
	 */
	public ArrayList<File> openProjectFile(File project) {
		
		/*
		 * Tests:
		 * Test that the given file is a text file (need to handle if it isnt?)
		 * Test that the given text file is actually a project file (check for/have a specific marker in the file? IE: "MARSHMELLOW PROJECT" at the top of the file?)
		 * Test that the files read from the project file correspond to a sound file (handle if sound files have been removed?)
		 * Test that the files listed/read from the project file have a combined duration equal to the corresponding project's wavFile's duration?
		 */
		
		return null;
	}

}
