package com.bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName=request.getParameter("UserName");
		String Email=request.getParameter("Email");
		String PhoneNumber=request.getParameter("PhoneNumber");
		String Password=request.getParameter("Password");
		String RepeatPassword=request.getParameter("RepeatPassword");
		String DoBmonth=request.getParameter("DoBmonth");
		String DoBday=request.getParameter("DoBday");
		String DoByear=request.getParameter("DoByear");
		String Gender=request.getParameter("Gender");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			PreparedStatement ps= con.prepareStatement("insert into user(UserName,Email,PhoneNumber,Password,RepeatPassword,DoBmonth,DoBday,DoByear,Gender) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,UserName);
			ps.setString(2 ,Email);
			ps.setString(3,PhoneNumber);
			ps.setString(4,Password);
			ps.setString(5,RepeatPassword);
			ps.setString( 6,DoBmonth);
			ps.setString(7,DoBday);
			ps.setString(8,DoByear);
			ps.setString(9,Gender);
			int x=ps.executeUpdate();
			if(x>0){
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
				rd.forward(request, response);
			}
			else{
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
				rd.forward(request, response);
			}
		}
		catch(Exception e){
			PrintWriter out = response.getWriter();
			out.println(e);
			
		}
	}

}
