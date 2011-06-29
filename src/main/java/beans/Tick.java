package br.usp.marketvc.beans;

import java.util.*;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

@XmlAccessorType(XmlAccessType.FIELD)
class TickHelper {

    @XmlElement(name="LastTradePriceOnly")
    public Double lasttrade;

    @XmlElement(name="Change")
    public Double change;

    @XmlElement(name="ChangeinPercent")
    public String changeinpercent;

    @XmlElement(name="Ask")
    public Double ask;

    @XmlElement(name="Bid")
    public Double bid;

    @XmlElement(name="Volume")
    public Double volume;

}

@Entity
@Table(name="ttick")
@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tick {
	public Tick() { }

	@Id
	@Column(name="tid", unique=true, nullable=false)
	@SequenceGenerator(name = "seq_tid", sequenceName = "seq_tid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_tid")
	private Long tid;

	@Column(name="lasttrade")
    private Double lasttrade;

	@Column(name="change")
    private Double change;

	@Column(name="changeinpercent")
    private Double changeinpercent;

	@Column(name="ask")
    private Double ask;

	@Column(name="bid")
    private Double bid;

	@Column(name="volume")
    private Double volume;

    @Transient
	@XmlElementWrapper(name="results")
	@XmlElement(name="quote")
	private List<TickHelper> infos;

	public void update() {
		TickHelper tick = this.infos.get(0);
		this.lasttrade = tick.lasttrade;
		this.change = tick.change;
		this.changeinpercent = Double.parseDouble(tick.changeinpercent.substring(0,tick.changeinpercent.length()-1));
		this.ask = tick.ask;
		this.bid = tick.bid;
		this.volume = tick.volume;
		this.infos = null;
	}

	public Long getId() { return this.tid; }
    public Double getLastTrade() { return this.lasttrade; }
    public Double getChange() { return this.change; }
    public Double getChangeInPercent() { return this.changeinpercent; }
    public Double getAsk() { return this.ask; }
    public Double getBid() { return this.bid; }
    public Double getVolume() { return this.volume; }

}
