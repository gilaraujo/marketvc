<%@page import="java.util.*, br.usp.marketvc.beans.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
		<div class="row">
			<div class="three columns sidebar">
				<nav>
					<%  User user = (User) session.getAttribute("user");
						if (user != null) { %>
					<h4><%= msg.getString("ACCOUNT") %></h4>
					<img src="/image" />
					<ul>
						<li><a href="/user?op=5"><%= msg.getString("MY_PROFILE") %></a></li>
						<li><a href="/user?op=6"><%= msg.getString("MY_FUNDS") %></a></li>
						<li><a href="/user?op=7"><%= msg.getString("MY_INVESTMENTS") %></a></li>
						<li><a href="/user?op=4"><%= msg.getString("LOGOUT") %></a></li>
					</ul>
					<% } %>
					<h4><%= msg.getString("MARKET") %></h4>
					<ul>
						<li><a href="/market?op=10"><%= msg.getString("LATEST_QUOTES") %></a></li>
					<% if (user != null) { %>
						<li><a href="/market?op=11"><%= msg.getString("ALL_STOCKS") %></a></li>
						<li><a href="/market?op=12"><%= msg.getString("RECOMMENDED_STOCKS") %></a></li>
					</ul>
					<h4><%= msg.getString("BANK") %></h4>
					<ul>
						<li><a href="/bank?op=20"><%= msg.getString("LOANS") %></a></li>
					<% } %>
					</ul>
				</nav>
			</div>