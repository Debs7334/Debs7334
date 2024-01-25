import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RptIndustryWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lblorgn,lbladd1,lbladd2;
	JPanel p0,p1;
	GridLayout glw41;
	Connection conn;
	Statement stmtselect;
	ResultSet rsuser;
	Box vb;
	Object objdata[][];
	JTable jtbldata;
	JScrollPane jspdata;
	String colhead[]={"Serial No.","Registation Number","Established Year","Name Of Industry","Head Of Industry","Locality","City","State","Phone Number","E-mail ID","GST Number","GST Date"};
	int slno,rw,tot;
	JButton btnclose;
	ImageIcon imgclose;
	JOptionPane msg;
public RptIndustryWin(){
	f1=new JFrame();
	p0=new JPanel();
	p1=new JPanel();
	vb=Box.createVerticalBox();
	imgclose=new ImageIcon("close.png");
	btnclose=new JButton("Close",imgclose);
	btnclose.addActionListener(this);
	btnclose.setToolTipText("To Close the Frame");
	glw41=new GridLayout(4,1);
	lbltitle=new JLabel("INDUSTRY  PROFILE",JLabel.CENTER);
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbltitle.setForeground(new Color(204,0,0));
	lblorgn=new JLabel(" DD  Group  of  Industries");
	lblorgn.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lblorgn.setForeground(new Color(0,0,153));
	lbladd1=new JLabel(" Industrial  Estate, Jagatpur");
	lbladd1.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lbladd1.setForeground(new Color(0,0,153));
	lbladd2=new JLabel(" Cuttack - 754021");
	lbladd2.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lbladd2.setForeground(new Color(0,0,153));
	p0.setLayout(new FlowLayout(FlowLayout.CENTER));
	p1.setLayout(glw41);
	p0.add(lbltitle);
	p1.add(lblorgn);
	p1.add(lbladd1);
	p1.add(lbladd2);
	vb.add(Box.createVerticalStrut(10));
	vb.add(p0);
	//vb.add(Box.createVerticalStrut(10));
	vb.add(p1);
	//vb.add(Box.createVerticalStrut(10));
	doconnect();
	jtbldata=new JTable(objdata,colhead);
	jspdata=new JScrollPane(jtbldata);
	f1.setLayout(new BorderLayout());
	f1.add(vb,BorderLayout.NORTH);
	f1.add(btnclose,BorderLayout.SOUTH);
	f1.add(jspdata,BorderLayout.CENTER);
	f1.setBounds(1,20,1366,728);
	f1.setVisible(true);
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
		stmtselect=conn.createStatement();
		rsuser=stmtselect.executeQuery("select * from tblindustry");
		rsuser.last();
		tot=rsuser.getRow();
		objdata=new Object[tot][12];
		slno=1;
		rw=0;
		rsuser.beforeFirst();
		while(rsuser.next()){
			objdata[rw][0]=slno;
			objdata[rw][1]=rsuser.getString("rgno");
			objdata[rw][2]=rsuser.getString("eyr");
			objdata[rw][3]=rsuser.getString("nmoi");
			objdata[rw][4]=rsuser.getString("hoi");
			objdata[rw][5]=rsuser.getString("loc");
			objdata[rw][6]=rsuser.getString("city");
			objdata[rw][7]=rsuser.getString("state");
			objdata[rw][8]=rsuser.getString("pno");
			objdata[rw][9]=rsuser.getString("mail");
			objdata[rw][10]=rsuser.getString("gstno");
			objdata[rw][11]=rsuser.getString("gstdt");
			slno=slno+1;
			rw=rw+1;
		}
	}//try ends
	catch(SQLException se){
		System.out.println("Unable to connect");
	}   
} // doconnect ends here
public void actionPerformed(ActionEvent ae){
	if(ae.getSource()==btnclose){
		
		//code to close
		
		int choice;
		choice=msg.showConfirmDialog(f1,"Are You Sure to Close ?","Alert",2);
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
class RptIndustryApp{
    public static void main(String args[]) throws Exception  
    {   
        RptIndustryWin riw;
        riw=new RptIndustryWin();
    }
}