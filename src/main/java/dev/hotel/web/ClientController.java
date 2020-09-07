package dev.hotel.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	// Voir si L'UUDI d'un client est en base de donnée...

	@RequestMapping(method = RequestMethod.GET, path = "clients/{uuid}")
	public ResponseEntity<?> GetClient(@PathVariable UUID uuid) {

		Optional<Client> optClient = this.clientRepository.findById(uuid);

		if (optClient.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optClient.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'UUDI ne correspond à aucun client en BDD");
		}
	}

}
