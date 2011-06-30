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

		JAXBContext jc = JAXBContext.newInstance(Quote.class);
		JAXBContext jc2 = JAXBContext.newInstance(Stock.class);
		JAXBContext jc3 = JAXBContext.newInstance(Tick.class);
		
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Unmarshaller unmarshaller2 = jc2.createUnmarshaller();
		Unmarshaller unmarshaller3 = jc3.createUnmarshaller();
		URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20(%22MSFT%22)%20and%20startDate%3D%222011-2-12%22%20and%20endDate%3D%222011-2-12%22%0A%09%09&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
		InputStream xmlStream = url.openStream();
		Quote quote = (Quote) unmarshaller.unmarshal(xmlStream);
		quote.update();
		url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22MSFT%22)&env=store://datatables.org/alltableswithkeys");
		xmlStream = url.openStream();
		Stock stock = (Stock) unmarshaller2.unmarshal(xmlStream);
		stock.update();
		url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22MSFT%22)&env=store://datatables.org/alltableswithkeys");
		xmlStream = url.openStream();
		Tick tick = (Tick) unmarshaller3.unmarshal(xmlStream);
		tick.update();

		//System.out.println("symbol="+stock.getSymbol()+" name="+stock.getName()+" yearlow="+stock.getYearLow()+" yearhigh="+stock.getYearHigh());
		System.out.println("Date="+quote.getDate()+" Open="+quote.getOpen()+" High="+quote.getHigh());
		System.out.println("Name="+stock.getName()+" Symbol="+stock.getSymbol());
		System.out.println("LastTrade="+tick.getLastTrade()+" Change="+tick.getChange()+" ChangeInPercent="+tick.getChangeInPercent());
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(stock);
		session.save(quote);
		session.save(tick);
		session.getTransaction().commit();

	}
	public void test2() throws Exception{
		Market.addStock("PBR");
	}
	public void test3() throws Exception{
		List<Stock> stocks = Market.getStocks();
		for (int i=0;i<stocks.size();i++) {
			Stock s = stocks.get(i);
			System.out.println("ELEMENTO RECUPERADO: "+s.getSymbol());
			Market.generateQuote(s);
			System.out.println("QUOTE GERADO PARA: "+s.getSymbol());
		}
	}
}
