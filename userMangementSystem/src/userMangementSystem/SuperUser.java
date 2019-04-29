package userMangementSystem;

public class SuperUser extends AbstractUser{

	
	public SuperUser(int level){
	      this.level = level;
	}

	@Override
	protected void write(String message) {		
	   System.out.println("SuperUser: " + message );
	}
	
	@Override
	protected void set_Uplevelenable() {
		this.uplevelenable = true;
		
		
	}
	
	@Override
	protected void set_Downlevelenable() {
		this.downlevelenable = true;
		
		
	}
	
	@Override
	protected void set_Deleteenable() {
		this.deleteenable = true;
		
		
	}
	
}
