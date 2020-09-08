package dev.hotel.web.reservations;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import dev.hotel.entite.Client;

public class CreerReservationsRequestDto {

	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Client Client;
	private List<UUID> Chambre;

	public CreerReservationsRequestDto(LocalDate dateDebut, LocalDate dateFin, Client client, List<UUID> chambre) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.Client = client;
		this.Chambre = chambre;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Client getClient() {
		return Client;
	}

	public void setClient(Client client) {
		Client = client;
	}

	public List<UUID> getChambre() {
		return Chambre;
	}

	public void setChambre(List<UUID> chambre) {
		Chambre = chambre;
	}

}
