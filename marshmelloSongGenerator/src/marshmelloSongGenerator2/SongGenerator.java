package marshmelloSongGenerator2;

import java.io.File;
import java.util.ArrayList;
import wavFile.WavFile;

public class SongGenerator extends CreateSong {
	
	/**
	 * Calls the methods in the SongGenerator class to create a new song
	 */
	public void generateSong() {
		ProjectList newProject = createProjectList(projectManager.getSoundFiles());
		String projectName = createName();
		createProject(newProject, projectName);
	}
	
	/**
	 * Takes an arrayList of all the available sound files and randomly selects files to create a new song
	 * @param
	 * @return Returns a project list object that can be used by the createSong class to generate a finished wavFile and project file
	 */
	@Override
	protected ProjectList createProjectList(ArrayList<File> soundFiles) {
		
		/*
		 * Tests:
		 * Check if the duration of the final wavFile list is greater than the minimum song length
		 */
		
		return null;
	}
	
	/**
	 * Creates a unique name for the new project
	 * @return A string of a unique name
	 */
	private String createName() {
		/*
		 * Tests:
		 * Check that the created name doesn't equal any of the names currently in the projectFiles folder
		 */
		return null;
	}

}
