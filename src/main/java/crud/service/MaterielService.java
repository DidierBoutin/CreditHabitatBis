package crud.service;

import java.util.List;

import crud.model.Materiel;

public interface MaterielService {

	public Materiel findMateriel(String materiel);
	
	public List<Materiel> findAllMateriel();

	public Materiel createMateriel(Materiel materiel);

	public void deleteMateriel(String materiel);
	
	public Materiel updateMateriel(Materiel materiel);


}
