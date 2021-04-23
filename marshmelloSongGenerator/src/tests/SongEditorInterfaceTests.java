package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import marshmelloSongGenerator2.*;

public class SongEditorInterfaceTests {
	
	public static void main(String[] args) {
		new SongEditorInterfaceTests().test1();
	}
	
	public void test1() {
		SongEditorInterface driver = new SongEditorInterface(new File(""));
	}

}
