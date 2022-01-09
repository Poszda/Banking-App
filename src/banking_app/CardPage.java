package banking_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Cursor;

public class CardPage extends JPanel implements MouseListener {

	private int userID;
	private String clientCardNumber ="";
	private String clientCardName = "";
	private String clientCardCVV = "";
	private Date clientCardExpirationDate = new Date(0);
	
	private Connection myConn = null;
	private Statement stmt = null;
	private ResultSet rez = null;
	
	JLabel copyToClipboardBtn;
	
	public CardPage(int userID) {
		
		this.userID = userID; // trebuie sa schimb peste tot 'userID' in 'clientID'
		
		//DATABASE CONNECTON
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app","root","");
			stmt = myConn.createStatement();
			rez = stmt.executeQuery("SELECT * FROM `cards` WHERE id=" + this.userID +"");
			if(rez.next()){
				clientCardNumber = rez.getString("number");
				clientCardName = rez.getString("full_name");
				clientCardCVV = rez.getString("cvv");
				clientCardExpirationDate = rez.getDate("expiration_date");
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rez != null)
				try {
					rez.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if(myConn != null)
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
        setBackground(new Color(245,245,245));
        setLayout(null);
        
        Card card = new Card(this.clientCardNumber,this.clientCardName,this.clientCardCVV,this.clientCardExpirationDate);
        card.setSize(480, 300);
        card.setLocation(50, 110);
        add(card);
                
        JLabel cardPageMascote = new JLabel("");
        cardPageMascote.setIcon(new ImageIcon(CardPage.class.getResource("/images/Wallet-pana3.png")));
        cardPageMascote.setHorizontalAlignment(SwingConstants.CENTER);
        cardPageMascote.setBounds(560, 270, 615, 432);
        add(cardPageMascote);
        
        JLabel cardPageTitle = new JLabel("Card Details");
        cardPageTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        cardPageTitle.setBounds(75, 38, 316, 37);
        add(cardPageTitle);
        
        copyToClipboardBtn = new JLabel("   Copy card number to clipboard");
        copyToClipboardBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        copyToClipboardBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        copyToClipboardBtn.setIcon(new ImageIcon(CardPage.class.getResource("/images/icons8-copy-30.png")));
        copyToClipboardBtn.setBounds(75, 500, 281, 29);
        copyToClipboardBtn.addMouseListener(this);
        add(copyToClipboardBtn);
        
        JLabel coin1 = new JLabel("");
        coin1.setIcon(new ImageIcon(CardPage.class.getResource("/images/coin3.png")));
        coin1.setBounds(775, 100, 87, 90);
        add(coin1);
        
        JLabel coin2 = new JLabel("");
        coin2.setIcon(new ImageIcon(CardPage.class.getResource("/images/coin2.png")));
        coin2.setBounds(850, 10, 87, 90);
        add(coin2);
        
        JLabel coin3 = new JLabel("");
        coin3.setIcon(new ImageIcon(CardPage.class.getResource("/images/coin1.png")));
        coin3.setBounds(679, 228, 87, 90);
        add(coin3);
        
        JLabel coin4 = new JLabel("");
        coin4.setIcon(new ImageIcon(CardPage.class.getResource("/images/coin4.png")));
        coin4.setBounds(894, 145, 87, 90);
        add(coin4);
        
        JLabel coin5 = new JLabel("");
        coin5.setIcon(new ImageIcon(CardPage.class.getResource("/images/coin5.png")));
        coin5.setBounds(987, 209, 87, 90);
        add(coin5);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == copyToClipboardBtn) {
			StringSelection stringSelection = new StringSelection(this.clientCardNumber);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == copyToClipboardBtn) {
			copyToClipboardBtn.setForeground(new Color(9,188,138));
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == copyToClipboardBtn) {
			copyToClipboardBtn.setForeground(Color.BLACK);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
