import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class LoginWin extends MouseAdapter implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lbllogid,lblpword,lblfogt,lblclid;
	JTextField txtlogid;
	JPasswordField ptxtpword;
	String login,login1,pword,pword1;
	JPanel ptitle,p1,p2;
	GridLayout glw42;
	FlowLayout flw;
	Connection conn;
	Statement stmtselect;
	ResultSet rsuser;
	Box vb,hb;
	JOptionPane msg;
	JButton btnlog,btncancel,btnfogt,btnclid;
public LoginWin(){
	f1=new JFrame();
	
	//panel 
	
	ptitle=new JPanel();
	p1=new JPanel();
	p2=new JPanel();	
	
	//Button
	
	//imgclose=new ImageIcon("close.png");
	btnlog=new JButton("Login");
	btncancel=new JButton("Cancel");
	btnlog.addActionListener(this);
	btncancel.addActionListener(this);
	btnlog.setToolTipText("User Login Request");
	btncancel.setToolTipText("To Cancel Current Work");
	//btnfogt.setBorderPainted(false);
	//btnclid.setBorderPainted(false);
	//Box
	
	vb=Box.createVerticalBox();
	hb=Box.createHorizontalBox();
	
	//Layout
	
	glw42=new GridLayout(4,2);
	flw=new FlowLayout();
	
	//JLabel
	
	lbltitle=new JLabel("Who You Are ?",JLabel.CENTER);
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbltitle.setForeground(new Color(204,0,0));
	lbllogid=new JLabel("Your Login ID :");
	lbllogid.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	lblclid=new JLabel("                         Sign Up");
	lblclid.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	lblfogt=new JLabel(" Forgot Password ");
	lblfogt.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	//lblorgn.setForeground(new Color(0,0,153));
	lblpword=new JLabel("Valid Password :");
	lblpword.setFont(new Font("Franklin Gothic Medium",Font.PLAIN,16));
	//lbladd1.setForeground(new Color(0,0,153));
	lblfogt.addMouseListener(this);
	lblclid.addMouseListener(this);
	lblfogt.setForeground(new Color(0,0,153));
	lblclid.setForeground(new Color(0,0,153));
	
	//JTextField
	
	txtlogid=new JTextField();
	ptxtpword=new JPasswordField();
	
	//Panel ptitle
	
	ptitle.setLayout(flw);
	ptitle.add(lbltitle);
	
	//Panel p1
	
	p1.setLayout(glw42);
	p1.add(lbllogid);
	p1.add(txtlogid);
	p1.add(lblpword);
	p1.add(ptxtpword);
	p1.add(new JLabel());
	p1.add(new JLabel());
	p1.add(lblfogt);
	p1.add(lblclid);
	
	//Panel p2
	
	p2.setLayout(flw);
	p2.add(btnlog);
	p2.add(btncancel);
	
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
public void mouseClicked(MouseEvent me){
	if(me.getSource()==lblfogt){
		ForgotWin fw;
        fw=new ForgotWin();
	}
	else if(me.getSource()==lblclid){
		 SignWin sw;
        sw=new SignWin();
	}
}
public void actionPerformed(ActionEvent ae){

	//btn to login
	
	if(ae.getSource()==btnlog){
		login=txtlogid.getText();
		pword=ptxtpword.getText();
		try{
			stmtselect=conn.createStatement();
			rsuser=stmtselect.executeQuery("select * from tbllogin where logid='"+login+"' and pword='"+pword+"'");
			if(rsuser.next()){
				//msg.showMessageDialog(f1,"Welcome","Message",1);
				TtpaMenuWin tmw;
				tmw=new TtpaMenuWin();
			}
			else{
				msg.showConfirmDialog(f1,"Invalid Login Id or Password","message",2);
			}
		}
		catch(SQLException ex){
			System.out.println("Unable to Fetch data");
		}
	
	}
	else if(ae.getSource()==btncancel){
		//code to cancel
		
		int choice;
		choice=msg.showConfirmDialog(f1,"Are You Sure to Cancel ?","Alert",2);
		if(choice==0){
		f1.setVisible(false);
		f1.dispose();
		}
		else{
			System.out.println("Welcome");
		}
	}
}
}//class ends
class LoginApp{
    public static void main(String args[]) throws Exception  
    {   
        LoginWin lw;
        lw=new LoginWin();
    }
}