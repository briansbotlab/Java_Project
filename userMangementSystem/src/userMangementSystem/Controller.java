package userMangementSystem;

import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Controller {
	
	// singleton pattern
	
	private static Controller aController = null; 
		
	private Controller() { 
		setDBinit(); //«Ø¥ßªì©lDB
		
		
	   }
		
	synchronized public static Controller getController() {
		if (aController == null) {
			aController = new Controller(); 
	       }

	       return aController; 
	}
	
	// Attritubes
	
	private String userName;
	private String userAccount;
	private String userPassword;
	private int userLevel;
	private int userId;
	private String currentGUI = "login";
	private String selectedAcc;

	Login_gui login =  null;
	Userlist_gui userlist =  null;
	Modifyinfo_gui modifyinfo =  null; 
	Selfinfo_gui selfinfo = null;
	CreateAccount_gui createaccount = null;
	ForgetPass_gui forgetpass = null;
	ChangePass_gui changepass = null;
	
	public void setLogin_gui(Login_gui gui) {
		this.login = gui;
	}
	
	public void setUserlist_gui(Userlist_gui gui) {
		this.userlist = gui;
	}
	
	public void setModifyinfo_gui(Modifyinfo_gui gui) {
		this.modifyinfo = gui;
	}
	
	public void setSelfinfo_gui(Selfinfo_gui gui) {
		this.selfinfo = gui;
	}
	
	public void setForgetPass_gui(ForgetPass_gui gui) {
		this.forgetpass = gui;
	}
	
	public void setCreateAccount_gui(CreateAccount_gui gui) {
		this.createaccount = gui;
	}
	
	public void setChangePass_gui(ChangePass_gui gui) {
		this.changepass = gui;
	}
	
	public Login_gui getLogin_gui() {
		if(this.login == null) {
			this.login = new Login_gui();
		}
		return this.login;
	}
	
	public Userlist_gui getUserlist_gui() {
		if(this.userlist == null) {
			this.userlist = new Userlist_gui();
		}
		return this.userlist;
	}
	
	public Modifyinfo_gui getModifyinfo_gui() {
		if(this.modifyinfo == null) {
			this.modifyinfo = new Modifyinfo_gui();
		}
		return this.modifyinfo;
	}
	
	public Selfinfo_gui getSelfinfo_gui() {
		if(this.selfinfo == null) {
			this.selfinfo = new Selfinfo_gui();
		}
		return this.selfinfo;
	}
	
	public CreateAccount_gui getCreateAccount_gui() {
		if(this.createaccount == null) {
			this.createaccount = new CreateAccount_gui();
		}
		return this.createaccount;
	}
	
	public ForgetPass_gui getForgetPass_gui() {
		if(this.forgetpass == null) {
			this.forgetpass = new ForgetPass_gui();
		}
		return this.forgetpass;
	}
	
	public ChangePass_gui getChangePass_gui() {
		if(this.changepass == null) {
			this.changepass = new ChangePass_gui();
		}
		return this.changepass;
	}
	
	
	DBMger dBMger =  DBMger.getDBMger();
	
	private static AbstractUser getChainOfUsers(){

		AbstractUser user = new User(AbstractUser.user);
		AbstractUser userManager = new UserManager(AbstractUser.usermanager);
		AbstractUser superUser = new SuperUser(AbstractUser.superuser);

		user.setNextUser(userManager);
		userManager.setNextUser(superUser);

	     return user;	
	 }
	
	AbstractUser userChain = getChainOfUsers();
	
	public void setSelectedAcc(String sAcc) {
		this.selectedAcc = sAcc;
		//System.out.println("set selectedAcc :" + this.selectedAcc);
	}
	
	public void setUserName(String uName) {
		this.userName = uName;
		//System.out.println("set Name :" + this.userName);
	}
	
	public void setUserAccount(String uAccount) {
		if(uAccount.length() > 0) {
			this.userAccount = uAccount;
			
			//System.out.println("set Account :" + this.userAccount);
		}else {
			System.out.println("Please Input Your Account!");
		}
	}
	
	public void setUserPassword(String uPassword) {
		if(uPassword.length() > 0) {
			this.userPassword = uPassword;
			
			//System.out.println("set Password :" + this.userPassword);
		}else {
			System.out.println("Please Input Your Password!");
			
		}
	}
	
	public void setUserLevel(int uLevel) {
		this.userLevel = uLevel;
		//System.out.println("set Level :" + this.userLevel);
	}
	
	public String getSelectedAcc(){
		return this.selectedAcc;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getUserAccount(){
		return this.userAccount;
	}
	
	public int getUserLevel(){
		return this.userLevel;
	}
	
	public void setDBinit() {
		if(dBMger.checkDBExists()) {
			//do nothing
		}else {
			dBMger.initDB();
		}
	}
	
	public void checkUser() {
		
		String dBPassword = "";
		if(!(isNullOrEmpty(this.userAccount))) {
			dBPassword = dBMger.getPassWord(this.userAccount);
			if(dBPassword == null) {
				JOptionPane.showMessageDialog(null, "Account Incorrect!" , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}else if(dBPassword.equals(this.userPassword)) {
				JOptionPane.showMessageDialog(null, "Login Success!" , "System Notice",JOptionPane.PLAIN_MESSAGE);
				
				load_UserLevel();
				load_UserName();
				
				checkLevel();
				
				gui_Control("selfinfo");
			}else {
				JOptionPane.showMessageDialog(null, "Password Incorrect!" , "System Notice",JOptionPane.PLAIN_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please Input Your Password!" , "System Notice",JOptionPane.PLAIN_MESSAGE);
		}
		
		
		
	}
	
	public void checkLevel() {
		userChain.userMessage(this.userLevel, "Login Success");	
	}
	
	private boolean checkNewUserData() {
		boolean check_rs = false;
		int userAccount_length = this.userAccount.length();
		int userName_length = this.userName.length();
		int userPassword_length = this.userPassword.length();
		
		
		if(userAccount_length>4 &&
			userAccount_length<10 &&
			userName_length>4 &&
			userName_length<10 &&
			userPassword_length>4 &&
			userPassword_length<10) {
			check_rs = true;	
		}
		
		return check_rs;
		
	}
	
	private boolean checkNewAccount_isnot_duplicate() {
		boolean check_rs = false;
		
		String dBAccount = dBMger.getAccount(this.userAccount);
		
		if(dBAccount==null) {
			check_rs = true;
		}
		return check_rs;
	}
	
	
	public boolean get_UserChain_Uplevelenable() {
		userChain.userRight(this.userLevel);
		return userChain.get_Uplevelenable();
	}
	
	public boolean get_UserChain_Downlevelenable() {
		userChain.userRight(this.userLevel);
		return userChain.get_Downlevelenable();
	}
	
	public boolean get_UserChain_Deleteenable() {
		userChain.userRight(this.userLevel);
		return userChain.get_Deleteenable();
	}
	
	private void load_UserName() {
		//load user name
		String dBName = dBMger.getName(this.userAccount);
		this.userName = dBName;
	}
	private void load_UserLevel() {
		//load user level
		int dBLevel = dBMger.getLevel(this.userAccount);
		this.userLevel = dBLevel;
	}
	public String FP_UserPassword() {
		//this method only fot FP_gui
		//load user Password 
		String dBPass = dBMger.getPassWord(this.selectedAcc);
		if(isNullOrEmpty(dBPass)) {
			return "This Account is not existing";
		}else {
			return "Your Password is "+dBPass;
		}
		
	}
	
	public DefaultTableModel getDBUserList() {
		
		return dBMger.getUserListByLevel(this.userLevel);
	}
	
	public String createUserData() {
		String info = null;
		if(checkNewUserData() && checkNewAccount_isnot_duplicate()) {
			dBMger.createData(this.userName, this.userAccount, this.userPassword, 1);
			
			info = "Successful account creation.";
		}else if(checkNewUserData()) {
			
			info = "This Account is existing.";
		}else {
			
			info = "You need to increase or decrease the data length.";
		}
		return info;
	}
	
	public String updateUserData() {
		String info = null;
		dBMger.updateData(this.userName, this.userAccount, this.userPassword, this.userLevel);
		info = "Data update successfully!";
		return info;
	}
	
	public String deleteUserData() {
		String info = null;
		dBMger.deleteData(this.selectedAcc);
		info = this.selectedAcc + " is deleted successfully!";
		return info;
	}
	
	public String upUserLevel() {
		String sName = dBMger.getAccount(this.selectedAcc);
		String sPass = dBMger.getPassWord(this.selectedAcc);
		String info = null;
		int sLv = dBMger.getLevel(this.selectedAcc);
		
		if(sLv < 2 && this.userLevel >=2) {
			dBMger.updateData(sName, this.selectedAcc, sPass, 2);
			info = this.userAccount+" let "+ this.selectedAcc +" level up to 2!!";
			
		}else if(sLv == 3) {
			info = this.userAccount+" is SuperUser in the System.\r\n The system refused this operation!!";
		}else {
			
			info = this.selectedAcc +"'s level is 2,and you do not have the right to up his/her level.";
		}
		
		return info;
		
	}
	
	public String downUserLevel() {
		String sName = dBMger.getAccount(this.selectedAcc);
		String sPass = dBMger.getPassWord(this.selectedAcc);
		String info = null;
		int sLv = dBMger.getLevel(this.selectedAcc);
		
		if(sLv == 2 && this.userLevel >2) {
			dBMger.updateData(sName, this.selectedAcc, sPass, 1);
			info = this.userAccount+" let "+ this.selectedAcc +" level down to 1!!";
			
		}else if(sLv == 3) {
			info = this.userAccount+" is SuperUser in the System.\r\nThe system refused this operation!!";
		}else {
			
			info = this.selectedAcc +"'s level is 1,and you can not set his/her level down.";
		}
		
		return info;
		
	}
	
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
	
	private void resetControllerVariables() {
		userName = null;
		userAccount = null;
		userPassword = null;
		userLevel = 0;
		userId = 0;
		selectedAcc = null;
	}
	
	// GUI Control
	
	
	private String getCurrentGUI() {
		return this.currentGUI;
	}
	private void setCurrentGUI(String gui_Name) {
		this.currentGUI = gui_Name;
	}
	
	
	private void setGUI_Visible_false(String gui_Name) {
		switch (gui_Name) {
        case "login":
        	login.setVisible(false);
        	login = null;
        	//System.out.println("setGUI_Visible_false  :   login");
        	break;
        case "userlist":
        	userlist.setVisible(false);
        	userlist = null;
        	//System.out.println("setGUI_Visible_false  :   userlist");
        	break;
        case "modifyinfo":
        	modifyinfo.setVisible(false);
        	modifyinfo = null;
        	//System.out.println("setGUI_Visible_false  :   modifyinfo");
        	break;
        case "selfinfo":
        	selfinfo.setVisible(false);
        	selfinfo = null;
        	//System.out.println("setGUI_Visible_false  :   selfinfo");
        	break;
        case "createaccount":
        	createaccount.setVisible(false);
        	createaccount = null;
        	//System.out.println("setGUI_Visible_false  :   createaccount");
        	break;	
        case "forgetpass":
        	forgetpass.setVisible(false);
        	forgetpass = null;
        	//System.out.println("setGUI_Visible_false  :   forgetpass");
        	break;
        case "changepass":
        	changepass.setVisible(false);
        	changepass = null;
        	//System.out.println("setGUI_Visible_false  :   changepass");
        	break;	
        default:
        	System.out.println("get gui error!");
		}
	}	
	
	
	public void gui_Control(String gui_Name) {
		
		switch (gui_Name) {
        case "login":
        	setGUI_Visible_false(getCurrentGUI());
        	resetControllerVariables();
        	setCurrentGUI("login");
        	
        	login = getLogin_gui();
        	
        	login.setVisible(true);
        	break;
        case "userlist":
        	setGUI_Visible_false(getCurrentGUI());
        	
        	setCurrentGUI("userlist");
        	
        	userlist = getUserlist_gui();
        	
        	userlist.setVisible(true);
        	break;
        case "modifyinfo":
        	setGUI_Visible_false(getCurrentGUI());
        	
        	setCurrentGUI("modifyinfo");
        	
        	modifyinfo = getModifyinfo_gui();
        	
        	modifyinfo.setVisible(true);
        	break;
        case "selfinfo":
        	setGUI_Visible_false(getCurrentGUI());
        	
        	setCurrentGUI("selfinfo");
        	
        	selfinfo = getSelfinfo_gui();
        	
        	selfinfo.setVisible(true);
        	break;
        case "createaccount":
        	setGUI_Visible_false(getCurrentGUI());
        	
        	setCurrentGUI("createaccount");
        	
        	createaccount = getCreateAccount_gui();
        	
        	createaccount.setVisible(true);
        	break;  
        case "forgetpass":
        	setGUI_Visible_false(getCurrentGUI());
        	
        	setCurrentGUI("forgetpass");
        	
        	forgetpass = getForgetPass_gui();
        	
        	forgetpass.setVisible(true);
        	break;	
        case "changepass":
        	setGUI_Visible_false(getCurrentGUI());
        	
        	setCurrentGUI("changepass");
        	
        	changepass = getChangePass_gui();
        	
        	changepass.setVisible(true);
        	break;		
        default:
        	System.out.println("get gui error!");
        }
		
		
	}
	
	
	
	
	
}
