package dash.springframework.dashpetclinic.services;

import dash.springframework.dashpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
