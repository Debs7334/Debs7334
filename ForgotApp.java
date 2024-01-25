import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class ForgotWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lbllogid,lblnewpswd,lblphint;
	JTextField txtlogid,txtphint,txtanshint;
	JPasswordField ptxtpword;
	String login,login1,hint,phint,pword,anshint;
	JPanel  ptitle,p1,p2;
	GridLayout glw32;
	FlowLayout flw;
	Connection conn;
	Statement stmtselect,stmtInsert,stmthint;
	ResultSet rsuser,rshint;
	Box vb,hb;
	JOptionPane msg;
	JButton btnreset,btncancel;
public ForgotWin(){
	f1=new JFrame();
	
	//panel 
	
	ptitle=new JPanel();
	p1=new JPanel();
	p2=new JPanel();	
	
	//Button
	
	//imgclose=new ImageIcon("close.png");
	btnreset=new JButton("Reset");
	btncancel=new JButton("Cancel");
	btnreset.addActionListener(this);
	btncancel.addActionListener(this);
	btnreset.setToolTipText("To Reset the New Password");
	btncancel.setToolTipText("To close the window");
	
	//Box
	
	vb=Box.createVerticalBox();
	hb=Box.createHorizontalBox();
	
	//Layout
	 
	glw32=new GridLayout(3,2);
	flw=new FlowLayout();
	
	//JLabel
	
	lbltitle=new JLabel("Forgot Password");
	lbltitle.setFont(new Font("Arial Black",Font.PLAIN,16));
	lbltitle.setForeground(new Color(204,0,0));
	lbllogid=new JLabel("Your Login ID :");
	lbllogid.setFont(new Font("Arial Black",Font.PLAIN,12));
	//lbllogid.setForeground(new Color(204,0,0));
	lblphint=new JLabel("Password Hint:");
	lblphint.setFont(new Font("Arial Black",Font.PLAIN,12));
	//lblphint.setForeground(new Color(204,0,0));
	lblnewpswd=new JLabel("Enter New Password :");
	lblnewpswd.setFont(new Font("Arial Black",Font.PLAIN,12));
	//lblnewpswd.setForeground(new Color(204,0,0));
	
	//JTextField
	
	txtlogid=new JTextField();
	txtlogid.setToolTipText("Enter Your Login ID");
	txtlogid.addActionListener(this);
	txtphint=new JTextField();
	//txtphint.setToolTipText("To close the window");
	txtanshint=new JTextField();
	txtanshint.setToolTipText("Enter the Answer of the Hint Question");
	ptxtpword=new JPasswordField();
	ptxtpword.setToolTipText("Enter the new Password");
	
	//panel ptitle
	
	ptitle.setLayout(flw);
	ptitle.add(lbltitle);
	
	//Panel p1
	
	p1.setLayout(glw32);
	p1.add(lbllogid);
	p1.add(txtlogid);
	p1.add(lblphint);
	JPanel p1a=new JPanel();
		p1a.setLayout(new GridLayout(1,2));
		p1a.add(txtphint);
		p1a.add(txtanshint);
		//p1a.add(btnclose);
	p1.add(p1a);
	p1.add(lblnewpswd);
	p1.add(ptxtpword);
	
	//Panel p2
	
	p2.setLayout(flw);
	p2.add(btnreset);
	p2.add(btncancel);

	// Vb Box
	
	vb.add(Box.createVerticalStrut(10));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(20));
	vb.add(p1);
	vb.add(Box.createVerticalStrut(10));
	vb.add(p2);
	vb.add(Box.createVerticalStrut(20));
	hb.add(Box.createHorizontalStrut(30));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(30));
	
	//Frame
	
	f1.setLayout(new BorderLayout());
	doconnect();
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(433,325,500,220);
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
	try{
		stmtselect=conn.createStatement();
		rsuser=stmtselect.executeQuery("select * from tbllogin");
	}
	catch(SQLException ex){
		System.out.println("Unable to Fetch data");
	}
} // doconnect ends here
public void actionPerformed(ActionEvent ae){
	
	if(ae.getSource()==btnreset){
		//code for Update	
		
		login=txtlogid.getText();
		anshint=txtanshint.getText();
		pword=ptxtpword.getText();
		if(login.length()==0||anshint.length()==0||pword.length()==0){
			msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
		}
		else{
			try{
				pword=ptxtpword.getText();
				stmtInsert=conn.createStatement();
				stmtInsert.executeUpdate("insert into tbllogin values where logid='"+login+"' && hintans='"+anshint+"'(pword='"+pword+"')");
				msg.showMessageDialog(f1,"Your Password is change Succesfully","Message",1);
				f1.setVisible(false);
				f1.dispose();
			}
			catch(SQLException ex){
				msg.showMessageDialog(f1,"Unable to Change","Message",1);
				System.out.println("Unable to Update"+ex);
			}
		}
	}
	else if(ae.getSource()==txtlogid){
		login=txtlogid.getText();
		try{
			stmthint=conn.createStatement();
			rshint=stmthint.executeQuery("select * from tbllogin where logid='"+login+"'");
			if(rshint.next()){
				hint=rshint.getString("hint");
				txtphint.setText(hint);
			}
		}
		catch(SQLException ex){
			System.out.println("Unable to Show Data");
		}	
	} 
	else if(ae.getSource()==btncancel){
		f1.setVisible(false);
				f1.dispose();
	}
}
}//class ends
class ForgotApp{
    public static void main(String args[]) throws Exception  
    {   
        ForgotWin fw;
        fw=new ForgotWin();
    }
}