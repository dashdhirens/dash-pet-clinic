package dash.springframework.dashpetclinic.repositories;

import dash.springframework.dashpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
