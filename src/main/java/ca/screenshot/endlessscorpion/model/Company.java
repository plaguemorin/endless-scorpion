package ca.screenshot.endlessscorpion.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String uuid;
	private String name;

	@OneToMany
	private List<Subscription> subscriptions;

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

	public Collection<Subscription> getSubscriptions() {
		return Collections.unmodifiableList(this.subscriptions);
	}

	public void setSubscriptions(final List<Subscription> subscriptions) {
		// Might want to copy the list in real-life situations
		this.subscriptions = subscriptions;
	}
}
