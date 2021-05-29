package dash.springframework.dashpetclinic.controllers;

import dash.springframework.dashpetclinic.model.Owner;
import dash.springframework.dashpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnersControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnersController ownersController;

    Set<Owner> setOfOwners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        setOfOwners = new HashSet<>();
        setOfOwners.add(Owner.builder().id(1l).build());
        setOfOwners.add(Owner.builder().id(2l).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownersController)
                .build();
    }

    @Test
    void listOwners() throws Exception {
        Mockito.when(ownerService.findAll()).thenReturn(setOfOwners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/notImplemented"));

        verifyNoInteractions(ownerService);
    }
}
