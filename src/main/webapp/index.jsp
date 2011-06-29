<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div class="thirteen columns content">
				<h2><%= msg.getString("WELCOME") %></h2>
				<h5><%= msg.getString("WELCOME_TEXT") %></h5>
			</div>
<jsp:include page="footer.jsp"/>
