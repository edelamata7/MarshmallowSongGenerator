package marshmelloSongGenerator2;

import java.io.File;
import java.util.ArrayList;

import wavFile.WavFile;

public class ProjectList {
	
	/**
	 * A list of the wavFiles used in the project.
	 * This will use the same files as the files list, but as wavFile objects.
	 */
	private ArrayList<WavFile> wavFiles;
	
	/**
	 * A list of the sound files used in the project.
	 * This will use the same files as the wavFiles list, but as File objects.
	 */
	private ArrayList<File> files;
	
	/**
	 * Sets the value of the wavFiles and files lists
	 * @param wavFiles - Given list of wavFiles
	 * @param files - Given list of files
	 */
	public ProjectList(ArrayList<WavFile> wavFiles, ArrayList<File> files) {
		this.wavFiles = wavFiles;
		this.files = files;
	}
	
	public ArrayList<WavFile> getWavFiles() {
		return wavFiles;
	}
	
	public ArrayList<File> getFiles() {
		return files;
	}
	
	//need to think about adding setters b/c the wavFiles and files lists must be the same, so a setter must set both @ the same time
	//setter for files could be different b/c file objects can be converted into wavFile objects, but wavFile objects cannot be converted into file objects
	//files setter could just take a file arraylist and convert use it to create a wavfile list
	//but are setters even necessary for this? will see in future

}