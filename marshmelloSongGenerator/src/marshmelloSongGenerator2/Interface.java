package marshmelloSongGenerator2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface {
	
	private StorageManager storageManager;
	private ArrayList<File> sampleFiles;
	private ArrayList<File> projectFiles;
	
	public Interface() {
		storageManager = new StorageManager();
		sampleFiles = storageManager.getSoundFiles();
		projectFiles = storageManager.getProjectFiles();
		
		makeInterface();
	}
	
	public void makeInterface() {
		
	}

}
