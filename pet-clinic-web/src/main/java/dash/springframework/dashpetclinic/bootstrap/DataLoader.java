package dash.springframework.dashpetclinic.bootstrap;

import dash.springframework.dashpetclinic.model.*;
import dash.springframework.dashpetclinic.services.OwnerService;
import dash.springframework.dashpetclinic.services.PetTypeService;
import dash.springframework.dashpetclinic.services.SpecialityService;
import dash.springframework.dashpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;

        System.out.println("--------In bootstrap dataloader constructor....");
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        //Define pet types
        PetType catPetType = new PetType();
        catPetType.setName("Sample Cat");
        catPetType = petTypeService.save(catPetType);

        PetType dogPetType = new PetType();
        dogPetType.setName("Sample Dog");
        dogPetType = petTypeService.save(dogPetType);

        //Define specialities
        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        surgery = specialityService.save(surgery);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        radiology = specialityService.save(radiology);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        dentistry = specialityService.save(dentistry);

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
        System.out.println("Loaded owners........");

        Vet vet1 = new Vet();
        vet1.setFirstName("Maker");
        vet1.setLastName("Boots");
        vet1.getSpecialities().add(surgery);

        Vet vet2 = new Vet();
        vet2.setFirstName("Teesta");
        vet2.setLastName("Wreck");
        vet2.getSpecialities().add(radiology);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded vets........");
    }
}
