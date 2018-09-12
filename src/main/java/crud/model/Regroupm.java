package crud.model;

public class Regroupm {

	private final String codeRegroupMat;
	private final String libelleRegroupMat;
	
	
	public Regroupm() {
		  this.codeRegroupMat = null;
		  this.libelleRegroupMat = null;
	  }
	
	
	public Regroupm(String codeRegroupMat) {
		  this.codeRegroupMat = codeRegroupMat;
		  this.libelleRegroupMat = null;
	  }
	
	
	public Regroupm(String codeRegroupMat, String libelleRegroupMat) {
 		this.codeRegroupMat = codeRegroupMat;
		this.libelleRegroupMat = libelleRegroupMat;
	}

	public String getCodeRegroupMat() {
		return codeRegroupMat;
	}

	public String getLibelleRegroupMat() {
		return libelleRegroupMat;
	}

	@Override
	public String toString() {
		return "Regroupm [codeRegroupMat=" + codeRegroupMat + ", libelleRegroupMat=" + libelleRegroupMat + "]";
	}
	
	
}
