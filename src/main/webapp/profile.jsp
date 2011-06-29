<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="profile" class="thirteen columns content">
				<h2><%= msg.getString("MY_PROFILE") %></h2>
				<p><strong><%= msg.getString("NAME") %>:</strong> <%= user.getName() %></p>
				<p><strong><%= msg.getString("EMAIL") %>:</strong> <%= user.getEmail() %></p>
				<p><strong><%= msg.getString("PHONE") %>:</strong> <%= user.getPhone() %></p>
				<p><strong><%= msg.getString("BIRTHDATE") %>:</strong> <%= user.getBirth().toString %></p>
				<form action="/user">
					<input type="hidden" name="op" value="1">
					<button type="submit"><%= msg.getString("EDIT_PROFILE") %></button>
				</form>
			</div>
<jsp:include page="footer.jsp"/>
