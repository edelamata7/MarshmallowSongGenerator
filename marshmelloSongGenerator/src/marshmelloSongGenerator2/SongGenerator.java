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
		for(int i=0; i<soundFiles.size(); i++) {
			assert(soundFiles.get(i).exists()) : "Error: One or more of the given files doesn't exist"; //Checks that every given file actually exists
			assert(projectManager.checkFileType(soundFiles.get(i), ".wav")) : "Error: One or more of the given files isnt of the .wav filetype extension"; //Checks that every given file is a wav file
		}
		
		
		
		
		ArrayList<WavFile> wavFileList = new ArrayList<WavFile>();
		int minimumLength = 0;
		
		
		
		
		int durationTest = 0;
		for (int i=0; i < wavFileList.size(); i++) {
			durationTest += (wavFileList.get(i).getNumFrames()/wavFileList.get(i).getSampleRate());
		}
		assert (durationTest >= minimumLength) : "Error: The duration of the generated list of wavFiles is less than the allowed minimum duration of a complete song"; //Check if the duration of the generated list of wavFiles combined's duration is greater than the minimum duration
		
		return null;
	}
	
	/**
	 * Creates a unique name for the new project
	 * @return A string of a unique name
	 */
	private String createName() {
		String newName = "";
		ArrayList<File> projectFiles = projectManager.getProjectFiles();
		
		
		for(int i=0; i < projectFiles.size(); i++) {
			assert(newName != projectFiles.get(i).getName()) : "Error: Newly generated name is already used in the projectFiles folder"; //Checks to make sure the new name isnt already in the projectFiles folder
		}
		
		
		return null;
	}

}
