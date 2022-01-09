package banking_app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class HomeFrame extends JFrame implements MouseListener {
	private int userID;
	private String userFirstName;
	private float userBalance;
	
	private JPanel content;
	
	
	private JPanel main;  //first page
	private CardPage cardPage; // second page
	private AboutPage aboutPage; //third page
	private AccountPage accountPage; // forth page
	
	private JLabel closeBtn;
	private JLabel overviewOption;
	private JLabel cardOption;
	private JLabel aboutOption;
	private JLabel accountOption;
	private JTextField transferIbanField;
	private JTextField transferAmountField;
	private JTextField requestAmountField;
	private JLabel transferButton;
	private JLabel requestButton;
	private JLabel currentBalanceAmount;
	private TransactionsVisualiser transactionVisualiser;
	private BottomData bottomData;

	private Connection myConn = null;
	private Statement stmt = null;
	private Statement stmt2 = null;
	private ResultSet rez = null;
	private ResultSet rez2 = null;
	private PreparedStatement ps = null;

    public HomeFrame(int userID) throws SQLException {
    	this.userID = userID;
    	
		//DATABASE CONNECTON
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app","root","");
			stmt = myConn.createStatement();
			stmt2 = myConn.createStatement();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		rez = stmt.executeQuery("SELECT * FROM `clients` WHERE id=" + this.userID +""); // poate un join aici
		if(rez.next()){
			this.userFirstName = rez.getString("Prenume");
			this.userBalance = rez.getFloat("account_balance");
		}
		//------- GUI ------
        setSize(1300, 800);
        setLocationRelativeTo(null);
		setUndecorated(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setExtendedState(JFrame.NORMAL);
        
        JPanel menu = new JPanel();
        menu.setBackground(Color.WHITE);
        menu.setPreferredSize(new Dimension(100, 0));
        getContentPane().add(menu, BorderLayout.WEST);
        menu.setLayout(new BorderLayout(0, 0));
       
        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setHorizontalTextPosition(SwingConstants.CENTER);
        logo.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/logo11.png")));
        logo.setPreferredSize(new Dimension(100, 80));
        menu.add(logo, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        menu.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        overviewOption = new JLabel("");
        overviewOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        overviewOption.setHorizontalAlignment(SwingConstants.CENTER);
        overviewOption.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-bank-account-30.png")));
        overviewOption.setBackground(Color.WHITE);
        overviewOption.setBounds(0, 200, 100, 80);
        overviewOption.addMouseListener(this);
        panel.add(overviewOption);
        
        cardOption = new JLabel("");
        cardOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cardOption.setHorizontalAlignment(SwingConstants.CENTER);
        cardOption.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-credit-card-30.png")));
        cardOption.setBounds(0, 280, 100, 80);
        cardOption.addMouseListener(this);
        panel.add(cardOption);
        
        aboutOption = new JLabel("");
        aboutOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aboutOption.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-info-30.png")));
        aboutOption.setHorizontalAlignment(SwingConstants.CENTER);
        aboutOption.setBounds(0, 360, 100, 80);
        aboutOption.addMouseListener(this);
        panel.add(aboutOption);
        
        accountOption = new JLabel("");
        accountOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accountOption.setBackground(Color.WHITE);
        accountOption.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-user-30.png")));
        accountOption.setHorizontalAlignment(SwingConstants.CENTER);
        accountOption.setPreferredSize(new Dimension(0, 80));
        accountOption.addMouseListener(this);
        menu.add(accountOption, BorderLayout.SOUTH);
        
        content = new JPanel();
        content.setBackground(Color.white);
        getContentPane().add(content, BorderLayout.CENTER);
        content.setLayout(new BorderLayout(0, 0));
        
        //HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(245,245,245));
        header.setPreferredSize(new Dimension(0, 80));
        content.add(header, BorderLayout.NORTH);
        header.setLayout(new BorderLayout(0, 0));
        
        JLabel welcomeText = new JLabel("Welcome back, " + this.userFirstName);
        welcomeText.setBackground(Color.WHITE);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setAlignmentY(Component.TOP_ALIGNMENT);
        welcomeText.setFont(new Font("Tahoma", Font.PLAIN, 24));
        welcomeText.setPreferredSize(new Dimension(300, 0));
        header.add(welcomeText, BorderLayout.WEST);
        
        closeBtn = new JLabel("");
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-close-30.png")));
        closeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        closeBtn.setPreferredSize(new Dimension(100, 0));
        closeBtn.addMouseListener(this);
        header.add(closeBtn, BorderLayout.EAST);
        
        //MAIN (PAGE)      
        main = new JPanel();
        main.setFont(new Font("Tahoma", Font.PLAIN, 14));
        main.setBackground(new Color(245,245,245));
        content.add(main, BorderLayout.CENTER); // --------------------------------------------------------------------------------------//////////
        main.setLayout(null);
        
        
        //TRANSACTION VISUALISER
        transactionVisualiser = new TransactionsVisualiser(getAndFormatTransactionListFromDatabase());
        transactionVisualiser.setLocation(75, 120);
        main.add(transactionVisualiser); 
        
        //TRANSFER PANNEL
        JPanel transferPanel = new JPanel();
        transferPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        transferPanel.setBackground(new Color(9,188,138));
        transferPanel.setBounds(675, 120, 450, 142);
        main.add(transferPanel);
        transferPanel.setLayout(null);
        
        JLabel transferTitle = new JLabel("Transfer money");
        transferTitle.setForeground(Color.WHITE);
        transferTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        transferTitle.setBounds(40, 25, 217, 25);
        transferPanel.add(transferTitle);
        
        transferIbanField = new JTextField();
        transferIbanField.setBorder(null);
        transferIbanField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        transferIbanField.setBounds(40, 60, 180, 35);
        transferPanel.add(transferIbanField);
        transferIbanField.setColumns(10);
        
        transferAmountField = new JTextField();
        transferAmountField.setBorder(null);
        transferAmountField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        transferAmountField.setColumns(10);
        transferAmountField.setBounds(231, 60, 119, 35);
        transferPanel.add(transferAmountField);
        
        transferButton = new JLabel("");
        transferButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        transferButton.setHorizontalTextPosition(SwingConstants.CENTER);
        transferButton.setHorizontalAlignment(SwingConstants.CENTER);
        transferButton.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-right-arrow-16.png")));
        transferButton.setOpaque(true);
        transferButton.setBackground(Color.WHITE);
        transferButton.setBounds(360, 60, 58, 35);
        transferButton.addMouseListener(this);
        transferPanel.add(transferButton);
        
        JLabel transferIban = new JLabel("IBAN");
        transferIban.setForeground(Color.WHITE);
        transferIban.setFont(new Font("Tahoma", Font.PLAIN, 12));
        transferIban.setBounds(114, 105, 30, 12);
        transferPanel.add(transferIban);
        
        JLabel transferAmount = new JLabel("Amount");
        transferAmount.setForeground(Color.WHITE);
        transferAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        transferAmount.setBounds(272, 105, 45, 13);
        transferPanel.add(transferAmount);
        
        //REQUEST PANEL
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(null);
        requestPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        requestPanel.setBackground(Color.WHITE);
        requestPanel.setBounds(675, 272, 450, 142);
        main.add(requestPanel);
        
        JLabel requestTitle = new JLabel("Request Loan");
        requestTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        requestTitle.setBounds(40, 25, 217, 25);
        requestPanel.add(requestTitle);
        
        requestAmountField = new JTextField();
        requestAmountField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        requestAmountField.setColumns(10);
        requestAmountField.setBounds(40, 60, 310, 35);
        requestPanel.add(requestAmountField);
        
        requestButton = new JLabel("");
        requestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestButton.setIcon(new ImageIcon(HomeFrame.class.getResource("/images/icons8-right-arrow-16-white.png")));
        requestButton.setOpaque(true);
        requestButton.setHorizontalTextPosition(SwingConstants.CENTER);
        requestButton.setHorizontalAlignment(SwingConstants.CENTER);
        requestButton.setBackground(new Color(9, 188, 138));
        requestButton.setBounds(360, 60, 58, 35);
        requestButton.addMouseListener(this);
        requestPanel.add(requestButton);
        
        JLabel requestAmount = new JLabel("Amount");
        requestAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        requestAmount.setBounds(184, 105, 46, 12);
        requestPanel.add(requestAmount);
        
        //OTHER TITLES AND SUBTITLES
        JLabel currentBalance = new JLabel("Current Balance");
        currentBalance.setHorizontalTextPosition(SwingConstants.LEADING);
        currentBalance.setFont(new Font("Tahoma", Font.PLAIN, 24));
        currentBalance.setBounds(75, 38, 200, 24);
        main.add(currentBalance);
        
        JLabel todayData = new JLabel("As of " + getTodayData());
        todayData.setForeground(Color.DARK_GRAY);
        todayData.setBackground(Color.WHITE);
        todayData.setFont(new Font("Tahoma", Font.PLAIN, 12));
        todayData.setBounds(75, 72, 170, 12);
        main.add(todayData);
        
        currentBalanceAmount = new JLabel(this.userBalance+"\u20AC");
        currentBalanceAmount.setHorizontalAlignment(SwingConstants.CENTER);
        currentBalanceAmount.setFont(new Font("Tahoma", Font.PLAIN, 40));
        currentBalanceAmount.setBounds(925, 38, 200, 46);
        main.add(currentBalanceAmount);
        
        bottomData = new BottomData(CalculateInOutInterestRate());
        bottomData.setLocation(75, 570);
        main.add(bottomData);
        
        
       // CARD PAGE
        cardPage = new CardPage(this.userID);
        aboutPage = new AboutPage();
        accountPage = new AccountPage();
        //content.add(cardPage, BorderLayout.CENTER);
        setVisible(true);

    }
    
    //EVENTS
    
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == transferButton) {
			makeATransfer();
		}
		else if(e.getSource() == requestButton) {
			makeALoanRequest();
		}
		else if(e.getSource() == closeBtn) {
			System.exit(0);
		}
		else if(e.getSource() == cardOption) {
			content.remove(main);
			content.remove(aboutPage);
			content.remove(accountPage);
			content.add(cardPage, BorderLayout.CENTER);
			content.updateUI();
		}
		else if(e.getSource() == overviewOption) {
			content.remove(cardPage);
			content.remove(aboutPage);
			content.remove(accountPage);
			content.add(main, BorderLayout.CENTER);
			content.updateUI();
		}
		else if(e.getSource() == aboutOption) {
			content.remove(main);
			content.remove(cardPage);
			content.remove(accountPage);
			content.add(aboutPage, BorderLayout.CENTER);
			content.updateUI();
		}
		else if(e.getSource() == accountOption) {
			content.remove(main);
			content.remove(cardPage);
			content.remove(aboutPage);
			content.add(accountPage, BorderLayout.CENTER);
			content.updateUI();
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
		// TODO Auto-generated method stu
	}
	
	
	//FUNCTIONS
    public String getTodayData() {
 	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
 	   LocalDateTime now = LocalDateTime.now();
 	   String formattedDateTime = now.format(dtf);
 	   return formattedDateTime;  
    }
        
    public void makeATransfer() {
    	try {
			rez = stmt.executeQuery("SELECT * FROM `clients` WHERE iban=" + "'" + transferIbanField.getText() +"'" +"");
			if(rez.next()){ // daca exista un user cu ibanul asta , ii luam id ul
				int otherClientID = rez.getInt("id");
				float otherClientBalance = rez.getFloat("account_balance");
				float moneyAmount = Float.parseFloat(transferAmountField.getText());
				
				//VALIDATION
				if(moneyAmount < 0) 
					JOptionPane.showMessageDialog(this, "Ati introdus o valoare gresita");
				else if(moneyAmount > this.userBalance)
					JOptionPane.showMessageDialog(this, "Insuficienti bani in cont pentru a face aceasta tranzactie");
				else if(otherClientID == this.userID)
					JOptionPane.showMessageDialog(this, "Nu puteti face un transfer tot in contul dvs");
				else{
				//INSERAM TRANZACTIA IN BAZA DE DATE
				ps = myConn.prepareStatement("INSERT INTO `transactions` (id_giver,id_receiver,amount) VALUES (?,?,?)");
				ps.setInt(1,this.userID);
				ps.setInt(2,otherClientID);
				ps.setFloat(3,moneyAmount); 
				ps.execute();
				
				//UPDATEZ NOUL BALANCE IN AMBELE CONTURI ALE CLIENTILOR
				this.userBalance -= moneyAmount; // noul balance al userului dupa tranzactie
				otherClientBalance += moneyAmount;
				
				ps = myConn.prepareStatement("UPDATE `clients` SET account_balance = ? WHERE id = ?");
				ps.setFloat(1, this.userBalance);
				ps.setInt(2,this.userID);
				ps.execute();
				
				ps = myConn.prepareStatement("UPDATE `clients` SET account_balance = ? WHERE id = ?");
				ps.setFloat(1, otherClientBalance);
				ps.setInt(2,otherClientID);
				ps.execute();
				
				//MODIFIC NOUL BALANCE AL USERULUI IN UI
				currentBalanceAmount.setText(Float.toString(this.userBalance) + "\u20AC");
				currentBalanceAmount.revalidate();
				currentBalanceAmount.repaint();
				}
				//MODIFIC NOUL PANOU CU TRANZACTII IN UI
				main.remove(transactionVisualiser);
				transactionVisualiser = new TransactionsVisualiser(getAndFormatTransactionListFromDatabase());
				transactionVisualiser.setLocation(75, 120);
				main.add(transactionVisualiser);
				
				//MODIFIC NOUL PANOU CU DATE DESPRE CONT IN UI
				main.remove(bottomData);
				bottomData = new BottomData(CalculateInOutInterestRate());
		        bottomData.setLocation(75, 570);
				main.add(bottomData);
				
				//REVALIDATE AND REPAINT CONTAINERUL 
				main.revalidate();
				main.repaint();
			}
			else {
				JOptionPane.showMessageDialog(this, "Acest IBAN este inexistent");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void makeALoanRequest(){
    	float requestedAmount = Float.parseFloat(requestAmountField.getText());
    	if(requestedAmount <= 0) JOptionPane.showMessageDialog(this, "Ati introdus o valoare gresita");
    	else if(requestedAmount > this.userBalance * 100) JOptionPane.showMessageDialog(this, "Aveti venituri prea mici pentru a imprumuta o suma asa mare");
    	else {
			try {
				
				//UPDATAM SUMA DIN CONTUL USERULUI
				ps = myConn.prepareStatement("UPDATE `clients` SET account_balance = ? WHERE id = ?");
				ps.setFloat(1,this.userBalance + requestedAmount);
				ps.setInt(2,this.userID);
				ps.execute();
				
				//INSERAM TRANZACTIA IN BAZA DE DATE
				ps = myConn.prepareStatement("INSERT INTO `transactions` (id_receiver,amount) VALUES (?,?)");
				ps.setInt(1,this.userID);
				ps.setFloat(2,requestedAmount); 
				ps.execute();
				
				//MODIFICAM VARIABLA DEOARECE CELE 2 ACTIUNI DE MAI SUS AU MERS
				this.userBalance += requestedAmount;
				
				//MODIFIC NOUL BALANCE AL USERULUI IN UI
				currentBalanceAmount.setText(Float.toString(this.userBalance) + "\u20AC");
				currentBalanceAmount.revalidate();
				currentBalanceAmount.repaint();
				
				//MODIFIC NOUL PANOU CU TRANZACTII IN UI  ////////////////////////////TREBUIE SA MA MAI GANDESC
				main.remove(transactionVisualiser);
				transactionVisualiser = new TransactionsVisualiser(getAndFormatTransactionListFromDatabase());
				transactionVisualiser.setLocation(75, 120);
				main.add(transactionVisualiser);
				
				//MODIFIC NOUL PANOU CU DATE DESPRE CONT IN UI
				main.remove(bottomData);
				bottomData = new BottomData(CalculateInOutInterestRate());
		        bottomData.setLocation(75, 570);
				main.add(bottomData);
				
				//REVALIDATE AND REPAINT CONTAINERUL 
				main.revalidate();
				main.repaint();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public ArrayList<Transaction> getAndFormatTransactionListFromDatabase() throws SQLException {
    	List <Transaction> transactionsList = new ArrayList<Transaction>(); 
    	rez = stmt.executeQuery("SELECT * FROM `transactions` WHERE id_giver=" + this.userID + " or " + "id_receiver=" + this.userID +"");
		while(rez.next()){
			float amount = rez.getFloat("amount");
			String data = rez.getString("data").substring(0,rez.getString("data").length() - 10);
			String typeOfTransaction = rez.getInt("id_giver") == this.userID ? "withdrawn":"deposit";
			String name = "";
			if(typeOfTransaction.equals("withdrawn")) { // daca userul a dat bani din cont
				rez2 = stmt2.executeQuery("SELECT * FROM `clients` WHERE id=" + rez.getInt("id_receiver") +"");
				rez2.next();
				name = rez2.getString("Prenume") + " " + rez2.getString("Nume");
			}
			else if(typeOfTransaction.equals("deposit") && rez.getInt("id_giver") == 0) { //inseamna ca am primit de la banca
				name = "bankitself";
				
			}
			else { //inseamna ca a primit de la cineva
				rez2 = stmt2.executeQuery("SELECT * FROM `clients` WHERE id=" + rez.getInt("id_giver") +"");
				rez2.next();
				name = rez2.getString("Prenume") + " " + rez2.getString("Nume");
			}
			transactionsList.add(new Transaction(name,typeOfTransaction,data,amount));
		}
		Collections.reverse(transactionsList);
    	return (ArrayList) transactionsList;
    }
    
    public ArrayList<Double> CalculateInOutInterestRate() throws SQLException{
    	double in = 0;
    	double out = 0;
    	double interestRate = 0; 
    	List <Transaction> transactions = getAndFormatTransactionListFromDatabase();
    	   	
    	for(Transaction transaction : transactions) {
    		if(transaction.getTypeOfTransaction().equals("withdrawn")) {
    			out += transaction.getSum();
    		}
    		else in += transaction.getSum();
    	}
    	interestRate = 1.5; // trebuie sa ma gandesc la o formula
    	
    	List <Double> informationReturned = new ArrayList<Double>();
    	informationReturned.add(in);
    	informationReturned.add(out);
    	informationReturned.add(interestRate);
    	return (ArrayList) informationReturned;
    }
}