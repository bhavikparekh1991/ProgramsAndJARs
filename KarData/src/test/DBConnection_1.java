package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnection_1 {
	
	public static void DBtest() throws ClassNotFoundException{/*

        ------connection url----
        String dbUrl= "jdbc:sqlite:/home/bhavik/Downloads/CMS_Data.db";//

        ------dbUsername----
        String dbUsername= "dbbkpfree";

        ------dbPassword----
        String dbPassword= "iamhappy1234+";

        ------db query---
        String query= "SELECT * FROM Presonal_Info";

        -----load Mysql jdbc driver------
        Class.forName("com.mysql.jdbc.Driver");


        ----Get connection to DB
        Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement stmt = con.createStatement();

        //send sql query to database
        ResultSet rs= stmt.executeQuery(query);


        // while loop to get ResultSet all rows data
        while(rs.next()){

            //Store columns state,country,created,modified as separate strings 

            //(pls chk spellings of column name and also datatypes of the column if it is int change it to (rs.getInt) before running)

            String state =rs.getString("name");
            String country =rs.getString("country_id");
            String created_DATE= rs.getString("created");
            String modified_DATE=rs.getString("modified");
            System.out.println(state);
            System.out.println(country);
            System.out.println(created_DATE);
            System.out.println(modified_DATE);
        }

        //Close db connection

        con.close();
 */		Connection conn = null;
 		try {
		String dbUrl= "jdbc:sqlite:/home/bhavik/Downloads/CMS_Data.db";
		 Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(dbUrl);
		System.out.println("Connection to SQLite has been established.");
		Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("Select * from CMT_DATA_20170919");
        while ( rs.next() ) 
        {
        	String  name = rs.getString("Exclude_Campaign");
        	System.out.println( "NAME = " + name );
        }
        rs.close();
        stmt.close();
        conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection to SQLite has not established.");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException {
    	DBtest();
    }
}
