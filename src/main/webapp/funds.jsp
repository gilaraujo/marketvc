<%@page import="java.util.*,br.usp.marketvc.beans.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="funds" class="thirteen columns content">
				<h2><%= msg.getString("MY_FUNDS") %></h2>
				<%  User user = (User) session.getAttribute("user");
					if (user != null) { %>
				<h4><%= msg.getString("TOTAL_FUNDS") %>:</h3>
				<h3><%= msg.getString("CURRENCY") %> <%= user.getFunds() %></h3>
				<form action="/user">
					<input type="hidden" name="op" value="7">
					<button type="submit"><%= msg.getString("INVESTMENTS_BUTTON") %></button>
				</form>
				<form action="/bank">
					<input type="hidden" name="op" value="20">
					<button type="submit"><%= msg.getString("LOANS_BUTTON") %></button>
				</form>
				<% } %>
			</div>
<jsp:include page="footer.jsp"/>
