package banking_app;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class AccountPage extends JPanel {

	/**
	 * Create the panel.
	 */
	public AccountPage() {
		setLayout(null);
		
		JLabel accountPageTitle = new JLabel("Account");
		accountPageTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		accountPageTitle.setBounds(75, 38, 117, 54);
		add(accountPageTitle);
		
		JLabel lblNewLabel = new JLabel("<html>Things to add: <br/> - log out <br> - account details <br/> - generate extras de cont <br/> - change password <html/> ");
		lblNewLabel.setBounds(75, 164, 194, 232);
		add(lblNewLabel);

	}
}
