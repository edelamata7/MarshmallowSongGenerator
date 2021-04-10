package marshmelloSongGenerator2;

import java.io.File;
import java.util.ArrayList;
import wavFile.WavFile;

public class SongGenerator extends CreateSong {
	
	/**
	 * Calls the methods in the SongGenerator class to create a new song
	 */
	public void generateSong() {
		
	}
	
	/**
	 * Takes an arrayList of all the available sound files and randomly selects files to create a new song
	 * @param
	 * @return Returns a project list object that can be used by the createSong class to generate a finished wavFile and project file
	 */
	@Override
	protected ProjectList createProjectList(ArrayList<File> soundFiles) {
		
		return null;
	}
	
	/**
	 * Creates a unique name for the new project
	 * @return A string of a unique name
	 */
	private String createName() {
		return null;
	}

}
