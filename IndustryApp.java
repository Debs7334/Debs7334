import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class IndustryWin implements ActionListener{
	JLabel lbltitle,lblrgno,lblnmoi,lbleyr,lblhoi,lblcu,lblloc,lblcity,lblstate,lblphn,lblmail,lblgstno,lblgstdt;
	JTextField txtrgno,txtnmoi,txteyr,txthoi,txtloc,txtcity,txtpno,txtmail,txtgstno,txtgstdt;
	String rgno,nmoi,eyr,hoi,loc,city,state,pno,mail,gstno,gstdt;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2,p3,p4,p5;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw14,glw62,glw24;
	JComboBox cmbstate;
	String state1[]={"No Choice","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal"};
 public IndustryWin(){
		f1=new JFrame("INDUSTRY INFORMATION MANAGEMENT");//frame
		
		//Border Declearation
		
		border=BorderFactory.createLineBorder(Color.black);
		raised=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		lowered=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		bevel=BorderFactory.createBevelBorder(BevelBorder.RAISED);
		
		//Box Declearation
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
		//fONT DECLEARTION
		
		fnt1=new Font("TimeNewRomsn",Font.BOLD,16);
		fnt2=new Font("TimeNewRomsn",Font.BOLD,13);
		
		//Combo box controls
		
		cmbstate=new JComboBox(state1);//JComboBox
		
		//Panel Controls
		
		ptitle=new JPanel();//panel
        p1=new JPanel();//panel
        p2=new JPanel();//panel
        p3=new JPanel();//panel
        p4=new JPanel();//panel
        p5=new JPanel();//panel
		
        //Layaout Controls
		
		flw=new FlowLayout();
		glw14=new GridLayout(1,4);
		glw62=new GridLayout(6,2);
		glw24=new GridLayout(2,4);
		
		ptitle.setLayout(flw);
		p1.setLayout(glw14);
        p2.setLayout(glw62);
        //p3.setLayout(glw12);
        p4.setLayout(glw24);
        p5.setLayout(flw);
		
		//JLable Declearation
		
		JLabel lblblank=new JLabel(" ");
		lbltitle=new JLabel("I N D U S T R Y   P R O F I L E ",JLabel.CENTER);
		lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
		lblrgno=new JLabel("Registation Number:");
		lblnmoi=new JLabel("Name Of Industry:");
		lbleyr=new JLabel("Established Year:");
		lblhoi=new JLabel("Head Of Industry:");
		lblcu=new JLabel("Contact Us:",JLabel.CENTER);
		lblcu.setFont(new Font("Copperplate Gothic",Font.BOLD,16));
		lblloc=new JLabel("Locality: ");
		lblcity=new JLabel("City: ");
		lblstate=new JLabel("State: ");
		lblphn=new JLabel("Phone Number: ");
		lblmail=new JLabel("E-mail ID: ");
		lblgstno=new JLabel("GST Number: ");
		lblgstdt=new JLabel("GST Date: ");

		//SET FONT
		
		//lbltitle.setFont(fnt1);
		lblrgno.setFont(fnt2);
		lblnmoi.setFont(fnt2);
		lbleyr.setFont(fnt2);
		lblhoi.setFont(fnt2);
		//lblcu.setFont(fnt1);
		lblloc.setFont(fnt2);
		lblcity.setFont(fnt2);
		lblstate.setFont(fnt2);
		lblphn.setFont(fnt2);
		lblmail.setFont(fnt2);
		lblgstno.setFont(fnt2);
		lblgstdt.setFont(fnt2);
		
		//TextField declearation
		
        txtrgno=new JTextField(30);
		txtnmoi=new JTextField(30);
		txteyr=new JTextField(30);
		txthoi=new JTextField(30);
		txtloc=new JTextField(30);
		txtcity=new JTextField(30);
		txtpno=new JTextField(30);
		txtmail=new JTextField(30);
		txtgstno=new JTextField(30);
		txtgstdt=new JTextField(30);
		
		txtrgno.setToolTipText("Enter Registation Noumber");
		txtnmoi.setToolTipText("Enter the Name of the Industry");
		txteyr.setToolTipText("Enter the Established Year");
		txthoi.setToolTipText("Enter the Name of Head Of Industry");
		txtloc.setToolTipText("Enter Your Location");
		txtcity.setToolTipText("Enter Your City Name");
		txtpno.setToolTipText("Enter Phone Number");
		txtmail.setToolTipText("Enter Your E-Mail ID");
		txtgstno.setToolTipText("Enter GST Number");
		txtgstdt.setToolTipText("Enter GST Date");
		
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
		
		//set tooltiptext
		
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
		
		//Button Background Color
		
		//btnclear.setBackground(new Color(51,204,255));
		//btnsave.setBackground(new Color(51,204,255));
		//btnnext.setBackground(new Color(51,204,255));
		//btnfirst.setBackground(new Color(51,204,255));
		//btnlast.setBackground(new Color(51,204,255));
		//btnsearch.setBackground(new Color(51,204,255));
		//btnpre.setBackground(new Color(51,204,255));
		//btndlt.setBackground(new Color(51,204,255));
		//btnupdate.setBackground(new Color(51,204,255));
		//btnclose.setBackground(new Color(51,204,255));

		//ptitle controls
		
		ptitle.setBorder(border);
		ptitle.add(lbltitle);
		ptitle.setBackground(new Color(255,204,51));
		
		//p1 control
		
		p1.add(lblrgno);
		p1.add(txtrgno);
		p1.add(lbleyr);
		p1.add(txteyr);
		
		//p2 controls
		
		p2.add(lblnmoi);
		p2.add(txtnmoi);
		p2.add(lblhoi);
		p2.add(txthoi);
		p2.add(lblcu);
		p2.add(lblblank);
		p2.add(lblloc);
		p2.add(txtloc);
		p2.add(lblcity);
		p2.add(txtcity);
		p2.add(lblstate);
		p2.add(cmbstate);
				
		//p4 controls
		
		p4.add(lblphn);
		p4.add(txtpno);
		p4.add(lblmail);
		p4.add(txtmail);
		p4.add(lblgstno);
		p4.add(txtgstno);
		p4.add(lblgstdt);
		p4.add(txtgstdt);
		
		//p5 controls
		p5.setBorder(BorderFactory.createTitledBorder(" "));
		p5.setBackground(new Color(204,204,204));
		p5.add(btnclear);
        p5.add(btnsave);
        p5.add(btnnext);
        p5.add(btnpre);
		p5.add(btnfirst);
		p5.add(btnlast);
		p5.add(btnsearch);
        p5.add(btndlt);
        p5.add(btnupdate);
        p5.add(btnclose);
		
		
		//Box control
		
		vb.add(Box.createVerticalStrut(50));
		vb.add(ptitle);
		vb.add(Box.createVerticalStrut(40));
		vb.add(p1);
		vb.add(p2);
		vb.add(p4);
		vb.add(Box.createVerticalStrut(40));
		vb.add(p5);
		vb.add(Box.createVerticalStrut(50));
		hb.add(Box.createHorizontalStrut(40));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(40));
		
		//f1 control
		
        f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
        f1.setBounds(158,84,1050,550);
		//f1.setResizable(false);
        doconnect();
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
			rsuser=stmtselect.executeQuery("select * from tblindustry");
		}
		catch(SQLException ex){
			System.out.println("Unable to Fetch data");
		}
	} // doconnect ends here
	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==btnclear){
					
                    //code to clear
					
					txtrgno.setText("");
					txtnmoi.setText("");
					txteyr.setText("");
					txthoi.setText("");
					txtloc.setText("");
					txtcity.setText("");
					txtpno.setText("");
					txtmail.setText("");
					txtgstno.setText("");
					txtgstdt.setText("");
					cmbstate.setSelectedItem("No Choice");
				}
                else if(ae.getSource()==btnsave){
					
                    //code for save
					try{
						String rgno2;
						rgno2=txtrgno.getText();
						stmtsearch=conn.createStatement();
						rsdup=stmtsearch.executeQuery("select * from tblindustry where rgno='"+rgno2+"'");
						if(rsuser.next()){
							msg.showMessageDialog(f1,"The Registation Number is Duplicate","Alert",2);
						}
						else{
							rgno=txtrgno.getText();
							nmoi=txtnmoi.getText();
							eyr=txteyr.getText();
							state=cmbstate.getSelectedItem().toString();
							hoi=txthoi.getText();
							loc=txtloc.getText();
							city=txtcity.getText();
							pno=txtpno.getText();
							mail=txtmail.getText();
							gstno=txtgstno.getText();
							gstdt=txtgstdt.getText();
								if(rgno.length()==0||nmoi.length()==0||eyr.length()==0||hoi.length()==0||pno.length()==0||mail.length()==0||gstno.length()==0||gstdt.length()==0){
									msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
								}
								else{
									int choice;
									choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
									if(choice==0){
										rgno=txtrgno.getText();
										nmoi=txtnmoi.getText();
										eyr=txteyr.getText();
										state=cmbstate.getSelectedItem().toString();
										hoi=txthoi.getText();
										loc=txtloc.getText();
										city=txtcity.getText();
										pno=txtpno.getText();
										mail=txtmail.getText();
										gstno=txtgstno.getText();
										gstdt=txtgstdt.getText();
										try{
											stmtInsert=conn.createStatement();
											stmtInsert.executeUpdate("insert into tblindustry values('"+rgno+"','"+eyr+"','"+nmoi+"','"+hoi+"','"+loc+"','"+city+"','"+state+"','"+pno+"','"+mail+"','"+gstno+"','"+gstdt+"')");
											//System.out.println("One Record Saved Succesfully");
											msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
											txtrgno.setText("");
											txtnmoi.setText("");
											txteyr.setText("");
											txthoi.setText("");
											txtloc.setText("");
											txtcity.setText("");
											txtpno.setText("");
											txtmail.setText("");
											txtgstno.setText("");
											txtgstdt.setText("");
											cmbstate.setSelectedItem("No Choice");
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
							rgno=rsuser.getString("rgno");
							eyr=rsuser.getString("eyr");
							//System.out.println("Date "+date);
							nmoi=rsuser.getString("nmoi");
							hoi=rsuser.getString("hoi");
							loc=rsuser.getString("loc");
							state=rsuser.getString("state");
							city=rsuser.getString("city");
							pno=rsuser.getString("pno");
							//System.out.println("year"+ayear);
							mail=rsuser.getString("mail");
							gstno=rsuser.getString("gstno");
							gstdt=rsuser.getString("gstdt");
							
							//setText

							txtrgno.setText(rgno);
							txteyr.setText(eyr);
							txtnmoi.setText(nmoi);
							txthoi.setText(hoi);
							txtloc.setText(loc);
							txtcity.setText(city);
							cmbstate.setSelectedItem(state);
							txtpno.setText(pno);
							txtmail.setText(mail);
							txtgstno.setText(gstno);
							txtgstdt.setText(gstdt);
						}
					}
					catch(SQLException ex){
						//System.out.println("Unable to Show data",ex);
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
							rgno=rsuser.getString("rgno");
							eyr=rsuser.getString("eyr");
							//System.out.println("Date "+date);
							nmoi=rsuser.getString("nmoi");
							hoi=rsuser.getString("hoi");
							loc=rsuser.getString("loc");
							state=rsuser.getString("state");
							city=rsuser.getString("city");
							pno=rsuser.getString("pno");
							//System.out.println("year"+ayear);
							mail=rsuser.getString("mail");
							gstno=rsuser.getString("gstno");
							gstdt=rsuser.getString("gstdt");
							
							//setText

							txtrgno.setText(rgno);
							txteyr.setText(eyr);
							txtnmoi.setText(nmoi);
							txthoi.setText(hoi);
							txtloc.setText(loc);
							txtcity.setText(city);
							cmbstate.setSelectedItem(state);
							txtpno.setText(pno);
							txtmail.setText(mail);
							txtgstno.setText(gstno);
							txtgstdt.setText(gstdt);
						}
					}
					catch(SQLException ex){
						//System.out.println("Unable to go previous");
						msg.showMessageDialog(f1,"Unable to go previous","Message",1);
					}
				}    
				else if(ae.getSource()==btndlt){
					
						//code for delete	

							mail=txtmail.getText();
							int choice;
							choice=msg.showConfirmDialog(f1,"Are You Sure to Delete ?","Alert",2);
						if(choice==0){
						try{
							stmtdelete=conn.createStatement();
							stmtdelete.executeUpdate("delete from tblindustry where rgno='"+rgno+"'");
							//System.out.println("One Record is deleted Succesfully");	
							msg.showMessageDialog(f1,"One Record is deleted Succesfully","Alert",1);
							txtrgno.setText("");
							txtnmoi.setText("");
							txteyr.setText("");
							txthoi.setText("");
							txtloc.setText("");
							txtcity.setText("");
							txtpno.setText("");
							txtmail.setText("");
							txtgstno.setText("");
							txtgstdt.setText("");
							cmbstate.setSelectedItem("No Choice");
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

					rgno=txtrgno.getText();
					nmoi=txtnmoi.getText();
					eyr=txteyr.getText();
					state=cmbstate.getSelectedItem().toString();
					hoi=txthoi.getText();
					loc=txtloc.getText();
					city=txtcity.getText();
					pno=txtpno.getText();
					mail=txtmail.getText();
					gstno=txtgstno.getText();
					gstdt=txtgstdt.getText();
					try{
						stmtInsert=conn.createStatement();
						stmtInsert.executeUpdate("insert into tblindustry values('"+rgno+"','"+eyr+"','"+nmoi+"','"+hoi+"','"+loc+"','"+city+"','"+state+"','"+pno+"','"+mail+"','"+gstno+"','"+gstdt+"')");
						//System.out.println("One Record is Update Succesfully");
						msg.showMessageDialog(f1,"One Record is Update Succesfully","Message",1);
						txtrgno.setText("");
						txtnmoi.setText("");
						txteyr.setText("");
						txthoi.setText("");
						txtloc.setText("");
						txtcity.setText("");
						txtpno.setText("");
						txtmail.setText("");
						txtgstno.setText("");
						txtgstdt.setText("");
						cmbstate.setSelectedItem("No Choice");
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
							rgno=rsuser.getString("rgno");
							eyr=rsuser.getString("eyr");
							nmoi=rsuser.getString("nmoi");
							hoi=rsuser.getString("hoi");
							loc=rsuser.getString("loc");
							state=rsuser.getString("state");
							city=rsuser.getString("city");
							pno=rsuser.getString("pno");
							mail=rsuser.getString("mail");
							gstno=rsuser.getString("gstno");
							gstdt=rsuser.getString("gstdt");
							
							//setText

							txtrgno.setText(rgno);
							txteyr.setText(eyr);
							txtnmoi.setText(nmoi);
							txthoi.setText(hoi);
							txtloc.setText(loc);
							txtcity.setText(city);
							cmbstate.setSelectedItem(state);
							txtpno.setText(pno);
							txtmail.setText(mail);
							txtgstno.setText(gstno);
							txtgstdt.setText(gstdt);
						}	
						//else{
							//System.out.println("Unable to show Data");
						//}
					}
					catch(SQLException ex){
						System.out.println("Unable to Delete"+ex);
						msg.showMessageDialog(f1,"Unable to Delete","Alert",1);
					}
				}
				else if(ae.getSource()==btnlast){
					
					//code for show last record
					
					try{
						btnpre.setEnabled(true);
						if(rsuser.last()){
							//rsuser.next();
							rgno=rsuser.getString("rgno");
							eyr=rsuser.getString("eyr");
							//System.out.println("Date"+date);
							nmoi=rsuser.getString("nmoi");
							hoi=rsuser.getString("hoi");
							loc=rsuser.getString("loc");
							state=rsuser.getString("state");
							city=rsuser.getString("city");
							pno=rsuser.getString("pno");
							//System.out.println("year"+ayear);
							mail=rsuser.getString("mail");
							gstno=rsuser.getString("gstno");
							gstdt=rsuser.getString("gstdt");
							
							//setText

							txtrgno.setText(rgno);
							txteyr.setText(eyr);
							txtnmoi.setText(nmoi);
							txthoi.setText(hoi);
							txtloc.setText(loc);
							txtcity.setText(city);
							cmbstate.setSelectedItem(state);
							txtpno.setText(pno);
							txtmail.setText(mail);
							txtgstno.setText(gstno);
							txtgstdt.setText(gstdt);
						}	
						else{
							System.out.println("Unable to show Data");
						}
					}
					catch(SQLException ex){
						System.out.println("Unable to Delete"+ex);
						msg.showMessageDialog(f1,"Unable to Delete","Alert",1);
					}
				}  
				else if(ae.getSource()==btnsearch){
					
					//code  for search
					
					String rgno1;
					rgno1=msg.showInputDialog(f1,"Enter Registation Number to Find Record","Message",1);
					//System.out.println("Data "+rgno1.length());
					if(rgno!=null){
						System.out.println("Data "+rgno1.length());
						try{
							stmtsearch=conn.createStatement();
							rssearch=stmtsearch.executeQuery("select * from tblindustry where rgno='"+rgno1+"'");
							rssearch.next();
							rgno=rssearch.getString("rgno");
							eyr=rssearch.getString("eyr");
							//System.out.println("Date"+date);
							nmoi=rssearch.getString("nmoi");
							hoi=rssearch.getString("hoi");
							loc=rssearch.getString("loc");
							state=rssearch.getString("state");
							city=rssearch.getString("city");
							pno=rssearch.getString("pno");
							//System.out.println("year"+ayear);
							mail=rssearch.getString("mail");
							gstno=rssearch.getString("gstno");
							gstdt=rssearch.getString("gstdt");
							
							//setText

							txtrgno.setText(rgno);
							txteyr.setText(eyr);
							txtnmoi.setText(nmoi);
							txthoi.setText(hoi);
							txtloc.setText(loc);
							txtcity.setText(city);
							cmbstate.setSelectedItem(state);
							txtpno.setText(pno);
							txtmail.setText(mail);
							txtgstno.setText(gstno);
							txtgstdt.setText(gstdt);
						}	
						catch(SQLException ex){
							//System.out.println("Unable to Delete"+ex);
							//msg.showMessageDialog(f1,"Unable to Search","Alert",1);
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

class IndustryApp{
    public static void main(String args[]) throws Exception  
    {   
        IndustryWin iwa;
        iwa=new IndustryWin();
    }
}