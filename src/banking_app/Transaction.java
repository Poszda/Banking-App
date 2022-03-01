package banking_app;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Transaction extends JPanel {

	private String name;
	private String typeOfTransaction;
	private String data;
	private double sum;

	public Transaction(String name, String typeOfTransaction, String data, double sum) {
		this.name = name;
		this.typeOfTransaction = typeOfTransaction;
		this.data = data;
		this.sum = sum;
				
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(32767, 60));
		setMinimumSize(new Dimension(32767, 60));
		setPreferredSize(new Dimension(555, 60));
		setLayout(null);
		
		JLabel transactionSum = new JLabel(stringifySum());
		transactionSum.setBackground(new Color(240, 240, 240));
		transactionSum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		transactionSum.setHorizontalAlignment(SwingConstants.CENTER);
		transactionSum.setBounds(460, 10, 65, 40);
		add(transactionSum);
		
		JLabel transactionIcon = new JLabel("");
		transactionIcon.setIcon(new ImageIcon(Transaction.class.getResource(obtainTransactionIcon())));
		transactionIcon.setHorizontalAlignment(SwingConstants.CENTER);
		transactionIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		transactionIcon.setBackground(SystemColor.menu);
		transactionIcon.setBounds(32, 10, 40, 40);
		add(transactionIcon);
		
		Border blackline = BorderFactory.createLineBorder(new Color(245,245,245));
		setBorder(blackline);
		
		JLabel transactionInvolvedClient = new JLabel(obtainTransactionInvolvedClient());
		transactionInvolvedClient.setHorizontalAlignment(SwingConstants.LEFT);
		transactionInvolvedClient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		transactionInvolvedClient.setBackground(SystemColor.menu);
		transactionInvolvedClient.setBounds(100, 22, 198, 19);
		add(transactionInvolvedClient);
		
		JLabel transactionType = new JLabel(capitalise(this.typeOfTransaction));
		transactionType.setForeground(Color.GRAY);
		transactionType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transactionType.setBounds(100, 7, 163, 14);
		add(transactionType);
		
		JLabel transactionData = new JLabel(this.data);
		transactionData.setForeground(Color.GRAY);
		transactionData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transactionData.setBounds(280, 24, 163, 12);
		add(transactionData);
	}
	
	public String stringifySum() {
		if(this.typeOfTransaction.equals("withdrawn"))
			return("-" + sum + "\u20AC");
		return(Double.toString(sum) + "\u20AC");
	}
	public String obtainTransactionIcon() {
		if(this.typeOfTransaction.equals("withdrawn"))
			return("/images/withdrawnx_red.png");
		return("/images/depositx.png");
	}
	public String obtainTransactionInvolvedClient() {
		if(this.typeOfTransaction.equals("withdrawn"))
			return ("Sent to " + this.name);
		else if(this.typeOfTransaction.equals("deposit") && !this.name.equals("bankitself")) // poate nu merge
			return ("From " + this.name);
		return ("Bank Loan");
	}
	public String capitalise(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	@Override
	public String toString() {
		return "Transaction [name=" + name + ", typeOfTransaction=" + typeOfTransaction + ", data=" + data + ", sum="
				+ sum + "]";
	}
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public String getData() {
		return data;
	}
	public double getSum() {
		return sum;
	}
	
}
