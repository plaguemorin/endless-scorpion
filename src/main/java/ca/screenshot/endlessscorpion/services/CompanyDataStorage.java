package ca.screenshot.endlessscorpion.services;

import ca.screenshot.endlessscorpion.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDataStorage extends CrudRepository<Company, Long> {
	Company findByUuid(final String uuid);
}
