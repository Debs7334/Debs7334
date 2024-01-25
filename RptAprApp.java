import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RptAprWin implements ActionListener{
	JFrame f1;
	JLabel lbltitle,lblorgn,lbladd1,lbladd2;
	JPanel p0,p1;
	GridLayout glw41;
	Connection conn;
	Statement stmtselect;
	ResultSet rsuser;
	Object objdata[][];
	JTable jtbldata;
	Box vb;
	JScrollPane jspdata;
	String colhead[]={"Serial No.","Date","Reference No.","Apply Date","Employee ID","Employee Name","Gender","Department Name","Shift Name","Start Time","End Time","Leave Type","Date From","Date To","Narration","Status"};
	int slno,rw,tot;
	JButton btnclose;
	ImageIcon imgclose;
	JOptionPane msg;
public RptAprWin(){
	f1=new JFrame();
	p0=new JPanel();
	p1=new JPanel();
	vb=Box.createVerticalBox();
	imgclose=new ImageIcon("close.png");
	btnclose=new JButton("Close",imgclose);
	btnclose.addActionListener(this);
	btnclose.setToolTipText("To Close the Frame");
	glw41=new GridLayout(4,1);
	lbltitle=new JLabel("LEAVE  APPLICATION",JLabel.CENTER);
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
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
		rsuser=stmtselect.executeQuery("select * from tblapproval");
		rsuser.last();
		tot=rsuser.getRow();
		objdata=new Object[tot][16];
		slno=1;
		rw=0;
		rsuser.beforeFirst();
		while(rsuser.next()){
			//objdata[rw][0]=slno;
			objdata[rw][0]=rsuser.getString("srlno");
			objdata[rw][1]=rsuser.getString("daten");
			objdata[rw][2]=rsuser.getString("refno");
			objdata[rw][3]=rsuser.getString("apdate");
			objdata[rw][4]=rsuser.getString("empid");
			objdata[rw][5]=rsuser.getString("empnm");
			objdata[rw][6]=rsuser.getString("gender");
			objdata[rw][7]=rsuser.getString("dpnm");
			objdata[rw][8]=rsuser.getString("shnm");
			objdata[rw][9]=rsuser.getString("srttym");
			objdata[rw][10]=rsuser.getString("endtym");
			objdata[rw][11]=rsuser.getString("ltype");
			objdata[rw][12]=rsuser.getString("dfrom");
			objdata[rw][13]=rsuser.getString("dto");
			objdata[rw][14]=rsuser.getString("nar");
			objdata[rw][15]=rsuser.getString("sts");
			//slno=slno+1;
			rw=rw+1;
		}
	}//try ends
	catch(SQLException se){
		//System.out.println("Unable to connect");
		System.out.println(se);
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
class RptAprApp{
    public static void main(String args[]) throws Exception  
    {   
        RptAprWin raaw;
        raaw=new RptAprWin();
    }
}