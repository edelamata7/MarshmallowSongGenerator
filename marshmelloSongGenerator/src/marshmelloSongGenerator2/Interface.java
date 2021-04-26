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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface {
	
	private StorageManager storageManager;
	private SongGenerator songGenerator;
	private ArrayList<File> sampleFiles;
	private ArrayList<File> projectFiles;
	
	public Interface() {
		storageManager = new StorageManager();
		songGenerator = new SongGenerator();
		sampleFiles = storageManager.getSoundFiles();
		projectFiles = storageManager.getProjectFiles();
		
		
		makeInterface();
	}
	
	public void makeInterface() {
		JFrame frame = new JFrame();
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel fileDisplay = new JPanel();
		fileDisplay.setLayout(new BoxLayout(fileDisplay, BoxLayout.X_AXIS));
		
		JPanel sampleFilesDisplay = new JPanel(new GridLayout(sampleFiles.size() + 1, 1));
		JPanel projectFilesDisplay = new JPanel(new GridLayout(projectFiles.size() + 1, 1));
		JPanel input = new JPanel(new GridLayout(2, 2));
		
		sampleFilesDisplay.add(new JLabel("Sample Projects"));
		for (int i = 0; i < sampleFiles.size(); i++) {
			//sampleFilesDisplay.add(new JLabel("" + i));
			sampleFilesDisplay.add(new JLabel(sampleFiles.get(i).getName()));
		}
		
		
		projectFilesDisplay.add(new JLabel("Available Projects"));
		//projectFilesDisplay.add(new JLabel("ID"));
		for (int i = 0; i < projectFiles.size(); i++) {
			projectFilesDisplay.add(new JLabel(projectFiles.get(i).getName()));
			//projectFilesDisplay.add(new JLabel("" + i));
		}
		
		input.add(new JLabel("Generate a new song:"));
		JButton generate = new JButton("New Song");
		input.add(generate);
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//songGenerator.generateSong();
				try {
					songGenerator.generateSong();
					projectFiles = storageManager.getProjectFiles();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Error: Couldn't generate project", "Error", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
					makeInterface();
				}
				frame.dispose();
				makeInterface();
			}
		});
		
		JTextField editWhat = new JTextField("What do you want to add");
		input.add(editWhat);
		JButton edit = new JButton("Edit");
		input.add(edit);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SongEditorInterface(storageManager.getProjectFile(editWhat.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Error: Couldn't edit project", "Error", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
					makeInterface();
				}
				frame.dispose();
			}
		});
		
		
		
		fileDisplay.add(sampleFilesDisplay);
		fileDisplay.add(projectFilesDisplay);
		mainPanel.add(fileDisplay);
		mainPanel.add(input);

		frame.add(mainPanel);
		frame.setVisible(true);
		
		
		
		
		
		
		
	}

}
