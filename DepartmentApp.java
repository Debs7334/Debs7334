import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class DepartmentWin implements ActionListener{
	JLabel lbltitle,lbldpcd,lbldpnm,lblhodp,lblcno;
	JTextField txtdpcd,txtdpnm,txthodp,txtcno;
	String dpcd,dpnm,hodp,cno;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw42;
public DepartmentWin(){

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
	
	//Panel Controls
		
	ptitle=new JPanel();//panel
	p1=new JPanel();//panel
	p2=new JPanel();//panel
	
	//Layaout Controls
		
	flw=new FlowLayout();
	glw42=new GridLayout(4,2);

	//setLayout
	
	ptitle.setLayout(flw);
	p1.setLayout(glw42);
	p2.setLayout(flw);
	
	//JLable Declearation
	
	lbltitle=new JLabel("D E P A R T M E N T  I N F O R M A T I O N",JLabel.CENTER);
	lbltitle.setFont(new Font("Arial Black",Font.BOLD,24));
	lbldpcd=new JLabel("Department Code:");
	lbldpnm=new JLabel("Department Name:");
	lblhodp=new JLabel("Head Of the Department:");
	lblcno=new JLabel("Contact Number:");
	
	//SET FONT
		
	lbldpcd.setFont(fnt2);
	lbldpnm.setFont(fnt2);
	lblhodp.setFont(fnt2);
	lblcno.setFont(fnt2);
	
	//TextField declearation
		
	txtdpcd=new JTextField(30);
	txtdpnm=new JTextField(30);
	txthodp=new JTextField(30);
	txtcno=new JTextField(30);
	
	//set tooltiptext(txtfield)
	
	txtdpcd.setToolTipText("Enter Department Code");
	txtdpnm.setToolTipText("Enter Department Name");
	txthodp.setToolTipText("Enter Head of the Department");
	txtcno.setToolTipText("Enter your Contact Number");
	
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
		
	//ptitle.setBorder(border);
	ptitle.setBorder(BorderFactory.createTitledBorder(" "));
	ptitle.add(lbltitle);
	ptitle.setBackground(new Color(255,204,51));
	
	//p1 control
	
	p1.add(lbldpcd);
	p1.add(txtdpcd);
	p1.add(lbldpnm);
	p1.add(txtdpnm);
	p1.add(lblhodp);
	p1.add(txthodp);
	p1.add(lblcno);
	p1.add(txtcno);
	
	//p2 controls
	
	p2.setBorder(BorderFactory.createTitledBorder(" "));
	p2.setBackground(new Color(204,204,204));
	p2.add(btnclear);
	p2.add(btnsave);
	p2.add(btnnext);
	p2.add(btnpre);
	p2.add(btnfirst);
	p2.add(btnlast);
	p2.add(btnsearch);
	p2.add(btndlt);
	p2.add(btnupdate);
	p2.add(btnclose);
	
	//Box control
		
	vb.add(Box.createVerticalStrut(60));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(60));
	vb.add(p1);
	vb.add(Box.createVerticalStrut(100));
	vb.add(p2);
	vb.add(Box.createVerticalStrut(70));
	hb.add(Box.createHorizontalStrut(40));
	hb.add(vb);
	hb.add(Box.createHorizontalStrut(40));
	
	//f1 control
	
	f1.setLayout(new BorderLayout());
	f1.add(hb,BorderLayout.CENTER);
	f1.setBounds(210,84,950,550);
	f1.setResizable(false);
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
			rsuser=stmtselect.executeQuery("select * from tbldepartment");
		}
		catch(SQLException ex){
			System.out.println("Unable to Fetch data");
		}
	} // doconnect ends here
	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==btnclear){
					
                    //code to clear
					
					txtdpcd.setText("");
					txtdpnm.setText("");
					txthodp.setText("");
					txtcno.setText("");
				}
                else if(ae.getSource()==btnsave){
					
                    //code for save
					try{
						String dpcd2;
						dpcd2=txtdpcd.getText();
						stmtsearch=conn.createStatement();
						rsdup=stmtsearch.executeQuery("select * from tbldepartment where dpcd='"+dpcd2+"'");
						if(rsdup.next()){
							msg.showMessageDialog(f1,"The Department Code is Duplicate","Alert",2);
						}
						else{
							dpcd=txtdpcd.getText();
							dpnm=txtdpnm.getText();
							hodp=txthodp.getText();
							cno=txtcno.getText();			
							if(dpcd.length()==0||dpnm.length()==0||hodp.length()==0||cno.length()==0){
									msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
								}
								else{
									int choice;
									choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
									if(choice==0){
										dpcd=txtdpcd.getText();
										dpnm=txtdpnm.getText();
										hodp=txthodp.getText();
										cno=txtcno.getText();
										try{
											stmtInsert=conn.createStatement();
											stmtInsert.executeUpdate("insert into tbldepartment values('"+dpcd+"','"+dpnm+"','"+hodp+"','"+cno+"')");
											//System.out.println("One Record Saved Succesfully");
											msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
											txtdpcd.setText("");
											txtdpnm.setText("");
											txthodp.setText("");
											txtcno.setText("");
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
							dpcd=rsuser.getString("dpcd");
							dpnm=rsuser.getString("dpnm");
							hodp=rsuser.getString("hodp");
							cno=rsuser.getString("cno");
							
							//setText

							txtdpcd.setText(dpcd);
							txtdpnm.setText(dpnm);
							txthodp.setText(hodp);
							txtcno.setText(cno);
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
							dpcd=rsuser.getString("dpcd");
							dpnm=rsuser.getString("dpnm");
							hodp=rsuser.getString("hodp");
							cno=rsuser.getString("cno");
							
							//setText

							txtdpcd.setText(dpcd);
							txtdpnm.setText(dpnm);
							txthodp.setText(hodp);
							txtcno.setText(cno);
						}
					}
					catch(SQLException ex){
						//System.out.println("Unable to go previous");
						msg.showMessageDialog(f1,"Unable to go previous","Message",1);
					}
				}    
				else if(ae.getSource()==btndlt){
					
						//code for delete	

							dpcd=txtdpcd.getText();
							int choice;
							choice=msg.showConfirmDialog(f1,"Are You Sure to Delete ?","Alert",2);
						if(choice==0){
						try{
							stmtdelete=conn.createStatement();
							stmtdelete.executeUpdate("delete from tbldepartment where dpcd='"+dpcd+"'");
							//System.out.println("One Record is deleted Succesfully");	
							msg.showMessageDialog(f1,"One Record is deleted Succesfully","Alert",1);
							txtdpcd.setText("");
							txtdpnm.setText("");
							txthodp.setText("");
							txtcno.setText("");
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

					dpcd=txtdpcd.getText();
					dpnm=txtdpnm.getText();
					hodp=txthodp.getText();
					cno=txtcno.getText();
					try{
						stmtInsert=conn.createStatement();
						stmtInsert.executeUpdate("insert into tbldepartment values('"+dpcd+"','"+dpnm+"','"+hodp+"','"+cno+"')");
						//System.out.println("One Record is Update Succesfully");
						msg.showMessageDialog(f1,"One Record is Update Succesfully","Message",1);
						txtdpcd.setText("");
						txtdpnm.setText("");
						txthodp.setText("");
						txtcno.setText("");
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
						dpcd=rsuser.getString("dpcd");
						dpnm=rsuser.getString("dpnm");
                        hodp=rsuser.getString("hodp");
                        cno=rsuser.getString("cno");
                        
                        //setText

                        txtdpcd.setText(dpcd);
                        txtdpnm.setText(dpnm);
                        txthodp.setText(hodp);
                        txtcno.setText(cno);
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
						dpcd=rsuser.getString("dpcd");
						dpnm=rsuser.getString("dpnm");
                        hodp=rsuser.getString("hodp");
                        cno=rsuser.getString("cno");
                        
                        //setText

                        txtdpcd.setText(dpcd);
                        txtdpnm.setText(dpnm);
                        txthodp.setText(hodp);
                        txtcno.setText(cno);
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
					
					String dpcd1;
					dpcd1=msg.showInputDialog(f1,"Enter Department Code to Find Record","Message",1);
					if(dpcd!=null){
						System.out.println("Data "+dpcd1.length());
						try{
							stmtsearch=conn.createStatement();
							rssearch=stmtsearch.executeQuery("select * from tbldepartment where dpcd='"+dpcd1+"'");
							rssearch.next();
							dpcd=rssearch.getString("dpcd");
							dpnm=rssearch.getString("dpnm");
							hodp=rssearch.getString("hodp");
							cno=rssearch.getString("cno");
							
							//setText

							txtdpcd.setText(dpcd);
							txtdpnm.setText(dpnm);
							txthodp.setText(hodp);
							txtcno.setText(cno);
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

class DepartmentApp{
    public static void main(String args[]) throws Exception  
    {   
        DepartmentWin dw;
        dw=new DepartmentWin();
    }
}
