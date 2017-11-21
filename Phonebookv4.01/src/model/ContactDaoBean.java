/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**The EJB bean, handles the business logic part of the application
 * @author nishant
 * @version 1.3 30 June 2013
 */
//No-interface view, stateless LocalBean
@Stateless 
@LocalBean
public class ContactDaoBean {


	@PersistenceContext//Makes the session bean thread-safe, no need of using transaction handling
	private EntityManager entityManager; //EntityManager is used to persist entities to a DB
	
	public ContactDaoBean() {
	}


	/**persists to the db
	 * @param contact the contact to be added
	 */
	public void addContact(Contacts contact) {
		entityManager.persist(contact); //Persists the current contact in a row of the DB table
	}

	

	/**deletes the contact
	 * @param contact the contact to be deleted
	 */
	public void deleteContact(Contacts contact) {
		entityManager.remove(entityManager.merge(contact)); //Deletes the contact of the DB table 
	}


	/**edits the contact and updates the table
	 * @param contact the contact to be edited
	 */
	public void edit(Contacts contact) {
		entityManager.merge(contact); //Merges the contact to the DB table
	}

	/**Queries the db for all the entries and stores in a list
	 * @return List contains all the entries of the db table
	 */
	public List<Contacts> getList() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); //invokes the getCriteriaBuilder() method on the entity manager
		CriteriaQuery<Contacts> query = cb.createQuery(Contacts.class); //obtains the instance of Contacts class implementing CriteriaQuery interface
		Root<Contacts> c = query.from(Contacts.class);//Jpa entity to be querying from (equivalent to 'from' in SQL)
		query.select(c);
		query.orderBy(cb.asc(c.get("firstname")));//Orders by Firstname

		return entityManager.createQuery(query).getResultList();//returns query results as a list
	}


	/**Queries the db table with the searchString
	 * @param searchString the string to be searched in the table
	 * @return List contains list of the result of the query
	 */
	public List<Contacts> search(String searchString) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contacts> query = cb.createQuery(Contacts.class);
		Root<Contacts> c = query.from(Contacts.class);
		
		List<Predicate> plist = new ArrayList<Predicate>(); 
		plist.add(cb.like(c.<String> get("firstname"), "%" + searchString + "%"));
		plist.add(cb.like(c.<String> get("lastname"), "%" + searchString + "%"));
		plist.add(cb.like(c.<String> get("city"), "%" + searchString + "%"));
		plist.add(cb.like(c.<String> get("company"), "%" + searchString + "%"));
		plist.add(cb.like(c.<String> get("phone"), "%" + searchString + "%"));
	
		query.select(c);
		query.where(cb.or(plist.toArray(new Predicate[plist.size()])));//The predicates are conjuncted using or
			
		return entityManager.createQuery(query).getResultList();
	
	}

}
