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

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountnumber=request.getParameter("accnumber");
		String amount=request.getParameter("amount");

		System.out.println("accountnumber:"+accountnumber);
		System.out.println("amount:"+amount);

		try{
			PrintWriter out=response.getWriter();
			String acstatus="";
			String accbal="";
			int finalbal=0;
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			
			//Testing Connection
		//	if(!con.isClosed())
		  //      out.println("Successfully connected to " + "MySQL server using TCP/IP...");
			
			PreparedStatement psselect= con.prepareStatement("select * from account where accnumber=?");
			psselect.setString(1,accountnumber);
			
			ResultSet rs =psselect.executeQuery();
			if(rs.next()){
				acstatus = rs.getString(3);
				accbal = rs.getString(6);
			}
			System.out.println("acstatus:"+acstatus);
			System.out.println("accbal:"+accbal);
			
			int wAmt =Integer.parseInt(amount);
			int bal = Integer.parseInt(accbal);
			finalbal = bal + wAmt;
			System.out.println("finalbal:"+finalbal);		
			
			if(acstatus.equals("N")){
				
				Class.forName("com.mysql.jdbc.Driver");		
				PreparedStatement psupdate= con.prepareStatement("update account set accstatus=?, accbalance=? where accnumber=?");
				psupdate.setString(1,"O");
				psupdate.setString(2 ,amount);
				psupdate.setString(3 ,accountnumber);
				int x=psupdate.executeUpdate();
				
				if(x>0){
					RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
					rd.forward(request, response);
				}
				else{
					out.println("failed....");
				}		
				
			}
			
		    if(acstatus.equals("O")){
				
		    	Class.forName("com.mysql.jdbc.Driver");		
				PreparedStatement psfinal= con.prepareStatement("update account set  accbalance=? where accnumber=?");
				psfinal.setInt(1, finalbal);
				psfinal.setString(2, accountnumber);
				int x = psfinal.executeUpdate();

				if (x > 0) {
							RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
							rd.forward(request, response);
					}

				}
		    if(acstatus.equals("C")){
				out.println("Oopss... Your Account is closed");
				con.close();
				}
		}
			catch (Exception e) {
			PrintWriter out=response.getWriter();
			out.println(e);

		}
	}
}

