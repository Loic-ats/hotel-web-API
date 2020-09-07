package dev.hotel.Controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;
import dev.hotel.web.ClientController;

@WebMvcTest(ClientController.class) // Controler à tester
public class ClientCtrlTest {

	@Autowired
	private MockMvc mockMvc; // Permet de vérifier les annotations du controller que l'on veux tester

	@MockBean
	ClientRepository clientRepository;

	@Test
	public void testListerClients() throws Exception {

		Client c1 = new Client();
		c1.setNom("Nom 1");
		c1.setPrenoms("Prenom 1");

		Client c2 = new Client();
		c1.setNom("Nom 2");
		c1.setPrenoms("Prenom 2");

		when(clientRepository.findAll(PageRequest.of(10, 20))).thenReturn(new PageImpl<>(Arrays.asList(c1, c2)));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=10&size=20"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].nom").value("Nom 1"))
				.andExpect(MockMvcResultMatchers.jsonPath("[0].prenom").value("Prenom 1"))
				.andExpect(MockMvcResultMatchers.jsonPath("[1].nom").value("Nom 2"));

	}

}
