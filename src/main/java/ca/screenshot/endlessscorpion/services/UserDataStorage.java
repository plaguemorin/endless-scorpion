package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by plaguemorin on 22/07/15.
 *
 */
public interface UserDataStorage extends CrudRepository<User, Long> {

}
