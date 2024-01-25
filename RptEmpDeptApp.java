import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RptEmpDeptWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lblorgn,lbladd1,lbladd2,lbldept;
	JPanel p0,p1;
	GridLayout glw41;
	Connection conn;
	Statement stmtselect,stmtdept;
	ResultSet rsuser,rsdept;
	Box vb;
	JComboBox cmbdept;
	Object objdata[][];
	JTable jtbldata;
	JScrollPane jspdata;
	String dept,dpnm;
	String dept1[]={"-Select-"};
	String colhead[]={"Serial No.","Employee ID","Employee Name","Gender","Date Of Birth","Father Name","Locality","City","State/U.Tertiary","Pin Code","Phone Number","E-Mail ID","Department Name","Date Of Join","Shift Name","Start Time","End Time","Basic Salary","DA in Percentage","PF in Percentage","HRA in Percentage","Tax in Percentage"};
	int slno,rw,tot;
	JButton btnclose,btnshow;
	ImageIcon imgclose;
	JOptionPane msg;
public RptEmpDeptWin(){
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
	btnshow.setToolTipText("To Show Employee Information Department wise");
	glw41=new GridLayout(4,1);
	cmbdept=new JComboBox(dept1);//JComboBox
	lbltitle=new JLabel("EMPLOYEE  INFORMATION",JLabel.CENTER);
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
	lbldept=new JLabel("Select Department: ");
	lbldept.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lbldept.setForeground(new Color(0,0,153));
	p0.setLayout(new FlowLayout(FlowLayout.CENTER));
	p0.add(lbltitle);
	p1.setLayout(glw41);
	p1.add(lblorgn);
	p1.add(lbladd1);
	p1.add(lbladd2);
	JPanel p1a;
		p1a=new JPanel();
		p1a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1a.add(lbldept);
        p1a.add(cmbdept);
        p1a.add(btnshow);
	p1.add(p1a);
	vb.add(Box.createVerticalStrut(10));
	vb.add(p0);
	//vb.add(Box.createVerticalStrut(10));
	vb.add(p1);
	//vb.add(Box.createVerticalStrut(10));
	doconnect();
	filldeptcombo();
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
public void filldeptcombo(){ 
	try{
		stmtdept=conn.createStatement();
		rsdept=stmtdept.executeQuery("select * from tbldepartment");
		while(rsdept.next()){
			dpnm=rsdept.getString("dpnm");
			cmbdept.addItem(dpnm);
		}
	}
	catch(SQLException ex){
		System.out.println("Unable to create Department Combo");
	}
}
public void actionPerformed(ActionEvent ae){
	if(ae.getSource()==btnshow){
		
		//code to show
		
		dept=cmbdept.getSelectedItem().toString();
		try{
			stmtselect=conn.createStatement();
			rsuser=stmtselect.executeQuery("select * from tblemployee where dpnm='"+dept+"'");
			rsuser.last();
			tot=rsuser.getRow();
			objdata=new Object[tot][22];
			slno=1;
			rw=0;
			rsuser.beforeFirst();
			while(rsuser.next()){
				objdata[rw][0]=slno;
				objdata[rw][1]=rsuser.getString("empid");
				objdata[rw][2]=rsuser.getString("empnm");
				objdata[rw][3]=rsuser.getString("gender");
				objdata[rw][4]=rsuser.getString("dob");
				objdata[rw][5]=rsuser.getString("fnm");
				objdata[rw][6]=rsuser.getString("loc");
				objdata[rw][7]=rsuser.getString("city");
				objdata[rw][8]=rsuser.getString("state");
				objdata[rw][9]=rsuser.getString("pin");
				objdata[rw][10]=rsuser.getString("pno");
				objdata[rw][11]=rsuser.getString("mail");
				objdata[rw][12]=rsuser.getString("dpnm");
				objdata[rw][13]=rsuser.getString("dojoin");
				objdata[rw][14]=rsuser.getString("shnm");
				objdata[rw][15]=rsuser.getString("srttym");
				objdata[rw][16]=rsuser.getString("endtym");
				objdata[rw][17]=rsuser.getString("bsicsal");
				objdata[rw][18]=rsuser.getString("da");
				objdata[rw][19]=rsuser.getString("pf");
				objdata[rw][20]=rsuser.getString("hra");
				objdata[rw][21]=rsuser.getString("tax");
				slno=slno+1;
				rw=rw+1;
			}
		}
		catch(SQLException se){
			System.out.println("Error");
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
class RptEmpDeptApp{
    public static void main(String args[]) throws Exception  
    {   
        RptEmpDeptWin redw;
        redw=new RptEmpDeptWin();
    }
}