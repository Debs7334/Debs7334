import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class SignWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lbllogid,lblpword,lblphint,lblanshint;
	JTextField txtlogid,txtpword,txtanshint;
	//JPasswordField ptxtpword;
	String login,login1,phint,anshint,pword,pword1;
	JPanel ptitle,p1,p2;
	GridLayout glw42;
	FlowLayout flw;
	JComboBox cmbphint;
	Connection conn;
	Statement stmtselect,stmtsearch,stmtInsert;
	ResultSet rsuser,rsdup;
	Box vb,hb;
	JOptionPane msg;
	JButton btnsave,btnclose;
	String phint1[]={"No Choice","Name of Your Pet ?","Your Mother Nick Name ?","Your Father Nick Name ?","Name of your School Bestfriend ?"};
public SignWin(){
	f1=new JFrame();
	
	//panel 
	
	ptitle=new JPanel();
	p1=new JPanel();
	p2=new JPanel();	
	
	//combobox
	
	cmbphint=new JComboBox(phint1);
	
	//Button
	
	//imgclose=new ImageIcon("close.png");
	btnsave=new JButton("Save");
	btnsave.addActionListener(this);
	btnclose=new JButton("Close");
	btnclose.addActionListener(this);
	
	//Box
	
	vb=Box.createVerticalBox();
	hb=Box.createHorizontalBox();
	
	//Layout
	
	glw42=new GridLayout(4,2);
	flw=new FlowLayout();
	
	//JLabel
	
	lbltitle=new JLabel("Sign Up",JLabel.CENTER);
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbltitle.setForeground(new Color(204,0,0));
	lbllogid=new JLabel("Your Login ID :");
	lbllogid.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	lblpword=new JLabel("Valid Password :");
	lblpword.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	lblphint=new JLabel("Password Forgot Hint :");
	lblphint.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	lblanshint=new JLabel("Answer of the Hint :");
	lblanshint.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	
	//JTextField
	
	txtlogid=new JTextField();
	txtpword=new JTextField();
	txtanshint=new JTextField();
	
	//Panel ptitle
	
	ptitle.setLayout(flw);
	ptitle.add(lbltitle);
	
	//Panel p1
	
	p1.setLayout(glw42);
	p1.add(lbllogid);
	p1.add(txtlogid);
	p1.add(lblpword);
	p1.add(txtpword);
	p1.add(lblphint);
	p1.add(cmbphint);
	p1.add(lblanshint);
	p1.add(txtanshint);
	
	//Panel p2
	
	p2.setLayout(flw);
	p2.add(btnsave);
	p2.add(btnclose);
	
	// Vb Box
	
	vb.add(Box.createVerticalStrut(10));
	vb.add(ptitle);
	vb.add(p1);
	vb.add(Box.createVerticalStrut(10));
	vb.add(p2);
	vb.add(Box.createVerticalStrut(10));
	hb.add(Box.createHorizontalStrut(30));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(30));
	
	//Frame
	
	f1.setLayout(new BorderLayout());
	doconnect();
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(433,283,500,250);
	f1.setVisible(true);
	f1.setResizable(false);
	
}
public void doconnect(){
	try{
		Class.forName("com.mysql.jdbc.Driver");
	}
	catch(ClassNotFoundException cnfe){
		System.out.println("Unable to load Driver");
	}
	try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ttpadb","root","root");
	}
	catch(SQLException se){
		System.out.println("Unable to connect");
	}   
} // doconnect ends here
public void actionPerformed(ActionEvent ae){
	if(ae.getSource()==btnsave){
		//btn to save
	
		try{
			String logid2;
			logid2=txtlogid.getText();
			stmtsearch=conn.createStatement();
			rsdup=stmtsearch.executeQuery("select * from tbllogin where logid='"+logid2+"'");
			if(rsdup.next()){
				msg.showMessageDialog(f1,"The Login ID is Alredy Registered ","Alert",2);
			}
			else{
				login=txtlogid.getText(); 
				pword=txtpword.getText();
				anshint=txtanshint.getText();
					if(login.length()==0||pword.length()==0||anshint.length()==0){
						msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
					}
					else{
						int choice;
						choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
						if(choice==0){
							login=txtlogid.getText(); 
							pword=txtpword.getText();
							anshint=txtanshint.getText();
							phint=cmbphint.getSelectedItem().toString();
							
							try{
								stmtInsert=conn.createStatement();
								stmtInsert.executeUpdate("insert into tbllogin values('"+login+"','"+pword+"','"+phint+"','"+anshint+"')");
								//System.out.println("One Record Saved Succesfully");
								msg.showMessageDialog(f1,"Your Login ID & Password is Saved Succesfully","Message",1);
							}
							catch(SQLException se){
								System.out.println(se);
								msg.showMessageDialog(f1,"Unable to Save","Message",1);
							}
						}
						else{
							//System.out.println("Recorrect Your Inputs");
							msg.showMessageDialog(f1,"Recorrect Your Inputs","Message",1);
						}
					}
				}
			}
		catch(SQLException se){
			System.out.println(se);
		}
	}//end of ifelse
	else if(ae.getSource()==btnclose){
		//code to close
		
		int choice;
		choice=msg.showConfirmDialog(f1,"Are You Sure to Cancel ?","Alert",2);
		if(choice==0){
		f1.setVisible(false);
		f1.dispose();
		}
		else{
			//System.out.println("Welcome");
			msg.showMessageDialog(f1,"Welcome","Message",1);
		}
	}
}
}//class ends
class SignApp{
    public static void main(String args[]) throws Exception  
    {   
        SignWin sw;
        sw=new SignWin();
    }
}