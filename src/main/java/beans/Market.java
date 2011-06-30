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
	static {
		List<Stock> stocks = new ArrayList<Stock>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		stocks = (ArrayList<Stock>)session.createQuery("select s from Stock s").list();
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

//	public static List<Stock> getStocks() { return stocks; }
/*
	public static void addStock(String s) {
		Iterator itr = stocks.iterator();
		while (itr.hasNext()) {
			Stock stock = (Stock) itr.next();
			if (stock.getSymbol().equals(s)) break;
		}
		if (!itr.hasNext()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(Stock.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+s+"%22)&env=store://datatables.org/alltableswithkeys");
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
*/
	public static Quote generateQuote(String s) {
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today;
		Quote quote = null;
		try {
			calendar.add(Calendar.DATE, -1);
			today = dateFormat.format(calendar.getTime());
			JAXBContext jc = JAXBContext.newInstance(Quote.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20(%22"+s+"%22)%20and%20startDate%3D%22"+today+"%22%20and%20endDate%3D%22"+today+"%22%0A%09%09&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			InputStream xmlStream = url.openStream();
			quote = (Quote) unmarshaller.unmarshal(xmlStream);
			quote.update();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(quote);
	    } catch (Exception e) {
			e.printStackTrace();
        }
		return quote;
	}
	public static Tick generateTick(String s) {
		Tick tick = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(Tick.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+s+"%22)&env=store://datatables.org/alltableswithkeys");
			InputStream xmlStream = url.openStream();
			tick = (Tick) unmarshaller.unmarshal(xmlStream);
			tick.update();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(tick);
		} catch (Exception e) {
			e.printStackTrace();
        }
		return tick;
	}
	public static void newDay() {
		List<Stock> stocks;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		stocks = (ArrayList<Stock>)session.createQuery("select s from Stock s left join fetch s.quotes").list();
		Iterator itr = stocks.iterator();
		while (itr.hasNext()) {
			Stock s = (Stock)itr.next();
			s.getQuotes().add(generateQuote(s.getSymbol()));
		}
		List<User> users = new ArrayList<User>();
		users = (ArrayList<User>)session.createQuery("select u from User u left join fetch u.loans").list();
		itr = users.iterator();
		while (itr.hasNext()) {
			User u = (User)itr.next();
			u.newDay();
		}
		session.getTransaction().commit();
	}
	public static void newHour() {
		List<Stock> stocks;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		stocks = (ArrayList<Stock>)session.createQuery("select s from Stock s left join fetch s.ticks").list();
		Iterator itr = stocks.iterator();
		while (itr.hasNext()) {
			Stock s = (Stock)itr.next();
			s.getTicks().add(generateTick(s.getSymbol()));
		}
		session.getTransaction().commit();
	}
	public static List<Stock> getStocks() {
		List<Stock> stocks;
		List<Stock> stocks2;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		stocks = (ArrayList<Stock>)session.createQuery("select s from Stock s left join fetch s.quotes").list();
		stocks2 = (ArrayList<Stock>)session.createQuery("select s from Stock s left join fetch s.ticks").list();
		for (int i=0;i<stocks.size();i++) {
			Stock s1 = stocks.get(i);
			Stock s2 = stocks2.get(i);
			s1.setTicks(s2.getTicks());
		}
		return stocks;
	}
}
