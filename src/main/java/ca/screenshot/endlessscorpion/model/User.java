package ca.screenshot.endlessscorpion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by plaguemorin on 22/07/15.
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;


	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
