package crud.model;

//import javax.persistence.Column;

public class Departement {
	
//	@Column(name = "NAME")
 	private final String codePTT;
	private final String nameDepartement;
	  
	public Departement(String codePTT,  String nameDepartement) {
	    this.codePTT = codePTT;
	    this.nameDepartement = nameDepartement;
	}
	  
	public String getCodePTT() {
	    return this.codePTT;
	}
  
	public String getNameDepartement() {
	    return nameDepartement;
	}

	@Override
	public String toString() {
		return "departement [codePTT=" + codePTT + ", nameDepartement=" + nameDepartement + "]";
	}
}