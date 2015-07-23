package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.beans.event.Creator;
import ca.screenshot.endlessscorpion.beans.event.Event;
import ca.screenshot.endlessscorpion.model.Company;
import ca.screenshot.endlessscorpion.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by plaguemorin on 15/07/15.
 *
 * SUBSCRIPTION_ORDER: fired by AppDirect when a user buys an app from AppDirect.
 * SUBSCRIPTION_CHANGE: fired by AppDirect when a user upgrades/downgrades/modifies an existing subscription.
 * SUBSCRIPTION_CANCEL: fired by AppDirect when a user cancels a subscription.
 * SUBSCRIPTION_NOTICE: fired by AppDirect when a subscription goes overdue or delinquent.
 * USER_ASSIGNMENT: fired by AppDirect when a user assigns a user to an app.
 * USER_UNASSIGNMENT: fired by AppDirect when a user unassigns a user from an app.
 */
public class DefaultEventProcessService implements EventProcessService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultEventProcessService.class);

	@Autowired
	private CompanyDataStorage companyDataStorage;
	@Autowired
	private UserDataStorage userDataStorage;

	@Override
	public void processEvent(final Event event) {
		LOG.info("Event Type = {}", event.getType());
		if (!event.isRealProductionEvent()) {
			LOG.info("Event is not real, not processing");
			return;
		}

		switch (event.getType()) {
			case SUBSCRIPTION_ORDER:
				this.handleOrder(event);
				break;
			case SUBSCRIPTION_CHANGE:
				this.handleChange(event);
				break;
			case SUBSCRIPTION_CANCEL:
				this.handleSubscriptionCancel(event);
				break;
			case SUBSCRIPTION_NOTICE:
				this.handleSubscriptionNotice(event);
				break;
			case USER_ASSIGNMENT:
				this.handleUserAssignment(event);
				break;
			case USER_UNASSIGNMENT:
				this.handleUserUnassignment(event);
				break;
			default:
				// This should never happen
				LOG.error("Unknown even type = {}", event.getType());
		}
	}

	private void handleOrder(final Event event) {
		// Get the company uuid
		final String uuid = event.getPayload().getCompany().getUuid();

		// TODO: Should this logic be here ?
		Company comp = this.companyDataStorage.findByUuid(uuid);
		if (comp == null) {
			comp = new Company();
			comp.setName(event.getPayload().getCompany().getName());
			comp.setUuid(uuid);

			this.companyDataStorage.save(comp);

			final Creator creator = event.getCreator();
			final User myUser = new User();

			myUser.setEmail(creator.getEmail());
			myUser.setFirstName(creator.getFirstName());
			myUser.setLastName(creator.getLastName());
			myUser.setOpenId(creator.getOpenId());

			this.userDataStorage.save(myUser);
		}

		// TODO: Do something with the company
	}

	private void handleUserUnassignment(final Event event) {

	}

	private void handleUserAssignment(final Event event) {

	}

	private void handleSubscriptionNotice(final Event event) {

	}

	private void handleSubscriptionCancel(final Event event) {

	}

	private void handleChange(final Event event) {

	}
}
