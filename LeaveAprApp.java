import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
class ApprovalWin implements ActionListener{
	JLabel lbltitle,lblsrlno,lbldate,lblrefno,lblapdate,lblempid,lblgen,lblempnm,lbldpnm,lblshift,lblsrttym,lblendtym,lblltype,lbldfrom,lbldto,lblnar,lblsts;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6;
	JTextField txtsrlno,txtapdate,txtempid,txtempnm,txtgen,txtdpnm,txtshift,txtsrttym,txtendtym,txtltype,txtdfrom,txtdto,txtnar;
	String dmy[],dmy1[],dmy2[],cdate[];
	String srlno,daten,date,month,year,refno,empid,empnm,apdate,gender,dpnm,shnm,srttym,endtym,ltype,dfrom,dto,nar,sts;
	String cudate,cumonth,cuyear;
	int Isrlno;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2;
	JPanel p1a;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch,rsrefno,rsrefnodata,rssrlno;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch,stmtrefno,stmtrefnodata,stmtsrlno;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw104;
	JComboBox cmbrefno,cmbDtDay,cmbDtMon,cmbDtYr,cmbsts;
	String crefno[]={"-Select-"};
	String date1[]={"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String month1[]={"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
    String yr1[]={"YYYY","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"}; 
	String sts1[]={"No Choice","Approved","Cancelled"};
	java.util.Date cdt;
	SimpleDateFormat sdf;
	String curDt;
	
public ApprovalWin(){
	
	f1=new JFrame("Leave Approval Management");//frame
		
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
	
	cmbDtDay=new JComboBox(date1);//JComboBox
	cmbDtMon=new JComboBox(month1);//JComboBox
	cmbDtYr=new JComboBox(yr1);//JComboBox
	cmbsts=new JComboBox(sts1);//JComboBox
	
	//JLable Declearation
	
	lbltitle=new JLabel("L E A V E   A P P R O V A L",JLabel.CENTER);
	lbltitle.setFont(fnt1);
	lblsrlno=new JLabel("Serial No :");
	lbldate=new JLabel("    Date :");
	lblrefno=new JLabel("Reference No. :");
	lblapdate=new JLabel("    Apply Date :");
	lblempid=new JLabel("Employee ID :");
	lblgen=new JLabel("    Gender :");
	lblempnm=new JLabel("Employee Name :");
	lbldpnm=new JLabel("Department Name :");
	lblshift=new JLabel("    Shift :");
	lblsrttym=new JLabel("Start Time :");
	lblendtym=new JLabel("    End Time :");
	lblltype=new JLabel("Leave Type :");
	lbldfrom=new JLabel("Date From :");
	lbldto=new JLabel("    To :");
	lblnar=new JLabel("Narration :");
	lblsts=new JLabel("Status :");
	
	lbl1=new JLabel("");
	lbl2=new JLabel("");
	lbl3=new JLabel("");
	lbl4=new JLabel("");
	lbl5=new JLabel("");
	lbl6=new JLabel("");
	
	//SET FONT
	
	lblsrlno.setFont(fnt2);
	lbldate.setFont(fnt2);
	lblrefno.setFont(fnt2);
	lblapdate.setFont(fnt2);
	lblempid.setFont(fnt2);
	lblgen.setFont(fnt2);
	lblempnm.setFont(fnt2);
	lbldpnm.setFont(fnt2);
	lblshift.setFont(fnt2);
	lblsrttym.setFont(fnt2);
	lblendtym.setFont(fnt2);
	lblltype.setFont(fnt2);
	lbldfrom.setFont(fnt2);
	lbldto.setFont(fnt2);
	lblnar.setFont(fnt2);
	lblsts.setFont(fnt2);
	
	//TextField declearation
	
	txtsrlno=new JTextField();
	txtapdate=new JTextField();
	txtempid=new JTextField();
	txtempnm=new JTextField();
	txtgen=new JTextField();
	txtdpnm=new JTextField();
	txtshift=new JTextField();
	txtsrttym=new JTextField();
	txtendtym=new JTextField();
	txtltype=new JTextField();
	txtdfrom=new JTextField();
	txtdto=new JTextField();
	txtnar=new JTextField();
	
	//set Editable(false)
	
	txtsrlno.setEditable(false);
	txtapdate.setEditable(false);
	txtempid.setEditable(false);
	txtempnm.setEditable(false);
	txtgen.setEditable(false);
	txtdpnm.setEditable(false);
	txtshift.setEditable(false);
	txtsrttym.setEditable(false);
	txtendtym.setEditable(false);
	txtltype.setEditable(false);
	txtdfrom.setEditable(false);
	txtdto.setEditable(false);
	txtnar.setEditable(false);
	
	//set tooltiptext(txtfield)
	
	txtsrlno.setToolTipText("Serial Number");
	txtapdate.setToolTipText("Leave Application Apply Date");
	txtempid.setToolTipText("Employee ID");
	txtempnm.setToolTipText("Employee Name");
	txtgen.setToolTipText("Employee Gender");
	txtdpnm.setToolTipText("Department Name");
	txtshift.setToolTipText("Shift Name");
	txtsrttym.setToolTipText("Shift Start Time");
	txtendtym.setToolTipText("Shift End Time");
	txtltype.setToolTipText("Leave Type");
	txtdfrom.setToolTipText("Leave Date From");
	txtdto.setToolTipText("Date To");
	txtnar.setToolTipText("Enter Leave Narration");
	
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
	cmbrefno.addActionListener(this);
	
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
	
	p1.add(lblsrlno);
	p1.add(txtsrlno);
	p1.add(lbldate);
		p1a=new JPanel();
		p1a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1a.add(cmbDtDay);
        p1a.add(cmbDtMon);
        p1a.add(cmbDtYr);
	p1.add(p1a);
	p1.add(lblrefno);
	p1.add(cmbrefno);
	p1.add(lblapdate);
	p1.add(txtapdate);
	p1.add(lblempid);
	p1.add(txtempid);
	p1.add(lbl1);
	p1.add(lbl2);
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
	p1.add(lblltype);
	p1.add(txtltype);
	p1.add(lbl3);
	p1.add(lbl4);
	p1.add(lbldfrom);
	p1.add(txtdfrom);
	p1.add(lbldto);
	p1.add(txtdto);
	p1.add(lblnar);
	p1.add(txtnar);
	p1.add(lbl5);
	p1.add(lbl6);
	p1.add(lblsts);
	p1.add(cmbsts);
	
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
	serialno();
	fillrefnocombo();
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
		rsuser=stmtselect.executeQuery("select * from tblapproval");
	}
	catch(SQLException ex){
		System.out.println("Unable to Fetch data");
	}
} // doconnect ends here
public void fillrefnocombo(){ 
	try{
		stmtrefno=conn.createStatement();
		rsrefno=stmtrefno.executeQuery("select * from tblleave");
		while(rsrefno.next()){
			refno=rsrefno.getString("refno");
			cmbrefno.addItem(refno);
		}
	}
	catch(SQLException ex){
		System.out.println("Unable to create Reference No Combo");
	}
}
public void serialno(){ 
	try{
		stmtsrlno=conn.createStatement();
		rssrlno=stmtsrlno.executeQuery("select count(*) as Isrlno from tblapproval");
		rssrlno.next();
		Isrlno=rssrlno.getInt("Isrlno");
		Isrlno=Isrlno+1;
		txtsrlno.setText(String.valueOf(Isrlno));
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
			txtsrlno.setText(String.valueOf(Isrlno));
		}
			//code to clear
			
			//txtsrlno.setText("");
			txtempid.setText("");
			txtapdate.setText("");
			txtempnm.setText("");
			txtgen.setText("");
			txtdpnm.setText("");
			txtshift.setText("");
			txtsrttym.setText("");
			txtendtym.setText("");
			txtltype.setText("");
			txtdfrom.setText("");
			txtdto.setText("");
			txtnar.setText("");
			cmbDtDay.setSelectedItem(cudate);
			cmbDtMon.setSelectedItem(cumonth);
			cmbDtYr.setSelectedItem(cuyear);
			cmbrefno.setSelectedItem("-Select-");
			cmbsts.setSelectedItem("No Choice");	
	}
	else if(ae.getSource()==cmbrefno){
		refno=cmbrefno.getSelectedItem().toString();
		System.out.println("Ref "+refno);
		
		try{
			stmtrefnodata=conn.createStatement();
			rsrefnodata=stmtrefnodata.executeQuery("select * from tblleave where refno='"+refno+"'");
			if(rsrefnodata.next()){
				empid=rsrefnodata.getString("empid");
				apdate=rsrefnodata.getString("daten");
				empnm=rsrefnodata.getString("empnm");
				gender=rsrefnodata.getString("gender");
				dpnm=rsrefnodata.getString("dpnm");
				shnm=rsrefnodata.getString("shnm");
				srttym=rsrefnodata.getString("srttym");
				endtym=rsrefnodata.getString("endtym");
				ltype=rsrefnodata.getString("ltype");
				dfrom=rsrefnodata.getString("dfrom");
				dto=rsrefnodata.getString("dto");
				nar=rsrefnodata.getString("nar");
				
				txtempnm.setText(empnm);
				txtapdate.setText(apdate);
				txtempid.setText(empid);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				txtltype.setText(ltype);
				txtdfrom.setText(dfrom);
				txtdto.setText(dto);
				txtnar.setText(nar);
				
			}
		}
		catch(SQLException ex){
			System.out.println(ex);
		}	
		
	}
	else if(ae.getSource()==btnsave){
					
		//code for save
		try{
			String srlno2;
			srlno2=txtsrlno.getText();
			stmtsearch=conn.createStatement();
			rsdup=stmtsearch.executeQuery("select * from tblapproval where srlno='"+srlno2+"'");
			if(rsdup.next()){
				msg.showMessageDialog(f1,"The Reference No. is Duplicate","Alert",2);
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
							empid=txtempid.getText();
							empnm=txtempnm.getText();
							apdate=txtapdate.getText();
							gender=txtgen.getText();
							dpnm=txtdpnm.getText();
							shnm=txtshift.getText();
							srttym=txtsrttym.getText();
							endtym=txtendtym.getText();
							dfrom=txtdfrom.getText();
							dto=txtdto.getText();
							ltype=txtltype.getText();
							nar=txtnar.getText();
							refno=cmbrefno.getSelectedItem().toString();
							sts=cmbsts.getSelectedItem().toString();
							
							date=cmbDtDay.getSelectedItem().toString();
							month=cmbDtMon.getSelectedItem().toString();
							year=cmbDtYr.getSelectedItem().toString();
							daten=date+"/"+month+"/"+year;
							
							try{
								stmtInsert=conn.createStatement();
								stmtInsert.executeUpdate("insert into tblapproval values('"+srlno+"','"+daten+"','"+refno+"','"+apdate+"','"+empid+"','"+empnm+"','"+gender+"','"+dpnm+"','"+shnm+"','"+srttym+"','"+endtym+"','"+ltype+"','"+dfrom+"','"+dto+"','"+nar+"','"+sts+"')");
								//System.out.println("One Record Saved Succesfully");
								msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
								Isrlno=Isrlno+1;
								txtsrlno.setText(String.valueOf(Isrlno));
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
				srlno=rsuser.getString("srlno");
				refno=rsuser.getString("refno");
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				gender=rsuser.getString("gender");
				apdate=rsuser.getString("apdate");
				
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];

				dpnm=rsuser.getString("dpnm");
				shnm=rsuser.getString("shnm");
				srttym=rsuser.getString("srttym");
				endtym=rsuser.getString("endtym");
				ltype=rsuser.getString("ltype");
				dfrom=rsuser.getString("dfrom");
				dto=rsuser.getString("dto");
				nar=rsuser.getString("nar");
				
				//setText

				txtsrlno.setText(srlno);
				txtnar.setText(nar);
				txtempid.setText(empid);
				txtempnm.setText(empnm);
				txtapdate.setText(apdate);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				txtdfrom.setText(dfrom);
				txtdto.setText(dto);
				txtltype.setText(ltype);
				
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				
				cmbrefno.setSelectedItem(refno);
				cmbsts.setSelectedItem(sts);
				
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
				refno=rsuser.getString("refno");
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				gender=rsuser.getString("gender");
				apdate=rsuser.getString("apdate");
				
				daten=rsuser.getString("daten");
				dmy=daten.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];

				dpnm=rsuser.getString("dpnm");
				shnm=rsuser.getString("shnm");
				srttym=rsuser.getString("srttym");
				endtym=rsuser.getString("endtym");
				ltype=rsuser.getString("ltype");
				dfrom=rsuser.getString("dfrom");
				dto=rsuser.getString("dto");
				nar=rsuser.getString("nar");
				
				//setText

				txtsrlno.setText(srlno);
				txtnar.setText(nar);
				txtempid.setText(empid);
				txtempnm.setText(empnm);
				txtapdate.setText(apdate);
				txtgen.setText(gender);
				txtdpnm.setText(dpnm);
				txtshift.setText(shnm);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				txtdfrom.setText(dfrom);
				txtdto.setText(dto);
				txtltype.setText(ltype);
				
				cmbDtDay.setSelectedItem(date);
				cmbDtMon.setSelectedItem(month);
				cmbDtYr.setSelectedItem(year);
				
				cmbrefno.setSelectedItem(refno);
				cmbsts.setSelectedItem(sts);
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
			stmtdelete.executeUpdate("delete from tblapproval where srlno='"+srlno+"'");
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
		empid=txtempid.getText();
		empnm=txtempnm.getText();
		apdate=txtapdate.getText();
		gender=txtgen.getText();
		dpnm=txtdpnm.getText();
		shnm=txtshift.getText();
		srttym=txtsrttym.getText();
		endtym=txtendtym.getText();
		dfrom=txtdfrom.getText();
		dto=txtdto.getText();
		ltype=txtltype.getText();
		nar=txtnar.getText();
		refno=cmbrefno.getSelectedItem().toString();
		sts=cmbsts.getSelectedItem().toString();
		
		date=cmbDtDay.getSelectedItem().toString();
		month=cmbDtMon.getSelectedItem().toString();
		year=cmbDtYr.getSelectedItem().toString();
		daten=date+"/"+month+"/"+year;
		
		try{
			stmtInsert=conn.createStatement();
			stmtInsert.executeUpdate("insert into tblleave values('"+srlno+"','"+daten+"','"+refno+"','"+apdate+"','"+empid+"','"+empnm+"','"+gender+"','"+dpnm+"','"+shnm+"','"+srttym+"','"+endtym+"','"+ltype+"','"+dfrom+"','"+dto+"','"+nar+"','"+sts+"')");
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
			refno=rsuser.getString("refno");
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			gender=rsuser.getString("gender");
			apdate=rsuser.getString("apdate");
			
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];

			dpnm=rsuser.getString("dpnm");
			shnm=rsuser.getString("shnm");
			srttym=rsuser.getString("srttym");
			endtym=rsuser.getString("endtym");
			ltype=rsuser.getString("ltype");
			dfrom=rsuser.getString("dfrom");
			dto=rsuser.getString("dto");
			nar=rsuser.getString("nar");
			
			//setText

			txtsrlno.setText(srlno);
			txtnar.setText(nar);
			txtempid.setText(empid);
			txtempnm.setText(empnm);
			txtapdate.setText(apdate);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			txtdfrom.setText(dfrom);
			txtdto.setText(dto);
			txtltype.setText(ltype);
			
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			
			cmbrefno.setSelectedItem(refno);
			cmbsts.setSelectedItem(sts);
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
			refno=rsuser.getString("refno");
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			gender=rsuser.getString("gender");
			apdate=rsuser.getString("apdate");
			
			daten=rsuser.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];

			dpnm=rsuser.getString("dpnm");
			shnm=rsuser.getString("shnm");
			srttym=rsuser.getString("srttym");
			endtym=rsuser.getString("endtym");
			ltype=rsuser.getString("ltype");
			dfrom=rsuser.getString("dfrom");
			dto=rsuser.getString("dto");
			nar=rsuser.getString("nar");
			
			//setText

			txtsrlno.setText(srlno);
			txtnar.setText(nar);
			txtempid.setText(empid);
			txtempnm.setText(empnm);
			txtapdate.setText(apdate);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			txtdfrom.setText(dfrom);
			txtdto.setText(dto);
			txtltype.setText(ltype);
			
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			
			cmbrefno.setSelectedItem(refno);
			cmbsts.setSelectedItem(sts);
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
		srlno3=msg.showInputDialog(f1,"Enter Reference No. to Find Record","Message",1);
		if(srlno3!=null){
			System.out.println("Data "+srlno3.length());
		try{
			stmtsearch=conn.createStatement();
			rssearch=stmtsearch.executeQuery("select * from tblapproval where srlno='"+srlno3+"'");
			rssearch.next();
			srlno=rssearch.getString("srlno");
			refno=rssearch.getString("refno");
			empid=rssearch.getString("empid");
			empnm=rssearch.getString("empnm");
			gender=rssearch.getString("gender");
			apdate=rssearch.getString("apdate");
			
			daten=rssearch.getString("daten");
			dmy=daten.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];

			dpnm=rssearch.getString("dpnm");
			shnm=rssearch.getString("shnm");
			srttym=rssearch.getString("srttym");
			endtym=rssearch.getString("endtym");
			ltype=rssearch.getString("ltype");
			dfrom=rssearch.getString("dfrom");
			dto=rssearch.getString("dto");
			nar=rssearch.getString("nar");
			
			//setText

			txtsrlno.setText(srlno);
			txtnar.setText(nar);
			txtempid.setText(empid);
			txtempnm.setText(empnm);
			txtapdate.setText(apdate);
			txtgen.setText(gender);
			txtdpnm.setText(dpnm);
			txtshift.setText(shnm);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			txtdfrom.setText(dfrom);
			txtdto.setText(dto);
			txtltype.setText(ltype);
			
			cmbDtDay.setSelectedItem(date);
			cmbDtMon.setSelectedItem(month);
			cmbDtYr.setSelectedItem(year);
			
			cmbrefno.setSelectedItem(refno);
			cmbsts.setSelectedItem(sts);
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
class LeaveAprApp{
    public static void main(String args[]) throws Exception  
    {   
        ApprovalWin law;
        law=new ApprovalWin();
    }
}