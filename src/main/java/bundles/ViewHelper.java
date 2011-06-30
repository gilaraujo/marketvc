package br.usp.marketvc.bundles;

import br.usp.marketvc.beans.*;
import br.usp.marketvc.util.*;
import org.hibernate.Session;
import java.util.*;
import java.text.*;
import java.lang.*;

public class ViewHelper {
	public static String getLatestQuotes(Locale locale)  throws Exception{
		ResourceBundle rb = ResourceBundle.getBundle("bundles.View");
		ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", locale);
		StringBuffer buffer = new StringBuffer("");
		MessageFormat formatter = new MessageFormat("");
		Object[] parameters = null;

		List<Stock> stocks = Market.getStocks();
	//	if (stocks.size() > 0) {
			for (Iterator it = stocks.iterator(); it.hasNext();) {
				Stock stock = (Stock) it.next();
				Tick tick = stock.getLastTick();
				buffer.append(rb.getString("START_QUOTE"));
				parameters = new Object[] { stock.getSymbol() };
				formatter.applyPattern(rb.getString("QUOTE_GRAPH"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_INNER_QUOTE"));
				parameters = new Object[] { stock.getSymbol(), stock.getName() };
				formatter.applyPattern(rb.getString("QUOTE_NAME"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { (tick.getChange() > 0) ? "up" : "down", tick.getChange(), tick.getChangeInPercent() };
				formatter.applyPattern(rb.getString("QUOTE_CHANGE"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_ASKBID"));
				parameters = new Object[] { messages.getString("ASK"), tick.getAsk() };
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("BID"), tick.getBid() };	
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("VOLUME"), tick.getVolume() };
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("END_ASKBID"));
				buffer.append(rb.getString("END_INNER_QUOTE"));
				buffer.append(rb.getString("END_QUOTE"));
			}
	//	}
		
		return buffer.toString();
	}
	
	public static String getAllStocks(Locale locale)  throws Exception{
		ResourceBundle rb = ResourceBundle.getBundle("bundles.View");
		ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", locale);
		StringBuffer buffer = new StringBuffer("");
		MessageFormat formatter = new MessageFormat("");
		Object[] parameters = null;

		List<Stock> stocks = Market.getStocks();
	//	if (stocks.size() > 0) {
			for (Iterator it = stocks.iterator(); it.hasNext();) {
				Stock stock = (Stock) it.next();
				Tick tick = stock.getLastTick();
				buffer.append(rb.getString("START_QUOTE"));
				parameters = new Object[] { stock.getSymbol() };
				formatter.applyPattern(rb.getString("QUOTE_GRAPH"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_INNER_QUOTE"));
				parameters = new Object[] { stock.getSymbol(), stock.getName() };
				formatter.applyPattern(rb.getString("QUOTE_NAME"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { (tick.getChange() > 0) ? "up" : "down", tick.getChange(), tick.getChangeInPercent() };
				formatter.applyPattern(rb.getString("QUOTE_CHANGE"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_ASKBID"));
				parameters = new Object[] { messages.getString("ASK"), tick.getAsk() };
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("BID"), tick.getBid() };	
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("VOLUME"), tick.getVolume() };
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("END_ASKBID"));
				parameters = new Object[] { messages.getString("BUY"), stock.getSymbol() };
				formatter.applyPattern(rb.getString("QUOTE_BUY"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("END_INNER_QUOTE"));
				buffer.append(rb.getString("END_QUOTE"));
			}
	//	}
		
		return buffer.toString();
	}
	
	public static String getDebtList(Locale locale)  throws Exception{
		ResourceBundle rb = ResourceBundle.getBundle("bundles.View");
		ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", locale);
		StringBuffer buffer = new StringBuffer("");
		MessageFormat formatter = new MessageFormat("");
		Object[] parameters = null;
	
	// 	OBTER DADOS
	//	List<Stock> stocks = Market.getStocks();
	//	if (stocks.size() > 0) {
	// 	MODIFICAR ITERATOR
		for (Iterator it = stocks.iterator(); it.hasNext();) {
				// MODIFICAR OBJETOS
				Stock stock = (Stock) it.next();
				Tick tick = stock.getLastTick();
				
				buffer.append(rb.getString("START_DEBT"));
				// MODIFICAR PARAMETROS = Debt.getDate().toString()
				parameters = new Object[] { stock.getSymbol() };
				formatter.applyPattern(rb.getString("DEBT_DATE"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_INNER_DEBT"));
				// MODIFICAR PARAMETROS = messages.getString("CURRENCY")         Debt.getLoan()
				parameters = new Object[] { stock.getSymbol(), stock.getName() };
				formatter.applyPattern(rb.getString("DEBT_AMOUNT"));
				buffer.append(formatter.format(parameters));
				// MODIFICAR PARAMETROS = Debt.getInterest()           messages.getString("PER_DAY")
				parameters = new Object[] { (tick.getChange() > 0) ? "up" : "down", tick.getChange(), tick.getChangeInPercent() };
				formatter.applyPattern(rb.getString("DEBT_INTEREST"));
				buffer.append(formatter.format(parameters));
				// MODIFICAR PARAMETROS = Debt.getId()             messages.getString("PAY_OFF")                 
				parameters = new Object[] { messages.getString("ASK"), tick.getAsk() };
				formatter.applyPattern(rb.getString("DEBT_PAY"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("END_INNER_DEBT"));
				buffer.append(rb.getString("END_DEBT"));
			}
	//	}
		
		return buffer.toString();
	}
	
	public static String getInvestmentsList(Locale locale, User user)  throws Exception{
		ResourceBundle rb = ResourceBundle.getBundle("bundles.View");
		ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", locale);
		StringBuffer buffer = new StringBuffer("");
		MessageFormat formatter = new MessageFormat("");
		Object[] parameters = null;

		List<Investment> investments = user.getInvestments();
	//	if (stocks.size() > 0) {
			for (Iterator it = investments.iterator(); it.hasNext();) {
				Investment investment = (Investment) it.next();
				Stock stock = investment.getStock();
				Tick tick = stock.getLastTick();
				buffer.append(rb.getString("START_INV"));
				parameters = new Object[] { stock.getSymbol() };
				formatter.applyPattern(rb.getString("QUOTE_GRAPH"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_INNER_INV"));
				parameters = new Object[] { stock.getSymbol(), stock.getName() };
				formatter.applyPattern(rb.getString("QUOTE_NAME"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { (tick.getChange() > 0) ? "up" : "down", tick.getChange(), tick.getChangeInPercent() };
				formatter.applyPattern(rb.getString("QUOTE_CHANGE"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("START_ASKBID"));
				parameters = new Object[] { messages.getString("ASK"), tick.getAsk() };
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("BID"), tick.getBid() };	
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("VOLUME"), tick.getVolume() };
				formatter.applyPattern(rb.getString("ASKBID_ITEM"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("END_ASKBID"));
				parameters = new Object[] { investment.getId(), messages.getString("CURRENCY"), investment.getPrice(), messages.getString("SELLING"), (investment.getSelling()) ? "checked" : "" };
				formatter.applyPattern(rb.getString("START_INV_FORM"));
				buffer.append(formatter.format(parameters));
				parameters = new Object[] { messages.getString("ASK"), messages.getString("OR"), messages.getString("BANK_SELL") };
				formatter.applyPattern(rb.getString("END_INV_FORM"));
				buffer.append(formatter.format(parameters));
				buffer.append(rb.getString("END_INNER_INV"));
				buffer.append(rb.getString("END_INV"));
			}
	//	}
		
		return buffer.toString();
	}
	
}