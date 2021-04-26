package marshmelloSongGenerator2;

//import static javax.swing.JOptionPane.showMessageDialog;

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

public class SongEditorInterface implements ActionListener {

	ArrayList<File> projectFiles;
	ArrayList<File> soundFiles;
	ProjectManager projectManager;
	SongEditor songEditor;
	
	boolean exit = false;

	public SongEditorInterface(File project) {
		// Gets a list of the available sample files and those used in the complete
		// given project file
		projectManager = new ProjectManager();
		projectFiles = projectManager.openProjectFile(project);
		soundFiles = projectManager.getSoundFiles();
		songEditor = new SongEditor(projectFiles, project.getName());
		
		makeInterface();
	}
	
	public void makeInterface() {
		// Create the interface
		JFrame frame = new JFrame();
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// master panel everything else is attached to
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// used to have the available files/song files panels right next to each other
		JPanel fileDisplay = new JPanel();
		fileDisplay.setLayout(new BoxLayout(fileDisplay, BoxLayout.X_AXIS));

		// Create the panels that actually have components like labels
		// These need to be initialized with a GridLayout so the components added to them will be laid out properly
		// How to use GridLayout:
		// https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
		JPanel availableFiles = new JPanel(new GridLayout(soundFiles.size() + 1, 2));
		JPanel songFiles = new JPanel(new GridLayout(projectFiles.size() + 1, 2));
		JPanel input = new JPanel(new GridLayout(4, 1));

		// Displays the contents of the soundFiles list in the availableFiles panel (shows all the available sample files)
		availableFiles.add(new JLabel("ID"));
		availableFiles.add(new JLabel("Files Available"));
		for (int i = 0; i < soundFiles.size(); i++) {
			// Song ID (used for the input textbox; we cant just use the name of the file we need its location in the arrayList)
			availableFiles.add(new JLabel("" + i));
			// Name of the file
			availableFiles.add(new JLabel(soundFiles.get(i).getName()));
		}
		
		// Displays the contents of the projectFiles list in the songFiles panel (shows everything thats part of the song)
		songFiles.add(new JLabel("ID"));
		songFiles.add(new JLabel("File in Song"));
		for (int i = 0; i < projectFiles.size(); i++) {
			// Song ID
			songFiles.add(new JLabel("" + i));
			// Name of the song
			songFiles.add(new JLabel(projectFiles.get(i).getName()));
		}

		// Component for adding a new file to the song
		JTextField addField = new JTextField("What do you want to add");
		input.add(addField);
		JTextField addWhere = new JTextField("Add where");
		input.add(addWhere);
		JButton addButton = new JButton("Click to Add");
		input.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String addFieldText = addField.getText();
				String addWhereText = addWhere.getText();
				// Make sure the given values are numbers
				if (addFieldText.matches("\\d*") && addWhereText.matches("\\d*")) {
					// Convert given strings to ints
					int addFieldInt = Integer.parseInt(addFieldText);
					int addWhereInt = Integer.parseInt(addWhereText);
					// Check that the given ID is within the arrays
					if (addFieldInt < soundFiles.size() && addWhereInt < projectFiles.size()) {
						songEditor.addFile(addFieldInt, addWhereInt);
					}
				}
				
				frame.dispose();
				makeInterface();
			}
		});

		// Component for deleting a file from the song
		input.add(new JLabel("What do you want to delete"));
		JTextField deleteField = new JTextField("Delete what");
		input.add(deleteField);
		JButton deleteButton = new JButton("Click to Delete");
		input.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String deleteFieldText = deleteField.getText();
				// Make sure the given value is a number
				if (deleteFieldText.matches("\\d*")) {
					// Convert given string to an int
					int deleteFieldInt = Integer.parseInt(deleteFieldText);
					// Check that the given ID is within the arrays
					if (deleteFieldInt < projectFiles.size()) {
						songEditor.deleteFile(deleteFieldInt);
					}
				}
				
				frame.dispose();
				makeInterface();
			}
		});

		// Component for modifying a file in the song
		JTextField modifyWhat = new JTextField("What do you want to modify");
		input.add(modifyWhat);
		JTextField modifyWhere = new JTextField("Replace with what");
		input.add(modifyWhere);
		JButton modifyButton = new JButton("Click to Modify");
		input.add(modifyButton);
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String modifyWhatText = modifyWhat.getText();
				String modifyWhereText = modifyWhere.getText();
				// Make sure the given values are numbers
				if (modifyWhatText.matches("\\d*") && modifyWhereText.matches("\\d*")) {
					// Convert given strings to ints
					int modifyWhatInt = Integer.parseInt(modifyWhatText);
					int modifyWhereInt = Integer.parseInt(modifyWhereText);
					// Check that the given ID is within the arrays
					if (modifyWhatInt < soundFiles.size() && modifyWhereInt < projectFiles.size()) {
						songEditor.modify(modifyWhereInt, modifyWhatInt);
					}
				}
				
				frame.dispose();
				makeInterface();
			}
		});

		// Component to finish editing
		input.add(new JLabel("Hit button to Generate New Song"));
		JButton finishButton = new JButton("Finish Song");
		input.add(finishButton);
		finishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					songEditor.finishProject();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Error: Couldn't edit project", "Error", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
					makeInterface();
				}
				frame.dispose();
				new Interface();
			}
		});
		
		// Add the panels together so they are laid out correctly
		fileDisplay.add(availableFiles);
		fileDisplay.add(songFiles);
		mainPanel.add(fileDisplay);
		mainPanel.add(input);

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}

}
