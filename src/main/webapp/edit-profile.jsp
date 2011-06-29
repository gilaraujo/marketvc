<%@page import="java.util.*,br.usp.marketvc.beans.*" %>
<%  ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="profile" class="thirteen columns content">
				<h2><%= msg.getString("EDIT_PROFILE") %></h2>
				<form action="/user" enctype="multipart/form-data" method="post">
					<input type="hidden" name="op" value="2">
					<label for="email"><%= msg.getString("EMAIL") %></label>
					<input type="text" id="email" name="email" value="<%= user.getEmail() %>" disabled="disabled" />
					<label for="password"><%= msg.getString("PASS") %></label>
					<input type="password" id="password" name="pass" />
					<label for="name"><%= msg.getString("NAME") %></label>
					<input type="text" id="name" name="name" value="<%= user.getName() %>" />
					<label for="phone"><%= msg.getString("PHONE") %></label>
					<input type="text" id="phone" name="phone" value="<%= user.getPhone() %>" />
					<label for="bday"><%= msg.getString("BIRTHDATE") %></label>
					<fieldset>
						<input type="text" id="bday" name="bday" maxlength="2" placeholder="<%= user.getBirth().getDate() %>" />
						<input type="text" id="bmonth" name="bmonth" maxlength="2" placeholder="<%= user.getBirth().getMonth()+1 %>" />
						<input type="text" id="byear" name="byear" maxlength="4" placeholder="<%= user.getBirth().getYear()+1900 %>" />
					</fieldset>
					<label for="pic"><%= msg.getString("PICTURE") %></label>
					<input type="file" id="pic" name="pic" />
					<button type="submit" onclick="return dateValidation(this.form.bday.value, this.form.bmonth.value, this.form.byear.value, '<%= msg.getString("INVALID_DATE") %>');"><%= msg.getString("SAVE") %></button>
				</form>
			</div>
<jsp:include page="footer.jsp"/>
