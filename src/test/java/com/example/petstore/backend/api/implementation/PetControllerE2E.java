package com.example.petstore.backend.api.implementation;

import com.example.petstore.backend.api.model.Category;
import com.example.petstore.backend.api.model.Pet;
import com.example.petstore.backend.api.model.Tag;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PetController.class)
public class PetControllerE2E {

    //https://reflectoring.io/spring-boot-web-controller-test/#3-verifying-input-validation

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetRepository pr;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        //exact value from swagger-ui
        Pet pet = new Pet()
                .id(0L)
                .name("string")
                .category(new Category()
                        .id(0L)
                        .name("string"))
                .photoUrls(Collections.singletonList("string"))
                .tags(Collections.singletonList(new Tag()
                        .id(0L)
                        .name("string")))
                .status(Pet.StatusEnum.AVAILABLE);

        mockMvc.perform(post("/pet")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(pet))
            .accept("application/json")) //TODO enable xml as well
            .andExpect(status().isOk());

        ArgumentCaptor<PetBE> petCaptor = ArgumentCaptor.forClass(PetBE.class);
        verify(pr, times(1)).save(petCaptor.capture());
        assertThat(petCaptor.getValue().getId()).isEqualTo(0L);
        assertThat(petCaptor.getValue().getName()).isEqualTo("string");

    }

    @Test
    void whenNullValue_thenReturns400() throws Exception {
        //https://reflectoring.io/spring-boot-web-controller-test/#3-verifying-input-validation
        //gives exception
        Pet pet = new Pet()
                .id(0L)
                .name(null)
                .category(new Category()
                        .id(0L)
                        .name("string"))
                .photoUrls(Collections.singletonList("string"))
                .tags(Collections.singletonList(new Tag()
                        .id(0L)
                        .name("string")))
                .status(Pet.StatusEnum.AVAILABLE);

        mockMvc.perform(post("/pet")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isBadRequest());
    }

}
