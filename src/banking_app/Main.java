package banking_app;

import java.sql.*;
import javax.swing.*;  
public class Main {
 public static void main(String[] args) {

    /*JFrame f=new JFrame();//creating instance of JFrame  
    
    JButton b=new JButton("click");//creating instance of JButton  
    b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
              
    f.add(b);//adding button in JFrame  
              
    f.setSize(400,500);//400 width and 500 height  
    f.setLayout(null);//using no layout managers  
    f.setVisible(true);//making the frame visible  */
     
    //Login l = new Login();
    // DesignTest1 d = new DesignTest1();
	 
	
     System.setProperty("sun.java2d.uiScale", "1.0");

     try {
		HomeFrame home = new HomeFrame(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
    //AuthentificationFrame frame = new AuthentificationFrame();
 }
}