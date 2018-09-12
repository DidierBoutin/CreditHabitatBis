package crud.service;

import java.util.List;

import crud.model.Donnees;

public interface DonneesService {
	public Donnees find(Donnees donnee);
	public List<Donnees> findAll();
	public Donnees create(Donnees donnee);
	public void delete(Donnees donnee);
	public Donnees update(Donnees donnee);
}
