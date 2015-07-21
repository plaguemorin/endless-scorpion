package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.model.Subscription;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by plaguemorin on 20/07/15.
 */
public interface SubscriptionDataStorage extends CrudRepository<Subscription, Long> {

}
