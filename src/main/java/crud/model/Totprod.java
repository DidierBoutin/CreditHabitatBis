package crud.model;

public class Totprod {

	private final String codeSoc; 
	private final  int numTrimTrait ; 
	private final  int anTrimTait;  
	private final  int totProd;  
	private final  String codeDev ;
	
	public Totprod(String codeSoc, int numTrimTrait, int anTrimTait, int totProd, String codeDev) {
 		this.codeSoc = codeSoc;
		this.numTrimTrait = numTrimTrait;
		this.anTrimTait = anTrimTait;
		this.totProd = totProd;
		this.codeDev = codeDev;
	}

	public String getCodeSoc() {
		return codeSoc;
	}

	public int getNumTrimTrait() {
		return numTrimTrait;
	}

	public int getAnTrimTait() {
		return anTrimTait;
	}

	public int getTotProd() {
		return totProd;
	}

	public String getCodeDev() {
		return codeDev;
	}

	@Override
	public String toString() {
		return "Totprod [codeSoc=" + codeSoc + ", numTrimTrait=" + numTrimTrait + ", anTrimTait=" + anTrimTait
				+ ", totProd=" + totProd + ", codeDev=" + codeDev + "]";
	} 
	
	
	
	
}
