package marshmelloSongGenerator2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SongEditorInterface implements ActionListener {
	
	public void test2() {
		JFrame frame = new JFrame();
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//master panel everything else is attached to
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//used to have the available files/song files panels right next to each other
		JPanel fileDisplay = new JPanel();
		fileDisplay.setLayout(new BoxLayout(fileDisplay, BoxLayout.X_AXIS));
		
		//Create the panels that actually have components like labels
		//These need to be initialized with a GridLayout so the components added to them will be laid out properly
		//How to use GridLayout: https://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
		JPanel availableFiles = new JPanel(new GridLayout(testFiles.size()+1, 2));
		JPanel songFiles = new JPanel(new GridLayout(testFiles.size()+1, 2));
		JPanel input = new JPanel(new GridLayout(4, 1));
		
		//Ignore this; just to help show how the panels work
		Border red = BorderFactory.createLineBorder(Color.red);
		Border blue = BorderFactory.createLineBorder(Color.blue);
		Border green = BorderFactory.createLineBorder(Color.green);
		Border pink = BorderFactory.createLineBorder(Color.pink);
		mainPanel.setBorder(red);
		songFiles.setBorder(blue);
		availableFiles.setBorder(green);
		input.setBorder(pink);
		
		//Add files to the availableFiles (shows all the available sample files)
		availableFiles.add(new JLabel("ID"));
		availableFiles.add(new JLabel("File in Song"));
		for (int i = 0; i < testFiles.size(); i++) {
			//Song ID (used for the input textbox; we cant just use the name of the file we need its location in the arrayList)
			availableFiles.add(new JLabel(""+i));
			//Name of the file
			availableFiles.add(new JLabel(testFiles.get(i).getName()));
		}
		
		//Adds files to the songFiles panel (shows everything thats part of the song)
		songFiles.add(new JLabel("ID"));
		songFiles.add(new JLabel("File Available"));
		for (int i = 0; i < testFiles.size(); i++) {
			//Song ID
			songFiles.add(new JLabel(""+i));
			//Name of the song
			songFiles.add(new JLabel(testFiles.get(i).getName()));
		}
		
		//Sample idea for how to have the add/delete/modify/finish buttons/textboxes/whatever
		JTextField addField = new JTextField("What do you want to add");
		input.add(addField);
		JTextField addWhere = new JTextField("Add where");
		input.add(addWhere);
		JButton addButton = new JButton("Click to Add");
		input.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(addField.getText());
			}
		});
		//ADD ACTION LISTENER
		
		
		input.add(new JLabel("What do you want to delete"));
		JTextField deleteField = new JTextField("Delete what");
		input.add(deleteField);
		JButton deleteButton = new JButton("Click to Delete");
		input.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("delete");
			}
		});		
	
		JTextField modifyWhat = new JTextField("What do you want to modify");
		input.add(modifyWhat);
		JTextField modifyWhere = new JTextField("Where");
		input.add(modifyWhere);
		JButton modifyButton = new JButton("Click to Modify");
		input.add(modifyButton);
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("modify");
			}
		});	
		
		input.add(new JLabel("Hit button to Generate New Song"));
		JButton finishButton = new JButton("Finish Song");
		input.add(finishButton);
		finishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Generating Song");
			}
		});	
		
		//Add the panels together so they are laid out correctly
		fileDisplay.add(availableFiles);
		fileDisplay.add(songFiles);
		mainPanel.add(fileDisplay);
		mainPanel.add(input);
		
		frame.add(mainPanel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
