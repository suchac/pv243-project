package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: ShoppingCart
 *
 */
@Entity

public class ShoppingCart implements Serializable {

	   
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(fetch=FetchType.EAGER, orphanRemoval=true)
	@NotNull
	private List<OrderItem> items;
	private static final long serialVersionUID = 1L;

	public ShoppingCart() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public List<OrderItem> getItems() {
		return this.items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}   
	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", items=" + items +
				 "]";
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
		ShoppingCart other = (ShoppingCart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   
}