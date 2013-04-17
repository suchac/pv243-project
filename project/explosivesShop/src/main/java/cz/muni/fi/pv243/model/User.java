package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	   
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String address;	
	@NotNull
	private String email;
	@NotNull
	private String passwordHash;
	@OneToOne(fetch=FetchType.EAGER, orphanRemoval=true)
	private ShoppingCart cart;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public String getEmail() {
		return this.email;
	}

	public ShoppingCart getCart() {
		return cart;
	}
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}   
	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address
				+ ", email=" + email + "]";
	}
   
}