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


@WebServlet("/CloseServlet")
public class CloseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountnumber=request.getParameter("accnumber");
		int accnumber = Integer.parseInt(accountnumber);

		System.out.println("accountnumber:"+accountnumber);

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			
			PreparedStatement ps= con.prepareStatement("update account set accstatus=? where accnumber=?");
			ps.setString(1,"C");
			ps.setInt(2,accnumber);
			System.out.println("hello kamal");
			int rs = ps.executeUpdate();
			if(rs>0){
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");  
				rd.forward(request, response);
			}else{
				request.getRequestDispatcher("index.jsp");  
			}

		} catch (Exception e) {
			PrintWriter out=response.getWriter();
			out.println(e);
	}
	}
}
