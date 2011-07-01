<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="quotes" class="thirteen columns content">
				<h2><%= msg.getString("ALL_STOCKS") %></h2>
				<ul>
					<!--<li class="quote row">
						<img src="http://chart.finance.yahoo.com/t?s=MSFT" />
						<div class="inner-quote">
							<h3>Microsoft Corporation (MSFT)</h3>
							<h4 class="up">+0.60 (+2.38%)</h4>
							<ul class="ask-bid">
								<li><strong>Last open:</strong> 26.78</li>
								<li><strong>close:</strong> 26.78</li>
								<li><strong>high:</strong> 26.78</li>
								<li><strong>low:</strong> 26.78</li>
							</ul>
							<ul class="ask-bid">
								<li><strong>Ask:</strong> 26.78</li>
								<li><strong>Bid:</strong> 26.73</li>
								<li><strong>Volume:</strong> 1000</li>
							</ul>
							<form action="/market">
								<input type="hidden" name="id" value="XXX">
								<input type="text" id="qty" name="qty" />
								<button type="submit">Buy</button>
							</form>
						</div>
					</li>-->
					<jsp:include page="market">
						<jsp:param name="op" value="12" />
					</jsp:include>
				</ul>
			</div>
<jsp:include page="footer.jsp"/>
