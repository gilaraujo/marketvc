package br.usp.marketvc.beans;

import java.util.*;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

@XmlAccessorType(XmlAccessType.FIELD)
class QuoteHelper {

	@XmlElement(name="Date")
	@XmlSchemaType(name="date")
	public Date date;

	@XmlElement(name="Open")
	public double open;

	@XmlElement(name="High")
	public double high;

	@XmlElement(name="Low")
	public double low;

	@XmlElement(name="Close")
	public double close;

	@XmlElement(name="Volume")
	public long volume;

	@XmlElement(name="Adj_Close")
	public double adjclose;

}

@Entity
@Table(name="tquote")
@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.NONE)
public class Quote {
	public Quote() { }

	@Id
	@Column(name="qid", unique=true, nullable=false)
	@SequenceGenerator(name = "seq_qid", sequenceName = "seq_qid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_qid")
	private Long qid;

	@Column(name="date")
	private Date date;

	@Column(name="open")
	private Double open;

	@Column(name="high")
	private Double high;

	@Column(name="low")
	private Double low;

	@Column(name="close")
	private Double close;

	@Column(name="volume")
	private Long volume;

	@Column(name="adjclose")
	private Double adjclose;

	@ManyToOne
	@JoinColumn(name="symbol", nullable=true, updatable=false, insertable=false)
	private Stock stock;
	public Stock getStock() { return this.stock; }

	@Transient
	@XmlElementWrapper(name="results")
	@XmlElement(name="quote")
	private List<QuoteHelper> quotes;

	public void update() {
		QuoteHelper quote = this.quotes.get(0);
		this.date = quote.date;
		this.open = quote.open;
		this.high = quote.high;
		this.low = quote.low;
		this.close = quote.close;
		this.volume = quote.volume;
		this.adjclose = quote.adjclose;
		this.quotes = null;
	}

	public Long getId() { return this.qid; }
	public Date getDate() { return this.date; }
	public double getOpen() { return this.open; }
	public double getHigh() { return this.high; }
	public double getLow() { return this.low; }
	public double getClose() { return this.close; }
	public long getVolume() { return this.volume; }
	public double getAdjClose() { return this.adjclose; }
	
}
