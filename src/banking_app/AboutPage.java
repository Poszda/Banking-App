package banking_app;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class AboutPage extends JPanel {

	public AboutPage() {
		setLayout(null);
		
        JLabel aboutPageTitle = new JLabel("About Us");
        aboutPageTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        aboutPageTitle.setBounds(75, 38, 316, 37);
        add(aboutPageTitle);
        
		JLabel lblNewLabel = new JLabel("<html>Things to add: <br/> - how to use app guide <br> - images and text about the bank <html/> ");
		lblNewLabel.setBounds(75, 164, 194, 232);
		add(lblNewLabel);

	}

}