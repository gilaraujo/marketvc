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

    @XmlElement(name="YearLow")
    public Double yearlow;

    @XmlElement(name="YearHigh")
    public Double yearhigh;

}

@Entity
@Table(name="tstock")
@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stock {
	public Stock() { }

	@Column(name="name", nullable=false)
    private String name;

	@Id
	@Column(name="symbol", nullable=false, unique=true)
    private String symbol;

	@Column(name="yearlow")
    private Double yearlow;

	@Column(name="yearhigh")
    private Double yearhigh;

	@Transient
	@XmlElementWrapper(name="results")
	@XmlElement(name="quote")
	private List<StockHelper> infos;

	public void update() {
		StockHelper stock = this.infos.get(0);
		this.name = stock.name;
		this.symbol = stock.symbol;
		this.yearlow = stock.yearlow;
		this.yearhigh = stock.yearhigh;
		this.infos = null;
	}

    public String getName() { return this.name; }
    public String getSymbol() { return this.symbol; }
    public Double getYearLow() { return this.yearlow; }
    public Double getYearHigh() { return this.yearhigh; }

}