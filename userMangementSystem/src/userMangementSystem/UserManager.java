package userMangementSystem;

public class UserManager extends AbstractUser{

	
	public UserManager(int level){
	      this.level = level;
	}

	@Override
	protected void write(String message) {		
	   System.out.println("UserManager: " + message);
	}
	
	@Override
	protected void set_Uplevelenable() {
		this.uplevelenable = true;
	}
	
	@Override
	protected void set_Downlevelenable() {
		this.downlevelenable = false;
	}
	
	@Override
	protected void set_Deleteenable() {
		this.deleteenable = false;
	}
}
