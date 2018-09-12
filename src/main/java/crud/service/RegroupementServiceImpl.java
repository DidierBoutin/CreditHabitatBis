package crud.service;

import java.util.List;

import javax.inject.Inject;
 
import crud.model.Regroupm;
import crud.repository.RegroupementDAO;


public class RegroupementServiceImpl implements RegroupementService{

	@Inject
	RegroupementDAO regroupmDAO;
	
	@Inject
	public RegroupementServiceImpl() {
		super();
	}
	
	public Regroupm findRegroupm(String r ) {
    	return regroupmDAO.find(r);
    }
    	
    public List<Regroupm> findAllRegroupm() {
        return regroupmDAO.findAll();
    }
	
    public Regroupm createRegroupm(Regroupm regroupm) {
    	return regroupmDAO.create(regroupm);
    }
    	
    public void deleteRegroupm(String regroupm) {
    	regroupmDAO.delete(regroupm);
    }
    
    public Regroupm updateRegroupm(Regroupm regroupm) {
    	return regroupmDAO.update(regroupm);
}
    
}
