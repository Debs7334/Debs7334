import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class TtpaMenuWin implements ActionListener{
	JFrame f1;
	JOptionPane msg;
	Box vb;
	JLabel lbltitle,lbllast,lblttpa;
	ImageIcon imgmenu;
	JPanel p1;
	JMenuBar mbar;
	JMenu master,mgmt,sreport,dreport,help,exit1;
	JMenuItem industryinfo,rptindustry,deptinfo,rptdept,shiftinfo,rptshift,empinfo,rptemp,attendinfo,rptattend,leaveinfo,rptleave,laprinfo,rptlapr,sslipinfo,rptsslip,perappinfo,rptperapp,rptempdeptinfo,rptempshiftinfo,rptempgeninfo,rptattendempinfo,rptattenddtinfo,rptattenddeptinfo,rptattendshiftinfo,rptleavedtinfo,rptleaveempinfo,rptleavedeptinfo,rptleaveshiftinfo,rptleaveltypeinfo,rptaprempinfo,rptaprdeptinfo,rptaprshiftinfo,rptaprltypeinfo,rptaprstsinfo,rptsalslipinfo,rptsalmmyyinfo,rptperdtinfo,rptperdeptinfo,rptperempinfo,rptsslipmaininfo,userman,yes,no;
public TtpaMenuWin(){
	f1=new JFrame();
	mbar=new JMenuBar();
	imgmenu=new ImageIcon("menuimage2.jpg");
	
	//Box
	
	vb=Box.createVerticalBox();
	
	//panel
	
	p1=new JPanel();
	
	//Menu Controls
	mbar.setBackground(Color.black);
	master=new JMenu("       Master            ");
	master.setForeground(Color.white);
	mgmt=new JMenu("            Management           ");
	mgmt.setForeground(Color.white);
	sreport=new JMenu("           Summary Report           ");
	sreport.setForeground(Color.white);
	dreport=new JMenu("           Detail Report           ");
	dreport.setForeground(Color.white);
	help=new JMenu("           Help           ");
	help.setForeground(Color.white);
	exit1=new JMenu("           Exit           ");
	exit1.setForeground(Color.white);
	
	//Label
	lbltitle=new JLabel("Time Tracker & Performance Appraiser",JLabel.CENTER);
	lbltitle.setForeground(Color.white);
	lbltitle.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lbllast=new JLabel(" DD  Group  of  Industries",JLabel.CENTER);
	lbllast.setForeground(Color.white);
	lbllast.setFont(new Font("Franklin Gothic Medium",Font.BOLD,16));
	lblttpa=new JLabel(imgmenu);
	//lblttpa.setFont(new Font("Algerian",Font.PLAIN,42));
	
	//menuitem controls
	
	industryinfo=new JMenuItem("Industry Information");
	industryinfo.setAccelerator(KeyStroke.getKeyStroke("alt I"));
	//industryinfo.setForeground(new Color(128,0,0));
	//industryinfo.setBackground(new Color(128,0,0));
	rptindustry=new JMenuItem("Industry Information Report");
	rptindustry.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
	
	deptinfo=new JMenuItem("Department Information");
	deptinfo.setAccelerator(KeyStroke.getKeyStroke("alt D"));
	
	rptdept=new JMenuItem("Department Information Report");
	rptdept.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
	
	shiftinfo=new JMenuItem("Shift Information");
	shiftinfo.setAccelerator(KeyStroke.getKeyStroke("alt S"));
	
	rptshift=new JMenuItem("Shift Information Report");
	rptshift.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
	
	empinfo=new JMenuItem("Employee Information");
	empinfo.setAccelerator(KeyStroke.getKeyStroke("alt E"));
	
	rptemp=new JMenuItem("Employee Information Report");
	rptemp.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
	
	attendinfo=new JMenuItem("Attend Register");
	attendinfo.setAccelerator(KeyStroke.getKeyStroke("alt A"));
	
	rptattend=new JMenuItem("Attend Register Report");
	rptattend.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
	
	leaveinfo=new JMenuItem("Leave Application");
	leaveinfo.setAccelerator(KeyStroke.getKeyStroke("alt L"));
	
	rptleave=new JMenuItem("Leave Application Report");
	rptleave.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
	
	laprinfo=new JMenuItem("Leave Approval");
	laprinfo.setAccelerator(KeyStroke.getKeyStroke("alt P"));
	
	rptlapr=new JMenuItem("Leave Approval Report");
	rptlapr.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
	
	sslipinfo=new JMenuItem("Salary Slip Generation");
	sslipinfo.setAccelerator(KeyStroke.getKeyStroke("alt 1"));
	
	rptsslip=new JMenuItem("Salary Slip Report");
	rptsslip.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
	
	perappinfo=new JMenuItem("Performance Appraisal");
	perappinfo.setAccelerator(KeyStroke.getKeyStroke("alt P"));
	
	rptperapp=new JMenuItem("Performance Appraisal Report");
	rptperapp.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
	
	
	rptempdeptinfo=new JMenuItem("Employee Information(Department)");
	rptempdeptinfo.setAccelerator(KeyStroke.getKeyStroke("shift C"));
	
	rptempshiftinfo=new JMenuItem("Employee Information(Shift)");
	rptempshiftinfo.setAccelerator(KeyStroke.getKeyStroke("shift D"));
	
	rptempgeninfo=new JMenuItem("Employee Information(Gender)");
	rptempgeninfo.setAccelerator(KeyStroke.getKeyStroke("shift B"));
	
	rptattendempinfo=new JMenuItem("Attend Register(Employee ID)");
	rptattendempinfo.setAccelerator(KeyStroke.getKeyStroke("shift F"));
	
	rptattenddtinfo=new JMenuItem("Attend Register(Date)");
	rptattenddtinfo.setAccelerator(KeyStroke.getKeyStroke("shift E"));
	
	rptattenddeptinfo=new JMenuItem("Attend Register(Department)");
	rptattenddeptinfo.setAccelerator(KeyStroke.getKeyStroke("shift G"));
	
	rptattendshiftinfo=new JMenuItem("Attend Register(Shift)");
	rptattendshiftinfo.setAccelerator(KeyStroke.getKeyStroke("shift H"));
	
	rptleavedtinfo=new JMenuItem("Leave Application(Date)");
	rptleavedtinfo.setAccelerator(KeyStroke.getKeyStroke("shift I"));
	
	rptleaveempinfo=new JMenuItem("Leave Application(Employee ID)");
	rptleaveempinfo.setAccelerator(KeyStroke.getKeyStroke("shift J"));
	
	rptleavedeptinfo=new JMenuItem("Leave Application(Department)");
	rptleavedeptinfo.setAccelerator(KeyStroke.getKeyStroke("shift K"));
	
	rptleaveshiftinfo=new JMenuItem("Leave Application(Shift)");
	rptleaveshiftinfo.setAccelerator(KeyStroke.getKeyStroke("shift L"));
	
	rptleaveltypeinfo=new JMenuItem("Leave Application(Leave Type)");
	rptleaveltypeinfo.setAccelerator(KeyStroke.getKeyStroke("shift M"));
	
	rptaprempinfo=new JMenuItem("Leave Approval(Employee ID)");
	rptaprempinfo.setAccelerator(KeyStroke.getKeyStroke("shift N"));
	
	rptaprdeptinfo=new JMenuItem("Leave Approval(Department)");
	rptaprdeptinfo.setAccelerator(KeyStroke.getKeyStroke("shift O"));
	
	rptaprshiftinfo=new JMenuItem("Leave Approval(Shift)");
	rptaprshiftinfo.setAccelerator(KeyStroke.getKeyStroke("shift P"));
	
	rptaprltypeinfo=new JMenuItem("Leave Approval(Leave Type)");
	rptaprltypeinfo.setAccelerator(KeyStroke.getKeyStroke("shift Q"));
	
	rptaprstsinfo=new JMenuItem("Leave Approval(Status)");
	rptaprstsinfo.setAccelerator(KeyStroke.getKeyStroke("shift R"));
	
	rptsalslipinfo=new JMenuItem("Salary Slip(Slip No.)");
	rptsalslipinfo.setAccelerator(KeyStroke.getKeyStroke("shift S"));
	
	rptsalmmyyinfo=new JMenuItem("Salary Slip(Month & Year)");
	rptsalmmyyinfo.setAccelerator(KeyStroke.getKeyStroke("shift T"));
	
	rptperdtinfo=new JMenuItem("Performance Appraisal(Date)");
	rptperdtinfo.setAccelerator(KeyStroke.getKeyStroke("shift U"));
	
	rptperdeptinfo=new JMenuItem("Performance Appraisal(Department)");
	rptperdeptinfo.setAccelerator(KeyStroke.getKeyStroke("shift W"));
	
	rptperempinfo=new JMenuItem("Performance Appraisal(Employee ID)");
	rptperempinfo.setAccelerator(KeyStroke.getKeyStroke("shift V"));
	
	rptsslipmaininfo=new JMenuItem("Main Salary Slip");
	rptperapp.setAccelerator(KeyStroke.getKeyStroke("shift A"));
	
	userman=new JMenuItem("UserManual");
	userman.setAccelerator(KeyStroke.getKeyStroke("ctrl shift H"));
	
	yes=new JMenuItem("Yes,Please");
	yes.setAccelerator(KeyStroke.getKeyStroke("ctrl shift C"));
	
	no=new JMenuItem("No,Thanks");
	no.setAccelerator(KeyStroke.getKeyStroke("ctrl shift N"));
	
	
	//actionPerformed
	
	industryinfo.addActionListener(this);
	rptindustry.addActionListener(this);
	deptinfo.addActionListener(this);
	rptdept.addActionListener(this);
	shiftinfo.addActionListener(this);
	rptshift.addActionListener(this);
	empinfo.addActionListener(this);
	rptemp.addActionListener(this);
	attendinfo.addActionListener(this);
	rptattend.addActionListener(this);
	leaveinfo.addActionListener(this);
	rptleave.addActionListener(this);
	laprinfo.addActionListener(this);
	rptlapr.addActionListener(this);
	sslipinfo.addActionListener(this);
	rptsslip.addActionListener(this);
	perappinfo.addActionListener(this);
	rptperapp.addActionListener(this);
	rptempdeptinfo.addActionListener(this);
	rptempshiftinfo.addActionListener(this);
	rptempgeninfo.addActionListener(this);
	rptattendempinfo.addActionListener(this);
	rptattenddtinfo.addActionListener(this);
	rptattenddeptinfo.addActionListener(this);
	rptattendshiftinfo.addActionListener(this);
	rptleavedtinfo.addActionListener(this);
	rptleaveempinfo.addActionListener(this);
	rptleavedeptinfo.addActionListener(this);
	rptleaveshiftinfo.addActionListener(this);
	rptleaveltypeinfo.addActionListener(this);
	rptaprempinfo.addActionListener(this);
	rptaprdeptinfo.addActionListener(this);
	rptaprshiftinfo.addActionListener(this);
	rptaprltypeinfo.addActionListener(this);
	rptaprstsinfo.addActionListener(this);
	rptsalslipinfo.addActionListener(this);
	rptsalmmyyinfo.addActionListener(this);
	rptperdtinfo.addActionListener(this);
	rptperdeptinfo.addActionListener(this);
	rptperempinfo.addActionListener(this);
	rptsslipmaininfo.addActionListener(this);
	userman.addActionListener(this);
	yes.addActionListener(this);
	no.addActionListener(this);

	//mbar ADD.
	
	mbar.add(master);
	mbar.add(mgmt);
	mbar.add(sreport);
	mbar.add(dreport);
	mbar.add(help);
	mbar.add(exit1);
	
	
	//master ADD
	
	master.add(industryinfo);
	master.add(deptinfo);
	master.add(shiftinfo);
	master.add(empinfo);
	
	//mgmt ADD
	
	mgmt.add(attendinfo);
	mgmt.add(leaveinfo);
	mgmt.add(laprinfo);
	mgmt.addSeparator();
	mgmt.add(sslipinfo);
	mgmt.add(perappinfo);
	
	//sreport ADD
	
	sreport.add(rptindustry);
	sreport.add(rptdept);
	sreport.add(rptshift);
	sreport.add(rptemp);
	sreport.add(rptattend);
	sreport.add(rptleave);
	sreport.add(rptlapr);
	sreport.add(rptsslip);
	sreport.add(rptperapp);
	
	//dreport ADD
	
	dreport.add(rptsslipmaininfo);
	dreport.add(rptempgeninfo);
	dreport.add(rptempdeptinfo);
	dreport.add(rptempshiftinfo);
	dreport.add(rptattenddtinfo);
	dreport.add(rptattendempinfo);
	dreport.add(rptattenddeptinfo);
	dreport.add(rptattendshiftinfo);
	dreport.add(rptleavedtinfo);
	dreport.add(rptleaveempinfo);
	dreport.add(rptleavedeptinfo);
	dreport.add(rptleaveshiftinfo);
	dreport.add(rptleaveltypeinfo);
	dreport.add(rptaprempinfo);
	dreport.add(rptaprdeptinfo);
	dreport.add(rptaprshiftinfo);
	dreport.add(rptaprltypeinfo);
	dreport.add(rptaprstsinfo);
	dreport.add(rptsalslipinfo);
	dreport.add(rptsalmmyyinfo);
	dreport.add(rptperdtinfo);
	dreport.add(rptperempinfo);
	dreport.add(rptperdeptinfo);
	
	//help ADD
	
	help.add(userman);
	
	//exit1 ADD
	
	exit1.add(yes);
	exit1.add(no);
	
	//panel add
	
	p1.setLayout(new GridLayout(2,1));
	p1.setBackground(Color.black);
	p1.add(lbltitle);
	p1.add(lbllast);
	
	
	//FRame control
	
	f1.setLayout(new BorderLayout());
	f1.add(mbar,BorderLayout.NORTH);
	f1.add(lblttpa,BorderLayout.CENTER);
	f1.add(p1,BorderLayout.SOUTH);
	f1.setSize(1370,770);
	f1.setVisible(true);
}
public void actionPerformed(ActionEvent ae){
	if(ae.getSource()==industryinfo){
		
		IndustryWin iwa;
        iwa=new IndustryWin();	
	}
	else if(ae.getSource()==rptindustry){
		
		RptIndustryWin riw;
        riw=new RptIndustryWin();	
	}
	else if(ae.getSource()==deptinfo){
		
		DepartmentWin dw;
        dw=new DepartmentWin();
	}
	else if(ae.getSource()==rptdept){
		
		RptDeptWin rdw;
        rdw=new RptDeptWin();
	}
	else if(ae.getSource()==shiftinfo){
		
		ShiftWin sw;
        sw=new ShiftWin();
	}
	else if(ae.getSource()==rptshift){
		
		RptShiftWin rdw;
        rdw=new RptShiftWin();
	}
	else if(ae.getSource()==empinfo){
		
		EmployeeWin ew;
        ew=new EmployeeWin();
	}
	else if(ae.getSource()==rptemp){
		
		RptEmpWin rdw;
        rdw=new RptEmpWin();
	}
	else if(ae.getSource()==attendinfo){
		
		AttendanceWin aw;
        aw=new AttendanceWin();
	}
	else if(ae.getSource()==rptattend){
		
		RptAttendWin raw;
        raw=new RptAttendWin();
	}
	else if(ae.getSource()==leaveinfo){
		
		LeaveWin lw;
        lw=new LeaveWin();
	}
	else if(ae.getSource()==rptleave){
		
		RptLeaveWin rdw;
        rdw=new RptLeaveWin();	
	}
	else if(ae.getSource()==laprinfo){
		
		ApprovalWin law;
        law=new ApprovalWin();	
	}
	else if(ae.getSource()==rptlapr){
		
		RptAprWin raaw;
        raaw=new RptAprWin();
	}
	else if(ae.getSource()==sslipinfo){
		
		SalaryWin sw;
        sw=new SalaryWin();
	}
	else if(ae.getSource()==rptsslip){
		
		RptSslipWin1 rssw;
        rssw=new RptSslipWin1();	
	}
	else if(ae.getSource()==perappinfo){
		
		PerAppWin paw;
        paw=new PerAppWin();	
	}
	else if(ae.getSource()==rptperapp){
		
		RptEmpDeptWin redw;
        redw=new RptEmpDeptWin();	
	}
	else if(ae.getSource()==rptempdeptinfo){
		
		RptEmpDeptWin redw;
        redw=new RptEmpDeptWin();	
	}
	else if(ae.getSource()==rptempshiftinfo){
		
		 RptEmpShiftWin resw;
        resw=new RptEmpShiftWin();	
	}
	else if(ae.getSource()==rptempgeninfo){
		
		RptEmpGenWin regw;
        regw=new RptEmpGenWin();	
	}
	else if(ae.getSource()==rptattendempinfo){
		
		RptAttendEmpWin raew;
        raew=new RptAttendEmpWin();	
	}
	else if(ae.getSource()==rptattenddtinfo){
		
		RptAttendDtWin radw;
        radw=new RptAttendDtWin();
	}
	else if(ae.getSource()==rptattenddeptinfo){
		
		RptAttendDeptWin radw1;
        radw1=new RptAttendDeptWin();	
	}
	else if(ae.getSource()==rptattendshiftinfo){
		
		RptAttendShftWin rasw;
        rasw=new RptAttendShftWin();;	
	}
	else if(ae.getSource()==rptleavedtinfo){
		
		RptLeaveDtWin rldw;
        rldw=new RptLeaveDtWin();
	}
	else if(ae.getSource()==rptleaveempinfo){
		
		RptLeaveEmpWin rlew;
        rlew=new RptLeaveEmpWin();	
	}
	else if(ae.getSource()==rptleavedeptinfo){
		
		RptLeaveDeptWin rldw;
        rldw=new RptLeaveDeptWin();
	}
	else if(ae.getSource()==rptleaveshiftinfo){
		
		RptLeaveShftWin rlsw;
        rlsw=new RptLeaveShftWin();	
	}
	else if(ae.getSource()==rptleaveltypeinfo){
		
		RptLeaveLtypeWin rllw;
        rllw=new RptLeaveLtypeWin();	
	}
	else if(ae.getSource()==rptaprempinfo){
		
		RptAprEmpWin raew;
        raew=new RptAprEmpWin();
	}
	else if(ae.getSource()==rptaprdeptinfo){
		
		RptAprDeptWin radw;
        radw=new RptAprDeptWin();	
	}
	else if(ae.getSource()==rptaprshiftinfo){
		
		RptAprShftWin rasw;
        rasw=new RptAprShftWin();	
	}
	else if(ae.getSource()==rptaprltypeinfo){
		
		 RptAprLtypeWin ralw;
        ralw=new RptAprLtypeWin();	
	}
	else if(ae.getSource()==rptaprstsinfo){
		
		RptAprStsWin rasw;
        rasw=new RptAprStsWin();
	}
	else if(ae.getSource()==rptsalslipinfo){
		
		RptSalSlipWin rssw;
        rssw=new RptSalSlipWin();
	}
	else if(ae.getSource()==rptsalmmyyinfo){
		
		RptSalMmYyWin rsmyw;
        rsmyw=new RptSalMmYyWin();
	}
	else if(ae.getSource()==rptperdtinfo){
		
		RptPerDtWin rpdw;
        rpdw=new RptPerDtWin();
	}
	else if(ae.getSource()==rptperdeptinfo){
		
		RptPerDeptWin rpdw;
        rpdw=new RptPerDeptWin();
	}
	else if(ae.getSource()==rptperempinfo){
		
		RptPerEmpWin rpew;
        rpew=new RptPerEmpWin();
	}
	else if(ae.getSource()==rptsslipmaininfo){
		
		RptSslipWin rssw1;
        rssw1=new RptSslipWin();	
	}
	else if(ae.getSource()==yes){
		
		int choice;
		choice=msg.showConfirmDialog(f1,"Are You Sure to Exit ?","Alert",2);
		if(choice==0){
		f1.setVisible(false);
		f1.dispose();
		}
		else{
			System.out.println("Welcome");
		}	
	}
	else if(ae.getSource()==no){
		
		msg.showMessageDialog(f1,"Welcome","Message",1);
	}
}
}
class TtpaMenuApp{
	public static void main (String args[])throws Exception{
		TtpaMenuWin tmw;
		tmw=new TtpaMenuWin();
		/*
		try{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		tmw=new TtpaMenuWin();
	}
	catch(ClassNotFoundException ex){
		System.out.println("Error...");
	}*/
}
}