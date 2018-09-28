package crud.service;

import java.util.List;

import javax.inject.Inject;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRowTempo;
import crud.model.Declarer;
import crud.repository.DeclarerDAO;

public class DeclarerServiceImpl implements DeclarerService {
	@Inject
	DeclarerDAO declarerDAO;

	@Inject
	public DeclarerServiceImpl() {
		super();
	}

	public Declarer find(Declarer declarer ) {
		return declarerDAO.find(declarer);
	}

	public List<Declarer> findAll() {
		return declarerDAO.findAll();
	}

	public Declarer create(Declarer declarer) {
		return declarerDAO.create(declarer);
	}

	public void delete(Declarer declarer) {
		declarerDAO.delete(declarer);
	}

	public Declarer update(Declarer declarer) {
		return declarerDAO.update(declarer);
	}

	public void validSave(String soc, int an, int trim) {
		declarerDAO.validSave(soc, an, trim);
	}
	
	public List<AvaliderRowTempo> findValides(String soc, int an, int trim) {
		return declarerDAO.findValides(soc, an, trim);
	}
	
	public List<AvaliderBoxRow> findAvaliderBox() {
		return declarerDAO.findAvaliderBox();
	}
	
}
