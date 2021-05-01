package dash.springframework.dashpetclinic.bootstrap;

import dash.springframework.dashpetclinic.model.Owner;
import dash.springframework.dashpetclinic.model.Vet;
import dash.springframework.dashpetclinic.services.OwnerService;
import dash.springframework.dashpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;

        System.out.println("--------In bootstrap dataloader constructor....");
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Dhiren");
        owner1.setLastName("Dash");

        Owner owner2 = new Owner();
        owner2.setFirstName("Jess");
        owner2.setLastName("Rollins");

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
