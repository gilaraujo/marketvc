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

	@OneToMany
	@JoinColumn(name="symbol")
	private Set<Quote> quotes = new HashSet<Quote>();
	public void setQuotes(Set<Quote> quotes) { this.quotes = quotes; }
	public Set<Quote> getQuotes() { return this.quotes; }

	@OneToMany
	@JoinColumn(name="symbol")
	private Set<Tick> ticks = new HashSet<Tick>();
	public void setTicks(Set<Tick> ticks) { this.ticks = ticks; }
	public Set<Tick> getTicks() { return this.ticks; }

	@OneToMany
	@JoinColumn(name="symbol")
	private Set<Investment> investments = new HashSet<Investment>();
	public void setInvestments(Set<Investment> investments) { this.investments = investments; }
	public Set<Investment> getInvestments() { return this.investments; }

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
		return new Tick();
	}
}
