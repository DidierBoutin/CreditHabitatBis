package crud.service;

import java.util.List;

import crud.model.Anomalie;

public interface AnomalieService {

public Anomalie findAnomalie(Anomalie anomalie);
	
	public List<Anomalie> findAllAnomalie();

	public Anomalie createAnomalie(Anomalie anomalie);

	public void deleteAnomalie(Anomalie anomalie);
	
	public Anomalie updateAnomalie(Anomalie anomalie);

 	
}
