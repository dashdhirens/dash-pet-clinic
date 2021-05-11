package dash.springframework.dashpetclinic.repositories;

import dash.springframework.dashpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
