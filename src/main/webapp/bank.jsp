<%@page import="java.util.*,br.usp.marketvc.beans.*" %>
<% ResourceBundle msg = ResourceBundle.getBundle("bundles.Messages", request.getLocale()); %>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
			<div id="loans" class="thirteen columns content">
				<h2><%= msg.getString("LOANS") %></h2>
				<h3><%= msg.getString("TAKE_LOAN") %></h3>
				<h4><%= msg.getString("INTEREST_RATE") %>: <%= Bank.getInstance().getInterestRate() %>% <em><%= msg.getString("PER_DAY") %></em></h4>
				<form action="/bank">
					<input type="hidden" name="op" value="22">
					<label for="amount"><%= msg.getString("AMOUNT") %></label>
					<input type="text" id="amount" name="amount" placeholder="<%= msg.getString("CURRENCY") %>" />
					<button type="submit"><%= msg.getString("ACQUIRE") %></button>
				</form>
				<h3><%= msg.getString("MY_DEBT") %></h3>
				<ul>
					<!--<li class="debt row">
						<h5>May 4, 2011</h5>
						<div class="inner-debt">
							<h4>US$ 1000.00</h4>
							<h6>+2.00% per day</h4>
							<form action="/bank">
								<input type="hidden" name="op" value="23">
								<button type="submit"><.%= msg.getString("PAY_OFF") %></button>
							</form>
						</div>
					</li>-->
					<jsp:include page="bank">
						<jsp:param name="op" value="21" />
					</jsp:include>
				</ul>
			</div>
<jsp:include page="footer.jsp"/>
