package br.usp.marketvc.beans;

import java.util.*;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

@XmlAccessorType(XmlAccessType.FIELD)
class StockHelper {
	public StockHelper() { }

	@XmlElement(name="Name")
    public String name;

    @XmlElement(name="Symbol")
    public String symbol;

}

@Entity
@Table(name="tstock")
@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.NONE)
public class Stock {
	public Stock() { }

	@Column(name="name", nullable=false)
    private String name;

	@Id
	@Column(name="symbol", nullable=false, unique=true)
    private String symbol;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="symbol")
	private List<Quote> quotes = new ArrayList<Quote>();
	public void setQuotes(List<Quote> quotes) { this.quotes = quotes; }
	public List<Quote> getQuotes() { return this.quotes; }

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="symbol")
	private List<Tick> ticks = new ArrayList<Tick>();
	public void setTicks(List<Tick> ticks) { this.ticks = ticks; }
	public List<Tick> getTicks() { return this.ticks; }

	@OneToMany
	@JoinColumn(name="symbol")
	private List<Investment> investments = new ArrayList<Investment>();
	public void setInvestments(List<Investment> investments) { this.investments = investments; }
	public List<Investment> getInvestments() { return this.investments; }

	@Transient
	@XmlElementWrapper(name="results")
	@XmlElement(name="quote")
	private List<StockHelper> infos;

	public void update() {
		StockHelper stock = this.infos.get(0);
		this.name = stock.name;
		this.symbol = stock.symbol;
		this.infos = null;
	}

    public String getName() { return this.name; }
    public String getSymbol() { return this.symbol; }
	public Tick getLastTick() {
		return (Tick)this.ticks.get(this.ticks.size()-1);
	}
}
