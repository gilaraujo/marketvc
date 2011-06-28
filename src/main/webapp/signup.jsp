<%@page import="java.util.*" %>
<%  ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale());
	ResourceBundle country = ResourceBundle.getBundle("bundles.Countries", request.getLocale()); %>
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
					<label for="address"><%= msg.getString("ADDRESS") %></label>
					<input type="text" id="name" name="address" />
					<label for="city"><%= msg.getString("CITY") %></label>
					<input type="text" id="city" name="city" />
					<label for="state"><%= msg.getString("STATE") %></label>
					<input type="text" id="state" name="state" />
					<label for="country"><%= msg.getString("COUNTRY") %></label>
					<select id="country" name="country">
					<jsp:include page="user">
						<jsp:param name="op" value="5" />
					</jsp:include>
					</select>
					<label for="postcode"><%= msg.getString("POSTCODE") %></label>
					<input type="text" id="postcode" name="postcode" />
					<button type="submit"><%= msg.getString("SIGNUP") %></button>
				</form>
			</div>
<jsp:include page="footer.jsp"/>
