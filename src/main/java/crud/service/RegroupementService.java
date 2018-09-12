package crud.service;

import java.util.List;

import crud.model.Regroupm;

public interface RegroupementService {

	public Regroupm findRegroupm(String regroupm);
	
	public List<Regroupm> findAllRegroupm();

	public Regroupm createRegroupm(Regroupm regroupm);

	public void deleteRegroupm(String s);
	
	public Regroupm updateRegroupm(Regroupm regroupm);


}
