<%@page import="java.util.*, java.text.*, br.usp.marketvc.beans.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
	<meta charset="ISO-8859-1" />
	<title><%= msg.getString("APPNAME") %></title>
	<meta name="description" content="">
	<meta name="author" content="">
	<!--[if lt IE 9]> <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 

	<link rel="stylesheet" href="stylesheets/base.css">
	<link rel="stylesheet" href="stylesheets/skeleton.css">
	<link rel="stylesheet" href="stylesheets/layout.css">
	
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png" />
	<link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png" />
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.js"></script>
	<script>window.jQuery || document.write("<script src='javascripts/jquery-1.5.1.min.js'>\x3C/script>")</script>
	<script src="javascripts/app.js"></script>
</head>
<body>
	<jsp:include page="messages.jsp" />
	<div class="container">	
		<header class="row">
			<div class="eleven columns">
				<h1 class="remove-bottom"><a href="/"><%= msg.getString("APPNAME") %></a></h1>
				<h5><%= msg.getString("APPDESC") %></h5>
			</div>
			<div class="five columns">
				<%  User user = (User) session.getAttribute("user");
					if (user == null) {
				%>
				<form id="login" action="/user">
					<input type="hidden" name="op" value="3">	
					<input type="text" id="email" name="email" placeholder="<%= msg.getString("EMAIL") %>" />
					<input type="password" id="pass" name="pass" placeholder="<%= msg.getString("PASS") %>" />
					<a href="/signup.jsp"><%= msg.getString("NOT_A_USER") %>? <%= msg.getString("SIGNUP") %></a>
					<button type="submit"><%= msg.getString("LOGIN") %></button>
				</form>
				<% } else {
					MessageFormat formatter = new MessageFormat("");
					formatter.applyPattern(msg.getString("GREETING"));
					String greeting = formatter.format(new Object[] { user.getName() });
				%>
					<p class="logged-in"><%= greeting %> (<a href="/user?op=4"><%= msg.getString("LOGOUT") %></a>)</p>
				<% } %>
			</div>
		</header>
