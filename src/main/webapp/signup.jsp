<%@page import="java.util.*" %>
<%  ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div class="thirteen columns content">
				<h2><%= msg.getString("NEW_USER") %></h2>
				<form id="signup" action="/user" enctype="multipart/form-data" method="post">
					<input type="hidden" name="op" value="0">
					<label for="email"><%= msg.getString("EMAIL") %></label>
					<input type="text" id="email" name="email" />
					<label for="password"><%= msg.getString("PASS") %></label>
					<input type="password" id="password" name="pass" />
					<label for="name"><%= msg.getString("NAME") %></label>
					<input type="text" id="name" name="name" />
					<label for="bday"><%= msg.getString("BIRTHDATE") %></label>
					<fieldset>
						<input type="text" id="bday" name="bday" maxlength="2" placeholder="<%= msg.getString("DAY_ABBR") %>" />
						<input type="text" id="bmonth" name="bmonth" maxlength="2" placeholder="<%= msg.getString("MONTH_ABBR") %>" />
						<input type="text" id="byear" name="byear" maxlength="4" placeholder="<%= msg.getString("YEAR_ABBR") %>" />
					</fieldset>
					<label for="pic"><%= msg.getString("PICTURE") %></label>
					<input type="file" id="pic" name="pic" />
					<button type="submit" onclick="return dateValidation(this.form.bday.value, this.form.bmonth.value, this.form.byear.value, '<%= msg.getString("INVALID_DATE") %>');"><%= msg.getString("SIGNUP") %></button>
				</form>
			</div>
<jsp:include page="footer.jsp"/>
