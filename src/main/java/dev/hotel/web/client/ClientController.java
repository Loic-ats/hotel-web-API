package dev.hotel.web.client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.ClientService.ClientService;
import dev.hotel.entite.Client;

@RestController
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	// Partie GET/Client...
	@RequestMapping(method = RequestMethod.GET, path = "clients")

	public List<Client> ListerClient(@RequestParam int start, @RequestParam int size) {

		return clientService.listerClients(start, size);

	}

	// Voir si L'UUDI d'un client est en base de donnée...

	@RequestMapping(method = RequestMethod.GET, path = "clients/{uuid}")
	public ResponseEntity<?> GetClient(@PathVariable UUID uuid) {

		Optional<Client> optClient = this.clientService.recupererClient(uuid);

		if (optClient.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optClient.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'UUDI ne correspond à aucun client en BDD");
		}
	}

	@PostMapping(path = "clients")
	public ResponseEntity<?> creerClient(@RequestBody @Valid CreerClientRequestDto client,
			BindingResult resultatValidation) {

		if (resultatValidation.hasErrors()) {
			return ResponseEntity.badRequest().body("Le Nom ou le Prénom comporte moins de 3 caractères");
		}

		return ResponseEntity
				.ok(new CreerClientResponseDto(clientService.creerNouveauClient(client.getNom(), client.getPrenoms())));
	}

}
