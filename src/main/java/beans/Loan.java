package br.usp.marketvc.beans;

import java.util.*;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

@Entity
@Table(name="tloan")
public class Loan {
	public Loan() { }

	@Id
	@Column(name="lid", unique=true, nullable=false)
	@SequenceGenerator(name = "seq_lid", sequenceName = "seq_lid")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_lid")
	private Long lid;

	@Column(name="amount")
    private Double amount;

	@Column(name="interest")
    private Double interest;

	@ManyToOne
	@JoinColumn(name="email", nullable=true, updatable=false, insertable=false)
	private User owner;
	public User getOwner() { return this.owner; }
	public void setOwner(User owner) { this.owner = owner; }

	public Long getId() { return this.lid; }
    public Double getAmount() { return this.amount; }
    public Double getInterest() { return this.interest; }

	public void setAmount(Double amount) { this.amount = amount; }
	public void setInterest(Double interest) { this.interest = interest; }

	public void updateInterest() { this.amount += this.amount * (this.interest/100); }
}
