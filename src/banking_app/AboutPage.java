package banking_app;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class AboutPage extends JPanel {

	public AboutPage() {
		setBackground(new Color(245,245,245));
		setLayout(null);
		
		
        JLabel header1 = new JLabel("About Us");
        header1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        header1.setBounds(75, 38, 316, 37);
        add(header1);
        
		JLabel text1 = new JLabel("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Mauris justo dui, auctor a mi vel, scelerisque sagittis lacus. In nulla ipsum, posuere ac viverra id,"
				+ "convallis in ex. Vivamus consequat nulla ac tortor porta, lobortis euismod leo pellentesque. Nullam id mollis augue."
				+ " Cras nec eros erat. Sed consectetur ornare tincidunt. Aliquam efficitur, augue a dictum maximus, nunc velit."
				+ "Aliquam efficitur, augue a dictum maximus, nunc velit tristique sapien, sit amet convallis massa diam sit amet neque.");
		text1.setVerticalAlignment(SwingConstants.TOP);
		text1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text1.setBounds(75, 100, 609, 160);
		add(text1);
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon(AboutPage.class.getResource("/images/about_us_img4.png")));
		img1.setBounds(738, 20, 387, 341);
		add(img1);
		
		JLabel img2 = new JLabel("");
		img2.setIcon(new ImageIcon(AboutPage.class.getResource("/images/about_us_img2.png")));
		img2.setBounds(75, 281, 406, 327);
		add(img2);
		
		JLabel header2 = new JLabel("Our Plans");
		header2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		header2.setBounds(516, 362, 316, 37);
		add(header2);
		
		JLabel text2 = new JLabel("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris justo dui, auctor a mi vel, "
				+ "scelerisque sagittis lacus. In nulla ipsum, posuere ac viverra id,convallis in ex. Vivamus consequat nulla ac tortor porta, "
				+ "lobortis euismod leo pellentesque. Nullam id mollis augue. Cras nec eros erat. Sed consectetur ornare tincidunt. Aliquam efficitur, augue a dictum maximus, nunc velit."
				+ "Aliquam efficitur, augue a dictum maximus, nunc velit tristique sapien, sit amet convallis massa diam sit amet neque. "
				+ "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
		text2.setVerticalAlignment(SwingConstants.TOP);
		text2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text2.setBounds(516, 423, 609, 160);
		add(text2);

	}
}