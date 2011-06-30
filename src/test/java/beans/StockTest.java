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
		System.out.println("INTEREST RATE "+Bank.getInterestRate());
		Market.newDay();
		Market.newHour();
	}
	public void test2() throws Exception{
		User user = new User();
		user.setEmail("1");
		user.setPasswd("1");
		user.setName("name");
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
	}
	public void test3() throws Exception{
		Loan loan = new Loan();
		loan.setAmount(new Double(10));
		loan.setInterest(Bank.newLoan());
		System.out.println("INTEREST RATE "+Bank.getInterestRate());
	}
	public void test4() throws Exception{
		List<Stock> stocks = Market.getStocks();
		Iterator itr = stocks.iterator();
		System.out.println("Stocks:");
		while (itr.hasNext()) {
			Stock s = (Stock)itr.next();
			System.out.println(s.getName());
		}
	}
}
