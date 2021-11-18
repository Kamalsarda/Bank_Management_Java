package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accnumber=request.getParameter("AccountNumber");
		String accname=request.getParameter("AccountName");
		String accstatus=request.getParameter("AccountStatus");
		String accnomini=request.getParameter("AccountNomini");
		String acctype=request.getParameter("AccountType");
		String accbal=request.getParameter("AccountBalance");
		String message= "Successfully opened your account";


		try{
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			PreparedStatement ps= con.prepareStatement("insert into account(UserName,accstatus,accbalance,accnomini,acctype) values(?,?,?,?,?)");
			ps.setString(1 ,accname);
			ps.setString(2,"N");
			ps.setString(3,"0");
			ps.setString(4 ,accnomini);
			ps.setString(5 ,acctype);
			int x=ps.executeUpdate();
			if(x>0){
				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
				rd.forward(request, response);  

			}
			else{
				out.println("failed....");
			}
		}
		catch(Exception e){
			PrintWriter out=response.getWriter();
			out.println(e);
			
		}
	}

}
