package dash.springframework.dashpetclinic.services.springdatajpa;

import dash.springframework.dashpetclinic.model.Owner;
import dash.springframework.dashpetclinic.repositories.OwnerRepository;
import dash.springframework.dashpetclinic.repositories.PetRepository;
import dash.springframework.dashpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Dash";

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner mockedOwner;

    @BeforeEach
    void setUp() {
        mockedOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockedOwner));

        Owner foundOwner = ownerSDJpaService.findById(1l);

        assertNotNull(foundOwner);

        Mockito.verify(ownerRepository).findById(Mockito.anyLong());
    }

    @Test
    void findByIdNotExists() {
        Mockito.when(ownerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Owner foundOwner = ownerSDJpaService.findById(1l);

        assertNull(foundOwner);

        Mockito.verify(ownerRepository).findById(Mockito.anyLong());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerList = new HashSet<>();
        returnOwnerList.add(Owner.builder().id(1l).lastName(LAST_NAME).build());
        returnOwnerList.add(Owner.builder().id(2l).lastName(LAST_NAME).build());

        Mockito.when(ownerRepository.findAll()).thenReturn(returnOwnerList);

        Set verifyOwnerList = ownerSDJpaService.findAll();

        assertEquals(2, verifyOwnerList.size());

        Mockito.verify(ownerRepository, Mockito.times(1)).findAll();
    }

    @Test
    void save() {
        Mockito.when(ownerRepository.save(Mockito.any())).thenReturn(mockedOwner);

        Owner savedOwner = ownerSDJpaService.save(mockedOwner);

        assertNotNull(savedOwner);
        assertEquals(savedOwner.getId(), mockedOwner.getId());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(mockedOwner);

        Mockito.verify(ownerRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deleteById() {

        ownerSDJpaService.deleteById(1l);

        Mockito.verify(ownerRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void findByLastName() {
        Owner mockedOwner = Owner.builder().lastName(LAST_NAME).build();

        Mockito.when(ownerRepository.findByLastName(Mockito.any())).thenReturn(mockedOwner);

        assertEquals(LAST_NAME, ownerSDJpaService.findByLastName(LAST_NAME).getLastName());

        Mockito.verify(ownerRepository).findByLastName(Mockito.any());

    }
}
