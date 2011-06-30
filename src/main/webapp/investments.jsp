<%@page import="java.util.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="inv" class="thirteen columns content">
				<h2><%= msg.getString("MY_INVESTMENTS") %></h2>
				<ul>
					<!--<li class="inv row">
						<img src="http://chart.finance.yahoo.com/t?s=MSFT" />
						<div class="inner-inv">
							<h3>Microsoft Corporation (MSFT)</h3>
							<h4 class="up">+0.60 (+2.38%)</h4>
							<ul class="ask-bid">
								<li><strong>Bid:</strong> 26.73</li>
								<li><strong>Volume:</strong> 1000</li>
							</ul>
							<form action="/quote">
								<input type="hidden" name="id" value="XXX">
								<fieldset>
									<input type="text" id="price" name="price" placeholder="<.%= msg.getString("CURRENCY") %>" />
									<input type="checkbox" id="selling" name="selling" /><label for="selling">Selling</label>
								</fieldset>
								<button type="submit">Ask</button>
								or
								<button type="submit" name="bank">Sell to the bank for half the price</button>
							</form>
						</div>
					</li>-->
					<jsp:include page="user">
						<jsp:param name="op" value="8" />
					</jsp:include>
				</ul>
			</div>
<jsp:include page="footer.jsp"/>
