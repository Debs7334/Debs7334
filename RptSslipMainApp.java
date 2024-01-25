import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RptSslipWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lblorgn,lbladd1,lbladd2,lblslpno,lblslpval,lbldt,lbldtval,lblmonth,lblmonthval,lblyear,lblyearval,lblempnm,lblempnmval,lblempid,lblempidval,lbldept,lbldeptval,lblshift,lblshiftval,lblbsal,lblbsalval,lblalwance,lbldeduction,lbldaam,lbldaval,lblpfam,lblpfval,lblhraam,lblhraval,lbltaxam,lbltaxval,lblgsal,lblgsalval,lblnetsal,lblnetsalval,lbllsttitle,lblsign,lblsignval,lbluline;
	String slpno,dt,month,year,empnm,empid,dept,shift,bsal,da,pf,hra,tax,gsal,netsal,mmyy;
	String my[];
	JPanel p0,p1,p2,p3,p4,p5,puline1,puline2,puline3,pbtn;
	GridLayout glw41,glw24,glw44,glw91;
	Connection conn;
	Statement stmtselect;
	ResultSet rsuser;
	Box vb;
	Object objdata[][];
	JTable jtbldata;
	JScrollPane jspdata;
	int slno,rw,tot;
	JOptionPane msg;
	JButton btnclose,btnshow;
	ImageIcon imgclose;
