package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import crud.model.Materiel;



public class MaterielDAO extends DAO<Materiel> {

	private final static String SQLFIND = "SELECT * FROM MATERIEL WHERE CODE_MAT = ?  ORDER BY CODE_MAT desc";
	private final static String SQLFINDALL = "SELECT * FROM MATERIEL ORDER BY CODE_MAT desc";
	private final static String SQLCREATE = "INSERT INTO MATERIEL (CODE_MAT, CODE_REGROUP_MAT,LIBELLE_MAT) VALUES(?,?,?)";
	private final static String SQLDELETE = "DELETE FROM MATERIEL WHERE CODE_MAT = ? ";
	private final static String SQLUPDATE = "UPDATE MATERIEL SET CODE_REGROUP_MAT = ? , LIBELLE_MAT = ? WHERE CODE_MAT = ?";

	
	
	//====== FIND : get a materiel =================
	public Materiel find(String codeMat) {

		Materiel materiel = new Materiel();

		try {
			System.out.println("Connect");

			
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLFIND);
			prepare.setString(1, codeMat);
 
			ResultSet result = prepare.executeQuery();
	
			if (result.first()) {
				System.out.println("result first");

				materiel = new Materiel(
						result.getNString("CODE_MAT"),
						result.getNString("CODE_REGROUP_MAT"),
						result.getNString("LIBELLE_MAT"));


			}

		}
		catch (SQLException e) { 
			System.out.println("Select Materiel KO : " + e);

		}
		System.out.println("return materiel : " + materiel.toString());

		return materiel;
	}

	//====== CREATE : Create a materiel =================
	public Materiel create(Materiel materiel) { 
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLCREATE);
			prepare.setString(1, materiel.getCodeMat());
			prepare.setString(2, materiel.getCodeRegroupMat());
			prepare.setString(3, materiel.getLibelleMat());

			prepare.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Insert Materiel KO : " + e);
		}
		return materiel;
	}


	//====== DELETE delete a materiel =================
	public  void delete(String materiel) { 

		try {
			System.out.println("Connect");

			PreparedStatement prepare = this.connect
			.prepareStatement(SQLDELETE);
			prepare.setString(1, materiel);
			prepare.executeUpdate();
 		}
		catch (SQLException e) { 
			System.out.println("Delete Materiel KO : " + e);
		}
		System.out.println("materiel delete ");
	}

	//=======DELETEALL : To delete all materiels
	public  void deleteAll(Materiel materiel) { };



	//====== FINDALL : get all  materiels =================
	public List<Materiel> findAll() { 

		List<Materiel> listMateriel = new ArrayList<Materiel>();
		Materiel materiel;
		
		try {
			System.out.println("Connect");

			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
					.executeQuery(SQLFINDALL);


			while (result.next()) {
				materiel = new Materiel(
						result.getNString("CODE_MAT"),
						result.getNString("CODE_REGROUP_MAT"),
						result.getNString("LIBELLE_MAT"));

				listMateriel.add(materiel);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select ListMateriel KO : " + e);
		}
		return listMateriel;
	};





	//====== UPDATE : update a  materiel =================


	public Materiel update(Materiel materiel) { 

		try {
			System.out.println("Connect");

			PreparedStatement prepare =this.connect
			.prepareStatement(SQLUPDATE);
			prepare.setString(1, materiel.getCodeRegroupMat());
			prepare.setString(2, materiel.getLibelleMat());
			prepare.setString(3, materiel.getCodeMat());

			prepare.executeUpdate();

			
			materiel = this.find(materiel.getCodeMat());
 

		}
		catch (SQLException e) { 
			System.out.println("Update Materiel KO : " + e);

		}
		System.out.println("materiel update "  );
		
		return materiel;

	};
	
	
	
}
