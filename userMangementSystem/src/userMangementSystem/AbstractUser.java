package userMangementSystem;

public abstract class AbstractUser {
	public static int superuser = 3;
	public static int usermanager = 2;
	public static int user = 1;
	
	protected int level;
	protected static boolean uplevelenable;
	protected static boolean downlevelenable;
	protected static boolean deleteenable;
	//next element in chain of responsibility
	protected AbstractUser nextuser;

	public void setNextUser(AbstractUser nextUser){
	   this.nextuser = nextUser;
	}

	public void userMessage(int level, String message){
	   if(this.level == level){
	         write(message);
	   }
	   if(nextuser !=null){
		   nextuser.userMessage(level, message);
	   }
	}
	
	public void userRight(int level){
		   if(this.level == level){
  
		         set_Uplevelenable();
		         set_Downlevelenable();
		         set_Deleteenable();
		   }
		   if(nextuser !=null){
			   nextuser.userRight(level);
		   }
		}
	

	abstract protected void write(String message);
	abstract protected void set_Uplevelenable();
	abstract protected void set_Downlevelenable(); 
	abstract protected void set_Deleteenable(); 
	
	public boolean get_Uplevelenable() {
		return this.uplevelenable;
	}
	public boolean get_Downlevelenable() {
		return this.downlevelenable;
	}
	public boolean get_Deleteenable() {
		return this.deleteenable;
	}
}
