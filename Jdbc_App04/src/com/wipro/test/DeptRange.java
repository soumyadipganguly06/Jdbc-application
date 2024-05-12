package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeptRange {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		int startDept=0,endDept=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.print("Enter the Starting Department: ");
				startDept= sc.nextInt();
				System.out.print("Enter the Ending Department: ");
				 endDept=sc.nextInt();
				}
			//Creating  query required for SQL
			String query="SELECT DEPTNO,DNAME,LOC  FROM DEPT WHERE DEPTNO>="+startDept+ "AND DEPTNO<="+endDept ;
			System.out.println(query);
			//#Step-1 Loading and Registering the Driver 
			Class.forName("oracle.jdbc.OracleDriver");
			//#Step-2 Connecting with Database
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Lion6");
			//#Step-3 Creating Statement Object
			if(con!=null) {
				st=con.createStatement();
			}
			//#Step-4 Executing the query
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			//#Step-5 Processing the Result
			if(rs!=null) {
				boolean flag=false;
				System.out.println("DEPTNO \t\t"+"DNAME\t\t "+"LOC \t\t");
				System.out.println("-----------------------------------------------------------");
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t");
				if(flag==false) {
					System.out.println("No Record Found !!!");
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			if(e.getErrorCode()>=900 && e.getErrorCode()<=999) {
				System.out.println("Invalid Column name or Table name or SQL keywords !!!");
			}
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {										//#Step-6 Closing the resources
				try {
					rs.close();
					st.close();
					con.close();
					sc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
}	
