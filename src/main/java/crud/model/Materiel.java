package crud.model;

public class Materiel {

	
	private final String codeMat;
	private final String codeRegroupMat;
	private final String libelleMat;
	
	public Materiel() {
		this(null, null, null);
	}
	
	
	public Materiel(String codeMat, String codeRegroupMat, String libelleMat) {
 		this.codeMat = codeMat;
		this.codeRegroupMat = codeRegroupMat;
		this.libelleMat = libelleMat;
	}

	public String getCodeMat() {
		return codeMat;
	}

	public String getCodeRegroupMat() {
		return codeRegroupMat;
	}

	public String getLibelleMat() {
		return libelleMat;
	}

	@Override
	public String toString() {
		return "Materiel [codeMat=" + codeMat + ", codeRegroupMat=" + codeRegroupMat + ", libelleMat=" + libelleMat
				+ "]";
	}

	
	
	
	
}

