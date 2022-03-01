package banking_app;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class AccountPage extends JPanel implements ActionListener {

	private HomeFrame parentFrame;
	private String firstName;
	private String lastName;
	private String IBAN;
	private String email;
	
	
	private JButton logOutBtn;
	private JButton generateBankStatementBtn;
	private JDatePickerImpl datePicker1;
	private JDatePickerImpl datePicker2;
	private JLabel statementGeneratorLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	private JLabel ibanLabel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	
	public AccountPage(HomeFrame parentFrame) {
		setBackground(new Color(245,245,245));
		this.parentFrame = parentFrame;
		initVariables();
		
		setLayout(null);
		JLabel accountPageTitle = new JLabel("Account Details");
		accountPageTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		accountPageTitle.setBounds(75, 38, 182, 54);
		add(accountPageTitle);
		
		//ADD -----> account details , change password
		
		logOutBtn = new JButton("Log out");
		logOutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOutBtn.setBackground(new Color(9,188,138));
		logOutBtn.setForeground(Color.WHITE);
		logOutBtn.setBorder(BorderFactory.createEmptyBorder());
		logOutBtn.setFocusable(false);
		logOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logOutBtn.setBounds(75, 530, 150, 45);
		logOutBtn.addActionListener(this);
		add(logOutBtn);
		
		generateBankStatementBtn = new JButton("Generate");
		generateBankStatementBtn.setBackground(new Color(9,188,138));
		generateBankStatementBtn.setForeground(Color.WHITE);
		generateBankStatementBtn.setBorder(BorderFactory.createEmptyBorder());
		generateBankStatementBtn.setFocusable(false);
		generateBankStatementBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		generateBankStatementBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generateBankStatementBtn.setBounds(419, 365, 150, 45);
		generateBankStatementBtn.addActionListener(this);
		add(generateBankStatementBtn);
		
		// FIRST DATE PICKER (CALENDAR ONE)
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker1 = new JDatePickerImpl(datePanel,new FormaterForDataPicker());
		datePicker1.setForeground(Color.BLACK);
		datePicker1.setButtonFocusable(false);
		datePicker1.setBackground(new Color(245,245,245));
		datePicker1.setBounds(375, 234, 230,27);
		add(datePicker1);
		
		// FIRST DATE PICKER (CALENDAR TWO)
		SqlDateModel model2 = new SqlDateModel();
		Properties p2 = new Properties();
		p2.put("text.day", "Day");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		datePicker2 = new JDatePickerImpl(datePanel2,new FormaterForDataPicker());
		datePicker2.setButtonFocusable(false);
		datePicker2.setBackground(new Color(245,245,245));
		datePicker2.setBounds(375, 310, 230,27);
		add(datePicker2);
		
		JLabel intervalStart = new JLabel("Interval Start");
		intervalStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		intervalStart.setBounds(375, 215, 126, 20);
		add(intervalStart);
		
		JLabel intervalEnd = new JLabel("Interval End");
		intervalEnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		intervalEnd.setBounds(375, 289, 102, 20);
		add(intervalEnd);
		
		statementGeneratorLabel = new JLabel("<html>Account Statement<br>Generator </html>");
		statementGeneratorLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		statementGeneratorLabel.setBounds(375, 160, 239, 45);
		add(statementGeneratorLabel);
		
		firstNameLabel = new JLabel("First Name*   " + this.firstName);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameLabel.setBounds(75, 209, 300, 32);
		add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last Name*   " + this.lastName);
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameLabel.setBounds(75, 267, 300, 32);
		add(lastNameLabel);
		
		emailLabel = new JLabel("Email*   " + this.email);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailLabel.setBounds(75, 323, 300, 32);
		add(emailLabel);
		
		ibanLabel = new JLabel("IBAN*   " + this.IBAN);
		ibanLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ibanLabel.setBounds(75, 376, 249, 32);
		add(ibanLabel);
		
		lblNewLabel = new JLabel("Informations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(75, 162, 199, 21);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AccountPage.class.getResource("/images/account-mascote.png")));
		lblNewLabel_1.setBounds(518, 160, 613, 558);
		add(lblNewLabel_1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logOutBtn) {
			AuthentificationFrame frame = new AuthentificationFrame();
			parentFrame.dispose(); // se inchide si conexiunea cu baza de date presupun
		}
		else if(e.getSource() == generateBankStatementBtn) {
			Date firstSelectedDate = (Date) datePicker1.getModel().getValue();
			Date lastSelectedDate = (Date) datePicker2.getModel().getValue();
			System.out.println(firstSelectedDate);
			System.out.println(lastSelectedDate);
			
			try {
				generateBankStatement(firstSelectedDate,lastSelectedDate);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	//FUNCTIONS
	
	public void initVariables() {
		Connection myConn = null;
		Statement stmt = null;
		ResultSet rez = null;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app","root","");
			stmt = myConn.createStatement();
			rez = stmt.executeQuery(
			"select clients.last_name, clients.first_name, clients.IBAN, users.Email from clients inner join users on clients.id = users.id_client and clients.id = "
			+ parentFrame.getUserID()
			);
			if(rez.next()) {
				this.email = rez.getString("Email");
				this.firstName = rez.getString("first_name");
				this.lastName = rez.getString("last_name");
				this.IBAN = rez.getString("IBAN");
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
	}
	
    public String formatSQLDateToString(Date date) {
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
    	String text = df.format(date);
		return text;
     }
	public void generateBankStatement(Date firstDate,Date lastDate) throws FileNotFoundException{
		
		int totalTransactions = 0;
		double totalAmountOut = 0;
		double totalAmountIn  = 0;
		
		//WRITE FIRST PART OF PDF (about account details)
        PdfWriter writer = new PdfWriter("D:\\extras_de_cont.pdf");
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc); 
                                   
        doc.add(new Paragraph("EXTRAS DE CONT").setFontSize(16f).setTextAlignment(TextAlignment.CENTER));
        doc.add(new Paragraph(formatSQLDateToString(firstDate) + "  -  " + formatSQLDateToString(lastDate)).setFontSize(8f).setMultipliedLeading(0f).setTextAlignment(TextAlignment.CENTER));
        doc.add(new Paragraph("\n\n"));
        doc.add(new Paragraph("Titular:           " + this.firstName + " " + this.lastName).setFontSize(12f));
        doc.add(new Paragraph("Iban:              " + this.IBAN).setFontSize(12f));
        doc.add(new Paragraph("Email:            " + this.email).setFontSize(12f));
        doc.add(new Paragraph("Period:            " + formatSQLDateToString(firstDate) + "  -  " + formatSQLDateToString(lastDate)));
        doc.add(new Paragraph("\n\n"));
        doc.add(new Paragraph("Transactions Details"));
        doc.add(new Paragraph("\n"));
        
        float [] pointColumnWidths = {150F,150F,150F,150F};   
        Table table = new Table(pointColumnWidths);
        table.addCell("Nume");       
        table.addCell("Data");
        table.addCell("Amount");       
        table.addCell("Type of transaction");
		
        //GET TRANSACTIONS FROM DATABASE AND ADD THEM IN PDF
		Connection myConn = null;
		Statement stmt = null;
		ResultSet rez = null;
		Statement stmt2 = null;
		ResultSet rez2 = null;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app","root","");
			stmt = myConn.createStatement();
			stmt2 = myConn.createStatement();
			
			rez = stmt.executeQuery(
			"SELECT * FROM `transactions` WHERE (id_giver=" + this.parentFrame.getUserID() + " or " + "id_receiver=" + this.parentFrame.getUserID() +")"
			+ " and `data` BETWEEN '" + firstDate + "' and '" + lastDate + "'");
			
			while(rez.next()){
				
				totalTransactions++;
				
				float amount = rez.getFloat("amount");
				String data = rez.getString("data").substring(0,rez.getString("data").length() - 10);
				String typeOfTransaction = rez.getInt("id_giver") == this.parentFrame.getUserID() ? "Withdrawn":"Deposit";
				String name = "";
				
				//DECIDE THE NAME OF THE OTHER PARTY OF TRANSACTION 
				//poate fi o persoana (in cazul unui transfer) sau banca insasi (imprumut)
				
				if(typeOfTransaction.equals("Withdrawn")) { // inseamna ca a dat bani cuiva
					rez2 = stmt2.executeQuery("SELECT * FROM `clients` WHERE id=" + rez.getInt("id_receiver") +"");
					rez2.next();
					name = rez2.getString("first_name") + " " + rez2.getString("last_name");
					
					totalAmountOut += amount;
					
				}
				else if(typeOfTransaction.equals("Deposit") && rez.getInt("id_giver") == 0) { //inseamna ca am primit de la banca
					name = "Banking Loan";
					totalAmountIn +=amount;
					
				}
				else { //inseamna ca a primit de la cineva
					rez2 = stmt2.executeQuery("SELECT * FROM `clients` WHERE id=" + rez.getInt("id_giver") +"");
					rez2.next();
					name = rez2.getString("first_name") + " " + rez2.getString("last_name");
					
					totalAmountIn += amount;
				}
				
				
				//WRITE DATA ABOUT THIS TRANSACTION IN PDF FILE
		        table.addCell(name);       
		        table.addCell(data);
		        table.addCell(String.valueOf(amount));       
		        table.addCell(typeOfTransaction);
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
		//ADD TABLE AFTER IT IS COMPLETED
        doc.add(table);
        doc.add(new Paragraph("\n\n"));
        doc.add(new Paragraph("Total tranzactii finalizate: "+ String.valueOf(totalTransactions)));
        doc.add(new Paragraph("Pierderi in acesta perioada: "+ String.valueOf(totalAmountOut)));
        doc.add(new Paragraph("Castiguri in aceasta perioada: "+ String.valueOf(totalAmountIn)));
        doc.add(new Paragraph("\n\n"));
        doc.add(new Paragraph("PREZENTUL DOCUMENT ESTE ELIBERAT DE GREENY BANK ROMANIA SI ARE VALOARE DE ORIGINAL FIIND VALABIL FARA SEMNATURA SI STAMPILA"));
        doc.add(new Paragraph("Soldul disponibil al zilei bancare inscris pe extrasul de cont reflecta situatia sumelor inregistrate in contul curent in momentul editarii extrasului de cont, in functie de obligatiile de plataale titularului de cont initiate sau evidentiate pana la momentul editarii extrasului de cont."));
        doc.close();
	}
}
