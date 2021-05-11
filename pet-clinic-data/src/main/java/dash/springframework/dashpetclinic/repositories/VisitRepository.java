package dash.springframework.dashpetclinic.repositories;

import dash.springframework.dashpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