public RptSslipWin(){
	f1=new JFrame();
	
	//panel 
	
	p0=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p5=new JPanel();
	puline1=new JPanel();
	puline2=new JPanel();
	puline3=new JPanel();
	pbtn=new JPanel();
	
	//Button
	
	imgclose=new ImageIcon("close.png");
	btnclose=new JButton("Close",imgclose);
	btnclose.addActionListener(this);
	btnclose.setToolTipText("To Close the Frame");
	
	//Box
	
	vb=Box.createVerticalBox();
	
	//Layout
	
	glw41=new GridLayout(4,1,5,10);
	glw24=new GridLayout(2,4,5,20);
	glw44=new GridLayout(4,4,5,10);
	glw91=new GridLayout(9,1,5,10);
	
	//JLabel
	
	lbltitle=new JLabel("  SALARY  SLIP ",JLabel.CENTER);
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbltitle.setForeground(new Color(204,0,0));
	lblorgn=new JLabel(" DD  Group  of  Industries",JLabel.CENTER);
	lblorgn.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lblorgn.setForeground(new Color(0,0,153));
	lbladd1=new JLabel(" Industrial  Estate, Jagatpur",JLabel.CENTER);
	lbladd1.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lbladd1.setForeground(new Color(0,0,153));
	lbladd2=new JLabel(" Cuttack - 754021, Odisha",JLabel.CENTER);
	lbladd2.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lbladd2.setForeground(new Color(0,0,153));
	
	lblslpno=new JLabel("Slip No.: ");
	lblslpval=new JLabel("");
	lbldt=new JLabel("Date : ");
	lbldtval=new JLabel("");
	lblmonth=new JLabel("Month of : ");
	lblmonthval=new JLabel("");
	lblyear=new JLabel("Year: ");
	lblyearval=new JLabel("");
	lblempnm=new JLabel("Emplyoee Name : ");
	lblempnmval=new JLabel("");
	lblempid=new JLabel("Employee ID : ");
	lblempidval=new JLabel("");
	lbldept=new JLabel("Department : ");
	lbldeptval=new JLabel("");
	lblshift=new JLabel("Shift : ");
	lblshiftval=new JLabel("");
	lblbsal=new JLabel("Basic Salary : ");
	lblbsalval=new JLabel("");
	lblalwance=new JLabel("Allowance :- ");
	lbldeduction=new JLabel("Deduction :- ");
	lbldaam=new JLabel("DA Amount : ");
	lbldaval=new JLabel("");
	lblpfam=new JLabel("PF Amount : ");
	lblpfval=new JLabel("");
	lblhraam=new JLabel("HRA Amount : ");
	lblhraval=new JLabel("");
	lbltaxam=new JLabel("TAX : ");
	lbltaxval=new JLabel("");
	lblgsal=new JLabel("Gross Salary : ");
	lblgsalval=new JLabel("");
	lblnetsal=new JLabel("Net Salary : ");
	lblnetsalval=new JLabel("");
	lbllsttitle=new JLabel("For DD  Group  of  Industries ",JLabel.LEFT);
	lblsign=new JLabel("Signature: ");
	lblsignval=new JLabel("");

	//Panel p0
	
	p0.setLayout(new GridLayout(2,1));
	p0.add(lbltitle);
	JPanel p0a;
		p0a=new JPanel();
		p0a.setLayout(new GridLayout(3,1));
		p0a.add(lblorgn);
		p0a.add(lbladd1);
		p0a.add(lbladd2);
	p0.add(p0a);
	
	//Panel puline1
	puline1.setLayout(new FlowLayout());
	puline1.add(new JLabel("________________________________________________________________________________________________________________________________"));
	
	//Panel p1
	
	p1.setLayout(glw24);
	p1.add(lblslpno);
	p1.add(lblslpval);
	p1.add(lbldt);
	p1.add(lbldtval);
	p1.add(lblmonth);
	p1.add(lblmonthval);
	p1.add(lblyear);
	p1.add(lblyearval);
	
	//Panel puline2
	puline2.setLayout(new FlowLayout());
	puline2.add(new JLabel("________________________________________________________________________________________________________________________________"));
	
	//Panel p2
	
	p2.setLayout(glw24);
	p2.add(lblempnm);
	p2.add(lblempnmval);
	p2.add(lblempid);
	p2.add(lblempidval);
	p2.add(lbldept);
	p2.add(lbldeptval);
	p2.add(lblshift);
	p2.add(lblshiftval);
	
	//Panel p3
	
	p3.setLayout(glw44);
	p3.add(lblbsal);
	p3.add(lblbsalval);
	p3.add(new JLabel(""));
	p3.add(new JLabel(""));
	p3.add(lblalwance);
	p3.add(new JLabel(""));
	p3.add(lbldeduction);
	p3.add(new JLabel(""));
	p3.add(lbldaam);
	p3.add(lbldaval);
	p3.add(lblpfam);
	p3.add(lblpfval);
	p3.add(lblhraam);
	p3.add(lblhraval);
	p3.add(lbltaxam);
	p3.add(lbltaxval);
	
	//Panel p4
	
	p4.setLayout(glw24);
	p4.add(lblgsal);
	p4.add(lblgsalval);
	p4.add(new JLabel(""));
	p4.add(new JLabel(""));
	p4.add(lblnetsal);
	p4.add(lblnetsalval);
	p4.add(new JLabel(""));
	p4.add(new JLabel(""));
	
	//Panel puline3
	puline3.setLayout(new FlowLayout());
	puline3.add(new JLabel("________________________________________________________________________________________________________________________________"));
	
	
	//Panel p5
	
	p5.setLayout(new GridLayout(2,1));
	p5.add(lbllsttitle);
	JPanel p5a;
		p5a=new JPanel();
		p5a.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p5a.add(lblsign);
		p5a.add(lblsignval);
	p5.add(p5a);	
	
	//panel pbtn
	
	pbtn.setLayout(new FlowLayout());
	pbtn.add(btnclose);
	
	// Vb Box
	
	vb.add(Box.createVerticalStrut(10));
	vb.add(p0);
	vb.add(puline1);
	//vb.add(Box.createVerticalStrut(30));
	vb.add(p1);
	vb.add(puline2);
	vb.add(p2);
	vb.add(Box.createVerticalStrut(20));
	vb.add(p3);
	vb.add(Box.createVerticalStrut(20));
	vb.add(p4);
	vb.add(Box.createVerticalStrut(20));
	vb.add(puline3);
	vb.add(p5);
	vb.add(pbtn);
	vb.add(Box.createVerticalStrut(10));

	//Frame
	
	//jtbldata=new JTable(objdata,colhead);
	//jspdata=new JScrollPane(jtbldata);
	f1.setLayout(new FlowLayout());
	doconnect();
	f1.add(vb);
	f1.setBackground(new Color(255,255,153));
	//f1.add(btnclose,BorderLayout.SOUTH);
	//f1.add(jspdata,BorderLayout.CENTER);
	f1.setBounds(183,58,1000,650);
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
		String slpno1;
		slpno1=msg.showInputDialog(f1,"Enter Shift Code to Find Record","Message",1);
		if(slpno1!=null){
			System.out.println("Data "+slpno1.length());
			try{
				stmtselect=conn.createStatement();
				rsuser=stmtselect.executeQuery("select * from tblsalaryslip where slpno='"+slpno1+"'");
				rsuser.next();
				
				//getText
				
				slpno=rsuser.getString("slpno");
				dt=rsuser.getString("daten");
				mmyy=rsuser.getString("mmyyyy");
				my=mmyy.split("/");
				month=my[0];
				year=my[1];		
				empnm=rsuser.getString("empnm");
				empid=rsuser.getString("empid");
				dept=rsuser.getString("dpnm");
				shift=rsuser.getString("shnm");
				bsal=rsuser.getString("bsal");
				da=rsuser.getString("daam");
				pf=rsuser.getString("pfam");
				hra=rsuser.getString("hraam");
				tax=rsuser.getString("taxam");
				gsal=rsuser.getString("gsal");
				netsal=rsuser.getString("nsal");
				
				//setText
				
				lblslpval.setText(slpno);
				lbldtval.setText(dt);
				lblmonthval.setText(month);
				lblyearval.setText(year);
				lblempnmval.setText(empnm);
				lblempidval.setText(empid);
				lbldeptval.setText(dept);
				lblshiftval.setText(shift);
				lblbsalval.setText(bsal);
				lbldaval.setText(da);
				lblpfval.setText(pf);
				lblhraval.setText(hra);
				lbltaxval.setText(tax);
				lblgsalval.setText(gsal);
				lblnetsalval.setText(netsal);
				}
			catch(SQLException se){
				System.out.println(se);
				msg.showMessageDialog(f1,"Enter the Propore Data","Alert",1);
			}
		}
		else{
			System.out.println("Enter the Propore Data");
			msg.showMessageDialog(f1,"Enter the Propore Data","Alert",1);
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
class RptSslipMainApp{
    public static void main(String args[]) throws Exception  
    {   
        RptSslipWin rssw1;
        rssw1=new RptSslipWin();
    }
}