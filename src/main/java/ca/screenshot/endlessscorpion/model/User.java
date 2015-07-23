package ca.screenshot.endlessscorpion.model;

import javax.persistence.*;

/**
 * Created by plaguemorin on 22/07/15.
 */
@Entity
@Table(name = "profile")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String email;
	private String openId;

	private String firstName;
	private String lastName;

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(final String openId) {
		this.openId = openId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
}
