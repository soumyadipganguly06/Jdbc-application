package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcMaven {

	public static void main(String[] args) {
		Connection con=null;
		Statement st1=null;
		Statement st2=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		try {
			/*#Step-1 Loading and Registering the Driver */
			//Class.forName("oracle.jdbc.OracleDriver");
			/*#Step-2 Connecting with Database*/
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Lion6");
			//#Step-3 Creating Statement Object
			if(con!=null) {
				st1=con.createStatement();
				st2=con.createStatement();
			}
			/*#Step-4 Preparing the queries */
			String query1="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			String query2="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			/*#Step-5 Creating ResultSet Objects */
			if(st1!=null) {
				rs1=st1.executeQuery(query1);
			}
			if(st2!=null) {
				rs2=st2.executeQuery(query2);
			}
			/*#Step-6 Processing the Result */
			//For selecting employee details with maximum salary
			if(rs1!=null) {   				
				System.out.println(query1);
				boolean flag=false;
				System.out.println(" EMPNO  "+"ENAME \t "+"JOB\t "+" SAL");
				System.out.println("------------------------------------------");
				while(rs1.next()) {
					flag=true;
					System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getInt(4));
				}
				if(flag==false) {
					System.out.println("No Record Found !!!");
				}
					
			}
			System.out.println();
			System.out.println();
			//For selecting employee details with minimum salary
			if(rs2!=null) {   				
				System.out.println(query2);
				boolean flag=false;
				System.out.println(" EMPNO  "+"ENAME \t "+"JOB\t "+" SAL");
				System.out.println("------------------------------------------");
				while(rs2.next()) {
					flag=true;
					System.out.println(rs2.getInt(1)+"\t"+rs2.getString(2)+"\t"+rs2.getString(3)+"\t"+rs2.getInt(4));
				}
				if(flag==false) {
					System.out.println("No Record Found !!!");
				}
					
			}
		} catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid Column name or Table name or SQL keywords !!!");
			}
		}finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(st1!=null) {
					st1.close();
				}
				if(st2!=null) {
					st2.close();
				}
				if(rs1!=null) {
					rs1.close();
				}
				if(rs2!=null) {
					rs2.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}//end of try-catch-finally block
	}//end of main

}//end of class
