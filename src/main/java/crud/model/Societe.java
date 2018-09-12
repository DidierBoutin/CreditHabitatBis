package crud.model;

public class Societe {

	private final String codeSoc;
	private final String CodeEtabl;
	private final int Valider;
	
	public Societe(String codeSoc, String codeEtabl, int valider) {
 		this.codeSoc = codeSoc;
		CodeEtabl = codeEtabl;
		Valider = valider;
	}

	public String getCodeSoc() {
		return codeSoc;
	}

	public String getCodeEtabl() {
		return CodeEtabl;
	}

	public int getValider() {
		return Valider;
	}

	@Override
	public String toString() {
		return "Societe [codeSoc=" + codeSoc + ", CodeEtabl=" + CodeEtabl + ", Valider=" + Valider + "]";
	}

	
	
	
}
