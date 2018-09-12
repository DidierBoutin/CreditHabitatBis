package crud.model;

import java.sql.Date;

public class Donnees {

	private final String codeSoc ; 
	private final String codeMat ; 
	private final String codePTT  ;
	private final int numTrimTrait ;
	private final int anTrimTrait ;
	private final int nbDoss  ;
	private final int mt  ;
	private final Date dateValid; 
	private final String codeDev;
	
	public Donnees() {
		this(null, null, null, 0, 0, 0, 0, null, null);
	}
	  
	public Donnees(String codeSoc,  String codeMat, String codePTT, int numTrimTrait, int anTrimTrait, String codeDev) {
		  this.codeSoc = codeSoc;
		  this.codePTT = codePTT;
		  this.codeMat  = codeMat;
		  this.numTrimTrait = numTrimTrait;
		  this.anTrimTrait = anTrimTrait;
		  this.nbDoss = 0;
		  this.mt = 0;
		  this.dateValid = null;
		  this.codeDev = codeDev;
	  }
	public Donnees(String codeSoc, String codeMat, String codePTT, int numTrimTrait, int anTrimTrait, int nbDoss, int mt,
			Date dateValid, String codeDev) {
 		this.codeSoc = codeSoc;
		this.codeMat = codeMat;
		this.codePTT = codePTT;
		this.numTrimTrait = numTrimTrait;
		this.anTrimTrait = anTrimTrait;
		this.nbDoss = nbDoss;
		this.mt = mt;
		this.dateValid = dateValid;
		this.codeDev = codeDev;
	}


	public String getCodeSoc() {
		return codeSoc;
	}


	public String getCodeMat() {
		return codeMat;
	}


	public String getCodePTT() {
		return codePTT;
	}


	public int getNumTrimTrait() {
		return numTrimTrait;
	}


	public int getAnTrimTrait() {
		return anTrimTrait;
	}


	public int getNbDoss() {
		return nbDoss;
	}


	public int getMt() {
		return mt;
	}


	public Date getDateValid() {
		return dateValid;
	}


	public String getCodeDev() {
		return codeDev;
	}


	@Override
	public String toString() {
		return "donnees [codeSoc=" + codeSoc + ", codeMat=" + codeMat + ", codePTT=" + codePTT + ", numTrimTrait="
				+ numTrimTrait + ", anTrimTrait=" + anTrimTrait + ", nbDoss=" + nbDoss + ", mt=" + mt + ", dateValid="
				+ dateValid + ", codeDev=" + codeDev + "]";
	}  
	
	
	
	
	
}
