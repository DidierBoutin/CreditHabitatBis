package crud.dto;

public class AvaliderBoxRow {


	private final String societe;
	private final int annee;
	private final int trimestre;
	private final int total;

	


	public AvaliderBoxRow() {
		this(null,  0, 0,0);
	}

	public AvaliderBoxRow(String societe, int annee, int trimestre, int total) {
				this.societe = societe;
				this.annee = annee;	
				this.trimestre = trimestre;
				this.total = total;
			
	}

	public String getSociete() {
		return societe;
	}

	public int getAnnee() {
		return annee;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public int getTotal() {
		return total;
	}

}
