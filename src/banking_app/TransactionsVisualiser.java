package banking_app;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TransactionsVisualiser extends JPanel {
	List <Transaction> transactionsList = new ArrayList<Transaction>();

	public TransactionsVisualiser(List <Transaction> transactionsList) {
		this.transactionsList = transactionsList;
		//System.out.println(this.transactionsList);
		/*transactionsList.add(new Transaction("Mihai Posdarescu","withdrawn","11/08/2023",100));
		transactionsList.add(new Transaction("Alexandru Aleno","deposit","12/09/2023",50));
		transactionsList.add(new Transaction("bankitself","deposit","11/08/2023",100));
		transactionsList.add(new Transaction("Alexandru Aleno","withdrawn","12/09/2023",50));
		transactionsList.add(new Transaction("Mihai Posdarescu","withdrawn","11/08/2023",100));
		transactionsList.add(new Transaction("Alexandru Aleno","withdrawn","12/09/2023",50));
		 */
		setBounds(0, 0, 575, 400); //  unde o pun
		setLayout(null);
		
		JPanel transactionsContainer = new JPanel();
		transactionsContainer.setBackground(Color.RED);
		transactionsContainer.setLayout(new BoxLayout(transactionsContainer, BoxLayout.Y_AXIS));
		
		for(Transaction transaction : transactionsList ) {
			transactionsContainer.add(transaction);
		}
		
		JScrollPane scrollPane = new JScrollPane(transactionsContainer);
		scrollPane.setBorder(null);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(575, 400);
		scrollPane.setBackground(Color.PINK);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		add(scrollPane);
	}
}
