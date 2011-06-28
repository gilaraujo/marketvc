package br.usp.marketvc.beans;

import java.util.*;
import java.io.*;
import javax.persistence.*;
import java.security.*;
import br.usp.marketvc.config.*;

@Entity
@Table(name="tuser")
public class User implements Serializable, Default {
@Id
@Column(name="email", unique=true, nullable=false)
private String email;
@Column(name="passwd", nullable=false, length=64)
private String passwd;
@Column(name="name", nullable=false, length=80)
private String name;
@Column(name="funds", nullable=false)
private Double funds;

public User() {
	this.funds = defaultFunds;
}

private static String stringHexa(byte[] bytes) {
   StringBuilder s = new StringBuilder();
   for (int i = 0; i < bytes.length; i++) {
       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
       int parteBaixa = bytes[i] & 0xf;
       if (parteAlta == 0) s.append('0');
       s.append(Integer.toHexString(parteAlta | parteBaixa));
   }
   return s.toString();
}

public static byte[] gerarHash(String frase) {
  try {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(frase.getBytes());
    return md.digest();
  } catch (NoSuchAlgorithmException e) {
    return null;
  }
}

public void setEmail(String email) { this.email = email; }

public void setPasswd(String passwd) { 
	passwd = stringHexa(gerarHash(passwd));
	this.passwd = passwd;
}

public boolean hasEnoughFunds(double amount) {
	return (this.funds >= amount);
}

public boolean decreaseFunds(double amount) {
	if (hasEnoughFunds(amount)) {
		this.funds -= amount;
		return true;
	}
	return false;
}

public void setName(String name) { this.name = name; }

public String getEmail() { return this.email; }
public String getPasswd() { return this.passwd; }
public String getName() { return this.name; }

/*@OneToMany
@JoinColumn (name="email")
private Set<Product> products = new HashSet<Product>();
public Set<Product> getProducts() { return this.products; }
public void setProducts(Set<Product> products) { this.products = products; }
@OneToMany
@JoinColumn (name="buys")
private Set<Order> buys = new HashSet<Order>();
public Set<Order> getBuys() { return this.buys; }
public void setBuys(Set<Order> buys) { this.buys = buys; }
*/
}
