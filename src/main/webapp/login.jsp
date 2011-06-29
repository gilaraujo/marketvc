<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div class="thirteen columns content">
				<h2><%= msg.getString("LOGIN") %></h2>
				<h4><%= msg.getString("MUST_LOGIN") %></h4>
				<form id="force-login" action="/user">
					<input type="hidden" name="op" value="3">
					<input type="text" id="email" name="email" placeholder="<%= msg.getString("EMAIL") %>" />
					<input type="password" id="pass" name="pass" placeholder="<%= msg.getString("PASS") %>" />
					<button type="submit"><%= msg.getString("LOGIN") %></button>
					<a href="/signup.jsp"><%= msg.getString("NOT_A_USER") %>? <%= msg.getString("SIGNUP") %></a>
				</form>
			</div>
<jsp:include page="footer.jsp"/>
