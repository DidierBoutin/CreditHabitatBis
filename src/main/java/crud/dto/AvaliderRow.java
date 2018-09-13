package crud.dto;

public class AvaliderRow {

	private final String departement;
	private final String codePTT;
 	private final int nbDossRg1;
	private final int mtRg1;
	private final int nbDossRg2;
	private final int mtRg2;
	private final int mtTot;


	public AvaliderRow() {
		this(null, null, 0, 0, 0, 0, 0);
	}
	   
	
	
	
	
	  public AvaliderRow(String departement, String codePTT, 
			  int nbDossRg1, int mtRg1,int nbDossRg2, int mtRg2, int mtTot) {
		  this.departement = departement;
		  this.codePTT = codePTT;
		  this.nbDossRg1  = nbDossRg1;
		  this.mtRg1 = mtRg1;
		  this.nbDossRg2 = mtRg2;
		  this.mtRg2 = mtRg2;
		  this.mtTot = mtTot;
 	  }





	public String getDepartement() {
		return departement;
	}





	public String getCodePTT() {
		return codePTT;
	}





	public int getNbDossRg1() {
		return nbDossRg1;
	}





	public int getMtRg1() {
		return mtRg1;
	}





	public int getNbDossRg2() {
		return nbDossRg2;
	}





	public int getMtRg2() {
		return mtRg2;
	}





	public int getMtTot() {
		return mtTot;
	}





	@Override
	public String toString() {
		return "AvaliderRow [departement=" + departement + ", codePTT=" + codePTT + ", nbDossRg1=" + nbDossRg1
				+ ", mtRg1=" + mtRg1 + ", nbDossRg2=" + nbDossRg2 + ", mtRg2=" + mtRg2 + ", mtTot=" + mtTot + "]";
	}


	
	
	
	
	
}
