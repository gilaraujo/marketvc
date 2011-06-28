<%@page import="java.util.*" %>
<%
	ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale());
	String msgNum = request.getParameter("msg");
	
	if (msgNum != null) {
		out.println("<script>alert('"+msg.getString("MSG"+msgNum)+"');</script>");
	}
%>
