package crud.service;

import java.util.List;

import javax.inject.Inject;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
import crud.model.Donnees;
import crud.repository.DonneesDAO;

public class DonneesServiceImpl implements DonneesService {
	@Inject
	DonneesDAO donneesDAO;
	
	@Inject
	public DonneesServiceImpl() {
		super();
	}
	
	public Donnees find(Donnees donnee ) {
    	return donneesDAO.find(donnee);
    }
    	
    public List<Donnees> findAll() {
        return donneesDAO.findAll();
    }
	
    public Donnees create(Donnees donnee) {
    	return donneesDAO.create(donnee);
    }
    	
    public void delete(Donnees donnee) {
    	donneesDAO.delete(donnee);
    }
    
    public void deleteAval(String soc, int an, int trim ) {
    	donneesDAO.deleteAval(soc, an, trim);
    }
    
    public Donnees update(Donnees donnee) {
    	return donneesDAO.update(donnee);
}
    public List<AvaliderRow> findAvalider(String soc, int an, int trim) {
		return donneesDAO.findAvalider(soc, an, trim);
	}
	
	public List<AvaliderBoxRow> findAvaliderBox() {
		return donneesDAO.findAvaliderBox();
	}
}
