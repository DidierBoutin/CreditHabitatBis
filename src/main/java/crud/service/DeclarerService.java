package crud.service;

import java.util.List;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
import crud.model.Declarer;


public interface DeclarerService {
		public Declarer find(Declarer declarer);
		public List<Declarer> findAll();
		public Declarer create(Declarer declarer);
		public void delete(Declarer declarer);
		public Declarer update(Declarer declarer);
		public List<AvaliderRow> findAvalider(String soc, int an, int trim); 
		public List<AvaliderBoxRow> findAvaliderBox(); 
}
	
	
	
	
	

