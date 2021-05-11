package dash.springframework.dashpetclinic.repositories;

import dash.springframework.dashpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
