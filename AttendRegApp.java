import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
class AttendanceWin implements ActionListener{
	JLabel lbltitle,lblsrlno,lbldate,lblempid,lblempnm,lblgen,lbldpnm,lblshift,lblsrttym,lblendtym,lblstatus;
	JTextField txtsrlno,txtempnm,txtgen,txtdpnm,txtshift,txtsrttym,txtendtym;
	String dmy[],cdate[];
	String srlno,empid,empnm,daten,date,month,year,gender,dpnm,shnm,srttym,endtym,status;
	String cudate,cumonth,cuyear;
	int Islno;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch,rsempid,rsempiddata,rsslno;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch,stmtempid,stmtempiddata,stmtslno;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw64;
	JComboBox cmbempid,cmbDtDay,cmbDtMon,cmbDtYr,cmbstatus;
	String cempid[]={"-Select-"};
	String date1[]={"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String month1[]={"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
    String yr[]={"YYYY","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	String sts[]={"No Choice","P","A","P/X","X/P"};
	java.util.Date cdt;
	SimpleDateFormat sdf;
	String curDt;
public AttendanceWin(){
	
	f1=new JFrame("Attendance Registation Management");//frame
		
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
	
	//Layaout Controls
		
	flw=new FlowLayout();
	glw64=new GridLayout(6,4);
	
	//setLayout
	
	ptitle.setLayout(flw);
	p1.setLayout(glw64);
	p2.setLayout(flw);
	
	//Combo box controls
	
	cmbempid=new JComboBox(cempid);//JComboBox
	cmbDtDay=new JComboBox(date1);//JComboBox
	cmbDtMon=new JComboBox(month1);//JComboBox
	cmbDtYr=new JComboBox(yr);//JComboBox
	cmbstatus=new JComboBox(sts);//JComboBox
	
	//JLable Declearation
	
	lbltitle=new JLabel("A T T E N D A N C E   R E G I S T E R",JLabel.CENTER);
	lbltitle.setFont(fnt1);
	lblsrlno=new JLabel("Serial No. :");
	lbldate=new JLabel("    Date :");
	lblempid=new JLabel("Employee ID :");
	lblempnm=new JLabel("Employee Name :");
	lblgen=new JLabel("    Gender :");
	lbldpnm=new JLabel("Department Name :");
	lblshift=new JLabel("    Shift :");
	lblsrttym=new JLabel("Start Time :");
	lblendtym=new JLabel("    End Time :");
	lblstatus=new JLabel("Status :");

	//SET FONT
	
	lblsrlno.setFont(fnt2);
	lbldate.setFont(fnt2);
	lblempid.setFont(fnt2);
	lblempnm.setFont(fnt2);
	lblgen.setFont(fnt2);
	lbldpnm.setFont(fnt2);
	lblshift.setFont(fnt2);
	lblsrttym.setFont(fnt2);
	lblendtym.setFont(fnt2);
	lblstatus.setFont(fnt2);
	
	//TextField declearation
	
	txtsrlno=new JTextField(30);
	txtsrlno.setEditable(false);
	//txtempid=new JTextField(30);
	txtempnm=new JTextField(30);
	txtgen=new JTextField(30);
	txtdpnm=new JTextField(30);
	txtshift=new JTextField(30);
	txtsrttym=new JTextField(30);
	txtendtym=new JTextField(30);
	
	//set tooltiptext(txtfield)
	
	txtsrlno.setToolTipText("Serial No.");
	//txtempid.setToolTipText("Select Employee ID");
	txtempnm.setToolTipText("Employee Name");
	txtgen.setToolTipText("Employee Gender");
	txtdpnm.setToolTipText("Department Name");
	txtshift.setToolTipText("Shift Name");
	txtsrttym.setToolTipText("Shift Start Time");
	txtendtym.setToolTipText("Shift End Time");
	
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
	
	p1.add(lblsrlno);
	p1.add(txtsrlno);
	p1.add(lbldate);
	JPanel p1a;
		p1a=new JPanel();
		p1a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1a.add(cmbDtDay);
        p1a.add("MONTH",cmbDtMon);
        p1a.add("YEAR",cmbDtYr);
	p1.add(p1a);
	p1.add(lblempid);
	p1.add(cmbempid);
	p1.add(new JLabel(""));
	p1.add(new JLabel(""));
	p1.add(lblempnm);
	p1.add(txtempnm);
	p1.add(lblgen);
	p1.add(txtgen);
	p1.add(lbldpnm);
	p1.add(txtdpnm);
	p1.add(lblshift);
	p1.add(txtshift);
	p1.add(lblsrttym);
	p1.add(txtsrttym);
	p1.add(lblendtym);
	p1.add(txtendtym);
	p1.add(lblstatus);
	p1.add(cmbstatus);
	
	//p2 controls
	
	p2.setBorder(BorderFactory.createTitledBorder(" "));
	p2.setBackground(new Color(204,204,204));
	p2.add(btnclear);
	p2.add(btnsave);
	p2.add(btnpre);
	p2.add(btnnext);
	p2.add(btnfirst);
	p2.add(btnlast);
	p2.add(btnsearch);
	p2.add(btndlt);
	p2.add(btnupdate);
	p2.add(btnclose);
	
	//Box control
		
	vb.add(Box.createVerticalStrut(40));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(40));
	vb.add(p1);
	//vb.add(p2);
	//vb.add(p3);
	//vb.add(p4);
	//vb.add(p5);
	vb.add(Box.createVerticalStrut(40));
	vb.add(p2);
	vb.add(Box.createVerticalStrut(40));
	hb.add(Box.createHorizontalStrut(40));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(40));
	
	//f1 control
	
	f1.setLayout(new BorderLayout());
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(183,108,1000,550);
	//f1.setResizable(false);
	doconnect();
	serialno();
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
		rsuser=stmtselect.executeQuery("select * from tblattendance");
	}
	catch(SQLException ex){
		System.out.println("Unable to Fetch data");
	}
} // doconnect ends here
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
public void serialno(){ 
	try{
		stmtslno=conn.createStatement();
		rsslno=stmtslno.executeQuery("select count(*) as Islno from tblattendance");
		rsslno.next();
		Islno=rsslno.getInt("Islno");
		Islno=Islno+1;
		txtsrlno.setText(String.valueOf(Islno));
	}
	catch(SQLException ex){
		System.out.println("Unable to create ");
	}
}
public void actionPerformed(ActionEvent ae){
	
	if(ae.getSource()==btnclear){
		empnm=txtempnm.getText();
		if(empnm.length()>0){
			//Islno=Islno+1;
			txtsrlno.setText(String.valueOf(Islno));
		}
			//code to clear
			//txtsrlno.setText("");
			txtempnm.setText("");
			txtgen.setText("");
			txtdpnm.setText("");
			txtshift.setText("");
			txtsrttym.setText("");
			txtendtym.setText("");
			cmbDtDay.setSelectedItem(cudate);
			cmbDtMon.setSelectedItem(cumonth);
			cmbDtYr.setSelectedItem(cuyear);
			cmbempid.setSelectedItem("-Select-");
			cmbstatus.setSelectedItem("No Choice");	
		
	}
	else if(ae.getSource()==cmbempid){
		empid=cmbempid.getSelectedItem().toString();
		try{
			stmtempiddata=conn.createStatement();
			rsempiddata=stmtempiddata.executeQuery("select * from tblemployee where empid='"+empid+"'");
			if(rsempiddata.next()){
				empnm=rsempiddata.getString("empnm");
				gender=rsempiddata.getString("gender");
				dpnm=rsempiddata.getString("dpnm");
				shnm=rsempiddata.getString("shnm");
				srttym=rsempiddata.getString("srttym");
				endtym=rsempiddata.getString("endtym");
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
			}
		}
		catch(SQLException ex){
			System.out.println("Unable to Show Data");
		}	
	}
	else if(ae.getSource()==btnsave){
					
		//code for save
		try{
			String srlno2;
			srlno2=txtsrlno.getText();
			stmtsearch=conn.createStatement();
			rsdup=stmtsearch.executeQuery("select * from tblattendance where srlno='"+srlno2+"'");
			if(rsdup.next()){
				msg.showMessageDialog(f1,"The Serial No. is Duplicate","Alert",2);
			}
			else{
				empnm=txtempnm.getText();
				gender=txtgen.getText();
				dpnm=txtdpnm.getText();
				shnm=txtshift.getText();
				srttym=txtsrttym.getText();
				endtym=txtendtym.getText();
					if(empnm.length()==0||gender.length()==0||srttym.length()==0||endtym.length()==0||dpnm.length()==0||shnm.length()==0){
						msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
					}
					else{
						int choice;
						choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
						if(choice==0){
							srlno=txtsrlno.getText();
							empid=cmbempid.getSelectedItem().toString();
							empnm=txtempnm.getText();
							gender=txtgen.getText();
							dpnm=txtdpnm.getText();
							shnm=txtshift.getText();
							srttym=txtsrttym.getText();
							endtym=txtendtym.getText();
							date=cmbDtDay.getSelectedItem().toString();
							month=cmbDtMon.getSelectedItem().toString();
							year=cmbDtYr.getSelectedItem().toString();
							daten=date+"/"+month+"/"+year;
							status=cmbstatus.getSelectedItem().toString();
							
							try{
								stmtInsert=conn.createStatement();
								stmtInsert.executeUpdate("insert into tblattendance values('"+srlno+"','"+daten+"','"+empid+"','"+empnm+"','"+gender+"','"+dpnm+"','"+shnm+"','"+srttym+"','"+endtym+"','"+status+"')");
								//System.out.println("One Record Saved Succesfully");
								msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
								Islno=Islno+1;
								txtsrlno.setText(String.valueOf(Islno));
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
				srlno=rsuser.getString("srlno");
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				gender=rsuser.getString("gender");
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];
				dpnm=rsuser.getString("dpnm");
				shnm=rsuser.getString("shnm");
				srttym=rsuser.getString("srttym");
				endtym=rsuser.getString("endtym");
				status=rsuser.getString("status");
				
				//setText

				txtsrlno.setText(srlno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				cmbempid.setSelectedItem(empid);
				cmbstatus.setSelectedItem(status);
				
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
				srlno=rsuser.getString("srlno");
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				gender=rsuser.getString("gender");
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];
				dpnm=rsuser.getString("dpnm");
				shnm=rsuser.getString("shnm");
				srttym=rsuser.getString("srttym");
				endtym=rsuser.getString("endtym");
				status=rsuser.getString("status");
				
				//setText

				txtsrlno.setText(srlno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				cmbempid.setSelectedItem(empid);
				cmbstatus.setSelectedItem(status);
			}
		}
		catch(SQLException ex){
			//System.out.println("Unable to go previous");
			msg.showMessageDialog(f1,"Unable to go previous","Message",1);
		}
	}
	else if(ae.getSource()==btndlt){
					
		//code for delete	

			srlno=txtsrlno.getText();
			int choice;
			choice=msg.showConfirmDialog(f1,"Are You Sure to Delete ?","Alert",2);
		if(choice==0){
		try{
			stmtdelete=conn.createStatement();
			stmtdelete.executeUpdate("delete from tblattendance where srlno='"+srlno+"'");
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

		srlno=txtsrlno.getText();
		empid=cmbempid.getSelectedItem().toString();
		empnm=txtempnm.getText();
		gender=txtgen.getText();
		dpnm=txtdpnm.getText();
		shnm=txtshift.getText();
		srttym=txtsrttym.getText();
		endtym=txtendtym.getText();
		date=cmbDtDay.getSelectedItem().toString();
		month=cmbDtMon.getSelectedItem().toString();
		year=cmbDtYr.getSelectedItem().toString();
		daten=date+"/"+month+"/"+year;
		status=cmbstatus.getSelectedItem().toString();
		
		try{
			stmtInsert=conn.createStatement();
			stmtInsert.executeUpdate("insert into tblattendance values('"+srlno+"','"+daten+"','"+empid+"','"+empnm+"','"+gender+"','"+dpnm+"','"+shnm+"','"+srttym+"','"+endtym+"','"+status+"')");
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
			srlno=rsuser.getString("srlno");
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			gender=rsuser.getString("gender");
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			dpnm=rsuser.getString("dpnm");
			shnm=rsuser.getString("shnm");
			srttym=rsuser.getString("srttym");
			endtym=rsuser.getString("endtym");
			status=rsuser.getString("status");
			
			//setText

			txtsrlno.setText(srlno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			cmbDtDay.setSelectedItem(date);
            cmbDtMon.setSelectedItem(month);
            cmbDtYr.setSelectedItem(year);
			cmbempid.setSelectedItem(empid);
			cmbstatus.setSelectedItem(status);
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
			srlno=rsuser.getString("srlno");
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			gender=rsuser.getString("gender");
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			dpnm=rsuser.getString("dpnm");
			shnm=rsuser.getString("shnm");
			srttym=rsuser.getString("srttym");
			endtym=rsuser.getString("endtym");
			status=rsuser.getString("status");
			
			//setText

			txtsrlno.setText(srlno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			cmbDtDay.setSelectedItem(date);
            cmbDtMon.setSelectedItem(month);
            cmbDtYr.setSelectedItem(year);
			cmbempid.setSelectedItem(empid);
			cmbstatus.setSelectedItem(status);
		}
		}
		catch(SQLException ex){
			//System.out.println("Unable to Delete"+ex);
			msg.showMessageDialog(f1,"Unable to Show Data","Alert",1);
		}
	}
	else if(ae.getSource()==btnsearch){
					
		//code  for search
		
		String srlno3;
		srlno3=msg.showInputDialog(f1,"Enter Employee ID to Find Record","Message",1);
		if(srlno3!=null){
			System.out.println("Data "+srlno3.length());
		try{
			stmtsearch=conn.createStatement();
			rssearch=stmtsearch.executeQuery("select * from tblattendance where srlno='"+srlno3+"'");
			rssearch.next();
			srlno=rssearch.getString("srlno");
			empid=rssearch.getString("empid");
			empnm=rssearch.getString("empnm");
			gender=rssearch.getString("gender");
			daten=rssearch.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			dpnm=rssearch.getString("dpnm");
			shnm=rssearch.getString("shnm");
			srttym=rssearch.getString("srttym");
			endtym=rssearch.getString("endtym");
			status=rssearch.getString("status");
			
			//setText

			txtsrlno.setText(srlno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			cmbDtDay.setSelectedItem(date);
            cmbDtMon.setSelectedItem(month);
            cmbDtYr.setSelectedItem(year);
			cmbempid.setSelectedItem(empid);
			cmbstatus.setSelectedItem(status);
		}
		catch(SQLException ex){
			//System.out.println("Unable to Delete"+ex);
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
class AttendRegApp{
    public static void main(String args[]) throws Exception  
    {   
        AttendanceWin aw;
        aw=new AttendanceWin();
    }
}