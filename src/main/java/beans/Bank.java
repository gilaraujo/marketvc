package br.usp.marketvc.beans;

import java.util.*;
import java.text.*;
import org.hibernate.*;
import javax.persistence.*;
import br.usp.marketvc.util.*;
import br.usp.marketvc.config.*;
import javax.xml.bind.*;
import java.net.*;
import java.io.*;

@Entity
@Table(name="tbank")
public class Bank implements Default {
	private static Bank singleton = null;
	private static Double interestrate;
	static {
		singleton = new Bank();
		
	}
	public synchronized static Bank getInstance() {
		return singleton;
	}
	public static Double getInterestRate() {
		return interestrate;
	}
}
