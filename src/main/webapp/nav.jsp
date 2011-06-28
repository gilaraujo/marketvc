<%@page import="java.util.*, br.usp.marketvc.beans.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
		<div class="row">
			<div class="three columns sidebar">
				<nav>
					<%  User user = (User) session.getAttribute("user");
						if (user != null) { %>
					<h4><%= msg.getString("PERSONAL") %></h4>
					<ul>
						<li><a href="/view-user.jsp?id=<%= user.getEmail() %>&op=6"><%= msg.getString("MY_PROF") %></a></li>
						<li><a href="/user?op=4"><%= msg.getString("LOGOUT") %></a></li>
					</ul>
					<% } %>
					<h4><%= msg.getString("STOCKS") %></h4>
					<ul>
						<.jsp:include page="stocks">
							<.jsp:param name="op" value="5" />
						<./jsp:include>
					</ul>
				</nav>
			</div>
