package marshmelloSongGenerator2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SongEditorInterface {
	
public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		JLabel Songlabel = new JLabel("Files in Song");
		Songlabel.setBounds(10,20,80,25);
		panel.add(Songlabel);
		
		JLabel addFiles = new JLabel("Add Files");
		addFiles.setBounds(400,20,80,25);
		panel.add(addFiles);
		
		JLabel files = new JLabel("Example file here");
		files.setBounds(10, 50, 200, 25);
		panel.add(files);
		
		
		JLabel availableFiles = new JLabel("Example available file here");
		availableFiles.setBounds(300, 50, 200, 25);
		panel.add(availableFiles);
		
		JButton button = new JButton("Generate New Song");
		button.setBounds(200, 200, 150, 25);
		panel.add(button);
		

		
		frame.setVisible(true);
		
	}

}
