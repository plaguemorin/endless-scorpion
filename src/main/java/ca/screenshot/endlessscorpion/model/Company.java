package ca.screenshot.endlessscorpion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String uuid;
	private String name;

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
}
