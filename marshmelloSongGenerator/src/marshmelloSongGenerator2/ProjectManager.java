package marshmelloSongGenerator2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class ProjectManager extends StorageManager {
	
	/**
	 * Temporary solution to setting the storageLocation string while changing as little of storageManager as possible
	 */
	public ProjectManager() {
		storageLocation = ".\\testFiles";
	}
	
	/**
	 * Converts a given ArrayList<File> of soundFiles used to create a finished song project into a saveable text file.
	 * Will create a text file named after the given projectName and save the contents of the ArrayList in the file.
	 * @param projectData - The given ArrayList of sound files used in a finished song project
	 * @param projectName - The name of the project to be saved
 	 */
	public void saveProject(ArrayList<File> projectData, String projectName) {
		//Create the file object that's used to create the new text file
		File project = new File(storageLocation+"\\projectFiles\\"+projectName+".txt");
		
		try {
			//Creates the text file
			project.createNewFile();
			FileWriter fileWriter = new FileWriter(project, false);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write("MARSHMELLOW SONG GENERATOR PROJECT");
			writer.newLine();
			
			for (File file : projectData) {
				writer.write(file.getName());
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Checks that a file can be found in the project files folder that matches the name of the newly created file
		assert (new File(storageLocation+"\\projectFiles\\"+projectName+".txt").exists()) : "Error: No file with the given filename exists in the projectFiles folder";
		
		try {
			Scanner reader = new Scanner(project);
			String data;
			if (reader.hasNextLine()) {
				data = reader.nextLine();
				assert (data.matches("MARSHMELLOW SONG GENERATOR PROJECT")) : "Error: Project file indicator wasnt found"; //Checks that the indicator is included in the file
				for(int i=0; i < projectData.size() && reader.hasNextLine(); i++) {
					data = reader.nextLine();
					assert(data.matches(projectData.get(i).getName())) : "Error: projectFile contents don't match the given arrayList"; //Checks that each line of the project text file matches the files in the projectData arrayList
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Opens the given project file and reads it contents.
	 * Uses the list in the project file to create an arrayList of all the sound files listed in the project file, in the order they were listed.
	 * @param project
	 * @return Returns an ArrayList of type File
	 */
	public ArrayList<File> openProjectFile(File project) { //Add throws FileNotFoundException?
		
		assert (checkFileType(project, ".txt")) : "Error: Given file isnt a text file"; //Check that the given file is a text file
		
		
		
		
		
		
		
		
		Scanner projectReader;
		try {
			projectReader = new Scanner(project);
			String data = projectReader.nextLine(); //Read first line (the indicator line)
			assert (data == "MARSHMELLOW SONG GENERATOR PROJECT") : "Error: Project file indicator wasnt found"; //Checks that the indicator is included in the file to ensure the given text file is a project file
			
			while (projectReader.hasNextLine()) {
				data = projectReader.nextLine(); //Read each line after the indicator line
				assert (getSoundFile(data) != null) : "Error: File recorded in project file cant be found in the sound files folder"; //Checks that each recorded soundfile in the project text file is actually in the sound files folder
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * Tests:
		 * Test that the files listed/read from the project file have a combined duration equal to the corresponding project's wavFile's duration? TBD
		 */
		
		return null;
	}

}
