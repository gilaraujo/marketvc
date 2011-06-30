import java.util.*;
import br.usp.marketvc.beans.*;
import br.usp.marketvc.util.*;
import junit.framework.*;
import org.hibernate.*;
import javax.xml.bind.*;
import java.io.*;
import java.net.URL;

public class StockTest extends TestCase {
	public void test1() throws Exception{
//		Market.newDay();
//		Market.newHour();
	}
	public void test2() throws Exception{
/*
		User user = new User();
		user.setEmail("teste1");
		user.setPasswd("pass1");
		user.setName("name1");
		user.setBirth(new Date());
		user.setPhone("12345678");
		Investment investment = new Investment();
		investment.setPrice(1.2);
		investment.setAmount(2);
		investment.setSelling(true);
		Investment investment2 = new Investment();
		investment2.setPrice(3.4);
		investment2.setAmount(4);
		investment2.setSelling(false);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Stock stock = (Stock)session.load(Stock.class,"MSFT");
		session.save(user);
		session.save(investment);
		session.save(investment2);
		user.getInvestments().add(investment);
		user.getInvestments().add(investment2);
		stock.getInvestments().add(investment);
		stock.getInvestments().add(investment2);
		session.getTransaction().commit();
*/
	}
	public void test3() throws Exception{
	/*	List<Stock> stocks = Market.getStocks();
		for (int i=0;i<stocks.size();i++) {
			Stock s = stocks.get(i);
			System.out.println("ELEMENTO RECUPERADO: "+s.getSymbol());
			Market.generateQuote(s);
			System.out.println("QUOTE GERADO PARA: "+s.getSymbol());
			Market.generateTick(s);
			System.out.println("TICK GERADO PARA: "+s.getSymbol());
		}
*/
	}
}
