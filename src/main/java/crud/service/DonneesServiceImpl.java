package crud.service;

import java.util.List;

import javax.inject.Inject;

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
    
    public Donnees update(Donnees donnee) {
    	return donneesDAO.update(donnee);
}
    
}
