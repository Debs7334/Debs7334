import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RptSalSlipWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lblorgn,lbladd1,lbladd2,lblslip;
	JPanel p0,p1;
	GridLayout glw41;
	Connection conn;
	Statement stmtselect,stmtslip;
	ResultSet rsuser,rsslip;
	Box vb;
	JComboBox cmbslip;
	Object objdata[][];
	JTable jtbldata;
	JScrollPane jspdata;
	String slp,slip;
	String slip1[]={"-Select-"};
	String colhead[]={"Serial No.","Slip No.","Date","Month & Year","Employee ID","Employee Name","Gender","Phone Number","E-Mail ID","Department Name","Shift Name","Basic Salary","DA(%)","DA Amount","PF(%)","PF Amount","HRA(%)","HRA Amout","TAX(%)","TAX Amount","Gross Salary","Net Salary"};
	int slno,rw,tot;
	JButton btnclose,btnshow;
	ImageIcon imgclose;
	JOptionPane msg;
public RptSalSlipWin(){
	f1=new JFrame();
	p0=new JPanel();
	p1=new JPanel();
	vb=Box.createVerticalBox();
	imgclose=new ImageIcon("close.png");
	btnclose=new JButton("Close",imgclose);
	btnshow=new JButton("Show");
	btnshow.addActionListener(this);
	btnclose.addActionListener(this);
	btnclose.setToolTipText("To Close the Frame");
	btnshow.setToolTipText("To Show Salary Slip Slp Number wise");
	glw41=new GridLayout(4,1);
	cmbslip=new JComboBox(slip1);//JComboBox
	lbltitle=new JLabel("Salary Slip Generation",JLabel.CENTER);
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
	lblslip=new JLabel("Select Leave Type : ");
	lblslip.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lblslip.setForeground(new Color(0,0,153));
	p0.setLayout(new FlowLayout(FlowLayout.CENTER));
	p0.add(lbltitle);
	p1.setLayout(glw41);
	p1.add(lblorgn);
	p1.add(lbladd1);
	p1.add(lbladd2);
	JPanel p1a;
		p1a=new JPanel();
		p1a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1a.add(lblslip);
        p1a.add(cmbslip);
        p1a.add(btnshow);
	p1.add(p1a);
	vb.add(Box.createVerticalStrut(10));
	vb.add(p0);
	//vb.add(Box.createVerticalStrut(10));
	vb.add(p1);
	//vb.add(Box.createVerticalStrut(10));
	doconnect();
	fillslipcombo();
	f1.setLayout(new BorderLayout());
	f1.add(vb,BorderLayout.NORTH);
	f1.add(btnclose,BorderLayout.SOUTH);
	
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
	}//try ends
	catch(SQLException se){
		System.out.println("Unable to connect");
	}   
} // doconnect ends here
public void fillslipcombo(){ 
	try{
		stmtslip=conn.createStatement();
		rsslip=stmtslip.executeQuery("select * from tblsalaryslip");
		while(rsslip.next()){
			slp=rsslip.getString("slpno");
			cmbslip.addItem(slp);
		}
	}
	catch(SQLException ex){
		System.out.println("Unable to create Shift Combo");
	}
}
public void actionPerformed(ActionEvent ae){
	if(ae.getSource()==btnshow){
		
		//code to show
		
		slip=cmbslip.getSelectedItem().toString();
		try{
		stmtselect=conn.createStatement();
		rsuser=stmtselect.executeQuery("select * from tblsalaryslip where slpno='"+slip+"'");
		rsuser.last();
		tot=rsuser.getRow();
		objdata=new Object[tot][22];
		slno=1;
		rw=0;
		rsuser.beforeFirst();
		while(rsuser.next()){
			objdata[rw][0]=slno;
			objdata[rw][1]=rsuser.getString("slpno");
			objdata[rw][2]=rsuser.getString("daten");
			objdata[rw][3]=rsuser.getString("mmyyyy");
			objdata[rw][4]=rsuser.getString("empid");
			objdata[rw][5]=rsuser.getString("empnm");
			objdata[rw][6]=rsuser.getString("gender");
			objdata[rw][7]=rsuser.getString("phno");
			objdata[rw][8]=rsuser.getString("mail");
			objdata[rw][9]=rsuser.getString("dpnm");
			objdata[rw][10]=rsuser.getString("shnm");
			objdata[rw][11]=rsuser.getString("bsal");
			objdata[rw][12]=rsuser.getString("da");
			objdata[rw][13]=rsuser.getString("daam");
			objdata[rw][14]=rsuser.getString("pf");
			objdata[rw][15]=rsuser.getString("pfam");
			objdata[rw][16]=rsuser.getString("hra");
			objdata[rw][17]=rsuser.getString("hraam");
			objdata[rw][18]=rsuser.getString("tax");
			objdata[rw][19]=rsuser.getString("taxam");
			objdata[rw][20]=rsuser.getString("gsal");
			objdata[rw][21]=rsuser.getString("nsal");
			slno=slno+1;
			rw=rw+1;
			}
		}
		catch(SQLException se){
			System.out.println(se);
		}
		jtbldata=new JTable(objdata,colhead);
		jspdata=new JScrollPane(jtbldata);
		f1.add(jspdata,BorderLayout.CENTER);
		f1.setVisible(true);
	}
	else if(ae.getSource()==btnclose){
		
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
class RptSalSlipApp{
    public static void main(String args[]) throws Exception  
    {   
        RptSalSlipWin rssw;
        rssw=new RptSalSlipWin();
    }
}