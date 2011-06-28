<%@page import="java.util.*" %>
<%  ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div class="thirteen columns content">
				<h2><%= msg.getString("NEW_USER") %></h2>
				<form action="/user">
					<input type="hidden" name="op" value="0">
					<label for="email"><%= msg.getString("EMAIL") %></label>
					<input type="text" id="email" name="email" />
					<label for="password"><%= msg.getString("PASS") %></label>
					<input type="password" id="password" name="pass" />
					<label for="name"><%= msg.getString("NAME") %></label>
					<input type="text" id="name" name="name" />
					<button type="submit"><%= msg.getString("SIGNUP") %></button>
				</form>
			</div>
<jsp:include page="footer.jsp"/>
