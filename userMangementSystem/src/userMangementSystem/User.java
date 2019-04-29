package userMangementSystem;

public class User extends AbstractUser{

	
	public User(int level){
	      this.level = level;
	}

	@Override
	protected void write(String message) {		
	   System.out.println("User: " + message);
	}
	
	@Override
	protected void set_Uplevelenable() {
		this.uplevelenable = false;
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
