import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
class PerAppWin implements ActionListener{
	JLabel lbltitle,lblslpno,lbldate,lblempid,lblgen,lblempnm,lbldpnm,lblevby,lbljob,lblconfit,lblcskill,lbliskill,lblintive,lbltwork,lbldimprv,lbloperfom;
	JTextField txtslpno,txtempnm,txtgen,txtdpnm;
	String dmy[],cdate[];
	String slpno,daten,empid,empnm,gender,dpnm,evby,job,confit,cskill,intive,iskill,twork,dimprv,operfom;
	String date,month,year;
	String cudate,cumonth,cuyear;
	int Islpno;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2;
	JPanel p1a;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch,rsempid,rsempiddata,rsslpno,rsempnm;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch,stmtempid,stmtempiddata,stmtslpno,stmtempnm;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw104;
	JComboBox cmbempid,cmbDtDay,cmbDtMon,cmbDtYr,cmbevby,cmbjob,cmbconfit,cmbcskill,cmbiskill,cmbintive,cmbtwork,cmbdimprv,cmboperfom;
	String cempid[]={"-Select-"};
	String date1[]={"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String month1[]={"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
    String yr1[]={"YYYY","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"}; 
	String evby1[]={"-Select-"};
	String job1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String confit1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String cskill1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String iskill1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String intive1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String twork1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String dimprv1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	String operfom1[]={"No Choice","Exceptional","Exceed Expectation","Meets Expectation","Needs Improvement"};
	java.util.Date cdt;
	SimpleDateFormat sdf;
	String curDt;
	
public PerAppWin(){
	
	f1=new JFrame("Performance Appraisal");//frame
		
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
	glw104=new GridLayout(10,4);
	
	//Combo box controls
	
	cmbempid=new JComboBox(cempid);//JComboBox
	cmbDtDay=new JComboBox(date1);//JComboBox
	cmbDtMon=new JComboBox(month1);//JComboBox
	cmbDtYr=new JComboBox(yr1);//JComboBox
	cmbevby=new JComboBox(evby1);//JComboBox
	cmbjob=new JComboBox(job1);//JComboBox
	cmbconfit=new JComboBox(confit1);//JComboBox
	cmbcskill=new JComboBox(cskill1);//JComboBox
	cmbiskill=new JComboBox(iskill1);//JComboBox
	cmbintive=new JComboBox(intive1);//JComboBox
	cmbtwork=new JComboBox(twork1);//JComboBox
	cmbdimprv=new JComboBox(dimprv1);//JComboBox
	cmboperfom=new JComboBox(operfom1);//JComboBox
	
	//JLable Declearation
	
	lbltitle=new JLabel("P E R F O R M A N C E    A P P R A I S A L",JLabel.CENTER);
	lbltitle.setFont(fnt1);
	lblslpno=new JLabel("Slip No :");
	lbldate=new JLabel("    Date :");
	lblempid=new JLabel("Employee ID :");
	lblempnm=new JLabel("Employee Name :");
	lblgen=new JLabel("Gender :");
	lbldpnm=new JLabel("Department Name :");
	lblevby=new JLabel("Evaluated By :");
	lbljob=new JLabel("Job Knowledge :");
	lblconfit=new JLabel("Conflict Resolution :");
	lblcskill=new JLabel("Communication Skill :");
	lbliskill=new JLabel("Interpersonal Skill :");
	lblintive=new JLabel("Initiative :");
	lbltwork=new JLabel("Team Work :");
	lbldimprv=new JLabel("Desire To Improve Quality :");
	lbloperfom=new JLabel("Overall Performance :");

	//SET FONT
	
	lblslpno.setFont(fnt2);
	lbldate.setFont(fnt2);
	lblempid.setFont(fnt2);
	lblgen.setFont(fnt2);
	lblempnm.setFont(fnt2);
	lbldpnm.setFont(fnt2);
	lblevby.setFont(fnt2);
	lbljob.setFont(fnt2);
	lblconfit.setFont(fnt2);
	lblcskill.setFont(fnt2);
	lbliskill.setFont(fnt2);
	lblintive.setFont(fnt2);
	lbltwork.setFont(fnt2);
	lbldimprv.setFont(fnt2);
	lbloperfom.setFont(fnt2);
	
	//TextField declearation
	
	txtslpno=new JTextField();
	txtempnm=new JTextField();
	txtgen=new JTextField();
	txtdpnm=new JTextField();
	
	//set Editable(false)
	
	txtslpno.setEditable(false);
	txtempnm.setEditable(false);
	txtgen.setEditable(false);
	txtdpnm.setEditable(false);
	
	//set tooltiptext(txtfield)
	
	txtslpno.setToolTipText("Slip Noumber");
	txtempnm.setToolTipText("Employee Name");
	txtgen.setToolTipText("Employee Gender");
	txtdpnm.setToolTipText("Department Name");
	
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
	
	//setLayout
	
	ptitle.setLayout(flw);
	p1.setLayout(glw104);
	p2.setLayout(flw);
	
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
		p1a=new JPanel();
		p1a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1a.add(cmbDtDay);
        p1a.add(cmbDtMon);
        p1a.add(cmbDtYr);
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
	p1.add(new JLabel(""));
	p1.add(new JLabel(""));
	p1.add(lblevby);
	p1.add(cmbevby);
	p1.add(new JLabel(""));
	p1.add(new JLabel(""));
	p1.add(lbljob);
	p1.add(cmbjob);
	p1.add(lblconfit);
	p1.add(cmbconfit);
	p1.add(lblcskill);
	p1.add(cmbcskill);
	p1.add(lbliskill);
	p1.add(cmbiskill);
	p1.add(lblintive);
	p1.add(cmbintive);
	p1.add(lbltwork);
	p1.add(cmbtwork);
	p1.add(lbldimprv);
	p1.add(cmbdimprv);
	p1.add(lbloperfom);
	p1.add(cmboperfom);
	
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
		
	vb.add(Box.createVerticalStrut(50));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(60));
	vb.add(p1);
	vb.add(Box.createVerticalStrut(60));
	vb.add(p2);
	vb.add(Box.createVerticalStrut(50));
	hb.add(Box.createHorizontalStrut(50));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(50));
	
