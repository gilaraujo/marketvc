<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="quotes" class="thirteen columns content">
				<h2><%= msg.getString("LATEST_QUOTES") %></h2>
				<ul>
					<!--<li class="quote row">
						<img src="http://chart.finance.yahoo.com/t?s=MSFT" />
						<div class="inner-quote">
							<h3>Microsoft Corporation (MSFT)</h3>
							<h4 class="up">+0.60 (+2.38%)</h4>
							<ul class="ask-bid">
								<li><strong>Ask:</strong> 26.78</li>
								<li><strong>Bid:</strong> 26.73</li>
								<li><strong>Volume:</strong> 1000</li>
							</ul>
						</div>
					</li>-->
					<jsp:include page="market">
						<jsp:param name="op" value="10" />
					</jsp:include>
				</ul>
			</div>
<jsp:include page="footer.jsp"/>
