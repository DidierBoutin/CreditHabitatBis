package crud.service;

import java.util.List;

import javax.inject.Inject;
 
import crud.model.Materiel;
import crud.repository.MaterielDAO;


public class MaterielServiceImpl implements MaterielService{

	@Inject
	MaterielDAO materielDAO;
	
	@Inject
	public MaterielServiceImpl() {
		super();
	}
	
	public Materiel findMateriel(String materiel) {
    	return materielDAO.find(materiel);
    }
    	
    public List<Materiel> findAllMateriel() {
        return materielDAO.findAll();
    }
	
    public Materiel createMateriel(Materiel materiel) {
    	return materielDAO.create(materiel);
    }
    	
    public void deleteMateriel(String materiel) {
        materielDAO.delete(materiel);
    }
    
    public Materiel updateMateriel(Materiel materiel) {
    	return materielDAO.update(materiel);
}
    
}
