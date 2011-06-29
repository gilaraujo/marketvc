package br.usp.marketvc.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
// Para Hibernate 
import org.hibernate.Session;
import br.usp.marketvc.util.*;
import br.usp.marketvc.beans.*;
import br.usp.marketvc.bundles.*;
import br.usp.marketvc.config.*;
import java.util.*;
import java.sql.*;

public class ImageServlet extends HttpServlet implements Default {

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
	ServletOutputStream out = response.getOutputStream();
	User user, user2;
	HttpSession usession;
		usession = request.getSession();
		user = (User) usession.getAttribute("user");
		if (user == null){
			response.sendRedirect("login.jsp");
		}
		else {
			try {
				byte[] image = user.getPhoto();
				response.setHeader("Cache-Control","no-store");
				response.setHeader("Pragma","no-cache");
				response.setDateHeader("Expires",0);
				response.setContentType("image/jpeg");
				out.write(image);
				out.flush();
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


