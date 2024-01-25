import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class ShiftWin implements ActionListener{
	JLabel lbltitle,lblsfcd,lblsfnm,lblsrttym,lblendtym,lbl24hr;
	JTextField txtsfcd,txtsfnm,txtsrttym,txtendtym;
	String sfcd,sfnm,srttym,endtym;
	JButton btnclear,btnsave,btnnext,btnfirst,btnlast,btnsearch,btnpre,btndlt,btnupdate,btnclose;
	ImageIcon imgclear,imgsave,imgnext,imgfirst,imglast,imgsearch,imgpre,imgdlt,imgupdate,imgclose;
	JPanel ptitle,p1,p2,p3;
	JOptionPane msg;
	Connection conn;
	ResultSet rsuser,rsdup,rssearch;
	Statement stmtInsert,stmtselect,stmtdelete,stmtupdate,stmtsearch;
	Box vb,hb;
	Border raised,lowered,bevel,border;
	JFrame f1;
	Font fnt1,fnt2;
	FlowLayout flw;
	GridLayout glw42,glw23;
public ShiftWin(){
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
	
	fnt1=new Font("Arial Black",Font.BOLD,24);
	fnt2=new Font("TimeNewRomsn",Font.BOLD,13);
	
	//Panel Controls
		
	ptitle=new JPanel();//panel
	p1=new JPanel();//panel
	p2=new JPanel();//panel
	p3=new JPanel();//panel
	
	//Layaout Controls
		
	flw=new FlowLayout();
	glw42=new GridLayout(4,2);
	//glw23=new GridLayout(2,3);

	//setLayout
	
	ptitle.setLayout(flw);
	p1.setLayout(glw42);
	//p2.setLayout(glw23);
	p3.setLayout(flw);
	
	//JLable Declearation
	
	lbltitle=new JLabel("S H I F T   I N F O R M A T I O N",JLabel.CENTER);
	lbltitle.setFont(fnt1);
	lblsfcd=new JLabel("Shift Code :");
	lblsfnm=new JLabel("Shift Name :");
	lblsrttym=new JLabel("Start Time (24 Hour Clock) :");
	lblendtym=new JLabel("End Time (24 Hour Clock ):");
	//lbl24hr=new JLabel("(24 Hour Clock)");
	//JLabel lbl24=new JLabel("(24 Hour Clock)");
	
	//SET FONT
		
	lblsfcd.setFont(fnt2);
	lblsfnm.setFont(fnt2);
	lblsrttym.setFont(fnt2);
	lblendtym.setFont(fnt2);
	//lbl24hr.setFont(fnt2);
	
	//TextField declearation
		
	txtsfcd=new JTextField(30);
	txtsfnm=new JTextField(30);
	txtsrttym=new JTextField(30);
	txtendtym=new JTextField(30);
	
	//set tooltiptext(txtfield)
	
	txtsfcd.setToolTipText("Enter Shift Code");
	txtsfnm.setToolTipText("Enter Shift Name");
	txtsrttym.setToolTipText("Enter Shift Start Time");
	txtendtym.setToolTipText("Enter Shift End Time");
	
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
	
	p1.add(lblsfcd);
	p1.add(txtsfcd);
	p1.add(lblsfnm);
	p1.add(txtsfnm);	
	p1.add(lblsrttym);
	p1.add(txtsrttym);
	//p1.add(lbl24);
	p1.add(lblendtym);
	p1.add(txtendtym);
	//p1.add(lbl24hr);
	
	//p3 controls
	
	p3.setBorder(BorderFactory.createTitledBorder(" "));
	p3.setBackground(new Color(204,204,204));
	p3.add(btnclear);
	p3.add(btnsave);
	p3.add(btnnext);
	p3.add(btnpre);
	p3.add(btnfirst);
	p3.add(btnlast);
	p3.add(btnsearch);
	p3.add(btndlt);
	p3.add(btnupdate);
	p3.add(btnclose);
	
	//Box control
		
	vb.add(Box.createVerticalStrut(60));
	vb.add(ptitle);
	vb.add(Box.createVerticalStrut(60));
	vb.add(p1);
	//vb.add(p2);
	vb.add(Box.createVerticalStrut(100));
	vb.add(p3);
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
			rsuser=stmtselect.executeQuery("select * from tblshift");
		}
		catch(SQLException ex){
			System.out.println("Unable to Fetch data");
		}
	} // doconnect ends here
	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==btnclear){
					
                    //code to clear
					
					txtsfcd.setText("");
					txtsfnm.setText("");
					txtsrttym.setText("");
					txtendtym.setText("");
				}
                else if(ae.getSource()==btnsave){
					
                    //code for save
					try{
						String sfcd2;
						sfcd2=txtsfcd.getText();
						stmtsearch=conn.createStatement();
						rsdup=stmtsearch.executeQuery("select * from tblshift where sfcd='"+sfcd2+"'");
						if(rsdup.next()){
							msg.showMessageDialog(f1,"The Shift Code is Duplicate","Alert",2);
						}
						else{
							sfcd=txtsfcd.getText();
							sfnm=txtsfnm.getText();
							srttym=txtsrttym.getText();
							endtym=txtendtym.getText();			
								if(sfcd.length()==0||sfnm.length()==0||srttym.length()==0||endtym.length()==0){
									msg.showMessageDialog(f1,"The Inputs are Empty","Alert",2);
								}
								else{
									int choice;
									choice=msg.showConfirmDialog(f1,"Check Properly \n\tOR\nif Sure then press OK Button !","Alert",2);
									if(choice==0){
										sfcd=txtsfcd.getText();
										sfnm=txtsfnm.getText();
										srttym=txtsrttym.getText();
										endtym=txtendtym.getText();
										try{
											stmtInsert=conn.createStatement();
											stmtInsert.executeUpdate("insert into tblshift values('"+sfcd+"','"+sfnm+"','"+srttym+"','"+endtym +"')");
											//System.out.println("One Record Saved Succesfully");
											msg.showMessageDialog(f1,"One Record Saved Succesfully","Message",1);
											txtsfcd.setText("");
											txtsfnm.setText("");
											txtsrttym.setText("");
											txtendtym.setText("");
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
							sfcd=rsuser.getString("sfcd");
							sfnm=rsuser.getString("sfnm");
							srttym=rsuser.getString("srttym");
							endtym=rsuser.getString("endtym");
							
							//setText

							txtsfcd.setText(sfcd);
							txtsfnm.setText(sfnm);
							txtsrttym.setText(srttym);
							txtendtym.setText(endtym);
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
							sfcd=rsuser.getString("sfcd");
							sfnm=rsuser.getString("sfnm");
							srttym=rsuser.getString("srttym");
							endtym=rsuser.getString("endtym");
							
							//setText

							txtsfcd.setText(sfcd);
							txtsfnm.setText(sfnm);
							txtsrttym.setText(srttym);
							txtendtym.setText(endtym);
						}
					}
					catch(SQLException ex){
						//System.out.println("Unable to go previous");
						msg.showMessageDialog(f1,"Unable to go previous","Message",1);
					}
				}    
				else if(ae.getSource()==btndlt){
					
						//code for delete	

							sfcd=txtsfcd.getText();
							int choice;
							choice=msg.showConfirmDialog(f1,"Are You Sure to Delete ?","Alert",2);
						if(choice==0){
						try{
							stmtdelete=conn.createStatement();
							stmtdelete.executeUpdate("delete from tblshift where sfcd='"+sfcd+"'");
							//System.out.println("One Record is deleted Succesfully");	
							msg.showMessageDialog(f1,"One Record is deleted Succesfully","Alert",1);
							txtsfcd.setText("");
							txtsfnm.setText("");
							txtsrttym.setText("");
							txtendtym.setText("");
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

					sfcd=txtsfcd.getText();
					sfnm=txtsfnm.getText();
					srttym=txtsrttym.getText();
					endtym=txtendtym.getText();	
					try{
						stmtInsert=conn.createStatement();
						stmtInsert.executeUpdate("insert into tblshift values('"+sfcd+"','"+sfnm+"','"+srttym+"','"+endtym+"')");
						//System.out.println("One Record is Update Succesfully");
						msg.showMessageDialog(f1,"One Record is Update Succesfully","Message",1);
						txtsfcd.setText("");
						txtsfnm.setText("");
						txtsrttym.setText("");
						txtendtym.setText("");
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
						sfcd=rsuser.getString("sfcd");
						sfnm=rsuser.getString("sfnm");
                        srttym=rsuser.getString("srttym");
                        endtym=rsuser.getString("endtym");
                        
                        //setText

                        txtsfcd.setText(sfcd);
                        txtsfnm.setText(sfnm);
                        txtsrttym.setText(srttym);
                        txtendtym.setText(endtym);
						}	
						//else{
							//System.out.println("Unable to show Data");
						//}
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
						sfcd=rsuser.getString("sfcd");
						sfnm=rsuser.getString("sfnm");
                        srttym=rsuser.getString("srttym");
                        endtym=rsuser.getString("endtym");
                        
                        //setText

                        txtsfcd.setText(sfcd);
                        txtsfnm.setText(sfnm);
                        txtsrttym.setText(srttym);
                        txtendtym.setText(endtym);
						}	
						else{
							System.out.println("Unable to show Data");
						}
					}
					catch(SQLException ex){
						//System.out.println("Unable to Delete"+ex);
						msg.showMessageDialog(f1,"Unable to Show Data","Alert",1);
					}
				}  
				else if(ae.getSource()==btnsearch){
					
					//code  for search
					
					String sfcd1;
					sfcd1=msg.showInputDialog(f1,"Enter Shift Code to Find Record","Message",1);
					if(sfcd!=null){
						System.out.println("Data "+sfcd1.length());
						try{
							stmtsearch=conn.createStatement();
							rssearch=stmtsearch.executeQuery("select * from tblshift where sfcd='"+sfcd1+"'");
							rssearch.next();
							sfcd=rssearch.getString("sfcd");
							sfnm=rssearch.getString("sfnm");
							srttym=rssearch.getString("srttym");
							endtym=rssearch.getString("endtym");
							
							//setText

							txtsfcd.setText(sfcd);
							txtsfnm.setText(sfnm);
							txtsrttym.setText(srttym);
							txtendtym.setText(endtym);
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

class ShiftApp{
    public static void main(String args[]) throws Exception  
    {   
        ShiftWin sw;
        sw=new ShiftWin();
    }
}
