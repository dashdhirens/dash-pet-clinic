package dash.springframework.dashpetclinic.bootstrap;

import dash.springframework.dashpetclinic.model.Owner;
import dash.springframework.dashpetclinic.model.Pet;
import dash.springframework.dashpetclinic.model.PetType;
import dash.springframework.dashpetclinic.model.Vet;
import dash.springframework.dashpetclinic.services.OwnerService;
import dash.springframework.dashpetclinic.services.PetTypeService;
import dash.springframework.dashpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;

        System.out.println("--------In bootstrap dataloader constructor....");
    }

    @Override
    public void run(String... args) throws Exception {

        PetType catPetType = new PetType();
        catPetType.setName("Sample Cat");
        catPetType = petTypeService.save(catPetType);

        PetType dogPetType = new PetType();
        dogPetType.setName("Sample Dog");
        dogPetType = petTypeService.save(dogPetType);

        Owner owner1 = new Owner();
        owner1.setFirstName("Dhiren");
        owner1.setLastName("Dash");
        owner1.setAddress("841 Jalvayu Towers");
        owner1.setCity("Bangalore");
        owner1.setTelephone("9591173557");

        Pet dashPet = new Pet();
        dashPet.setPetType(dogPetType);
        dashPet.setName("Mickey");
        dashPet.setOwner(owner1);
        dashPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(dashPet);


        Owner owner2 = new Owner();
        owner2.setFirstName("Jess");
        owner2.setLastName("Rollins");
        owner2.setAddress("842 Jalvayu Towers");
        owner2.setCity("Bangalore");
        owner2.setTelephone("9591175667");

        Pet jessPet = new Pet();
        jessPet.setName("Willy");
        jessPet.setPetType(catPetType);
        jessPet.setOwner(owner2);
        jessPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(jessPet);

        ownerService.save(owner1);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Maker");
        vet1.setLastName("Boots");

        Vet vet2 = new Vet();
        vet2.setFirstName("Teesta");
        vet2.setLastName("Wreck");

        vetService.save(vet1);
        vetService.save(vet2);
    }
}
