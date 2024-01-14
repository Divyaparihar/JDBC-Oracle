package com.jdbc.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class App 
{
	public static void main(String[] args) {
		//createTable();
		//insertRecord();
		//searchAnyData();
		//editRecord();
		deleteRecord();
	}
	
	private static void createTable() {
    	Connection con =null;
    	PreparedStatement st = null;
    	
       try {
    	  con = DbConnection.establishOracleConnection();
    	  
    	  //Step4
    	  PreparedStatement ps = con.prepareStatement("create table emp(id number(10) "
    	  		+ "primary key, name varchar2(40) not null, "
    	  		+ "city varchar2(40))");
    	  
    	 //Step5 -> Execute the sql query
			
			boolean isTableCreated = ps.execute("create table emp(id number(10) primary key, name varchar2(40) not null, city varchar2(40))");
			System.out.println("isTableCreated: "+isTableCreated);
			if(isTableCreated == false) {
				System.out.println("Table has been created.");
			}
    			  
    	  
    	  
      }catch(Exception e) {
    	  e.printStackTrace();
      }finally {
			try {
				if(con!=null && st !=null) {
					st.close();
					con.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
       
    }
   
	private static void insertRecord() {
		Connection con = null;
		Statement st = null;
		Scanner s = null;
		try {
			con = DbConnection.establishOracleConnection();
			
			// Step 4
			st = con.createStatement();
			
			
			//Step5 -> Execute the sql query
			s = new Scanner(System.in);
			System.out.println("Enter employee Id");
			int id = s.nextInt();
			System.out.println("Enter employee name");
			String name = s.next();
			System.out.println("Enter employee city");
			String city = s.next();
			
			int rows = st.executeUpdate("insert into emp values("+id+",'"+name+"','"+city+"')");
			if(rows> 0) {
				System.out.println("Record has been inserted successfully...");
			}else {
				System.out.println("Something went wrong to insert record...");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && st !=null) {
					st.close();
					con.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void searchAnyData() {
		 
		try {
			
    		Connection con = DbConnection.establishOracleConnection();      		
    		Statement st = con.createStatement();
    		Scanner s = new Scanner(System.in);
    		System.out.println("Enter emp Id");
    		int id = s.nextInt();
    		ResultSet rs = st.executeQuery("select * from emp where id= "+id+" ");
    		while(rs.next()) {
    			int id2 = rs.getInt("id");
    			String name = rs.getString("name");
    			String city = rs.getString("city");
    			System.out.println("Id = "+id2+" Name = "+name+" City = "+city);
    		}
    		
    		
    		//Step6: close the connection
    		st.close();
    		con.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}
 
	private static void editRecord() {

	    Connection con = null;
	    Statement st = null;
	    Scanner s = null;

	    try {
	        con = DbConnection.establishOracleConnection();
	        st = con.createStatement();

	        s = new Scanner(System.in);
	        System.out.println("Enter employee Id to edit");
	        int idToEdit = s.nextInt();

	        // Check if the record with the given id exists
	        ResultSet rs = st.executeQuery("SELECT * FROM emp WHERE id = " + idToEdit);
	        if (rs.next()) {
	            System.out.println("Enter new employee name");
	            String newName = s.next();
	            System.out.println("Enter new employee city");
	            String newCity = s.next();

	            // Execute the SQL query to update the record
	            int rows = st.executeUpdate("UPDATE emp SET name = '" + newName + "', city = '" + newCity + "' WHERE id = " + idToEdit);

	            if (rows > 0) {
	                System.out.println("Record has been updated successfully...");
	            } else {
	                System.out.println("Something went wrong to update record...");
	            }
	        } else {
	            System.out.println("Record with id " + idToEdit + " not found.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null && st != null) {
	                st.close();
	                con.close();
	            }
	        }catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}

	private static void deleteRecord() {

	    Connection con = null;
	    Statement st = null;
	    Scanner s = null;

	    try {
	        con = DbConnection.establishOracleConnection();
	        st = con.createStatement();

	        s = new Scanner(System.in);
	        System.out.println("Enter employee id to delete");
	        int idToDelete = s.nextInt();

	        // Check if the record with the given id exists
	        ResultSet rs = st.executeQuery("SELECT * FROM emp WHERE id = " + idToDelete);
	        if (rs.next()) {

	            // Execute the SQL query to delete the record
	            int rows = st.executeUpdate("DELETE FROM emp WHERE id = " + idToDelete);

	            if (rows > 0) {
	                System.out.println("Record has been deleted successfully...");
	            } else {
	                System.out.println("Something went wrong to delete record...");
	            }
	        } else {
	            System.out.println("Record with id " + idToDelete + " not found.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null && st != null) {
	                st.close();
	                con.close();
	            }
	        	
	        }catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    
	    }
	}
	
}

    
