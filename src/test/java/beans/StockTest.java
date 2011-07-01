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
/*
		Market.newDay();
		Market.newHour();
*/
	}
	public void test2() throws Exception{
/*
		User u = new User();
		u.setEmail("1");
		u.setPasswd("1");
		u.setName("name");
		u.setFunds(new Double(1000));
		u.setBirth(new Date());
		Investment inv = new Investment();
		inv.setAmount(new Integer(10));
		inv.setPrice(new Double(10.1));
		inv.setSelling(new Boolean(true));
		Investment inv2 = new Investment();
		inv2.setAmount(new Integer(10));
		inv2.setPrice(new Double(10.1));
		inv2.setSelling(new Boolean(false));
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Stock s = (Stock)session.load(Stock.class,"MSFT");
		session.save(u);
		session.save(inv);
		session.save(inv2);
		u.getInvestments().add(inv);
		u.getInvestments().add(inv2);
		s.getInvestments().add(inv);
		session.getTransaction().commit();
*/
	}
	public void test3() throws Exception{
/*		List investments = Market.getAvailableInvestments();
		Iterator itr = investments.iterator();
		while (itr.hasNext()) {
			Investment i = (Investment)itr.next();
			System.out.println("INVESTIMENTO "+i.getAmount()+" DA ACAO "+i.getStock().getSymbol()+" DO USUARIO "+i.getOwner().getName());
		}
*/
	}
	public void test4() throws Exception{
	
	}
	public void test5() throws Exception{
	
	}
}
