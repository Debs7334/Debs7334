import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class EmployeeWin implements ActionListener{
	JLabel lbltitle,lblempid,lblempnm,lblgen,lbldob,lblfnm,lblloc,lblcity,lblstate,lblpin,lblpno,lblmail,lbldpnm,lbldojoin,lblshnm,lblsrttym,lblendtym,lblbsicsal,lblda,lblpf,lblhra,lbltax;
	JTextField txtempid,txtempnm,txtfnm,txtloc,txtcity,txtpin,txtpno,txtmail,txtsrttym,txtendtym,txtdojoin,txtbsicsal,txtda,txtpf,txthra,txttax;
	String dmy[];
	String empid,empnm,gen,dob,fnm,loc,city,state,pin,pno,mail,dpnm,dojoin,shnm,srttym,endtym,bsicsal,da,pf,hra,tax,date,month,year,gender;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2,p3,p4,p5,p6;
	JRadioButton rbm,rbf;
	ButtonGroup bggen;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch,rsshift;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch,stmtshift;
	Statement stmtshiftdata;		
	ResultSet rsshiftdata;
	Statement stmtdept;
	ResultSet rsdept;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw22,glw12,glw94;
	JComboBox cmbstate,cmbDoBDay,cmbDoBMon,cmbDobYr,cmbshift,cmbdept;
	String shift[]={"-Select-"};//,"Morning","Day","Evening","Night"};
	String dept[]={"-Select-"};//,"Marketing Department","Operations Department","Sales Department","Human Resource Department","Purchase Department"};
	String date1[]={"DD","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","21","22","23","24","25","26","27","28","29","30","31"};
	String month1[]={"MM","01","02","03","04","05","06","07","08","09","10","11","12"};
    String yr[]={"YYYY","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003"};
	String state1[]={"No Choice","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal","Andaman and Nicobar Islands","Chandigarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Lakshadweep","Pondicherry"};
	
public EmployeeWin(){
	f1=new JFrame("EMPLOYEE INFORMATION MANAGEMENT");//frame
		
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
	p4=new JPanel();//panel
	p5=new JPanel();//panel
	p6=new JPanel();//panel
	
	//Layaout Controls
		
	flw=new FlowLayout();
	glw22=new GridLayout(2,2);
	glw12=new GridLayout(1,2);
	glw94=new GridLayout(9,4);

	//setLayout
	
	ptitle.setLayout(flw);
	p1.setLayout(glw22);
	p2.setLayout(glw12);
	p3.setLayout(glw12);
	p4.setLayout(glw12);
	p5.setLayout(glw94);
	p6.setLayout(flw);
	
	//Combo box controls
	
	cmbstate=new JComboBox(state1);//JComboBox
	cmbDoBDay=new JComboBox(date1);//JComboBox
	cmbDoBMon=new JComboBox(month1);//JComboBox
	cmbDobYr=new JComboBox(yr);//JComboBox
	cmbshift=new JComboBox(shift);//JComboBox
	cmbdept=new JComboBox(dept);//JComboBox
	
	//ButtonGroup Declearation//RadioButton control
	
	bggen=new ButtonGroup();
	rbm=new JRadioButton("Male");
	rbf=new JRadioButton("Female");
	bggen.add(rbm);
	bggen.add(rbf);
	
	//JLable Declearation
	
	lbltitle=new JLabel("E M P L O Y E E   I N F O R M A T I O N",JLabel.CENTER);
	lbltitle.setFont(fnt1);
	lblempid=new JLabel("Employee ID :");
	lblempnm=new JLabel("Employee Name :");
	lblgen=new JLabel("Gender :");
	lbldob=new JLabel("Date Of Birth :");
	lblfnm=new JLabel("Father Name :");
	lblloc=new JLabel("Locality :");
	lblcity=new JLabel("City :");
	lblstate=new JLabel("State/U.Tertiary :");
	lblpin=new JLabel("Pin Code :");
	lblpno=new JLabel("Phone Number :");
	lblmail=new JLabel("E-Mail ID :");
	lbldpnm=new JLabel("Department Name :");
	lbldojoin=new JLabel("Date Of Join :");
	lblshnm=new JLabel("Shift Name :");
	lblsrttym=new JLabel("Start Time :");
	lblendtym=new JLabel("End Time :");
	lblbsicsal=new JLabel("Basic Salary :");
	lblda=new JLabel("DA in Percentage :");
	lblpf=new JLabel("PF in Percentage :");
	lblhra=new JLabel("HRA in Percentage :");
	lbltax=new JLabel("Tax in Percentage :");
	
	//SET FONT
	
	lblempid.setFont(fnt2);
	lblempnm.setFont(fnt2);
	lblgen.setFont(fnt2);
	lbldob.setFont(fnt2);
	lblfnm.setFont(fnt2);
	lblloc.setFont(fnt2);
	lblcity.setFont(fnt2);
	lblstate.setFont(fnt2);
	lblpin.setFont(fnt2);
	lblpno.setFont(fnt2);
	lblmail.setFont(fnt2);
	lbldpnm.setFont(fnt2);
	lbldojoin.setFont(fnt2);
	lblshnm.setFont(fnt2);
	lblsrttym.setFont(fnt2);
	lblendtym.setFont(fnt2);
	lblbsicsal.setFont(fnt2);
	lblda.setFont(fnt2);
	lblpf.setFont(fnt2);
	lblhra.setFont(fnt2);
	lbltax.setFont(fnt2);
	
	//TextField declearation
		
	txtempid=new JTextField(30);
	txtempnm=new JTextField(30);
	txtfnm=new JTextField(30);
	txtloc=new JTextField(30);
	txtcity=new JTextField(30);
	txtpin=new JTextField(30);
	txtpno=new JTextField(30);
	txtmail=new JTextField(30);
	txtdojoin=new JTextField(30);
	txtsrttym=new JTextField(30);
	txtendtym=new JTextField(30);
	txtbsicsal=new JTextField(30);
	txtda=new JTextField(30);
	txtpf=new JTextField(30);
	txthra=new JTextField(30);
	txttax=new JTextField(30);
	
	//set tooltiptext(txtfield)
	
	txtempnm.setToolTipText("Enter Employee Name");
	txtempid.setToolTipText("Enter Employee ID");
	txtfnm.setToolTipText("Enter Father Name");
	txtloc.setToolTipText("Enter Your Locality");
	txtcity.setToolTipText("Enter Your City Name");
	txtdojoin.setToolTipText("Enter Your Join Date(DD/MM/YYYY)");
	txtpin.setToolTipText("Enter Your Pin-Code");
	txtpno.setToolTipText("Enter Your Phone Number");
	txtmail.setToolTipText("Enter Your E-mail ID");
	//txtdpnm.setToolTipText("Enter Department Name");
	//txtshnm.setToolTipText("Enter Shift Name");
	txtsrttym.setToolTipText("Enter Shift Start Time");
	txtendtym.setToolTipText("Enter Shift End Time");
	txtbsicsal.setToolTipText("Enter Basic Salary Of Employee");
	txtda.setToolTipText("Enter Dearness Allowance Percentage");
	txtpf.setToolTipText("Enter Provident Fund Percentage");
	txthra.setToolTipText("Enter House Rent Allowance Percentage");
	txttax.setToolTipText("Enter Tax Percentage");
	
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
	cmbshift.addActionListener(this);
	
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
	
	//p1 control
	
	p1.add(lblempid);
	p1.add(txtempid);
	p1.add(lblempnm);
	p1.add(txtempnm);

	//p2 control
	
	p2.add(lblgen);
	JPanel p2a;
		p2a=new JPanel();
		p2a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2a.add(rbm);
		p2a.add(rbf);
	p2.add(p2a);
	
	//p3 control
	
    p3.add(lbldob);
	JPanel p3a;
		p3a=new JPanel();
		p3a.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3a.add(cmbDoBDay);
        p3a.add("MONTH",cmbDoBMon);
        p3a.add("YEAR",cmbDobYr);
	p3.add(p3a);
	
	//p4 control
	
	p4.add(lblfnm);
	p4.add(txtfnm);
	
	//p5 control
	
	p5.add(lblloc);
	p5.add(txtloc);
	p5.add(lblcity);
	p5.add(txtcity);
	p5.add(lblstate);
	p5.add(cmbstate);
	p5.add(lblpin);
	p5.add(txtpin);
	p5.add(lblpno);
	p5.add(txtpno);
	p5.add(lblmail);
	p5.add(txtmail);
	p5.add(lbldpnm);
	p5.add(cmbdept);
	p5.add(lbldojoin);
	p5.add(txtdojoin);
	p5.add(lblshnm);
	p5.add(cmbshift);
	p5.add(new JLabel(""));
	p5.add(new JLabel(""));
	p5.add(lblsrttym);
	p5.add(txtsrttym);
	p5.add(lblendtym);
	p5.add(txtendtym);
	p5.add(lblbsicsal);
	p5.add(txtbsicsal);
	p5.add(new JLabel(""));
	p5.add(new JLabel(""));
	p5.add(lblda);
	p5.add(txtda);
	p5.add(lblpf);
	p5.add(txtpf);
	p5.add(lblhra);
	p5.add(txthra);
	p5.add(lbltax);
	p5.add(txttax);
	
	//p6 controls
	
	p6.setBorder(BorderFactory.createTitledBorder(" "));
	p6.setBackground(new Color(204,204,204));
	p6.add(btnclear);
	p6.add(btnsave);
	p6.add(btnpre);
	p6.add(btnnext);
	p6.add(btnfirst);
	p6.add(btnlast);
	p6.add(btnsearch);
	p6.add(btndlt);
	p6.add(btnupdate);
	p6.add(btnclose);
	
	//Box control
		
	vb.add(Box.createVerticalStrut(40));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(30));
	vb.add(p1);
	vb.add(p2);
	vb.add(p3);
	vb.add(p4);
	vb.add(p5);
	vb.add(Box.createVerticalStrut(30));
	vb.add(p6);
	vb.add(Box.createVerticalStrut(40));
	hb.add(Box.createHorizontalStrut(40));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(40));
	
	//f1 control
	
	f1.setLayout(new BorderLayout());
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(183,33,1000,700);
	//f1.setResizable(false);
	doconnect();
	fillshiftcombo();
	filldeptcombo();
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
		rsuser=stmtselect.executeQuery("select * from tblemployee");
	}
	catch(SQLException ex){
		System.out.println("Unable to Fetch data");
	}
} // doconnect ends here
public void fillshiftcombo(){ 
	try{
		stmtshift=conn.createStatement();
		rsshift=stmtshift.executeQuery("select * from tblshift");
		while(rsshift.next()){
			shnm=rsshift.getString("sfnm");
			cmbshift.addItem(shnm);
		}
	}
	catch(SQLException ex){
		System.out.println("Unable to create Shift Combo");
	}
}
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
	if(ae.getSource()==btnclear){
		
		//code to clear
		
		txtempid.setText("");
		txtempnm.setText("");
		txtfnm.setText("");
		txtloc.setText("");
		txtcity.setText("");
		txtpin.setText("");
		txtpno.setText("");
		txtmail.setText("");
		txtsrttym.setText(" ");
		txtendtym.setText(" ");
		txtdojoin.setText("");
		txtbsicsal.setText("");
		txtda.setText("");
		txtpf.setText("");
		txthra.setText("");
		txttax.setText("");
		rbm.setSelected(false);
		rbf.setSelected(false);
		cmbDoBDay.setSelectedItem("DD");
		cmbDoBMon.setSelectedItem("MM");
		cmbDobYr.setSelectedItem("YYYY");
		cmbdept.setSelectedItem("-Select-");
		cmbshift.setSelectedItem("-Select-");
		cmbstate.setSelectedItem("No Choice");	
		
	}
	else if(ae.getSource()==cmbshift){
		shnm=cmbshift.getSelectedItem().toString();
		try{
			stmtshiftdata=conn.createStatement();
			rsshiftdata=stmtshiftdata.executeQuery("select * from tblshift where sfnm='"+shnm+"'");
			if(rsshiftdata.next()){
				srttym=rsshiftdata.getString("srttym");
				endtym=rsshiftdata.getString("endtym");
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
			String empid2;
			empid2=txtempid.getText();
			stmtsearch=conn.createStatement();
			rsdup=stmtsearch.executeQuery("select * from tblemployee where empid='"+empid2+"'");
			if(rsdup.next()){
				msg.showMessageDialog(f1,"The Employee ID is Duplicate","Alert",2);
			}
			else{
				empid=txtempid.getText();
				empnm=txtempnm.getText();
				fnm=txtfnm.getText();
				loc=txtloc.getText();
				city=txtcity.getText();
				pin=txtpin.getText();
				pno=txtpno.getText();
				mail=txtmail.getText();
				srttym=txtsrttym.getText();
				endtym=txtendtym.getText();
				dojoin=txtdojoin.getText();
				bsicsal=txtbsicsal.getText();
				da=txtda.getText();
				pf=txtpf.getText();
				hra=txthra.getText();
				tax=txttax.getText();
					if(empid.length()==0||empnm.length()==0||fnm.length()==0||city.length()==0||pin.length()==0||pno.length()==0||dojoin.length()==0||srttym.length()==0||endtym.length()==0||bsicsal.length()==0||da.length()==0||pf.length()==0||hra.length()==0||tax.length()==0){
						msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
					}
					else{
						int choice;
						choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
						if(choice==0){
							empid=txtempid.getText();
							empnm=txtempnm.getText();
							fnm=txtfnm.getText();
							loc=txtloc.getText();
							city=txtcity.getText();
							pin=txtpin.getText();
							pno=txtpno.getText();
							mail=txtmail.getText();
							srttym=txtsrttym.getText();
							endtym=txtendtym.getText();
							dojoin=txtdojoin.getText();
							bsicsal=txtbsicsal.getText();
							da=txtda.getText();
							pf=txtpf.getText();
							hra=txthra.getText();
							tax=txttax.getText();
							date=cmbDoBDay.getSelectedItem().toString();
							month=cmbDoBMon.getSelectedItem().toString();
							year=cmbDobYr.getSelectedItem().toString();
							dob=date+"/"+month+"/"+year;
							state=cmbstate.getSelectedItem().toString();
							dpnm=cmbdept.getSelectedItem().toString();
							shnm=cmbshift.getSelectedItem().toString();
							if(rbm.isSelected())
								gender="Male";
							else if(rbf.isSelected())
								gender="Female";
							try{
								stmtInsert=conn.createStatement();
								stmtInsert.executeUpdate("insert into tblemployee values('"+empid+"','"+empnm+"','"+gender+"','"+dob+"','"+fnm+"','"+loc+"','"+city+"','"+state+"','"+pin+"','"+pno+"','"+mail+"','"+dpnm+"','"+dojoin+"','"+shnm+"','"+srttym+"','"+endtym+"','"+bsicsal+"','"+da+"','"+pf+"','"+hra+"','"+tax+"')");
								//System.out.println("One Record Saved Succesfully");
								msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
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
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				//gmale=rsuser.getString("gmale");
				//gfemale=rsuser.getString("gfemale");
				gender=rsuser.getString("gender");
				dob=rsuser.getString("dob");
				dmy=dob.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];
				fnm=rsuser.getString("fnm");
				loc=rsuser.getString("loc");
				city=rsuser.getString("city");
				state=rsuser.getString("state");
				pin=rsuser.getString("pin");
				pno=rsuser.getString("pno");
				mail=rsuser.getString("mail");
				dpnm=rsuser.getString("dpnm");
				dojoin=rsuser.getString("dojoin");
				shnm=rsuser.getString("shnm");
				srttym=rsuser.getString("srttym");
				endtym=rsuser.getString("endtym");
				bsicsal=rsuser.getString("bsicsal");
				da=rsuser.getString("da");
				pf=rsuser.getString("pf");
				hra=rsuser.getString("hra");
				tax=rsuser.getString("tax");
				
				//setText

				txtempid.setText(empid);
				txtempnm.setText(empnm);
				txtfnm.setText(fnm);
				txtloc.setText(loc);
				txtcity.setText(city);
				txtpin.setText(pin);
				txtpno.setText(pno);
				txtmail.setText(mail);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				txtdojoin.setText(dojoin);
				txtbsicsal.setText(bsicsal);
				txtda.setText(da);
				txtpf.setText(pf);
				txthra.setText(hra);
				txttax.setText(tax);
				cmbDoBDay.setSelectedItem(date);
				cmbDoBMon.setSelectedItem(month);
				cmbDobYr.setSelectedItem(year);
				cmbdept.setSelectedItem(dpnm);
				cmbshift.setSelectedItem(shnm);
				cmbstate.setSelectedItem(state);
				if(gender.equals("Male"))
					rbm.setSelected(true);
				else if(gender.equals("Female"))
					rbf.setSelected(true);
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
				empid=rsuser.getString("empid");
				empnm=rsuser.getString("empnm");
				//gmale=rsuser.getString("gmale");
				//gfemale=rsuser.getString("gfemale");
				gender=rsuser.getString("gender");
				dob=rsuser.getString("dob");
				dmy=dob.split("/");
				date=dmy[0];
				month=dmy[1];
				year=dmy[2];
				fnm=rsuser.getString("fnm");
				loc=rsuser.getString("loc");
				city=rsuser.getString("city");
				state=rsuser.getString("state");
				pin=rsuser.getString("pin");
				pno=rsuser.getString("pno");
				mail=rsuser.getString("mail");
				dpnm=rsuser.getString("dpnm");
				dojoin=rsuser.getString("dojoin");
				shnm=rsuser.getString("shnm");
				srttym=rsuser.getString("srttym");
				endtym=rsuser.getString("endtym");
				bsicsal=rsuser.getString("bsicsal");
				da=rsuser.getString("da");
				pf=rsuser.getString("pf");
				hra=rsuser.getString("hra");
				tax=rsuser.getString("tax");
				
				//setText

				txtempid.setText(empid);
				txtempnm.setText(empnm);
				txtfnm.setText(fnm);
				txtloc.setText(loc);
				txtcity.setText(city);
				txtpin.setText(pin);
				txtpno.setText(pno);
				txtmail.setText(mail);
				txtsrttym.setText(srttym);
				txtendtym.setText(endtym);
				txtdojoin.setText(dojoin);
				txtbsicsal.setText(bsicsal);
				txtda.setText(da);
				txtpf.setText(pf);
				txthra.setText(hra);
				txttax.setText(tax);
				cmbDoBDay.setSelectedItem(date);
				cmbDoBMon.setSelectedItem(month);
				cmbDobYr.setSelectedItem(year);
				cmbdept.setSelectedItem(dpnm);
				cmbshift.setSelectedItem(shnm);
				cmbstate.setSelectedItem(state);
				if(gender.equals("Male"))
					rbm.setSelected(true);
				else if(gender.equals("Female"))
					rbf.setSelected(true);
			}
		}
		catch(SQLException ex){
			//System.out.println("Unable to go previous");
			msg.showMessageDialog(f1,"Unable to go previous","Message",1);
		}
	}
	else if(ae.getSource()==btndlt){
					
		//code for delete	

			empid=txtempid.getText();
			int choice;
			choice=msg.showConfirmDialog(f1,"Are You Sure to Delete ?","Alert",2);
		if(choice==0){
		try{
			stmtdelete=conn.createStatement();
			stmtdelete.executeUpdate("delete from tblemployee where empid='"+empid+"'");
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

		empid=txtempid.getText();
		empnm=txtempnm.getText();
		fnm=txtfnm.getText();
		loc=txtloc.getText();
		city=txtcity.getText();
		pin=txtpin.getText();
		pno=txtpno.getText();
		mail=txtmail.getText();
		srttym=txtsrttym.getText();
		endtym=txtendtym.getText();
		dojoin=txtdojoin.getText();
		bsicsal=txtbsicsal.getText();
		da=txtda.getText();
		pf=txtpf.getText();
		hra=txthra.getText();
		tax=txttax.getText();
		date=cmbDoBDay.getSelectedItem().toString();
		month=cmbDoBMon.getSelectedItem().toString();
		year=cmbDobYr.getSelectedItem().toString();
		dob=date+"/"+month+"/"+year;
		state=cmbstate.getSelectedItem().toString();
		dpnm=cmbdept.getSelectedItem().toString();
		shnm=cmbshift.getSelectedItem().toString();
		if(rbm.isSelected())
			gender="Male";
		else if(rbf.isSelected())
			gender="Female";	
		try{
			stmtInsert=conn.createStatement();
			stmtInsert.executeUpdate("insert into tblemployee values('"+empid+"','"+empnm+"','"+gender+"','"+dob+"','"+fnm+"','"+loc+"','"+city+"','"+state+"','"+pin+"','"+pno+"','"+mail+"','"+dpnm+"','"+dojoin+"','"+shnm+"','"+srttym+"','"+endtym+"','"+bsicsal+"','"+da+"','"+pf+"','"+hra+"','"+tax+"')");
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
			//rsuser.next();
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			//gmale=rsuser.getString("gmale");
			//gfemale=rsuser.getString("gfemale");
			gender=rsuser.getString("gender");
			dob=rsuser.getString("dob");
			dmy=dob.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			fnm=rsuser.getString("fnm");
			loc=rsuser.getString("loc");
			city=rsuser.getString("city");
			state=rsuser.getString("state");
			pin=rsuser.getString("pin");
			pno=rsuser.getString("pno");
			mail=rsuser.getString("mail");
			dpnm=rsuser.getString("dpnm");
			dojoin=rsuser.getString("dojoin");
			shnm=rsuser.getString("shnm");
			srttym=rsuser.getString("srttym");
			endtym=rsuser.getString("endtym");
			bsicsal=rsuser.getString("bsicsal");
			da=rsuser.getString("da");
			pf=rsuser.getString("pf");
			hra=rsuser.getString("hra");
			tax=rsuser.getString("tax");
			
			//setText

			txtempid.setText(empid);
			txtempnm.setText(empnm);
			txtfnm.setText(fnm);
			txtloc.setText(loc);
			txtcity.setText(city);
			txtpin.setText(pin);
			txtpno.setText(pno);
			txtmail.setText(mail);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			txtdojoin.setText(dojoin);
			txtbsicsal.setText(bsicsal);
			txtda.setText(da);
			txtpf.setText(pf);
			txthra.setText(hra);
			txttax.setText(tax);
			cmbDoBDay.setSelectedItem(date);
            cmbDoBMon.setSelectedItem(month);
            cmbDobYr.setSelectedItem(year);
			cmbdept.setSelectedItem(dpnm);
			cmbshift.setSelectedItem(shnm);
			cmbstate.setSelectedItem(state);
			if(gender.equals("Male"))
				rbm.setSelected(true);
			else if(gender.equals("Female"))
				rbf.setSelected(true);
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
			//rsuser.next();
			empid=rsuser.getString("empid");
			empnm=rsuser.getString("empnm");
			//gmale=rsuser.getString("gmale");
			//gfemale=rsuser.getString("gfemale");
			gender=rsuser.getString("gender");
			dob=rsuser.getString("dob");
			dmy=dob.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			fnm=rsuser.getString("fnm");
			loc=rsuser.getString("loc");
			city=rsuser.getString("city");
			state=rsuser.getString("state");
			pin=rsuser.getString("pin");
			pno=rsuser.getString("pno");
			mail=rsuser.getString("mail");
			dpnm=rsuser.getString("dpnm");
			dojoin=rsuser.getString("dojoin");
			shnm=rsuser.getString("shnm");
			srttym=rsuser.getString("srttym");
			endtym=rsuser.getString("endtym");
			bsicsal=rsuser.getString("bsicsal");
			da=rsuser.getString("da");
			pf=rsuser.getString("pf");
			hra=rsuser.getString("hra");
			tax=rsuser.getString("tax");
			
			//setText

			txtempid.setText(empid);
			txtempnm.setText(empnm);
			txtfnm.setText(fnm);
			txtloc.setText(loc);
			txtcity.setText(city);
			txtpin.setText(pin);
			txtpno.setText(pno);
			txtmail.setText(mail);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			txtdojoin.setText(dojoin);
			txtbsicsal.setText(bsicsal);
			txtda.setText(da);
			txtpf.setText(pf);
			txthra.setText(hra);
			txttax.setText(tax);
			cmbDoBDay.setSelectedItem(date);
            cmbDoBMon.setSelectedItem(month);
            cmbDobYr.setSelectedItem(year);
			cmbdept.setSelectedItem(dpnm);
			cmbshift.setSelectedItem(shnm);
			cmbstate.setSelectedItem(state);
			if(gender.equals("Male"))
				rbm.setSelected(true);
			else if(gender.equals("Female"))
				rbf.setSelected(true);
		}
		}
		catch(SQLException ex){
			//System.out.println("Unable to Delete"+ex);
			msg.showMessageDialog(f1,"Unable to Show Data","Alert",1);
		}
	}
	else if(ae.getSource()==btnsearch){
					
		//code  for search
		
		String empid1;
		empid1=msg.showInputDialog(f1,"Enter Employee ID to Find Record","Message",1);
		if(empid!=null){
			System.out.println("Data "+empid1.length());
		try{
			stmtsearch=conn.createStatement();
			rssearch=stmtsearch.executeQuery("select * from tblemployee where empid='"+empid1+"'");
			rssearch.next();
			empid=rssearch.getString("empid");
			empid=rssearch.getString("empid");
			empnm=rssearch.getString("empnm");
			//gmale=rssearch.getString("gmale");
			//gfemale=rssearch.getString("gfemale");
			gender=rsuser.getString("gender");
			dob=rssearch.getString("dob");
			dmy=dob.split("/");
			date=dmy[0];
			month=dmy[1];
			year=dmy[2];
			fnm=rssearch.getString("fnm");
			loc=rssearch.getString("loc");
			city=rssearch.getString("city");
			state=rssearch.getString("state");
			pin=rssearch.getString("pin");
			pno=rssearch.getString("pno");
			mail=rssearch.getString("mail");
			dpnm=rssearch.getString("dpnm");
			dojoin=rssearch.getString("dojoin");
			shnm=rssearch.getString("shnm");
			srttym=rssearch.getString("srttym");
			endtym=rssearch.getString("endtym");
			bsicsal=rssearch.getString("bsicsal");
			da=rssearch.getString("da");
			pf=rssearch.getString("pf");
			hra=rssearch.getString("hra");
			tax=rssearch.getString("tax");
			
			//setText

			txtempid.setText(empid);
			txtempnm.setText(empnm);
			txtfnm.setText(fnm);
			txtloc.setText(loc);
			txtcity.setText(city);
			txtpin.setText(pin);
			txtpno.setText(pno);
			txtmail.setText(mail);
			txtsrttym.setText(srttym);
			txtendtym.setText(endtym);
			txtdojoin.setText(dojoin);
			txtbsicsal.setText(bsicsal);
			txtda.setText(da);
			txtpf.setText(pf);
			txthra.setText(hra);
			txttax.setText(tax);
			cmbDoBDay.setSelectedItem(date);
            cmbDoBMon.setSelectedItem(month);
            cmbDobYr.setSelectedItem(year);
			cmbdept.setSelectedItem(dpnm);
			cmbshift.setSelectedItem(shnm);
			cmbstate.setSelectedItem(state);
			if(gender.equals("Male"))
				rbm.setSelected(true);
			else if(gender.equals("Female"))
				rbf.setSelected(true);
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
class EmployeeApp{
    public static void main(String args[]) throws Exception  
    {   
        EmployeeWin ew;
        ew=new EmployeeWin();
    }
}