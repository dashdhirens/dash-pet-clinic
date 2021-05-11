package dash.springframework.dashpetclinic.repositories;

import dash.springframework.dashpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
