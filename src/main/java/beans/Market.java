package br.usp.marketvc.beans;

import java.util.*;
import java.text.*;
import org.hibernate.*;
import javax.persistence.*;
import br.usp.marketvc.util.*;
import br.usp.marketvc.config.*;
import javax.xml.bind.*;
import java.net.*;
import java.io.*;

public class Market implements Default {
	private static List<Stock> stocks;
	
	static {
		stocks = new ArrayList<Stock>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		stocks = (ArrayList<Stock>) (session.createQuery("select s from Stock s").list());
		Iterator itr = stocks.iterator();
		if (!itr.hasNext()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(Stock.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				for (int i=0;i<companies.length;i++) {
					URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+companies[i]+"%22)&env=store://datatables.org/alltableswithkeys");
					InputStream xmlStream = url.openStream();
					Stock stock = (Stock) unmarshaller.unmarshal(xmlStream);
					stock.update();
					stocks.add(stock);
					session.save(stock);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.getTransaction().commit();
	}

    public static List<Stock> getStocks() { return stocks; }

	public static void addStock(String symbol) {
		int i;
		for (i=0;i<stocks.size();i++) {
			Stock s = (Stock) stocks.get(i);
			if (s.getSymbol().equals(symbol)) break;
		}
		if (i == stocks.size()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(Stock.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+symbol+"%22)&env=store://datatables.org/alltableswithkeys");
				InputStream xmlStream = url.openStream();
				Stock stock = (Stock) unmarshaller.unmarshal(xmlStream);
				stock.update();
				stocks.add(stock);
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				session.save(stock);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void generateQuote(Stock s) {
		generateQuote(s.getSymbol());
	}
	public static void generateQuote(String s) {
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today;
		try {
			calendar.add(Calendar.DATE, -1);
			today = dateFormat.format(calendar.getTime());
			JAXBContext jc = JAXBContext.newInstance(Quote.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20(%22"+s+"%22)%20and%20startDate%3D%22"+today+"%22%20and%20endDate%3D%22"+today+"%22%0A%09%09&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			InputStream xmlStream = url.openStream();
			Quote quote = (Quote) unmarshaller.unmarshal(xmlStream);
			quote.update();
			//s.getQuotes().add(quote);
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(quote);
			session.getTransaction().commit();
        } catch (Exception e) {
			e.printStackTrace();
        }
	}
}