	//f1 control
	
	f1.setLayout(new BorderLayout());
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(83,0,1200,766);
	//f1.setResizable(false);
	doconnect();
	slipno();
	fillempidcombo();
	fillempnmcombo();
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
		rsuser=stmtselect.executeQuery("select * from tblperformance");
	}
	catch(SQLException ex){
		System.out.println("Unable to Fetch data");
	}
} // doconnect ends here
public void slipno(){ 
	try{
		stmtslpno=conn.createStatement();
		rsslpno=stmtslpno.executeQuery("select count(*) as Islpno from tblperformance");
		rsslpno.next();
		Islpno=rsslpno.getInt("Islpno");
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
public void fillempnmcombo(){ 
	try{
		stmtempnm=conn.createStatement();
		rsempnm=stmtempnm.executeQuery("select * from tblemployee");
		while(rsempnm.next()){
			empnm=rsempnm.getString("empnm");
			cmbevby.addItem(empnm);
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
			//Islno=Islno+1;
			txtslpno.setText(String.valueOf(Islpno));
		}
			//code to clear
			
			//txtslpno.setText("");
			txtempnm.setText("");
			txtgen.setText("");
			txtdpnm.setText("");
			cmbDtDay.setSelectedItem(cudate);
			cmbDtMon.setSelectedItem(cumonth);
			cmbDtYr.setSelectedItem(cuyear);
			cmbempid.setSelectedItem("-Select-");
			cmbevby.setSelectedItem("-Select-");
			cmbjob.setSelectedItem("No Choice");
			cmbconfit.setSelectedItem("No Choice");
			cmbcskill.setSelectedItem("No Choice");
			cmbiskill.setSelectedItem("No Choice");
			cmbintive.setSelectedItem("No Choice");
			cmbtwork.setSelectedItem("No Choice");
			cmbdimprv.setSelectedItem("No Choice");
			cmboperfom.setSelectedItem("No Choice");
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
				txtempnm.setText(empnm);
				txtdpnm.setText(dpnm);
				txtgen.setText(gender);
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
			rsdup=stmtsearch.executeQuery("select * from tblperformance where slpno='"+slpno2+"'");
			if(rsdup.next()){
				msg.showMessageDialog(f1,"The Reference No. is Duplicate","Alert",2);
			}
			else{
				empnm=txtempnm.getText(); 
				gender=txtgen.getText();
				dpnm=txtdpnm.getText();
					if(empnm.length()==0||gender.length()==0||dpnm.length()==0){
						msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
					}
					else{
						int choice;
						choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
						if(choice==0){
							slpno=txtslpno.getText();
							empnm=txtempnm.getText();
							gender=txtgen.getText();
							dpnm=txtdpnm.getText();
							empid=cmbempid.getSelectedItem().toString();
							evby=cmbevby.getSelectedItem().toString();
							job=cmbjob.getSelectedItem().toString();
							confit=cmbconfit.getSelectedItem().toString();
							cskill=cmbcskill.getSelectedItem().toString();
							iskill=cmbiskill.getSelectedItem().toString();
							intive=cmbintive.getSelectedItem().toString();
							twork=cmbtwork.getSelectedItem().toString();
							dimprv=cmbdimprv.getSelectedItem().toString();
							operfom=cmboperfom.getSelectedItem().toString();
							
							date=cmbDtDay.getSelectedItem().toString();
							month=cmbDtMon.getSelectedItem().toString();
							year=cmbDtYr.getSelectedItem().toString();
							daten=date+"/"+month+"/"+year;
							
							try{
								stmtInsert=conn.createStatement();
								stmtInsert.executeUpdate("insert into tblperformance values('"+slpno+"','"+daten+"','"+empid+"','"+empnm+"','"+gender+"','"+dpnm+"','"+evby+"','"+job+"','"+confit+"','"+cskill+"','"+iskill+"','"+intive+"','"+twork+"','"+dimprv+"','"+operfom+"')");
								//System.out.println("One Record Saved Succesfully");
								msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
								Islpno=Islpno+1;
								txtslpno.setText(String.valueOf(Islpno));
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
				
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];

				dpnm=rsuser.getString("dpnm");
				evby=rsuser.getString("evby");
				job=rsuser.getString("job");
				confit=rsuser.getString("confit");
				cskill=rsuser.getString("cskill");
				iskill=rsuser.getString("iskill");
				intive=rsuser.getString("intive");
				twork=rsuser.getString("twork");
				dimprv=rsuser.getString("dimprv");
				operfom=rsuser.getString("operfom");
				
				//setText

				txtslpno.setText(slpno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				
				cmbempid.setSelectedItem(empid);
				cmbevby.setSelectedItem(evby);
				cmbjob.setSelectedItem(job);
				cmbconfit.setSelectedItem(confit);
				cmbcskill.setSelectedItem(cskill);
				cmbiskill.setSelectedItem(iskill);
				cmbintive.setSelectedItem(intive);
				cmbtwork.setSelectedItem(twork);
				cmbdimprv.setSelectedItem(dimprv);
				cmboperfom.setSelectedItem(operfom);
				
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
				
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];

				dpnm=rsuser.getString("dpnm");
				evby=rsuser.getString("evby");
				job=rsuser.getString("job");
				confit=rsuser.getString("confit");
				cskill=rsuser.getString("cskill");
				iskill=rsuser.getString("iskill");
				intive=rsuser.getString("intive");
				twork=rsuser.getString("twork");
				dimprv=rsuser.getString("dimprv");
				operfom=rsuser.getString("operfom");
				
				//setText

				txtslpno.setText(slpno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				
				cmbempid.setSelectedItem(empid);
				cmbevby.setSelectedItem(evby);
				cmbjob.setSelectedItem(job);
				cmbconfit.setSelectedItem(confit);
				cmbcskill.setSelectedItem(cskill);
				cmbiskill.setSelectedItem(iskill);
				cmbintive.setSelectedItem(intive);
				cmbtwork.setSelectedItem(twork);
				cmbdimprv.setSelectedItem(dimprv);
				cmboperfom.setSelectedItem(operfom);
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
			stmtdelete.executeUpdate("delete from tblperformance where slpno='"+slpno+"'");
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
		empnm=txtempnm.getText();
		gender=txtgen.getText();
		dpnm=txtdpnm.getText();
		empid=cmbempid.getSelectedItem().toString();
		evby=cmbevby.getSelectedItem().toString();
		job=cmbjob.getSelectedItem().toString();
		confit=cmbconfit.getSelectedItem().toString();
		cskill=cmbcskill.getSelectedItem().toString();
		iskill=cmbiskill.getSelectedItem().toString();
		intive=cmbintive.getSelectedItem().toString();
		twork=cmbtwork.getSelectedItem().toString();
		dimprv=cmbdimprv.getSelectedItem().toString();
		operfom=cmboperfom.getSelectedItem().toString();
		
		date=cmbDtDay.getSelectedItem().toString();
		month=cmbDtMon.getSelectedItem().toString();
		year=cmbDtYr.getSelectedItem().toString();
		daten=date+"/"+month+"/"+year;
		
		try{
			stmtInsert=conn.createStatement();
			stmtInsert.executeUpdate("insert into tblperformance values('"+slpno+"','"+daten+"','"+empid+"','"+empnm+"','"+gender+"','"+dpnm+"','"+evby+"','"+job+"','"+confit+"','"+cskill+"','"+iskill+"','"+intive+"','"+twork+"','"+dimprv+"','"+operfom+"')");
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
				
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];

				dpnm=rsuser.getString("dpnm");
				evby=rsuser.getString("evby");
				job=rsuser.getString("job");
				confit=rsuser.getString("confit");
				cskill=rsuser.getString("cskill");
				iskill=rsuser.getString("iskill");
				intive=rsuser.getString("intive");
				twork=rsuser.getString("twork");
				dimprv=rsuser.getString("dimprv");
				operfom=rsuser.getString("operfom");
				
				//setText

				txtslpno.setText(slpno);
				txtempnm.setText(empnm);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				
				cmbempid.setSelectedItem(empid);
				cmbevby.setSelectedItem(evby);
				cmbjob.setSelectedItem(job);
				cmbconfit.setSelectedItem(confit);
				cmbcskill.setSelectedItem(cskill);
				cmbiskill.setSelectedItem(iskill);
				cmbintive.setSelectedItem(intive);
				cmbtwork.setSelectedItem(twork);
				cmbdimprv.setSelectedItem(dimprv);
				cmboperfom.setSelectedItem(operfom);
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
			
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];

			dpnm=rsuser.getString("dpnm");
			evby=rsuser.getString("evby");
			job=rsuser.getString("job");
			confit=rsuser.getString("confit");
			cskill=rsuser.getString("cskill");
			iskill=rsuser.getString("iskill");
			intive=rsuser.getString("intive");
			twork=rsuser.getString("twork");
			dimprv=rsuser.getString("dimprv");
			operfom=rsuser.getString("operfom");
			
			//setText

			txtslpno.setText(slpno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			
			cmbempid.setSelectedItem(empid);
			cmbevby.setSelectedItem(evby);
			cmbjob.setSelectedItem(job);
			cmbconfit.setSelectedItem(confit);
			cmbcskill.setSelectedItem(cskill);
			cmbiskill.setSelectedItem(iskill);
			cmbintive.setSelectedItem(intive);
			cmbtwork.setSelectedItem(twork);
			cmbdimprv.setSelectedItem(dimprv);
			cmboperfom.setSelectedItem(operfom);
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
		slpno3=msg.showInputDialog(f1,"Enter Reference No. to Find Record","Message",1);
		if(slpno3!=null){
			System.out.println("Data "+slpno3.length());
		try{
			stmtsearch=conn.createStatement();
			rssearch=stmtsearch.executeQuery("select * from tblperformance where slpno='"+slpno3+"'");
			rssearch.next();
			slpno=rssearch.getString("slpno");
			empid=rssearch.getString("empid");
			empnm=rssearch.getString("empnm");
			gender=rssearch.getString("gender");
			
			daten=rssearch.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];

			dpnm=rssearch.getString("dpnm");
			evby=rssearch.getString("evby");
			job=rssearch.getString("job");
			confit=rssearch.getString("confit");
			cskill=rssearch.getString("cskill");
			iskill=rssearch.getString("iskill");
			intive=rssearch.getString("intive");
			twork=rssearch.getString("twork");
			dimprv=rssearch.getString("dimprv");
			operfom=rssearch.getString("operfom");
			
			//setText

			txtslpno.setText(slpno);
			txtempnm.setText(empnm);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			
			cmbempid.setSelectedItem(empid);
			cmbevby.setSelectedItem(evby);
			cmbjob.setSelectedItem(job);
			cmbconfit.setSelectedItem(confit);
			cmbcskill.setSelectedItem(cskill);
			cmbiskill.setSelectedItem(iskill);
			cmbintive.setSelectedItem(intive);
			cmbtwork.setSelectedItem(twork);
			cmbdimprv.setSelectedItem(dimprv);
			cmboperfom.setSelectedItem(operfom);
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
class PerformanceApp{
    public static void main(String args[]) throws Exception  
    {   
        PerAppWin paw;
        paw=new PerAppWin();
    }
}
	
	
	