package crud.dto;

import java.util.List;

public class AvaliderRowTempo {

	private final String departement;
	private final String codePTT;
 	private final List<Integer> nbDossRg;
 	private final List<Integer> mtRg;

	private final int mtTot;


	public AvaliderRowTempo() {
		this(null, null, null, null, 0);
	}
	 
	  public AvaliderRowTempo(String departement, String codePTT, 
			  List<Integer> nbDossRg, List<Integer> mtRg,
			  int mtTot) {
		  this.departement = departement;
		  this.codePTT = codePTT;
		  this.nbDossRg  = nbDossRg;
		  this.mtRg = mtRg;
		  this.mtTot = mtTot;
		  
		  
 	  }





	public String getDepartement() {
		return departement;
	}
	public String getCodePTT() {
		return codePTT;
	}
	public List<Integer> getNbDossRg() {
		return nbDossRg;
	}

	public List<Integer> getMtRg() {
		return mtRg;
	}
	 
	
	public int getMtTot() {
		return mtTot;
	}

	@Override
	public String toString() {
		return "AvaliderRowTempo [departement=" + departement + ", codePTT=" + codePTT + ", nbDossRg=" + nbDossRg
				+ ", mtRg=" + mtRg + ", mtTot=" + mtTot + "]";
	}





	 


	
	
	
	
	
}
