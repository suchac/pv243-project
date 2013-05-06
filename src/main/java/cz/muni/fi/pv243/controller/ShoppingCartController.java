package cz.muni.fi.pv243.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;

import cz.muni.fi.pv243.model.OrderItem;
import cz.muni.fi.pv243.model.Product;
import cz.muni.fi.pv243.model.ShoppingCart;
import cz.muni.fi.pv243.service.OrderItemManager;
import cz.muni.fi.pv243.service.ProductManager;
import cz.muni.fi.pv243.service.ShoppingCartManager;

@Model
public class ShoppingCartController {

	 @Inject
	 private FacesContext facesContext;

	 @Inject
	 private Identity identity;
	 private ShoppingCart cart;
	 
	 @Inject
	 private ShoppingCartManager cartManager;
	 
	 @Inject 
	 private ProductManager productManager;
	 
	 @Inject
	 private OrderItemManager orderManager;
	 
	 @PostConstruct
	 public void initShoppingCart(){
		 if (cart == null) {
			 cart = new ShoppingCart();
			 List<OrderItem> list = new ArrayList<OrderItem>();
			 cart.setItems(list);
			 //cart.setUser(identity.getUser());
			 cartManager.create(cart);
		 }	 
	 }
	 
	 public void addToCart (Long productId, Long quantity) {
		 addToCart(productId, quantity.intValue());
	 }
	 
	 public void addToCart(Long productId, int quantity){
		 Product product = productManager.get(productId);
		 System.out.println(product);
		 List<OrderItem> items = cart.getItems();
		 System.out.println(items);
		 for (OrderItem element : items) {
			 System.out.println(element.getProduct());
			 System.out.println(element.getProduct().equals(product));
			 if(element.getProduct().equals(product)){
				 element.setQuantity(element.getQuantity()+quantity);
				 System.out.println(cart.getItems());
				 orderManager.update(element);
				 System.out.println(cart.getItems());
				 return;
			 }
		 }
		 OrderItem order = new OrderItem();
		 order.setProduct(product);
		 order.setQuantity(quantity);
		 orderManager.create(order);
		 items.add(order);
		 //propaguji se zmeny do kosiku bez implicitniho ulozeni??
	 }
	 
	 public void deleteOrderItemInCart(Long orderItemId){
		 OrderItem item = orderManager.get(orderItemId);
		 List<OrderItem> items = cart.getItems();
		 if(items.contains(item)){
			 items.remove(item);
		 }
		//propaguji se zmeny do kosiku bez implicitniho ulozeni??
	 }
	 @Named (value = "allItems")
	 public List<OrderItem> getAll(){
		 return cart.getItems();
	 }
	 
	 public BigDecimal getTotalPrice(){
		 List<OrderItem> items = getAll();
		 BigDecimal sum = new BigDecimal(0);
		 for(int i=0;i<items.size();i++){
			 sum = sum.add(getItemPrice((items.get(i)).getId()));
		 }
		 return sum;
	 }
	 
	 public BigDecimal getItemPrice(Long orderItemId){
		 OrderItem item = orderManager.get(orderItemId);
		 return ((item.getProduct()).getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
	 }
	 
	 public void clearCart(){
		 cart.setItems(new ArrayList<OrderItem>());
	 }

}
