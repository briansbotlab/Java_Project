package userMangementSystem;
import java.sql.*;

import javax.swing.table.DefaultTableModel;




public class DBMger {
	
	
	// singleton pattern
	
	private static DBMger dBMger = null; 
	
	private DBMger() { 
         
    }
	
	synchronized public static DBMger getDBMger() {
		if (dBMger == null) {
			dBMger = new DBMger(); 
        }

        return dBMger; 
	}
	
	public void connectDB() {
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	public void initDB() {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE USERS " +
	                   "(ID INTEGER  PRIMARY KEY AUTOINCREMENT," +
	                   " NAME           TEXT    NOT NULL, " + 
	                   " ACCOUNT        CHAR(20) NOT NULL, " + 
	                   " LEVEL        INTEGER NOT NULL, " + 
	                   " PASSWORD       CHAR(20) NOT NULL)"; 
	      stmt.executeUpdate(sql);
	      
	      stmt.close();
	 
	      c.close();
	      
	      createData("SuperUser","root","root",3);
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      //System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}
	
	public void disconnectDB() {
		
		
	}
	
	
	public void createData(String name,String account,String password,int level) {
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "INSERT INTO USERS (NAME,ACCOUNT,PASSWORD,LEVEL) " +
                  "VALUES (?,?,?,?);";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, name);

	      ps.setString(2, account);

	      ps.setString(3, password);
	      
	      ps.setInt(4, level);
	      
	      ps.executeUpdate();
	      
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records created successfully");
		
	}
	
	public void updateData(String name,String account,String password,int level) {
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "UPDATE USERS SET "
	      		+ "NAME = ?,"
	      		+ "PASSWORD = ?,"
	      		+ "LEVEL = ?"
	      		+ "WHERE ACCOUNT = ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, name);

	      ps.setString(2, password);
	      
	      ps.setInt(3, level);

	      ps.setString(4, account);
	      
	      ps.executeUpdate();
	      
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records updated successfully");
		
	}

	
	public void deleteData(String account) {
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "DELETE FROM USERS WHERE ACCOUNT = ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, account);
	      
	      ps.executeUpdate();
	      
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records deleted successfully");
		
	}

	public String getAccount(String account) {
		String  rs_account = null;
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "SELECT * FROM USERS WHERE ACCOUNT = ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, account);
	      
	      ResultSet rs = ps.executeQuery();
	      
	      while ( rs.next()  ) {   
	    	  rs_account = rs.getString("ACCOUNT");

	          //System.out.println( "After select ACCOUNT = " + rs_account );

	       }
	      
	      c.commit();
	      c.close();
	      
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records select successfully");
	    return rs_account;
	}	
	
	
	public String getPassWord(String account) {
		String  password = null;
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "SELECT * FROM USERS WHERE ACCOUNT = ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, account);
	      
	      ResultSet rs = ps.executeQuery();
	      
	      while ( rs.next()  ) {   
	           password = rs.getString("PASSWORD");

	          //System.out.println( "After select PASSWORD = " + password );

	       }
	      
	      c.commit();
	      c.close();
	      
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records select successfully");
	    return password;
	}
	
	public int getLevel(String account) {
		int  level = 0;
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "SELECT * FROM USERS WHERE ACCOUNT = ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, account);
	      
	      ResultSet rs = ps.executeQuery();
	      
	      while ( rs.next()  ) {   
	    	  level = rs.getInt("LEVEL");

	          //System.out.println( "After select LEVEL = " + level );
 
	       }
	      
	      c.commit();
	      c.close();
	      
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records select successfully");
	    return level;
	}
	
	public String getName(String account) {
		String  name = null;
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "SELECT * FROM USERS WHERE ACCOUNT = ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setString(1, account);
	      
	      ResultSet rs = ps.executeQuery();
	      
	      while ( rs.next()  ) {   
	    	  name = rs.getString("NAME");

	          //System.out.println( "After select NAME = " + name );
 
	       }
	      
	      c.commit();
	      c.close();
	      
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records select successfully");
	    return name;
	}
	
	public DefaultTableModel getUserListByLevel(int level) {
		
		Connection c = null;
	    PreparedStatement ps = null;
	    
	    int i;
		String n;
		String a;
		String p;
		int l; 
		
		DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "NAME", "ACCOUNT","PASSWORD","LEVEL"}, 0);
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      String sql = "SELECT * FROM USERS WHERE LEVEL <= ?;";
	      ps = c.prepareStatement(sql);
	      
	      ps.setInt(1, level);
	      
	      
	      ResultSet rs = ps.executeQuery();
	      
	      while ( rs.next()  ) {  
	       i = rs.getInt("ID");
		   n = rs.getString("NAME");
		   a = rs.getString("ACCOUNT");
		   p = rs.getString("PASSWORD");
		   l = rs.getInt("LEVEL");
		  model.addRow(new Object[]{i, n, a, p, l});
	      
	      
	      }
	      c.commit();
	      c.close();
	      
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records select successfully");
	    return model;
	}
	
	public boolean checkDBExists(){
		boolean exists = false;
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:UserData.sqlite");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String query = "SELECT COUNT(*) FROM USERS WHERE LEVEL > 0;";
	      ResultSet rs = stmt.executeQuery(query);                  
	      rs.next();
	      exists = rs.getInt("COUNT(*)") > 0;
	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      //System.exit(0);
	    }
	    
	    return exists;
	    
	}
	
	
	
}
