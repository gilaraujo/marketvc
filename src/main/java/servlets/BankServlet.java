package br.usp.marketvc.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
// Para Hibernate 
import org.hibernate.*;
import br.usp.marketvc.util.*;
import br.usp.marketvc.beans.*;
import br.usp.marketvc.bundles.*;
import br.usp.marketvc.config.*;
import java.util.*;

public class BankServlet extends HttpServlet implements Default {

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
	int op = Integer.parseInt(request.getParameter("op"));

    PrintWriter out = response.getWriter();
	HttpSession usession;
	User user;

	switch (op) {
		case VIEW_BANK:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else
				response.sendRedirect("bank.jsp");
			break;
		case LIST_DEBT:
		/*	usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else 
				try{
					out.println(ViewHelper.getDebtList(request.getLocale()));
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=6");
				} */
			break;
		case TAKE_LOAN:
		
		
			break;
		case PAY_DEBT:
			break;
	}

  }

  public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
      	doGet(request, response);
  }

}








