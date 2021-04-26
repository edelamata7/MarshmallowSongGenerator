package marshmelloSongGenerator2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wavFile.WavFile;
import wavFile.WavFileException;

public class SongGenerator extends CreateSong {
	
	/**
	 * Calls the methods in the SongGenerator class to create a new song
	 * @throws Exception 
	 */
	public void generateSong() throws Exception {
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
		
		//Create the list of wavFiles; needed to compute duration of the song
		ArrayList<WavFile> wavFiles = compileSoundFiles(soundFiles);
		
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
			try {
				generatedWavFiles.add(WavFile.openWavFile(selectedFile));
			} catch (IOException | WavFileException e) {
				e.printStackTrace();
			}
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
		String newName = "project";
		//Stores the largest number appended to the name of a project file
		int largestProject;
		ArrayList<File> projectFiles = projectManager.getProjectFiles();
		
		String projectNameRE = ".*(\\d)";
		Pattern projectNamePattern = Pattern.compile(projectNameRE);
		
		
		if (projectFiles.size() == 0) { //If there are no projects
			newName = newName+"1";
		} else if (projectFiles.size() > 1){ //If there is more than 1 project
			Matcher projectNameMatcher = projectNamePattern.matcher(projectFiles.get(0).getName());
			if (projectNameMatcher.find()) {
				largestProject = Integer.parseInt(projectNameMatcher.group(1));
				
				for (int i = 1; i < projectFiles.size(); i++) { //Cycle through each projectFile
					//Get the number of the next project file
					projectNameMatcher = projectNamePattern.matcher(projectFiles.get(i).getName());
					//Compare to the current largestProject number
					if (projectNameMatcher.find() && Integer.parseInt(projectNameMatcher.group(1)) > largestProject) { 
						//If the current project has a larger number than is in largestProject; set largestProject to the current number
						largestProject = Integer.parseInt(projectNameMatcher.group(1));
					}
				}
				
				//Increment the largestProject by 1 to have a new name
				largestProject++;
				//append the largestProject number to the new name
				newName = newName+largestProject;
			}
		} else { //If there is 1 project
			//Need to get the number of the only project; otherwise you risk creating an identical name to the project
			Matcher projectNameMatcher = projectNamePattern.matcher(projectFiles.get(0).getName());
			if (projectNameMatcher.find()) {
				largestProject = Integer.parseInt(projectNameMatcher.group(1));
				largestProject++;
				newName = newName+largestProject;
			}
		}
		
		if (newName.equals("project")) {
			newName = newName+1;
		}
		
		newName = newName+".txt";
		
		for (int i = 0; i < projectFiles.size(); i++) {
			Matcher projectNameMatcher = projectNamePattern.matcher(projectFiles.get(i).getName());
			if (projectNameMatcher.find()) {
				String.valueOf(projectNameMatcher.group(1));
			}
		}
		
		for(int i=0; i < projectFiles.size(); i++) {
			assert(newName != projectFiles.get(i).getName()) : "Error: Newly generated name is already used in the projectFiles folder"; //Checks to make sure the new name isnt already in the projectFiles folder
		}
		
		return newName;
	}
	
	/**
	 * Exists for testing the createProjectList method; do not use otherwise
	 */
	public ProjectList createProjectListTest(ArrayList<File> soundFiles) {
		return createProjectList(soundFiles);
	}

}
