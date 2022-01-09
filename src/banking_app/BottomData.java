package banking_app;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomData extends JPanel {

	/**
	 * Create the panel.
	 */
	private double in;
	private double out;
	private double interestRate;
	public BottomData(List <Double> informationReturned) {

		this.in = informationReturned.get(0);
		this.out = informationReturned.get(1);
		this.interestRate = informationReturned.get(2);
        setBounds(0, 0, 575, 30);
        setLayout(null);
        setBackground(new Color(245,245,245));
        
        JLabel in = new JLabel("IN");
        in.setBounds(0, 10, 13, 15);
        in.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add(in);
        
        JLabel inAmount = new JLabel(this.in + " \u20AC");
        inAmount.setForeground(new Color(9, 188, 138));
        inAmount.setFont(new Font("Tahoma", Font.PLAIN, 24));
        inAmount.setBounds(27, 0, 106, 24);
        add(inAmount);
        
        JLabel out = new JLabel("OUT");
        out.setFont(new Font("Tahoma", Font.PLAIN, 12));
        out.setBounds(143, 11, 35, 13);
        add(out);
        
        JLabel outAmount = new JLabel(this.out + " \u20AC");
        outAmount.setForeground(Color.RED);
        outAmount.setFont(new Font("Tahoma", Font.PLAIN, 24));
        outAmount.setBounds(188, 0, 123, 24);
        add(outAmount);
        
        JLabel interest = new JLabel("INTEREST");
        interest.setFont(new Font("Tahoma", Font.PLAIN, 12));
        interest.setBounds(321, 11, 61, 13);
        add(interest);
        
        JLabel interestRate = new JLabel(this.interestRate + "");
        interestRate.setForeground(new Color(9, 188, 138));
        interestRate.setFont(new Font("Tahoma", Font.PLAIN, 24));
        interestRate.setBounds(392, 0, 92, 24);
        add(interestRate);
	}

}
