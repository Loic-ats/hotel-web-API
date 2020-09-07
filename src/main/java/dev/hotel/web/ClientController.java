package dev.hotel.web;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
public class ClientController {

	private ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;

	}

	// Partie GET/Client...
	@RequestMapping(method = RequestMethod.GET, path = "clients")

	public List<Client> ListerClient(@RequestParam int start, @RequestParam int size) {

		return clientRepository.findAll(PageRequest.of(start, size)).getContent();

	}

	public ResponseEntity<?> postClient(@RequestBody Client client) {

		if (client.getNom().length() <= 2) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'UUDI ne correspond à aucun client en BDD");
		} else {
			return ResponseEntity.status(HttpStatus.OK).header("message", "cool").body(client);
		}
	}

}
