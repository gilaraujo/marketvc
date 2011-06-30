<%@page import="java.util.*,br.usp.marketvc.beans.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="profile" class="thirteen columns content">
				<h2><%= msg.getString("MY_PROFILE") %></h2>
				<%  User user = (User) session.getAttribute("user");
					if (user != null) { %>
				<p><strong><%= msg.getString("NAME") %>:</strong> <%= user.getName() %></p>
				<p><strong><%= msg.getString("EMAIL") %>:</strong> <%= user.getEmail() %></p>
				<p><strong><%= msg.getString("PHONE") %>:</strong> <%= user.getPhone() %></p>
				<p><strong><%= msg.getString("BIRTHDATE") %>:</strong> <%= (user.getBirth().getYear()+1900)+"-"+(user.getBirth().getMonth()+1)+"-"+user.getBirth().getDate()%></p>
				<% } %>
			</div>
<jsp:include page="footer.jsp"/>
