package dev.hotel.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;

	private ClientService clientService;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public Reservation creerNouvelleReservation(LocalDate dateDebut, LocalDate dateFin, List<UUID> chambresID,
			UUID uuid) {

		Optional<Client> optclient = clientService.recupererClient(uuid);

		if (optclient.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optclient.get());

		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'UUDI ne correspond à aucun client");
		}

		return reservationRepository.save(nouvelleReservation);
	}
}
