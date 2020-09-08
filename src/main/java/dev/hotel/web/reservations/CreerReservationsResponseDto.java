package dev.hotel.web.reservations;

import java.util.UUID;

import dev.hotel.entite.Reservation;

public class CreerReservationsResponseDto extends CreerReservationsRequestDto {

	private UUID uuid;

	public CreerReservationsResponseDto(Reservation reservation) {

		this.uuid = reservation.getUuid(uuid);
		this.setClient(reservation.getClient());
		this.setDateDebut(reservation.getDateDebut());
		this.setDateFin(reservation.getDateFin());

	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
