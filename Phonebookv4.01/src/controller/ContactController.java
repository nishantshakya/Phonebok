/**
 * 
 */
package controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.ContactDaoBean;
import model.Contacts;

/**Controller Part, handles the communication between model and view part
 * @author nishant
 * @version 1.3 30 June 2013
 */

@SessionScoped //An instance of each session scoped managed bean is assigned to each of the application's clients.
@ManagedBean(name = "contactController")// allows the use of value binding expression in our jsf pages
public class ContactController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB//Lets the EJB Container know that contactDaoBean is a variable used as a business interface for the sessionbean
	ContactDaoBean contactDaoBean;
	
	@ManagedProperty(value = "#{contacts}")//specifies the logical name of the bean to bind to the property of JSF expression matching the bean's name. 
	Contacts contacts;

	private String searchString;


	public ContactController() {

	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}
	
	public String getSearchString() {
		return searchString;
	}


	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**Lists the contact according to the string in searchString
	 * @return List that stores the Db table values  
	 */
	public List<Contacts> getContactList() {
		//Checks the searchString if its not or null
		if (searchString == null || searchString.isEmpty()
				|| searchString.equals("")) {
			//if null gets all the contacts
			return contactDaoBean.getList();
		} else {
			//else searches the db table for the searchString
			return contactDaoBean.search(searchString);
		}
	}

	/**Calls the edit or addcontact function according to the method required
	 * @return view the page to be navigated to
	 */
	public String add() {
		if (contacts.getIdcontacts() > 0) {
			System.out.println("asdfasdf" + contacts.getIdcontacts());
			contactDaoBean.edit(contacts);

			contacts = null;
		} else {
			contactDaoBean.addContact(contacts);
		}
		return "view";
	}

	/**Delete method
	 * @param contact contact entry to be deleted
	 * @return null same page
	 */
	public String delete(Contacts contact) {
		contactDaoBean.deleteContact(contact);
		return null;
	}

	/**Edit Method
	 * @param currentcontact the contact entry to be edited
	 * @return contact the page name to be navigated to
	 */
	public String edit(Contacts currentcontact) {
		System.out.println(currentcontact.getIdcontacts());
		this.contacts = currentcontact;
		return "contact";
	}

}