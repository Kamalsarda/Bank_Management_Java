package com.bank;


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

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u= request.getParameter("username");
		String p=request.getParameter("password");

		try{
			String s1="com.mysql.jdbc.Driver";

			Class.forName(s1);
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			PreparedStatement psselect= con.prepareStatement("select * from user");
			ResultSet rs =psselect.executeQuery();
			String UserName,Password;
			int flag=0;
			while(rs.next()){
				UserName = rs.getString(1);
				Password=rs.getString(4);
				if(u.equals(UserName) && p.equals(Password)){
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
					rd.forward(request, response);
					flag=1;
					break;
				}
			}
			if(flag==0){
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
