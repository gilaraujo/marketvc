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
import java.text.*;

public class BankServlet extends HttpServlet implements Default {

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
	int op = Integer.parseInt(request.getParameter("op"));
	Session session;
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
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else {
				try{
					out.println(ViewHelper.getDebtList(request.getLocale(), user));
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=6");
				} 
			}
			break;
		case TAKE_LOAN:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else {
				try {
					Loan loan = new Loan();
					loan.setAmount(Double.parseDouble(request.getParameter("amount")));
					loan.setInterest(Bank.newLoan());
					Calendar calendar = Calendar.getInstance();
					loan.setDate(calendar.getTime());
					loan.setOwner(user);
					user.getLoans().add(loan);
					user.increaseFunds(loan.getAmount());
					session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					session.save(loan);
					session.update(user);
					session.getTransaction().commit();
					response.sendRedirect("funds.jsp?msg=12");
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=13");
				} 
			}
			break;
		case PAY_DEBT:
			int i, find, id;
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else {
					id = Integer.parseInt(request.getParameter("id"));
					List<Loan> loans = user.getLoans();
					i = 0;
					find = 0;
					while ((i < loans.size()) && (find == 0)) {
						if (loans.get(i).getId() == id) find = 1;
						else i++;
					}
					if (user.hasEnoughFunds(loans.get(i).getAmount())) {
						try {
							Loan loan = loans.get(i);
							user.getLoans().remove(i);
							user.decreaseFunds(loan.getAmount());
							session = HibernateUtil.getSessionFactory().getCurrentSession();
							session.beginTransaction();
							session.update(user);
							session.delete(loan);
							Bank.paidLoan();
							session.getTransaction().commit();
							response.sendRedirect("bank.jsp?msg=14");	
						} 
						catch (Exception e) { 
							e.printStackTrace();
							response.sendRedirect("index.jsp?msg=15");
						} 
					} 
					else response.sendRedirect("bank.jsp?msg=16");
			}
			break;
	}

  }

  public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
      	doGet(request, response);
  }

}








