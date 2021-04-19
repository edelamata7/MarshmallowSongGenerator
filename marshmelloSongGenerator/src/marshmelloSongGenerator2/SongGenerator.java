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
		//String projectName = createName();
		//createProject(newProject, projectName);
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
		
		
		
		//Create the list of wavFiles; needed to compute duration of the song
		ArrayList<WavFile> wavFiles = new ArrayList<WavFile>();//compileSoundFiles(soundFiles);
		
		//NOTE: DONT FORGET TO REMOVE THIS!!!!!!!
		try {
			wavFiles.add(WavFile.openWavFile(soundFiles.get(0)));
			wavFiles.add(WavFile.openWavFile(soundFiles.get(1)));
			wavFiles.add(WavFile.openWavFile(soundFiles.get(2)));
			wavFiles.add(WavFile.openWavFile(soundFiles.get(3)));
			wavFiles.add(WavFile.openWavFile(soundFiles.get(4)));
		} catch (Exception e) {
			
		}
		
		System.out.println(wavFiles.size());
		
		assert(wavFiles.size() == soundFiles.size()) : "Error: The list of compiled wavFiles is a different size than the list of given soundFiles"; //Checks to make sure the number of wavFiles and soundFiles are the same
		
		//Song must be at least 3 minutes
		int minimumDuration = 180;
		
		int currentDuration = 0;
		
		//Parameters for the random generator
		int max = soundFiles.size();
		int min = 0;
		
		//Create new lists to store the collection of randomly selected files
		ArrayList<File> generatedSoundFiles = new ArrayList<File>();
		ArrayList<WavFile> generatedWavFiles = new ArrayList<WavFile>();
		
		while (currentDuration <= minimumDuration) {
			int randomNum = (int)Math.floor(Math.random()*(max-min)+min);
			
			File selectedFile = soundFiles.get(randomNum);
			WavFile selectedWavFile = wavFiles.get(randomNum);
			
			currentDuration += selectedWavFile.getNumFrames()/selectedWavFile.getSampleRate();
			
			generatedSoundFiles.add(selectedFile);
			generatedWavFiles.add(selectedWavFile);
		}
		
		int durationTest = 0;
		for (int i=0; i < wavFiles.size(); i++) {
			durationTest += (wavFiles.get(i).getNumFrames()/wavFiles.get(i).getSampleRate());
		}
		assert (durationTest >= minimumDuration) : "Error: The duration of the generated list of wavFiles is less than the allowed minimum duration of a complete song"; //Check if the duration of the generated list of wavFiles combined's duration is greater than the minimum duration
		
		return new ProjectList(generatedWavFiles, generatedSoundFiles);
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
	
	/**
	 * Exists for testing the createProjectList method; do not use otherwise
	 */
	public ProjectList createProjectListTest(ArrayList<File> soundFiles) {
		return createProjectList(soundFiles);
	}

}
