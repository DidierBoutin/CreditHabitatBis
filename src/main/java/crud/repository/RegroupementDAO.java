package crud.repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 import crud.model.Regroupm;
public class RegroupementDAO extends DAO<Regroupm> {
	private final static String 
					SQLFIND = "SELECT * "
							+ "FROM REGROUPM "
							+ "WHERE CODE_REGROUP_MAT = ? ";
 
	
	private final static String 
					SQLFINDALL = "SELECT * "
							+ "FROM REGROUPM "
							+ "ORDER BY  CODE_REGROUP_MAT";
	
	private final static String 
					SQLCREATE = "INSERT INTO REGROUPM "
							+ "(CODE_REGROUP_MAT, "
							+ "LIBELLE_REGROUP_MAT ) "
							+ "VALUES(?,?)";
	
	private final static String 
					SQLDELETE = "DELETE FROM REGROUPM "
							+ "WHERE CODE_REGROUP_MAT = ? ";
	
	private final static String 
					SQLUPDATE = "UPDATE REGROUPM "
							+ "SET LIBELLE_REGROUP_MAT = ? " 
							+ "WHERE CODE_REGROUP_MAT = ? ";
 
	 
	//====== FIND : get a Regroupm =================
	public Regroupm find(String r) {
 
		Regroupm regroupm = new Regroupm();

		
		try {
 
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLFIND);
			prepare.setString(1, r);

 
			ResultSet result = prepare.executeQuery();
	
			if (result.first()) {
				System.out.println("result first");
				regroupm = new Regroupm(
						result.getNString("CODE_REGROUP_MAT"),
						result.getNString("LIBELLE_REGROUP_MAT")
						);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select Regroupm KO : " + e);
		}
		System.out.println("return Regroupm : " + regroupm.toString());
		return regroupm;
	}
	//====== CREATE : Create a Regroupm =================
	public Regroupm create(Regroupm regroupm) { 
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLCREATE);
			prepare.setString(1, regroupm.getCodeRegroupMat());
			prepare.setString(2, regroupm.getLibelleRegroupMat());
			 
			prepare.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Insert Regroupm KO : " + e);
		}
		return regroupm;
	}

	//====== DELETE delete a Regroupm =================
	public  void delete(String r) { 
		try {
			System.out.println("Connect");
			PreparedStatement prepare = this.connect
			.prepareStatement(SQLDELETE);
			prepare.setString(1, r);
			 	
			
			prepare.executeUpdate();
 		}
		catch (SQLException e) { 
			System.out.println("Delete Regroupm KO : " + e);
		}
		System.out.println("Regroupm delete ");
	}
	//=======DELETEALL : To delete all Regroupms
	public  void deleteAll(Regroupm regroupm) { };

	//====== FINDALL : get all  Regroupms =================
	public List<Regroupm> findAll() { 
		List<Regroupm> listRegroupm = new ArrayList<Regroupm>();
		Regroupm regroupm;
		
		try {
			System.out.println("Connect");
			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
					.executeQuery(SQLFINDALL);

			while (result.next()) {
				regroupm = new Regroupm(
						result.getNString("CODE_REGROUP_MAT"),
						result.getNString("LIBELLE_REGROUP_MAT")
						);
				listRegroupm.add(regroupm);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select ListRegroupm KO : " + e);
		}
		return listRegroupm;
	};


	//====== UPDATE : update a  Regroupm =================

	public Regroupm update(Regroupm regroupm) { 
		try {
			System.out.println("Connect");
			PreparedStatement prepare =this.connect
			.prepareStatement(SQLUPDATE);
			prepare.setString(2, regroupm.getCodeRegroupMat());
			prepare.setString(1, regroupm.getLibelleRegroupMat());
			 	
			prepare.executeUpdate();
			
			regroupm = this.find(regroupm.getCodeRegroupMat()); 
 
		}
		catch (SQLException e) { 
			System.out.println("Update Regroupm KO : " + e);
		}
		System.out.println("Regroupm update "  );
		
		return regroupm;
	};
	
	
	
}

