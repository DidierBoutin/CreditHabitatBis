package crud.model;

public class Anomalie {

	private final String codeSoc;
	private final String codePTT;
	private final String codeMat;
	private final int numTrim;
	private final int anTrim;
	private final int nbDoss;
	private final int mtAno;
	private final String codeDev;


	public Anomalie() {
		this(null, null, null, 0, 0, 0, 0, null);
	}
	  
	public Anomalie(String codeSoc, String codePTT, String codeMat, int numTrim, int anTrim, String codeDev) {
		  this.codeSoc = codeSoc;
		  this.codePTT = codePTT;
		  this.codeMat  = codeMat;
		  this.numTrim = numTrim;
		  this.anTrim = anTrim;
		  this.nbDoss = 0;
		  this.mtAno = 0;
		  this.codeDev = codeDev;
	  }
	
	
	
	
	  public Anomalie(String codeSoc, String codePTT, String codeMat, int numTrim, int anTrim, int nbDoss, int mtAno, String codeDev) {
		  this.codeSoc = codeSoc;
		  this.codePTT = codePTT;
		  this.codeMat  = codeMat;
		  this.numTrim = numTrim;
		  this.anTrim = anTrim;
		  this.nbDoss = nbDoss;
		  this.mtAno = mtAno;
		  this.codeDev = codeDev;
 	  }




	public String getCodeSoc() {
		return codeSoc;
	}

	public String getCodePTT() {
		return codePTT;
	}

	public String getCodeMat() {
		return codeMat;
	}

	public int getNumTrim() {
		return numTrim;
	}

	public int getAnTrim() {
		return anTrim;
	}

	public int getNbDoss() {
		return nbDoss;
	}

	public int getMtAno() {
		return mtAno;
	}

	public String getCodeDev() {
		return codeDev;
	}




	@Override
	public String toString() {
		return "anomalie [codeSoc=" + codeSoc + ", codePTT=" + codePTT + ", codeMat=" + codeMat + ", numTrim=" + numTrim
				+ ", anTrim=" + anTrim + ", nbDoss=" + nbDoss + ", mtAno=" + mtAno + ", codeDev=" + codeDev + "]";
	}
	  
	
	
	
	
}
