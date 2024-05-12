package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentRange {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		float startAverage=0,endAverage=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.print("Enter the StartingAverage marks: ");
				startAverage= sc.nextInt();
				System.out.print("Enter the Ending average marks: ");
				 endAverage=sc.nextInt();
				}
			//Creating  query required for SQL
			String query="SELECT SNAME,SROLL,MATH,PHYSICS,CHEMISTRY,AVERAGE FROM STUDENT WHERE AVERAGE>="+startAverage+ "AND AVERAGE<="+endAverage;
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
				System.out.println("SNAME      "+"SROLL      "+"PHYSICS      "+"CHEMISTRY     "+"MATH     "+"AVERAGE    ");
				System.out.println("----------------------------------------------------------------------------------------------------------------");
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getString(1)+"\t\t"+rs.getInt(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getInt(4)+"\t\t\t"+rs.getInt(5)+"\t\t"+rs.getFloat(6)+"\t\t");
				}
				if(flag==false) {
					System.out.println("No Record Found !!!");
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