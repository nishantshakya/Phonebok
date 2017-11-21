/**
 * 
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**The JPA entity Bean Contacts 
 * @author nishant
 * @version 1.3 30 June 2013
 */
@Entity // Lets the server know that this class is an entity
@Table(name = "contacts")// This entity is mapped to Db table 'contacts'
@ManagedBean(name = "contacts")// JPA entity as a JSF Managed Bean, this allows the use of value binding expression in our jsf pages
public class Contacts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	// Maps idcontacts field to primary key
	@GeneratedValue
	// field idcontacts generated automatically
	private int idcontacts;
	private Integer bno;
	private String city;
	private String company;
	private String email;
	private String firstname;
	private String lastname;
	private String phone;
	private String street;
	private String wphone;

	public Contacts() {
	}

	public int getIdcontacts() {
		return this.idcontacts;
	}

	public void setIdcontacts(int idcontacts) {
		this.idcontacts = idcontacts;
	}

	public Integer getBno() {
		return this.bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWphone() {
		return this.wphone;
	}

	public void setWphone(String wphone) {
		this.wphone = wphone;
	}

}
