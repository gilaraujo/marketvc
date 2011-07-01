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
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.output.*;

public class UserServlet extends HttpServlet implements Default {

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	User user,user2;
	HttpSession usession;
    int op = LOGOUT;
	Session session;
	
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	Date birth = new Date();
	user = new User();    
	if (isMultipart) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			
			if (item.isFormField()) { // form field
				if (item.getFieldName().equals("email")) user.setEmail(item.getString());
				else if (item.getFieldName().equals("pass")) user.setPasswd(item.getString()); 
					 else if (item.getFieldName().equals("name")) user.setName(item.getString());
						  else if (item.getFieldName().equals("byear")) birth.setYear(Integer.parseInt(item.getString())-1900);
							   else if (item.getFieldName().equals("bmonth")) birth.setMonth(Integer.parseInt(item.getString())-1);
									else if (item.getFieldName().equals("bday")) birth.setDate(Integer.parseInt(item.getString()));
										 else if (item.getFieldName().equals("phone")) user.setPhone(item.getString());
											  else if (item.getFieldName().equals("op")) op = Integer.parseInt(item.getString());
			} else { // is a file
				user.setPhoto(item.get());
			}
		}
	}
	else op = Integer.parseInt(request.getParameter("op"));
	switch (op) {
		case INSERT:
				try {
					user.setBirth(birth);
					session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					session.save(user);
					session.getTransaction().commit();
					response.sendRedirect("login.jsp?msg=1");
				} catch (Exception e) { e.printStackTrace(); 
					response.sendRedirect("signup.jsp?msg=2");						
				}
			break;
		case LOGIN:
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			user2 = new User();
			user2.setEmail(request.getParameter("email"));
			user2.setPasswd(request.getParameter("pass"));
			user = (User) session.createQuery("select u from User u left join fetch u.investments where u.email = :uemail and u.passwd = :upasswd").setParameter("uemail", user2.getEmail()).setParameter("upasswd", user2.getPasswd()).uniqueResult();
			User user3 = (User) session.createQuery("select u from User u left join fetch u.loans where u.email = :uemail and u.passwd = :upasswd").setParameter("uemail", user2.getEmail()).setParameter("upasswd", user2.getPasswd()).uniqueResult();
			user.setLoans(user3.getLoans());
			session.getTransaction().commit();
			if (user == null) { response.sendRedirect("login.jsp"); }
			else {
				usession = request.getSession();
				usession.setAttribute("user", user);
				response.sendRedirect("index.jsp");
			}			
			break;
		case LOGOUT:
			usession = request.getSession();
			usession.invalidate();
			response.sendRedirect("login.jsp");
			break;
		case VIEW_PROFILE:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else
				response.sendRedirect("profile.jsp");
			break;
		case VIEW_FUNDS:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else
				response.sendRedirect("funds.jsp");
			break;
		case VIEW_INVESTMENTS:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else
				response.sendRedirect("investments.jsp");
			break;
		case LIST_INVESTMENTS:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else {
				try{
					out.println(ViewHelper.getInvestmentsList(request.getLocale(), user));
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=5");
				}
			}
			break;
		case SELL_INVESTMENTS:
			int id, i, find;
			double price;
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else {
				try{
					id = Integer.parseInt(request.getParameter("id"));
					List<Investment> investments = user.getInvestments();
					i = 0;
					find = 0;
					while ((i < investments.size()) && (find == 0)) {
						if (investments.get(i).getId() == id) find = 1;
						else i++;
					}
					if (request.getParameter("bank") != null) {
						Stock stock = investments.get(i).getStock();
						Tick tick = stock.getLastTick();
						price = tick.getLastTrade() * investments.get(i).getAmount();
						user.increaseFunds(price/2);
						Investment investment = user.getInvestments().get(i);
						user.getInvestments().remove(i);
						session = HibernateUtil.getSessionFactory().getCurrentSession();
						session.beginTransaction();
						session.update(user);
						session.delete(investment);
						session.getTransaction().commit();
						response.sendRedirect("funds.jsp?msg=8");
					}
					else {
						user.getInvestments().get(i).setPrice(Double.parseDouble(request.getParameter("price").replace(",",".")));
						user.getInvestments().get(i).setSelling(new Boolean(request.getParameter("selling") != null)); 
						session = HibernateUtil.getSessionFactory().getCurrentSession();
						session.beginTransaction();
						session.update(user.getInvestments().get(i));
						session.getTransaction().commit();
						response.sendRedirect("investments.jsp?msg=9");
					}			
				} 
				catch (Exception e) { 
					e.printStackTrace();
					response.sendRedirect("index.jsp?msg=7");
				}
			}
			break;			
	}

  }

  public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
  {
      	doGet(request, response);
  }

}


