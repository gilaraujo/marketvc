<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div class="thirteen columns content">
				<h2><%= msg.getString("ALL_PRODS") %></h2>
				<jsp:include page="product">
					<jsp:param name="op" value="10" />
				</jsp:include>
			</div>
<jsp:include page="footer.jsp"/>
