package crud.service;

import java.util.List;

import javax.inject.Inject;
 
import crud.model.Anomalie;
import crud.repository.AnomalieDAO;


public class AnomalieServiceImpl implements AnomalieService{

	@Inject
	AnomalieDAO anomalieDAO;
	
	@Inject
	public AnomalieServiceImpl() {
		super();
	}
	
	public Anomalie findAnomalie(Anomalie anomalie ) {
    	return anomalieDAO.find(anomalie);
    }
    	
    public List<Anomalie> findAllAnomalie() {
        return anomalieDAO.findAll();
    }
	
    public Anomalie createAnomalie(Anomalie anomalie) {
    	return anomalieDAO.create(anomalie);
    }
    	
    public void deleteAnomalie(Anomalie anomalie) {
        anomalieDAO.delete(anomalie);
    }
    
    public Anomalie updateAnomalie(Anomalie anomalie) {
    	return anomalieDAO.update(anomalie);
}
    
}
