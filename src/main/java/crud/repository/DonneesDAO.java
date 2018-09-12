package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import crud.model.Donnees;


public class DonneesDAO extends DAO<Donnees> {

	private final static String 
					SQLFIND = "SELECT * "
							+ "FROM DONNEES "
							+ "WHERE CODE_SOC = ? AND CODE_MAT = ? AND CODE_PTT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? ";
 	
	private final static String 
					SQLFINDALL = "SELECT * "
							+ "FROM DONNEES "
							+ "ORDER BY  CODE_SOC, CODE_MAT, CODE_PTT, NUM_TRIM_TRAIT, AN_TRIM_TRAIT ";
	
	private final static String 
					SQLCREATE = "INSERT INTO DONNEES "
							+ "(CODE_SOC,  CODE_MAT, CODE_PTT, NUM_TRIM_TRAIT, AN_TRIM_TRAIT,  NB_DOSS, MONTANT, DATE_VALID, CODE_DEV) "
							+ "VALUES(?,?,?,?,?,?,?,?,?)";
	
	private final static String 
					SQLDELETE = "DELETE FROM DONNEES "
							+ "WHERE CODE_SOC = ? AND CODE_MAT = ? AND CODE_PTT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? ";
	
	private final static String 
					SQLUPDATE = "UPDATE DONNEES "
							+ "SET NB_DOSS	= ? , "
							+ "MONTANT = ? "
							+ "WHERE CODE_SOC = ?  AND CODE_MAT = ? AND CODE_PTT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? ";
	
	
	//====== FIND : get a Donnees =================
	public Donnees find(Donnees donnee) {

 
		try {
 
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLFIND);
			prepare.setString(1, donnee.getCodeSoc());
			prepare.setString(2, donnee.getCodeMat());
			prepare.setString(3, donnee.getCodePTT());
			prepare.setInt(4, donnee.getNumTrimTrait());
			prepare.setInt(5, donnee.getAnTrimTrait());
			prepare.setString(6, donnee.getCodeDev());



 
			ResultSet result = prepare.executeQuery();
	
			if (result.first()) {
				System.out.println("result first");
				donnee = new Donnees(
						result.getNString("CODE_SOC"),
						result.getNString("CODE_MAT"),
						result.getNString("CODE_PTT"),
						result.getInt("NUM_TRIM_TRAIT"),
						result.getInt("AN_TRIM_TRAIT"),
						result.getInt("NB_DOSS"),
						result.getInt("MONTANT"),
						result.getDate("DATE_VALID"),
						result.getString("CODE_DEV")
						);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select Donnees KO : " + e);

		}
		System.out.println("return Donnees : " + donnee.toString());

		return donnee;
	}

	//====== CREATE : Create a Donnees =================
	public Donnees create(Donnees donnee) { 
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLCREATE);
			prepare.setString(1, donnee.getCodeSoc());
			prepare.setString(2, donnee.getCodeMat());
			prepare.setString(3, donnee.getCodePTT());
 			prepare.setInt(4, donnee.getNumTrimTrait());
			prepare.setInt(5, donnee.getAnTrimTrait());
			prepare.setInt(6, donnee.getNbDoss());
			prepare.setInt(7, donnee.getMt());
			prepare.setDate(8, donnee.getDateValid());
			prepare.setString(9, donnee.getCodeDev());
			
			prepare.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Insert Donnees KO : " + e);
		}
		return donnee;
	}


	//====== DELETE delete a Donnees =================
	public  void delete(Donnees donnee) { 

		try {
			System.out.println("Connect");
			System.out.println(SQLDELETE);
			System.out.println("1, "  +  donnee.getCodeSoc());
			System.out.println("2, "  + donnee.getCodeMat());
			System.out.println("3, "  + donnee.getCodePTT());
			System.out.println("4, "  + donnee.getNumTrimTrait());
			System.out.println("5, "  + donnee.getAnTrimTrait());
			System.out.println("6, "  + donnee.getCodeDev());	

			PreparedStatement prepare = this.connect
			.prepareStatement(SQLDELETE);
			prepare.setString(1, donnee.getCodeSoc());
			prepare.setString(2, donnee.getCodeMat());
 			prepare.setString(3, donnee.getCodePTT());
 			prepare.setInt(4, donnee.getNumTrimTrait());
			prepare.setInt(5, donnee.getAnTrimTrait());
			prepare.setString(6, donnee.getCodeDev());	
			
			prepare.executeUpdate();
 		}
		catch (SQLException e) { 
			System.out.println("Delete Donnees KO : " + e);
		}
		System.out.println("Donnees delete ");
	}

	//=======DELETEALL : To delete all Donneess
	public  void deleteAll(Donnees Donnees) { };



	//====== FINDALL : get all  Donneess =================
	public List<Donnees> findAll() { 

		List<Donnees> listDonnees = new ArrayList<Donnees>();
		Donnees donnee;
		
		try {
			System.out.println("Connect");

			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
					.executeQuery(SQLFINDALL);


			while (result.next()) {
				donnee = new Donnees(
						result.getNString("CODE_SOC"),
						result.getNString("CODE_MAT"),
						result.getNString("CODE_PTT"),
						result.getInt("NUM_TRIM_TRAIT"),
						result.getInt("AN_TRIM_TRAIT"),
						result.getInt("NB_DOSS"),
						result.getInt("MONTANT"),
						result.getDate("DATE_VALID"),
						result.getString("CODE_DEV")
						);
				listDonnees.add(donnee);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select ListDonnees KO : " + e);
		}
		return listDonnees;
	};





	//====== UPDATE : update a  Donnees =================


	public Donnees update(Donnees donnee) { 

		try {
			System.out.println("Connect");

			PreparedStatement prepare =this.connect
			.prepareStatement(SQLUPDATE);
			prepare.setInt(1, donnee.getNbDoss());
			prepare.setInt(2, donnee.getMt());
			prepare.setString(3, donnee.getCodeSoc());
 			prepare.setString(4, donnee.getCodeMat());
			prepare.setString(5, donnee.getCodePTT());
			prepare.setInt(6, donnee.getNumTrimTrait());
			prepare.setInt(7, donnee.getAnTrimTrait());
			prepare.setString(8, donnee.getCodeDev());	
			 

			prepare.executeUpdate();

			
			donnee = this.find(donnee); 
 

		}
		catch (SQLException e) { 
			System.out.println("Update Donnees KO : " + e);

		}
		System.out.println("Donnees update "  );
		
		return donnee;

	};
	
	
	
}


