package crud.service;

import java.util.List;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
import crud.model.Donnees;

public interface DonneesService {
	public Donnees find(Donnees donnee);
	public List<Donnees> findAll();
	public Donnees create(Donnees donnee);
	public void delete(Donnees donnee);
	public void deleteAval(String soc,  int an, int trim, String regroup);
	public Donnees update(Donnees donnee);
	public List<AvaliderRow> findAvalider(String soc, int an, int trim); 
	public List<AvaliderBoxRow> findAvaliderBox(); 
}
