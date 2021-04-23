package tests;

import java.io.FileNotFoundException;

import marshmelloSongGenerator2.*;

public class StorageManagerTest {

	public static void main(String[] args) throws Exception {
		
		StorageManager sm = new StorageManager();
		
		sm.getSoundFile("Music.wav");

	}

}
