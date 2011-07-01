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

public class MarketServlet extends HttpServlet implements Default {

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
	int op = Integer.parseInt(request.getParameter("op"));

    PrintWriter out = response.getWriter();
	HttpSession usession;
	User user;
	Session session;

	switch (op) {
		case VIEW_LATEST_QUOTES:
			response.sendRedirect("index.jsp");
			break;
		case LATEST_QUOTES:
			try{
				out.println(ViewHelper.getLatestQuotes(request.getLocale()));
			} 
			catch (Exception e) { 
				e.printStackTrace();
				response.sendRedirect("index.jsp?msg=3");
			}
			break;
		case VIEW_ALL_STOCKS:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else
				response.sendRedirect("stocks.jsp");
			break;
		case LIST_ALL_STOCK:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else 
				try{
					out.println(ViewHelper.getUsersStocks(request.getLocale()));
					out.println(ViewHelper.getAllStocks(request.getLocale()));
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=4");
				}
			break;
		case BUY_STOCK:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else 
				try{
					int qty;
					double value, total;
			
					qty = Integer.parseInt(request.getParameter("qty"));
					
					if (request.getParameter("inv") == null) {
						String id = request.getParameter("id");
						session = HibernateUtil.getSessionFactory().getCurrentSession();
						session.beginTransaction();
						Stock stock = (Stock) session.createQuery("select s from Stock s left join fetch s.ticks where s.symbol = :ssymbol").setParameter("ssymbol",id).uniqueResult();
						Tick tick = stock.getLastTick();
						value = tick.getLastTrade();
						total = value * qty;
						if (user.hasEnoughFunds(total)) {
							user.decreaseFunds(total);
							session.update(user);
							Investment investment = new Investment();
							investment.setPrice(value);
							investment.setAmount(qty);
							investment.setSelling(false);
							investment.setStock(stock);
							session.save(investment);
							stock.getInvestments().add(investment);
							user.getInvestments().add(investment);
							session.update(stock);
							session.update(user);
							session.getTransaction().commit();
							response.sendRedirect("investments.jsp?msg=11");
						}
						else {
							response.sendRedirect("funds.jsp?msg=10");
						}
					}
					else {
						session = HibernateUtil.getSessionFactory().getCurrentSession();
						session.beginTransaction();
						int id = Integer.parseInt(request.getParameter("id"));
						Investment investment = (Investment) session.createQuery("select i from Investment i where i.id = :iid").setParameter("iid",id).uniqueResult();
						User owner = investment.getOwner();
						if (qty < investment.getAmount()) {
							if (user.hasEnoughFunds(qty*investment.getPrice())) {
								owner.increaseFunds(qty*investment.getPrice());
								user.decreaseFunds(qty*investment.getPrice());
								if(qty == investment.getAmount()) {
									owner.getInvestments().remove(investment);	
									investment.setOwner(user);
									user.getInvestments().add(investment);
								}
								else {
									investment.setAmount(investment.getAmount() - qty);
									Investment new_investment = new Investment();
									new_investment.setAmount(qty);
									new_investment.setPrice(investment.getPrice());
									new_investment.setSelling(false);
									new_investment.setStock(investment.getStock());
									new_investment.setOwner(user);
									user.getInvestments().add(new_investment);
									session.save(new_investment);
								}
								session.update(user);
								session.update(owner);
								session.getTransaction().commit();
							}
							else response.sendRedirect("stocks.jsp?msg=10");
						}
						else response.sendRedirect("stocks.jsp?msg=17");
					}
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=4");
				}
			break;
			case VIEW_RECOMMENDED_STOCKS:
				usession = request.getSession();
				user = (User) usession.getAttribute("user");
				if (user == null){
					response.sendRedirect("login.jsp");
				}
				else
					response.sendRedirect("recommended.jsp");
			break;
			case LIST_RECOMMENDED_STOCKS:
			
			break;
	}

  }

  public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
      	doGet(request, response);
  }

}








