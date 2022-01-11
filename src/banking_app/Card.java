package banking_app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Card extends JPanel {

	private String number;
	private String name;
	private String cvv;
	private Date expirationDate;
	private boolean blocked;
	
	JLabel cardImage; 
	
	public Card(String number, String name, String cvv, Date expirationDate,boolean blocked) {
		this.number = number;
		this.name = name;
		this.cvv = cvv;
		this.expirationDate = expirationDate;
		this.blocked = blocked;
		
		setBackground(new Color(245,245,245));
		setLayout(null);
		setSize(480,300);
		JLabel CardNumber = new JLabel(formatCardNumber());
		CardNumber.setForeground(Color.WHITE);
		CardNumber.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		CardNumber.setBounds(50, 139, 262, 24);
		add(CardNumber);
		
		JLabel CardName= new JLabel(this.name);
		CardName.setForeground(Color.WHITE);
		CardName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		CardName.setBounds(50, 236, 197, 13);
		add(CardName);
		
		JLabel CardExpirationData = new JLabel(formatExpirationDate());
		CardExpirationData.setForeground(Color.WHITE);
		CardExpirationData.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		CardExpirationData.setBounds(365, 236, 49, 14);
		add(CardExpirationData);
		
		JLabel CardCVV = new JLabel(this.cvv);
		CardCVV.setForeground(Color.WHITE);
		CardCVV.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		CardCVV.setBounds(282, 236, 30, 13);
		add(CardCVV);
		
		cardImage = new JLabel("");
		cardImage.setHorizontalAlignment(SwingConstants.CENTER);
		cardImage.setIcon(new ImageIcon(Card.class.getResource(this.blocked == false?"/images/card-final.png":"/images/card-final-blocked.png")));
		cardImage.setBounds(0, 0, 480, 300);
		add(cardImage);
	}
	
	//GETTERS AND SETTERS
	
	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
		//extra - schimb si imaginea
		cardImage.setIcon(new ImageIcon(Card.class.getResource(this.blocked?"/images/card-final-blocked.png":"/images/card-final.png")));

	}

	//FUNCTIONS
	
	public String formatCardNumber(){
		
		return(this.number.substring(0,4) + "   " + this.number.substring(4,8) + "   "
		+ this.number.substring(8,12) + "   " + this.number.substring(12,16));
	}
	
    public String formatExpirationDate() {
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");  
    	String text = df.format(this.expirationDate);
		return text.substring(0,5);
     }

}
