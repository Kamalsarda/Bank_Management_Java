package com.bank;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class file
 */
@WebServlet(name = "fileU", urlPatterns = { "/fileU" })
public class file extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public file() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kamal","root","root");
			PreparedStatement ps = con.prepareStatement("insert into file values(?,?)");
			ps.setInt(1, 103);
			File f= new File("E:\\kaaml\\open.txt"); 
			FileReader fr = new FileReader(f);
			ps.setCharacterStream(2, fr, f.length());
			ps.executeUpdate();
			System.out.println("Successfully");
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
