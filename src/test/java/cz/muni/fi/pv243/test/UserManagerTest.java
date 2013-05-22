package cz.muni.fi.pv243.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv243.model.OrderItem;
import cz.muni.fi.pv243.model.Product;
import cz.muni.fi.pv243.model.ShoppingCart;
import cz.muni.fi.pv243.model.User;
import cz.muni.fi.pv243.service.UserManager;
import cz.muni.fi.pv243.service.UserManagerImpl;
import cz.muni.fi.pv243.util.Resources;

@RunWith(Arquillian.class)
public class UserManagerTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(User.class, ShoppingCart.class, OrderItem.class,
						Product.class, UserManager.class,
						UserManagerImpl.class, Resources.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");
	}

	@Inject
	Logger log;

	@Inject
	UserManager userManager;

	@Test
	public void findByEmailTest() {
		User user = new User();
		user.setName("Pepa z depa");
		user.setEmail("nebuduTo@milujipraci.cz");
		user.setAddress("doma");
		user.setPasswordHash("totalniH4sH");
		userManager.create(user);
		userManager.findByEmail("nebuduTo@milujipraci.cz");
	}

	@Test
	public void createUser() {
		User user = new User();
		user.setName("Pepa Depa");
		user.setEmail("nebuduTo@milujipraci.cz");
		user.setAddress("doma");
		user.setPasswordHash("totalniH4sH");
		userManager.create(user);
		
		User user2 = new User();
		user2.setName("Pepa z depa");
		user2.setEmail("nebuduTo@milujipraci.cz");
		user2.setAddress("doma");
		user2.setPasswordHash("totalniH4sH");
		user2.setId(9l);
		try{
			userManager.create(user2);
		} catch (IllegalArgumentException e){
			
		}
		try{
			userManager.create(null);
		} catch (IllegalArgumentException e){
			
		}
	}
	
	@Test
	public void createUserName() {
		User user = new User();
		user.setName("");
		user.setEmail("nebuduTo@milujipraci.cz");
		user.setAddress("doma");
		user.setPasswordHash("totalniH4sH");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}

		user.setName("jmeno");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}
		
		user.setName(null);
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}
	
		user.setName("Pavel Burke");
		userManager.create(user);
	}
	
	@Test
	public void createUserEmail() {
		User user = new User();
		user.setName("Pavel Burke");
		user.setEmail("");
		user.setAddress("doma");
		user.setPasswordHash("totalniH4sH");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}

		user.setEmail("email");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}
		
		user.setEmail(null);
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}
		
		user.setEmail("e@e");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}
	
		user.setEmail("e.e");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}

		user.setEmail("eee9873434333545454545454545eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee@seznam.com");
		
		try{
			userManager.create(user);
		}catch (IllegalArgumentException e){
			
		}
		user.setEmail("borec@zlesa.com");
		userManager.create(user);
	}
	
	@Test
	public void wrongQueries() {
		assertNull(userManager.findByEmail("tenTuNeni!"));
	}
	
	@Test
	public void testUsers(){
		log.info(userManager.findAll().toString());
	}
}
