package crud.model;

import java.sql.Date;

public class Declarer {

	private final String codeSoc;
	private final  String codePTT;  
	private final  String codeRegroupMat;
	private final  int numTrimDec ;
	private final  int anTrimDec  ;
	private final  int nbDossDec ; 
	private final  int mtDec  ;
	private final  String codeUtil;  
	private final  Date dateValid;
	private final  Date dateDec;
	
	private final  String codeDev;
	
	//*constructeur vide
	public Declarer() {
 		this.codeSoc = null;
		this.codePTT = null;
		this.codeRegroupMat = null;
		this.numTrimDec = 0;
		this.anTrimDec = 0;
		this.nbDossDec = 0;
		this.mtDec = 0;
		this.codeUtil = null;
		this.dateValid = null;
		this.dateDec = null;
		this.codeDev = null;
	}
	
	//*constructeur avec la clé de l'entité
	public Declarer(String codeSoc, String codePTT, String codeRegroupMat, int numTrimDec, int anTrimDec,  
			String codeDev) {
 		this.codeSoc = codeSoc;
		this.codePTT = codePTT;
		this.codeRegroupMat = codeRegroupMat;
		this.numTrimDec = numTrimDec;
		this.anTrimDec = anTrimDec;
		this.nbDossDec = 0;
		this.mtDec = 0;
		this.codeUtil = null;
		this.dateValid = null;
		this.dateDec = null;
		this.codeDev = codeDev;
	}
	
	
	
	public Declarer(String codeSoc, String codePTT, String codeRegroupMat, int numTrimDec, int anTrimDec, int nbDossDec,
			int mtDec, String codeUtil, Date dateValid, Date dateDec, String codeDev) {
 		this.codeSoc = codeSoc;
		this.codePTT = codePTT;
		this.codeRegroupMat = codeRegroupMat;
		this.numTrimDec = numTrimDec;
		this.anTrimDec = anTrimDec;
		this.nbDossDec = nbDossDec;
		this.mtDec = mtDec;
		this.codeUtil = codeUtil;
		this.dateValid = dateValid;
		this.dateDec = dateDec;
		this.codeDev = codeDev;
	}
	public String getCodeSoc() {
		return codeSoc;
	}
	public String getCodePTT() {
		return codePTT;
	}
	public String getCodeRegroupMat() {
		return codeRegroupMat;
	}
	public int getNumTrimDec() {
		return numTrimDec;
	}
	public int getAnTrimDec() {
		return anTrimDec;
	}
	public int getNbDossDec() {
		return nbDossDec;
	}
	public int getMtDec() {
		return mtDec;
	}
	public String getCodeUtil() {
		return codeUtil;
	}
	public Date getDateValid() {
		return dateValid;
	}
	public Date getDateDec() {
		return dateDec;
	}
	public String getCodeDev() {
		return codeDev;
	}
	@Override
	public String toString() {
		return "declarer [codeSoc=" + codeSoc + ", codePTT=" + codePTT + ", codeRegroupMat=" + codeRegroupMat
				+ ", numTrimDec=" + numTrimDec + ", anTrimDec=" + anTrimDec + ", nbDossDec=" + nbDossDec + ", mtDec="
				+ mtDec + ", codeUtil=" + codeUtil + ", dateValid=" + dateValid + ", dateDec=" + dateDec + ", codeDev="
				+ codeDev + "]";
	}
	
	
	
	
	 
	
	
	
	
}
