import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
class SalaryWin implements ActionListener{
	JLabel lbltitle,lblslpno,lbldate,lblmy,lblempid,lblempnm,lblgen,lblphno,lblmail,lbldpnm,lblshnm,lblbsal,lblda,lblpf,lblhra,lbltax,lblgsal,lblnetsal,lbldaam,lblpfam,lblhraam,lbltaxam;
	JTextField txtslpno,txtempnm,txtgen,txtphno,txtmail,txtdpnm,txtshift,txtbsal,txtda,txtpf,txthra,txttax,txtgsal,txtnetsal,txtdaam,txtpfam,txthraam,txttaxam;
	String slpno,mm,yyyy,empid,empnm,phno,mail,daten,date,month,year,gender,dpnm,shnm,bsal,da,pf,hra,tax,gsal,nsal;
	int Islpno;
	String dmy[],cdate[],my[];
	String cudate,cumonth,cuyear,daam,pfam,hraam,taxam,mmyyyy;
	float Idaam,Ipfam,Ihraam,Itaxam,Igsal,Ibsal,Insal;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2,p3;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch,rsempid,rsempiddata,rsslno;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch,stmtempid,stmtempiddata,stmtslno;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw74,glw38;
	JComboBox cmbempid,cmbDtDay,cmbDtMon,cmbDtYr,cmbmm,cmbyyyy;
	String cempid[]={"-Select-"};
	String date1[]={"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String month1[]={"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
    String yr[]={"YYYY","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	String cyyyy[]={"YYYY","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	String cmm[]={"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
	java.util.Date cdt;
	SimpleDateFormat sdf;
	String curDt;
	
public SalaryWin(){
	
	f1=new JFrame("Salary Slip Management");//frame
		
	//Border Declearation
	
	border=BorderFactory.createLineBorder(Color.black);
	raised=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	lowered=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	bevel=BorderFactory.createBevelBorder(BevelBorder.RAISED);
	
	//Box Declearation
	
	vb=Box.createVerticalBox();
	hb=Box.createHorizontalBox();
	
	//fONT DECLEARTION
	
	fnt1=new Font("Arial Black",Font.BOLD,24);
	fnt2=new Font("TimeNewRomsn",Font.BOLD,13);
	
	//Panel Controls
		
	ptitle=new JPanel();//panel
	p1=new JPanel();//panel
	p2=new JPanel();//panel
	p3=new JPanel();//panel
	
	//Layaout Controls
		
	flw=new FlowLayout();
	glw74=new GridLayout(7,4);
	glw38=new GridLayout(3,8);
	
	
	//setLayout
	
	ptitle.setLayout(flw);
	p1.setLayout(glw74);
	p2.setLayout(glw38);
	p3.setLayout(flw);
	
	//Combo box controls
	
	cmbempid=new JComboBox(cempid);//JComboBox
	cmbDtDay=new JComboBox(date1);//JComboBox
	cmbDtMon=new JComboBox(month1);//JComboBox
	cmbDtYr=new JComboBox(yr);//JComboBox
	cmbmm=new JComboBox(cmm);//JComboBox
	cmbyyyy=new JComboBox(cyyyy);//JComboBox
	
	//JLable Declearation
	
	lbltitle=new JLabel("S A L A R Y    S L I P    G E N E R A T I O N",JLabel.CENTER);
	lbltitle.setFont(fnt1);
	lblslpno=new JLabel("Slip No. :");
	lbldate=new JLabel("    Date :");
	lblmy=new JLabel("Month & Year :");
	lblempid=new JLabel("Employee ID :");
	lblempnm=new JLabel("Employee Name :");
	lblgen=new JLabel("    Gender :");
	lblphno=new JLabel("Phone NumberPhone Number :");
	lblmail=new JLabel("    E-Mail ID :");
	lbldpnm=new JLabel("Department :");
	lblshnm=new JLabel("    Shift :");
	lblbsal=new JLabel("Basic Salary :");
	lblda=new JLabel("DA in Percentage :");
	lblpf=new JLabel("    PF in Percentage :");
	lblhra=new JLabel("HRA in Percentage :");
	lbltax=new JLabel("    Tax in Percentage :");
	lblgsal=new JLabel("Gross Salary :");
	lblnetsal=new JLabel("    Net Salary :");
	lbldaam=new JLabel("    Amount :");
	lblpfam=new JLabel("    Amount :");
	lblhraam=new JLabel("    Amount :");
	lbltaxam=new JLabel("    Amount :");
	
	//SET FONT
	
	lblslpno.setFont(fnt2);
	lbldate.setFont(fnt2);
	lblmy.setFont(fnt2);
	lblempid.setFont(fnt2);
	lblempnm.setFont(fnt2);
	lblgen.setFont(fnt2);
	lblphno.setFont(fnt2);
	lblmail.setFont(fnt2);
	lbldpnm.setFont(fnt2);
	lblshnm.setFont(fnt2);
	lblbsal.setFont(fnt2);
	lblda.setFont(fnt2);
	lblpf.setFont(fnt2);
	lblhra.setFont(fnt2);
	lbltax.setFont(fnt2);
	lblgsal.setFont(fnt2);
	lblnetsal.setFont(fnt2);
	lbldaam.setFont(fnt2);
	lblpfam.setFont(fnt2);
	lblhraam.setFont(fnt2);
	lbltaxam.setFont(fnt2);
	
	//TextField declearation
	
	txtslpno=new JTextField(30);
	txtempnm=new JTextField(30);
	txtgen=new JTextField(30);
	txtphno=new JTextField(30);
	txtmail=new JTextField(30);
	txtdpnm=new JTextField(30);
	txtshift=new JTextField(30);
	txtbsal=new JTextField(30);
	txtda=new JTextField(30);
	txtpf=new JTextField(30);
	txthra=new JTextField(30);
	txttax=new JTextField(30);
	txtgsal=new JTextField(30);
	txtnetsal=new JTextField(30);
	txtdaam=new JTextField(30);
	txtpfam=new JTextField(30);
	txthraam=new JTextField(30);
	txttaxam=new JTextField(30);
	
	//set Editable(false)
	
	txtslpno.setEditable(false);
	txtempnm.setEditable(false);
	txtgen.setEditable(false);
	txtphno.setEditable(false);
	txtmail.setEditable(false);
	txtdpnm.setEditable(false);
	txtshift.setEditable(false);
	txtbsal.setEditable(false);
	txtda.setEditable(false);
	txtpf.setEditable(false);
	txthra.setEditable(false);
	txttax.setEditable(false);
	txtgsal.setEditable(false);
	txtnetsal.setEditable(false);
	txtdaam.setEditable(false);
	txtpfam.setEditable(false);
	txthraam.setEditable(false);
	txttaxam.setEditable(false);
	
	//set tooltiptext(txtfield)
	
	txtslpno.setToolTipText("Enter Salary Slip No.");
	txtempnm.setToolTipText("Enter Employee ID");
	txtgen.setToolTipText("Enter Your Gender");
	txtphno.setToolTipText("Enter Your Phone Number");
	txtmail.setToolTipText("Enter Your E-Mail ID");
	txtdpnm.setToolTipText("Enter Your Department Name");
	txtshift.setToolTipText("Enter Your Shift Name");
	txtbsal.setToolTipText("Enter Your Basic Salary");
	txtda.setToolTipText("Enter Dearness Allowance Percentage");
	txtpf.setToolTipText("Enter Provident Fund Percentage");
	txthra.setToolTipText("Enter House Rent Allowance Percentage");
	txttax.setToolTipText("Enter Tax Percentage");
	txtgsal.setToolTipText("Enter Your Gross Salary");
	txtnetsal.setToolTipText("Enter Your Net Salary");
	txtdaam.setToolTipText("Enter DA Amount");
	txtpfam.setToolTipText("Enter PF Amount");
	txthraam.setToolTipText("Enter HRA Amout");
	txttaxam.setToolTipText("Enter TAX Amount");
	
	//ImageIcon Declearation
	
	imgclear=new ImageIcon("clear.png");
	imgnext=new ImageIcon("next.png");
	imgclose=new ImageIcon("close.png");
	imgdlt=new ImageIcon("delete.png");
	imgfirst=new ImageIcon("first.png");
	imglast=new ImageIcon("last.png");
	imgpre=new ImageIcon("prev.png");
	imgsave=new ImageIcon("save.png");
	imgsearch=new ImageIcon("search.png");
	imgupdate=new ImageIcon("update.png");
	
	//Button Declearation
	
	btnclear=new JButton("Clear",imgclear);
	btnsave=new JButton("Save",imgsave);
	btnnext=new JButton("Next",imgnext);
	btnpre=new JButton("Previous",imgpre);
	btndlt=new JButton("Delete",imgdlt);
	btnupdate=new JButton("Update",imgupdate);
	btnclose=new JButton("Close",imgclose);
	btnfirst=new JButton("First",imgfirst);
	btnlast=new JButton("Last",imglast);
	btnsearch=new JButton("Search",imgsearch);
	
	//Add ActionListener
	
	btnclear.addActionListener(this);
	btnsave.addActionListener(this);
	btnnext.addActionListener(this);
	btnpre.addActionListener(this);
	btndlt.addActionListener(this);
	btnupdate.addActionListener(this);
	btnclose.addActionListener(this);
	btnfirst.addActionListener(this);
	btnlast.addActionListener(this);
	btnsearch.addActionListener(this);
	cmbempid.addActionListener(this);
	
	//set tooltiptext(btn)
	
	btnclear.setToolTipText("Clear All Data");
	btnsave.setToolTipText("Save the Record to Database");
	btnnext.setToolTipText("Go to Next Record");
	btnpre.setToolTipText("Go to Previous Record");
	btndlt.setToolTipText("Delete Current Record");
	btnupdate.setToolTipText("Update the Data");
	btnfirst.setToolTipText("Go to the First Record");
	btnlast.setToolTipText("Go to the Last Record");
	btnclose.setToolTipText("To Close the Frame");
	btnsearch.setToolTipText("To Search Any Record");
	
	//txtAmount Declearation
	
	
	
	//ptitle controls
	
	ptitle.setBorder(BorderFactory.createTitledBorder(" "));
	ptitle.add(lbltitle);
	ptitle.setBackground(new Color(255,204,51));
	
	//Current Date Declearation
	
	cdt=new java.util.Date();
	sdf=new SimpleDateFormat("dd/MM/yyyy");
	curDt=sdf.format(cdt);
	//txtempnm.setText(curDt);
	cdate=curDt.split("/");
		cudate=cdate[0];
		cumonth=cdate[1];
		cuyear=cdate[2];
	cmbDtDay.setSelectedItem(cudate);
	cmbDtMon.setSelectedItem(cumonth);
	cmbDtYr.setSelectedItem(cuyear);
	
	//p1 control
	
	p1.add(lblslpno);
	p1.add(txtslpno);
	p1.add(lbldate);
	JPanel p1a;
		p1a=new JPanel();
		p1a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1a.add(cmbDtDay);
        p1a.add("MONTH",cmbDtMon);
        p1a.add("YEAR",cmbDtYr);
	p1.add(p1a);
	p1.add(lblmy);
	JPanel p1b;
		p1b=new JPanel();
		p1b.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1b.add(cmbmm);
        p1b.add(cmbyyyy);
	p1.add(p1b);
	p1.add(new JLabel(""));
	p1.add(new JLabel(""));
	p1.add(lblempid);
	p1.add(cmbempid);
	p1.add(new JLabel(""));
	p1.add(new JLabel(""));
	p1.add(lblempnm);
	p1.add(txtempnm);
	p1.add(lblgen);
	p1.add(txtgen);
	p1.add(lblphno);
	p1.add(txtphno);
	p1.add(lblmail);
	p1.add(txtmail);
	p1.add(lbldpnm);
	p1.add(txtdpnm);
	p1.add(lblshnm);
	p1.add(txtshift);
	p1.add(lblbsal);
	p1.add(txtbsal);
	p1.add(new JLabel(""));
	p1.add(new JLabel(""));
	
	//p2 control
	
	p2.add(lblda);
	p2.add(txtda);
	p2.add(lbldaam);
	p2.add(txtdaam);
	p2.add(lblpf);
	p2.add(txtpf);
	p2.add(lblpfam);
	p2.add(txtpfam);
	p2.add(lblhra);
	p2.add(txthra);
	p2.add(lblhraam);
	p2.add(txthraam);
	p2.add(lbltax);
	p2.add(txttax);
	p2.add(lbltaxam);
	p2.add(txttaxam);
	p2.add(lblgsal);
	p2.add(txtgsal);
	p2.add(new JLabel(""));
	p2.add(new JLabel(""));
	p2.add(lblnetsal);
	p2.add(txtnetsal);
	p2.add(new JLabel(""));
	p2.add(new JLabel(""));
	
	//p3 controls
	
	p3.setBorder(BorderFactory.createTitledBorder(" "));
	p3.setBackground(new Color(204,204,204));
	p3.add(btnclear);
	p3.add(btnsave);
	p3.add(btnpre);
	p3.add(btnnext);
	p3.add(btnfirst);
	p3.add(btnlast);
	p3.add(btnsearch);
	p3.add(btndlt);
	p3.add(btnupdate);
	p3.add(btnclose);
	
	//Box control
		
	vb.add(Box.createVerticalStrut(50));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(50));
	vb.add(p1);
	vb.add(p2);
	//vb.add(p3);
	//vb.add(p4);
	//vb.add(p5);
	vb.add(Box.createVerticalStrut(50));
	vb.add(p3);
	vb.add(Box.createVerticalStrut(50));
	hb.add(Box.createHorizontalStrut(30));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(30));
	
	//f1 control
	
	f1.setLayout(new BorderLayout());
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(83,58,1200,650);
	//f1.setResizable(false);
	doconnect();
	slipno();
	fillempidcombo();
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
	}
	catch(SQLException se){
		System.out.println("Unable to connect");
	}   
	try{
		stmtselect=conn.createStatement();
		rsuser=stmtselect.executeQuery("select * from tblsalaryslip");
	}
	catch(SQLException ex){
		System.out.println("Unable to Fetch data");
	}
} // doconnect ends here
public void slipno(){ 
	try{
		stmtslno=conn.createStatement();
		rsslno=stmtslno.executeQuery("select count(*) as Islpno from tblsalaryslip");
		rsslno.next();
		Islpno=rsslno.getInt("Islpno");
		Islpno=Islpno+1;
		txtslpno.setText(String.valueOf(Islpno));
	}
	catch(SQLException ex){
		System.out.println("Unable to create ");
	}
}
public void fillempidcombo(){ 
	try{
		stmtempid=conn.createStatement();
		rsempid=stmtempid.executeQuery("select * from tblemployee");
		while(rsempid.next()){
			empid=rsempid.getString("empid");
			cmbempid.addItem(empid);
		}
	}
	catch(SQLException ex){
		System.out.println("Unable to create Employee ID Combo");
	}
}
public void actionPerformed(ActionEvent ae){
	if(ae.getSource()==btnclear){
		empnm=txtempnm.getText();
		if(empnm.length()>0){
			//Islpno=Islpno+1;
			txtslpno.setText(String.valueOf(Islpno));
		}
		
		//code to clear
		
		//txtslpno.setText("");
		txtempnm.setText("");
		txtgen.setText("");
		txtphno.setText("");
		txtmail.setText("");
		txtdpnm.setText("");
		txtshift.setText("");
		txtbsal.setText("");
		txtda.setText("");
		txtpf.setText("");
		txthra.setText("");
		txttax.setText("");
		txtgsal.setText("");
		txtnetsal.setText("");
		txtdaam.setText("");
		txtpfam.setText("");
		txthraam.setText("");
		txttaxam.setText("");
		cmbDtDay.setSelectedItem(cudate);
		cmbDtMon.setSelectedItem(cumonth);
		cmbDtYr.setSelectedItem(cuyear);
		cmbempid.setSelectedItem("-Select-");
		cmbmm.setSelectedItem("MM");
		cmbyyyy.setSelectedItem("YYYY");
		
	}
	else if(ae.getSource()==cmbempid){
		empid=cmbempid.getSelectedItem().toString();
		try{
			stmtempiddata=conn.createStatement();
			rsempiddata=stmtempiddata.executeQuery("select * from tblemployee where empid='"+empid+"'");
			if(rsempiddata.next()){
				empnm=rsempiddata.getString("empnm");
				gender=rsempiddata.getString("gender");
				phno=rsempiddata.getString("pno");
				mail=rsempiddata.getString("mail");
				dpnm=rsempiddata.getString("dpnm");
				shnm=rsempiddata.getString("shnm");
				float Ibsal=rsempiddata.getFloat("bsicsal");
				float Ida=rsempiddata.getFloat("da");
				float Ipf=rsempiddata.getFloat("pf");
				float Ihra=rsempiddata.getFloat("hra");
				float Itax=rsempiddata.getFloat("tax");
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtphno.setText(phno);
				txtmail.setText(mail);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				
				txtbsal.setText(String.valueOf(Ibsal));
				txtda.setText(String.valueOf(Ida));
				
				Idaam=Ibsal*(Ida/100);
				txtdaam.setText(String.valueOf(Idaam));
				txtpf.setText(String.valueOf(Ipf));
				Ipfam=Ibsal*(Ipf/100);
				txtpfam.setText(String.valueOf(Ipfam));
				txthra.setText(String.valueOf(Ihra));
				Ihraam=Ibsal*(Ihra/100);
				txthraam.setText(String.valueOf(Ihraam));
				txttax.setText(String.valueOf(Itax));
				Itaxam=Ibsal*(Itax/100);
				txttaxam.setText(String.valueOf(Itaxam));
				Igsal=Ibsal+Idaam+Ihraam;
				txtgsal.setText(String.valueOf(Igsal));
				Insal=Igsal-(Ipfam+Itaxam);
				txtnetsal.setText(String.valueOf(Insal));
			}
		}
		catch(SQLException ex){
			System.out.println("Unable to Show Data");
		}	
	}
	else if(ae.getSource()==btnsave){
					
		//code for save
		try{
			String slpno2;
			slpno2=txtslpno.getText();
			stmtsearch=conn.createStatement();
			rsdup=stmtsearch.executeQuery("select * from tblsalaryslip where slpno='"+slpno2+"'");
			if(rsdup.next()){
				msg.showMessageDialog(f1,"The Slip No. is Duplicate","Alert",2);
			}
			else{
				slpno=txtslpno.getText();
				gender=txtgen.getText();
				dpnm=txtdpnm.getText();
				shnm=txtshift.getText();
				empnm=txtempnm.getText();
				phno=txtphno.getText();
				bsal=txtbsal.getText();
				gsal=txtgsal.getText();
				nsal=txtnetsal.getText();
					if(slpno.length()==0||gender.length()==0||phno.length()==0||empnm.length()==0||dpnm.length()==0||shnm.length()==0||bsal.length()==0||gsal.length()==0||nsal.length()==0){
						msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
					}
					else{
						int choice;
						choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
						if(choice==0){
							slpno=txtslpno.getText();
							gender=txtgen.getText();
							dpnm=txtdpnm.getText();
							shnm=txtshift.getText();
							empnm=txtempnm.getText();
							phno=txtphno.getText();
							bsal=txtbsal.getText();
							gsal=txtgsal.getText();
							nsal=txtnetsal.getText();
							pf=txtpf.getText();
							pfam=txtpfam.getText();
							da=txtda.getText();
							daam=txtdaam.getText();
							hra=txthra.getText();
							hraam=txthraam.getText();
							tax=txttax.getText();
							taxam=txttaxam.getText();
							mail=txtmail.getText();
							date=cmbDtDay.getSelectedItem().toString();
							month=cmbDtMon.getSelectedItem().toString();
							year=cmbDtYr.getSelectedItem().toString();
							daten=date+"/"+month+"/"+year;
							mm=cmbmm.getSelectedItem().toString();
							yyyy=cmbyyyy.getSelectedItem().toString();
							mmyyyy=month+"/"+year;
							empid=cmbempid.getSelectedItem().toString();
							
							try{
								stmtInsert=conn.createStatement();
								stmtInsert.executeUpdate("insert into tblsalaryslip values('"+slpno+"','"+daten+"','"+mmyyyy+"','"+empid+"','"+empnm+"','"+gender+"','"+phno+"','"+mail+"','"+dpnm+"','"+shnm+"','"+bsal+"','"+da+"','"+daam+"','"+pf+"','"+pfam+"','"+hra+"','"+hraam+"','"+tax+"','"+taxam+"','"+gsal+"','"+nsal+"')");
								//System.out.println("One Record Saved Succesfully");
								msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
								Islpno=Islpno+1;
								txtslpno.setText(String.valueOf(Islpno));
							}
							catch(SQLException se){
								//System.out.println(se);
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
		catch(SQLException se){}
	}//end of ifelse
	else if(ae.getSource()==btnnext){

		//code to next
		try{
			if(rsuser.isLast()){
				btnnext.setEnabled(false);
				msg.showMessageDialog(f1,"Unable to Show data","Message",1);
			}
			else{
				btnpre.setEnabled(true);
				rsuser.next();
				slpno=rsuser.getString("slpno");
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				gender=rsuser.getString("gender");
				phno=rsuser.getString("phno");
				mail=rsuser.getString("mail");
				dpnm=rsuser.getString("dpnm");
				shnm=rsuser.getString("shnm");
				bsal=rsuser.getString("bsal");
				da=rsuser.getString("da");
				daam=rsuser.getString("daam");
				pf=rsuser.getString("pf");
				pfam=rsuser.getString("pfam");
				hra=rsuser.getString("hra");
				hraam=rsuser.getString("hraam");
				tax=rsuser.getString("tax");
				taxam=rsuser.getString("taxam");
				gsal=rsuser.getString("gsal");
				nsal=rsuser.getString("nsal");
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];
				mmyyyy=rsuser.getString("mmyyyy");
				my=mmyyyy.split("/");
				mm=my[0];
				yyyy=my[1];
				
				//setText
				
				txtslpno.setText(slpno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtphno.setText(phno);
				txtmail.setText(mail);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtbsal.setText(bsal);
				txtda.setText(da);
				txtdaam.setText(daam);
				txtpf.setText(pf);
				txtpfam.setText(pfam);
				txthra.setText(hra);
				txthraam.setText(hraam);
				txttax.setText(tax);
				txttaxam.setText(taxam);
				txtgsal.setText(gsal);
				txtnetsal.setText(nsal);
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				cmbempid.setSelectedItem(empid);
				cmbmm.setSelectedItem(mm);
				cmbyyyy.setSelectedItem(yyyy);
			}
		}
		catch(SQLException ex){
			System.out.println(ex);
			msg.showMessageDialog(f1,"Unable to Show data","Message",1);
		}
	}//end of ifelse
	else if(ae.getSource()==btnpre){
					
		//code for previous
			
		try{
			if(rsuser.isFirst()){
				btnpre.setEnabled(false);
				msg.showMessageDialog(f1,"Unable to Show data","Message",1);
			}
			else{
				btnnext.setEnabled(true);
				rsuser.previous();
				slpno=rsuser.getString("slpno");
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				gender=rsuser.getString("gender");
				phno=rsuser.getString("phno");
				mail=rsuser.getString("mail");
				dpnm=rsuser.getString("dpnm");
				shnm=rsuser.getString("shnm");
				bsal=rsuser.getString("bsal");
				da=rsuser.getString("da");
				daam=rsuser.getString("daam");
				pf=rsuser.getString("pf");
				pfam=rsuser.getString("pfam");
				hra=rsuser.getString("hra");
				hraam=rsuser.getString("hraam");
				tax=rsuser.getString("tax");
				taxam=rsuser.getString("taxam");
				gsal=rsuser.getString("gsal");
				nsal=rsuser.getString("nsal");
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];
				mmyyyy=rsuser.getString("mmyyyy");
				my=mmyyyy.split("/");
				mm=my[0];
				yyyy=my[1];
				
				//setText
				
				txtslpno.setText(slpno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtphno.setText(phno);
				txtmail.setText(mail);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtbsal.setText(bsal);
				txtda.setText(da);
				txtdaam.setText(daam);
				txtpf.setText(pf);
				txtpfam.setText(pfam);
				txthra.setText(hra);
				txthraam.setText(hraam);
				txttax.setText(tax);
				txttaxam.setText(taxam);
				txtgsal.setText(gsal);
				txtnetsal.setText(nsal);
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				cmbempid.setSelectedItem(empid);
				cmbmm.setSelectedItem(mm);
				cmbyyyy.setSelectedItem(yyyy);
			}
		}
		catch(SQLException ex){
			//System.out.println("Unable to go previous");
			msg.showMessageDialog(f1,"Unable to go previous","Message",1);
		}
	}
	else if(ae.getSource()==btndlt){
					
		//code for delete	

			slpno=txtslpno.getText();
			int choice;
			choice=msg.showConfirmDialog(f1,"Are You Sure to Delete ?","Alert",2);
		if(choice==0){
		try{
			stmtdelete=conn.createStatement();
			stmtdelete.executeUpdate("delete from tblsalaryslip where slpno='"+slpno+"'");
			//System.out.println("One Record is deleted Succesfully");	
			msg.showMessageDialog(f1,"One Record is deleted Succesfully","Alert",1);
		}
		catch(SQLException ex){
			//System.out.println("Unable to Delete"+ex);
			msg.showMessageDialog(f1,"Unable to Delete","Alert",1);
		}
		}
		else{
			//System.out.println("THANK YOU");
			msg.showMessageDialog(f1,"THANK YOU","Message",1);
		}
	}
	else if(ae.getSource()==btnupdate){
					
		//code for Update	

		slpno=txtslpno.getText();
		gender=txtgen.getText();
		dpnm=txtdpnm.getText();
		shnm=txtshift.getText();
		empnm=txtempnm.getText();
		phno=txtphno.getText();
		bsal=txtbsal.getText();
		gsal=txtgsal.getText();
		nsal=txtnetsal.getText();
		pf=txtpf.getText();
		pfam=txtpfam.getText();
		da=txtda.getText();
		daam=txtdaam.getText();
		hra=txthra.getText();
		hraam=txthraam.getText();
		tax=txttax.getText();
		taxam=txttaxam.getText();
		mail=txtmail.getText();
		date=cmbDtDay.getSelectedItem().toString();
		month=cmbDtMon.getSelectedItem().toString();
		year=cmbDtYr.getSelectedItem().toString();
		daten=date+"/"+month+"/"+year;
		mm=cmbmm.getSelectedItem().toString();
		yyyy=cmbyyyy.getSelectedItem().toString();
		mmyyyy=month+"/"+year;
		empid=cmbempid.getSelectedItem().toString();
		
		try{
			stmtInsert=conn.createStatement();
			stmtInsert.executeUpdate("insert into tblsalaryslip values('"+slpno+"','"+daten+"','"+mmyyyy+"','"+empid+"','"+empnm+"','"+gender+"','"+phno+"','"+mail+"','"+dpnm+"','"+shnm+"','"+bsal+"','"+da+"','"+daam+"','"+pf+"','"+pfam+"','"+hra+"','"+hraam+"','"+tax+"','"+taxam+"','"+gsal+"','"+nsal+"')");
			//System.out.println("One Record is Update Succesfully");
			msg.showMessageDialog(f1,"One Record is Update Succesfully","Message",1);
		}
		catch(SQLException ex){
			//msg.showMessageDialog(f1,"Unable to Update","Message",1);
			System.out.println("Unable to Update"+ex);
		}
	}
	else if(ae.getSource()==btnfirst){
					
		//code for show first record
		
		try{
			btnnext.setEnabled(true);
			if(rsuser.first()){
			slpno=rsuser.getString("slpno");
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			gender=rsuser.getString("gender");
			phno=rsuser.getString("phno");
			mail=rsuser.getString("mail");
			dpnm=rsuser.getString("dpnm");
			shnm=rsuser.getString("shnm");
			bsal=rsuser.getString("bsal");
			da=rsuser.getString("da");
			daam=rsuser.getString("daam");
			pf=rsuser.getString("pf");
			pfam=rsuser.getString("pfam");
			hra=rsuser.getString("hra");
			hraam=rsuser.getString("hraam");
			tax=rsuser.getString("tax");
			taxam=rsuser.getString("taxam");
			gsal=rsuser.getString("gsal");
			nsal=rsuser.getString("nsal");
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			mmyyyy=rsuser.getString("mmyyyy");
			my=mmyyyy.split("/");
			mm=my[0];
			yyyy=my[1];
			
			//setText
			
			txtslpno.setText(slpno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtphno.setText(phno);
			txtmail.setText(mail);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtbsal.setText(bsal);
			txtda.setText(da);
			txtdaam.setText(daam);
			txtpf.setText(pf);
			txtpfam.setText(pfam);
			txthra.setText(hra);
			txthraam.setText(hraam);
			txttax.setText(tax);
			txttaxam.setText(taxam);
			txtgsal.setText(gsal);
			txtnetsal.setText(nsal);
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			cmbempid.setSelectedItem(empid);
			cmbmm.setSelectedItem(mm);
			cmbyyyy.setSelectedItem(yyyy);
			}	
		}
		catch(SQLException ex){
			//System.out.println("Unable to Delete"+ex);
			msg.showMessageDialog(f1,"Unable to Show Data","Alert",1);
		}
	}
	else if(ae.getSource()==btnlast){
					
		//code for show last record
		
		try{
			btnpre.setEnabled(true);
			if(rsuser.last()){
			slpno=rsuser.getString("slpno");
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			gender=rsuser.getString("gender");
			phno=rsuser.getString("phno");
			mail=rsuser.getString("mail");
			dpnm=rsuser.getString("dpnm");
			shnm=rsuser.getString("shnm");
			bsal=rsuser.getString("bsal");
			da=rsuser.getString("da");
			daam=rsuser.getString("daam");
			pf=rsuser.getString("pf");
			pfam=rsuser.getString("pfam");
			hra=rsuser.getString("hra");
			hraam=rsuser.getString("hraam");
			tax=rsuser.getString("tax");
			taxam=rsuser.getString("taxam");
			gsal=rsuser.getString("gsal");
			nsal=rsuser.getString("nsal");
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			mmyyyy=rsuser.getString("mmyyyy");
			my=mmyyyy.split("/");
			mm=my[0];
			yyyy=my[1];
			
			//setText
			
			txtslpno.setText(slpno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtphno.setText(phno);
			txtmail.setText(mail);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtbsal.setText(bsal);
			txtda.setText(da);
			txtdaam.setText(daam);
			txtpf.setText(pf);
			txtpfam.setText(pfam);
			txthra.setText(hra);
			txthraam.setText(hraam);
			txttax.setText(tax);
			txttaxam.setText(taxam);
			txtgsal.setText(gsal);
			txtnetsal.setText(nsal);
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			cmbempid.setSelectedItem(empid);
			cmbmm.setSelectedItem(mm);
			cmbyyyy.setSelectedItem(yyyy);
		}
		}
		catch(SQLException ex){
			//System.out.println("Unable to Delete"+ex);
			msg.showMessageDialog(f1,"Unable to Show Data","Alert",1);
		}
	}
	else if(ae.getSource()==btnsearch){
					
		//code  for search
		
		String slpno3;
		slpno3=msg.showInputDialog(f1,"Enter Slip No. to Find Record","Message",1);
		if(slpno3!=null){
			//System.out.println("Data "+slpno3.length());
		try{
			stmtsearch=conn.createStatement();
			rssearch=stmtsearch.executeQuery("select * from tblsalaryslip where slpno='"+slpno3+"'");
			rssearch.next();
			slpno=rssearch.getString("slpno");
			empid=rssearch.getString("empid");
			empnm=rssearch.getString("empnm");
			gender=rssearch.getString("gender");
			phno=rssearch.getString("phno");
			mail=rssearch.getString("mail");
			dpnm=rssearch.getString("dpnm");
			shnm=rssearch.getString("shnm");
			bsal=rssearch.getString("bsal");
			da=rssearch.getString("da");
			daam=rssearch.getString("daam");
			pf=rssearch.getString("pf");
			pfam=rssearch.getString("pfam");
			hra=rssearch.getString("hra");
			hraam=rssearch.getString("hraam");
			tax=rssearch.getString("tax");
			taxam=rssearch.getString("taxam");
			gsal=rssearch.getString("gsal");
			nsal=rssearch.getString("nsal");
			daten=rssearch.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			mmyyyy=rssearch.getString("mmyyyy");
			my=mmyyyy.split("/");
			mm=my[0];
			yyyy=my[1];
			//*System.out.println("Data "+slpno3.length());
			
			//setText
			
			txtslpno.setText(slpno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtphno.setText(phno);
			txtmail.setText(mail);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtbsal.setText(bsal);
			txtda.setText(da);
			txtdaam.setText(daam);
			txtpf.setText(pf);
			txtpfam.setText(pfam);
			txthra.setText(hra);
			txthraam.setText(hraam);
			txttax.setText(tax);
			txttaxam.setText(taxam);
			txtgsal.setText(gsal);
			txtnetsal.setText(nsal);
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			cmbempid.setSelectedItem(empid);
			cmbmm.setSelectedItem(mm);
			cmbyyyy.setSelectedItem(yyyy);
		}
		catch(SQLException ex){
			System.out.println("Unable to Delete"+ex);
			msg.showMessageDialog(f1,"Unable to Search","Alert",1);
		}
	}
	}//end of else if
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
}
class SalarySlipApp{
    public static void main(String args[]) throws Exception  
    {   
        SalaryWin sw;
        sw=new SalaryWin();
    }
}