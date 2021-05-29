package dash.springframework.dashpetclinic.services.map;

import dash.springframework.dashpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;
    final String lastName = "Dash";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetMapService());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertNotNull(owner);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> ownerList = ownerServiceMap.findAll();

        assertEquals(1, ownerList.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerServiceMap.findById(ownerId).getId());

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long ownerId2 = 2l;

        Owner owner2 = Owner.builder().id(ownerId2).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(ownerId2, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner ownerWithNoId = Owner.builder().firstName("Dhiren").lastName("Dash").build();

        Owner savedOwner = ownerServiceMap.save(ownerWithNoId);

        assertNotNull(savedOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner ownerWithLastName = ownerServiceMap.findByLastName(lastName);

        assertNotNull(ownerWithLastName);

        assertEquals(ownerId, ownerWithLastName.getId());
    }
}
