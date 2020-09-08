package dev.hotel.web.reservations;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.Service.ReservationService;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.HotelException;

@RestController
@RequestMapping("reservations")
public class ReservationsController {

	private ReservationService resServ;

	/**
	 * @param resR
	 * @return
	 */
	public void ReservationController(ReservationService resServ) {
		this.resServ = resServ;
	}

	@PostMapping
	public ResponseEntity<?> reservations(@RequestBody @Valid CreerReservationsRequestDto res, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Reservation reservationCree = resServ.creerReservation(res.getDateDebut(), res.getDateFin(),
					res.getClientId(), res.getChambres());
			CreerReservationsResponseDto reservationResponse = new CreerReservationsResponseDto(reservationCree);

			return ResponseEntity.ok(reservationResponse);
		} else {
			return ResponseEntity.badRequest().body(" Tous les champs sont obligatoires !");
		}

	}

	@ExceptionHandler(HotelException.class)
	public ResponseEntity<List<String>> onHotelException(HotelException ex) {
		return ResponseEntity.badRequest().body(ex.getMessagesErreurs());
	}

}
