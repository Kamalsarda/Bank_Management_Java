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
 * Servlet implementation class TransferServlet
 */
@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountnumber=request.getParameter("accnumber");
		int x = Integer.parseInt(accountnumber);
		String amoun=request.getParameter("amount");
		int amount = Integer.parseInt(amoun);
		String taccnumber=request.getParameter("rec_acc_number");
		System.out.println("account number"+ x);
		System.out.println("amount"+ amount);
		System.out.println("taccountNumber"+ taccnumber);
		try{
			String acstatus ="";
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			PreparedStatement ps= con.prepareStatement("select * from account where accnumber='"+taccnumber+"'");
			ResultSet rs = ps.executeQuery();
			int dataamount=0;
			
			if(rs.next()){
			acstatus = rs.getString(3);
			}
			if(acstatus.equals("C")) {
				out.println("Oopss... Sorry your account is closed");
				con.close();
				
			}
			if(acstatus.equals("N")) {
				PreparedStatement ps1 = con.prepareStatement("update account set accstatus=? where accnumber=?");
				ps1.setString(1, "O");
				ps1.setString(2, taccnumber);
				int j = ps1.executeUpdate();
				
			}
			
			if(rs.next()){
			  dataamount = amount + rs.getInt(6);
			}
			
			
			PreparedStatement ps1 = con.prepareStatement("update account set accbalance=? where accnumber=?");
			ps1.setInt(1, dataamount);
			ps1.setString(2, taccnumber);
			int j = ps1.executeUpdate();
			
			if(j>0){}
			System.out.println("data amount  "+ dataamount);
			PreparedStatement ps2=con.prepareStatement("select * from account where accnumber=?");
			
		    ps2.setString(1,accountnumber);
			System.out.println(accountnumber);
			ResultSet rs2=ps2.executeQuery();
			
			int data_amount1=0;
			if(rs2.next()){
			data_amount1=rs2.getInt(6)- amount; 
			System.out.println(data_amount1);
			}
			
			PreparedStatement ps3=con.prepareStatement("update account set accbalance=? where accnumber=?");
			ps3.setInt(1,data_amount1);
			ps3.setString(2,accountnumber);
			int z= ps3.executeUpdate();
			
			
			if(z>0){
				
				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");  
				rd.forward(request, response);
			}
			
			else{
				out.print("Please check your username and Password and target accountno");
				request.setAttribute("balance","Please check your username and Password and target acountno");
			}
		}
		catch(Exception e){
			PrintWriter out=response.getWriter();
			out.println(e);
			
		}
	}

}
