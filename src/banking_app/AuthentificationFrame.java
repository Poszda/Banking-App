package banking_app;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AuthentificationFrame extends JFrame implements ActionListener, MouseListener, KeyListener  { //focus listener

	private JPanel contentPane;
	
	private JPanel panel_left;
	private JPanel panel_login;
	private JLabel lbLoginTitle;
	private JLabel lbLoginEmail;
	private JTextField textFieldLogin;
	private JLabel lbLoginPassword;
	private JPasswordField passwordFieldLogin;
	private JButton loginButton;
	private JLabel lbLoginText1;
	private JLabel lbLoginText2;
	
	private JPanel panel_right;
	private JPanel panel_sign;
	private JLabel lbSignTitle;
	private JLabel lbSignEmail;
	private JTextField textFieldSign;
	private JLabel lbSignPassword;
	private JPasswordField passwordFieldSign;
	private JButton signButton;
	private JLabel lbSignText1;
	private JLabel lbSignText2;
	private JLabel lbSignPasswordCheck;
	private JPasswordField passwordFieldSignCheck;
	private JLabel lbSignLastName;
	private JTextField textFieldSignLastName;
	private JLabel lbSignSecurityCode;
	private JTextField textFieldSignSecurityCode;
	
	private JLabel lbCloseImg;
	
	//Database variables
	Connection myConn = null;
	Statement stmt = null;
	ResultSet rez = null;
	ResultSet rez2 = null;
	PreparedStatement ps = null;
	private JLabel lblNewLabel;
	
	

	AuthentificationFrame() {
		//FRAMEUL
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		//CONTAINERUL MARE
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//CONTAINER STANGA
		panel_left = new JPanel();
		panel_left.setBackground(Color.WHITE);
		panel_left.setBounds(0, 0, 600, 700);//613
		panel_left.setLayout(null);
		contentPane.add(panel_left);
		
		//CONTAINER LOGIN
		panel_login = new JPanel();
		panel_login.setBackground(Color.WHITE);
		panel_login.setBounds(125,150, 350, 400);
		panel_login.setLayout(null);
		panel_left.add(panel_login);
		
		lbLoginTitle = new JLabel("Login");
		lbLoginTitle.setVerticalAlignment(SwingConstants.TOP);
		lbLoginTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lbLoginTitle.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lbLoginTitle.setBounds(0, 0, 113, 41);
		panel_login.add(lbLoginTitle);
		
		lbLoginEmail = new JLabel("Email*");
		lbLoginEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbLoginEmail.setBounds(0, 82, 84, 30);
		panel_login.add(lbLoginEmail);
		
		lbLoginPassword = new JLabel("Password*");
		lbLoginPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbLoginPassword.setBounds(0, 174, 84, 30);
		panel_login.add(lbLoginPassword);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldLogin.setBounds(0, 111, 350, 45);
		textFieldLogin.setColumns(10);
		panel_login.add(textFieldLogin);
		
		loginButton = new JButton("Login"); // login button
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setBackground(new Color(9,188,138));
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginButton.setBounds(100, 286, 150, 45);
		panel_login.add(loginButton);
		
		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordFieldLogin.setBounds(0, 204, 350,45);
		panel_login.add(passwordFieldLogin);
		
		lbLoginText1 = new JLabel("Not registered yet?");
		lbLoginText1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbLoginText1.setBounds(10, 360, 136, 17);
		panel_login.add(lbLoginText1);
		
		lbLoginText2 = new JLabel("Create an Account");
		lbLoginText2.addMouseListener(this);
		lbLoginText2.setForeground(new Color(9,188,138));
		lbLoginText2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbLoginText2.setBounds(10, 378, 136, 17);
		panel_login.add(lbLoginText2);
		
		//CONTAINER SIGN IN
		panel_sign = new JPanel();
		panel_sign.setBackground(Color.WHITE);
		panel_sign.setBounds(125, 60, 350, 580);
		panel_sign.setLayout(null);
		panel_sign.setVisible(false); // important
		panel_left.add(panel_sign);
		
		lbSignTitle = new JLabel("Sign Up");
		lbSignTitle.setVerticalAlignment(SwingConstants.TOP);
		lbSignTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lbSignTitle.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lbSignTitle.setBounds(0, 0, 113, 41);
		panel_sign.add(lbSignTitle);
		
		lbSignEmail = new JLabel("Email*");
		lbSignEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSignEmail.setBounds(0, 70, 84, 30);
		panel_sign.add(lbSignEmail);
		
		lbSignPassword = new JLabel("New Password*");
		lbSignPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSignPassword.setBounds(0, 323, 146, 30);
		panel_sign.add(lbSignPassword);
		
		textFieldSign = new JTextField();
		textFieldSign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSign.setBounds(0, 100, 350, 45);
		textFieldSign.setColumns(10);
		panel_sign.add(textFieldSign);
		
		signButton = new JButton("Sign Up"); // login button
		signButton.addActionListener(this);
		signButton.setFocusable(false);
		signButton.setForeground(Color.WHITE);
		signButton.setBorder(BorderFactory.createEmptyBorder());
		signButton.setBackground(new Color(9,188,138));
		signButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		signButton.setBounds(100, 501, 150, 45);
		panel_sign.add(signButton);
		
		passwordFieldSign = new JPasswordField();
		passwordFieldSign.addKeyListener(this);
		passwordFieldSign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordFieldSign.setBounds(0, 352, 350,45);
		panel_sign.add(passwordFieldSign);
		
		lbSignText1 = new JLabel("Already registered?");
		lbSignText1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSignText1.setBounds(0, 563, 136, 17);
		panel_sign.add(lbSignText1);
		
		lbSignText2 = new JLabel("Log in your Account");
		lbSignText2.addMouseListener(this);
		lbSignText2.setForeground(new Color(9,188,138));
		lbSignText2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSignText2.setBounds(129, 563, 136, 17);
		panel_sign.add(lbSignText2);
		
		lbSignPasswordCheck = new JLabel("Re-type Password*");
		lbSignPasswordCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSignPasswordCheck.setBounds(0, 407, 146, 30);
		panel_sign.add(lbSignPasswordCheck);
		
		passwordFieldSignCheck = new JPasswordField();
		passwordFieldSignCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordFieldSignCheck.setBounds(0, 437, 350, 45);
		panel_sign.add(passwordFieldSignCheck);
		
		lbSignLastName = new JLabel("Last Name*");
		lbSignLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSignLastName.setBounds(0, 155, 113, 30);
		panel_sign.add(lbSignLastName);
		
		textFieldSignLastName = new JTextField();
		textFieldSignLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSignLastName.setColumns(10);
		textFieldSignLastName.setBounds(0, 184, 350, 45);
		panel_sign.add(textFieldSignLastName);
		
		lbSignSecurityCode = new JLabel("Security Code*");
		lbSignSecurityCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSignSecurityCode.setBounds(0, 239, 113, 30);
		panel_sign.add(lbSignSecurityCode);
		
		textFieldSignSecurityCode = new JTextField();
		textFieldSignSecurityCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSignSecurityCode.setColumns(10);
		textFieldSignSecurityCode.setBounds(0, 268, 350, 45);
		panel_sign.add(textFieldSignSecurityCode);
		
		//CONTAINER DREAPTA
		panel_right = new JPanel();
		panel_right.setBackground(new Color(9,188,138)); //09bc8a
		panel_right.setBounds(600, 0, 600, 700);//613
		panel_right.setLayout(null);
		contentPane.add(panel_right);
		
		lbCloseImg = new JLabel("");
		lbCloseImg.addMouseListener(this);
		ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/images/close_icon2.png"));
		lbCloseImg.setIcon(closeImg);
		lbCloseImg.setBounds(543,25, 32, 32);
		panel_right.add(lbCloseImg);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AuthentificationFrame.class.getResource("/images/Savings-bro2.png")));
		lblNewLabel.setBounds(75, 125, 450, 450);
		panel_right.add(lblNewLabel);
		
		
		//DATABASE CONNECTON
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app","root","");
			stmt = myConn.createStatement();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		/*ImageIcon closeImg2 = new ImageIcon(this.getClass().getResource("singlebanner.jpg"));
		lblNewLabel = new JLabel(closeImg2);
		//lblNewLabel.setIcon(closeImg2);
		lblNewLabel.setBounds(10, 186, 441, 391);
		panel_right.add(lblNewLabel);*/
		
	    
	    //.setIcon(closeImg);
	   // panel_right.add(imgLabel);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//LOGIN BUTTON
		if(e.getSource() == loginButton) {
			String userEmail = textFieldLogin.getText();
			String userPassword = passwordFieldLogin.getText();
			
			try {
			rez = stmt.executeQuery("SELECT * FROM `users` WHERE Email = '" + userEmail + "' and Password = '" + userPassword+"'");
			if(rez.next()){	
				//GET USER ID FOR PASSING IT TO HomeFrame
				int userId = rez.getInt("Id");
				
				//CLOSE DATABASE CONNECTION
				if(myConn != null) myConn.close();
				if(stmt != null)stmt.close();
				if(rez != null)rez.close();
				if(ps != null)ps.close();
				
				//MOVE TO HomeFrame
				this.setVisible(false);
				HomeFrame h = new HomeFrame(userId);
				h.setVisible(true);
				}
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
		}
		// SIGN BUTTON
		else if(e.getSource() == signButton) {
			String userEmail = textFieldSign.getText().strip();
			String userLastName = textFieldSignLastName.getText().strip();
			String userPassword = passwordFieldSign.getText();
			String userPasswordCheck = passwordFieldSignCheck.getText();
			String userSecurityCode = textFieldSignSecurityCode.getText().strip();
			// form validation
			if(userEmail.isEmpty() || userLastName.isEmpty() || userPassword.isEmpty() || userPasswordCheck.isEmpty() || userSecurityCode.isEmpty()) 
				JOptionPane.showMessageDialog(this, "There is an empty field");
			else if(userPassword.equals(userPasswordCheck) == false)
				JOptionPane.showMessageDialog(this, "Passwords do not match");
			else if(userPassword.length() < 6)
				JOptionPane.showMessageDialog(this, "Passwords must have more then 5 characters");
			else { 
				try { // trying to create the accounut
					rez = stmt.executeQuery("SELECT * FROM `clients` WHERE Nume = '" + userLastName + "' and Security_code = '" + userSecurityCode+"'");
					if(rez.next()){			 // if input data match a client from our clients table of database	
						int userId = rez.getInt("Id"); // we get that client id
						rez.close(); //oricum se inchide in mod normal cand folosesc rez2
						rez2 = stmt.executeQuery("SELECT * FROM `users` WHERE Id = '" + userId + "'");
						if(!rez2.next()) {  // if client hasn't already an user account,we create the account
						ps = myConn.prepareStatement("INSERT INTO users (Id,Email,Password) VALUES (?,?,?)");
						ps.setInt(1,userId);
						ps.setString(2,userEmail);   
						ps.setString(3,userPassword);
						ps.execute();
						
						JOptionPane.showMessageDialog(this, "Contul a fost creat cu succes!");
						//CLOSE DATABASE // pot sterge astea si sa oblig clientul sa se logheze ca sa mearga la urmatorul ecran
						myConn.close();
						stmt.close();
						rez2.close();
						ps.close();
						}
						else { // The client has already an account so we don t create another one
							JOptionPane.showMessageDialog(this, "Contul exista deja in baza de date");
						}
						//MOVE TO HomeFrame
						//this.setVisible(false);
						HomeFrame h = new HomeFrame(userId);
						h.setVisible(true);
						}
					else {
						JOptionPane.showMessageDialog(this, "Datele introduse sunt gresite");	
					}
					} // try breaket
					catch (Exception exc) {
						exc.printStackTrace();
					}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == lbLoginText2) {
			panel_login.setVisible(false);
			panel_sign.setVisible(true);
			
		}
		else if(e.getSource() == lbSignText2) {
			panel_login.setVisible(true);
			panel_sign.setVisible(false);	
		}
		else if(e.getSource() == lbCloseImg) {
			System.exit(0);
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//KEY LISTENER
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == passwordFieldSign) {
			System.out.println("works");
		}
		
	}
}
