package br.usp.marketvc.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
// Para Hibernate 
import org.hibernate.*;
import org.hibernate.Session;
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
						  else if (item.getFieldName().equals("byear")) birth.setYear(Integer.parseInt(item.getString()));
							   else if (item.getFieldName().equals("bmonth")) birth.setMonth(Integer.parseInt(item.getString()));
									else if (item.getFieldName().equals("bday")) birth.setDate(Integer.parseInt(item.getString()));
										 else if (item.getFieldName().equals("phone")) user.setPhone(item.getString());
											  else if (item.getFieldName().equals("op")) op = Integer.parseInt(item.getString());
			} else { // is a file
				user.setPhoto(Hibernate.createBlob(item.getInputStream()));
			}
		}
	}
	else op = Integer.parseInt(request.getParameter("op"));
	switch (op) {
		case INSERT:
				try {
					user.setBirth(birth);
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					session.save(user);
					session.getTransaction().commit();
					response.sendRedirect("login.jsp?msg=6");
				} catch (Exception e) { e.printStackTrace(); 
					response.sendRedirect("signup.jsp?msg=7");			
				}
			break;
		case EDIT:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else
				response.sendRedirect("edit-user.jsp");
			break;
		case UPDATE:
			usession = request.getSession();
			user = (User) usession.getAttribute("user");
			if (user == null){
				response.sendRedirect("login.jsp");
			}
			else{
				try {
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
					session.beginTransaction();
					user2 = (User) session.load(User.class,user.getEmail());
					user2.setPasswd(request.getParameter("pass"));
					user2.setName(request.getParameter("name"));
					session.update(user2);
					usession.setAttribute("user", user2);
					session.getTransaction().commit();
					response.sendRedirect("index.jsp?msg=8");
				} catch (Exception e) { e.printStackTrace();
					response.sendRedirect("edit-user.jsp?msg=9");
				 }

			}
			break;
		case LOGIN:
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			user2 = new User();
			user2.setEmail(request.getParameter("email"));
			user2.setPasswd(request.getParameter("pass"));
			user = (User) session.createQuery("select u from User u where u.email = :uemail and u.passwd = :upasswd").setParameter("uemail", user2.getEmail()).setParameter("upasswd", user2.getPasswd()).uniqueResult();				
			session.getTransaction().commit();
			if (user == null) { response.sendRedirect("login.jsp?msg=12"); }
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

	}

  }

  public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
  {
      	doGet(request, response);
  }

}


