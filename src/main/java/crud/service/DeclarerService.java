package crud.service;

import java.util.List;

import crud.model.Declarer;


public interface DeclarerService {
		public Declarer find(Declarer declarer);
		public List<Declarer> findAll();
		public Declarer create(Declarer declarer);
		public void delete(Declarer declarer);
		public Declarer update(Declarer declarer);
		public void validSave(String soc, int an, int trim);

}
	
	
	
	
	

