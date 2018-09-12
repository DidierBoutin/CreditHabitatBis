package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import crud.model.Anomalie;



public class AnomalieDAO extends DAO<Anomalie> {

	private final static String 
					SQLFIND = "SELECT * "
							+ "FROM ANOMALIE "
							+ "WHERE CODE_SOC = ? AND CODE_PTT = ? AND CODE_MAT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? "
							+ "ORDER BY CODE_SOC, CODE_PTT, CODE_MAT, NUM_TRIM_TRAIT, AN_TRIM_TRAIT" ;	
	
	private final static String 
					SQLFINDALL = "SELECT * "
							+ "FROM ANOMALIE "
							+ "ORDER BY  CODE_SOC, CODE_PTT, CODE_MAT	, NUM_TRIM_TRAIT, AN_TRIM_TRAIT ";
	
	private final static String 
					SQLCREATE = "INSERT INTO ANOMALIE "
							+ "(CODE_SOC, CODE_PTT, CODE_MAT, NUM_TRIM_TRAIT, AN_TRIM_TRAIT,  NB_DOSS_ANO, MONTANT_ANO, CODE_DEV) "
							+ "VALUES(?,?,?,?,?,?,?,?)";
	
	private final static String 
					SQLDELETE = "DELETE FROM ANOMALIE "
							+ "WHERE CODE_SOC = ? AND CODE_PTT = ? AND CODE_MAT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? ";
	
	private final static String 
					SQLUPDATE = "UPDATE ANOMALIE "
							+ "SET NB_DOSS_ANO	= ? , "
							+ "MONTANT_ANO = ? "
							+ "WHERE CODE_SOC = ? AND CODE_PTT = ? AND CODE_MAT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? ";
	
	
	//====== FIND : get a anomalie =================
	public Anomalie find(Anomalie anomalie) {

 
		try {
 
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLFIND);
			prepare.setString(1, anomalie.getCodeSoc());
			prepare.setString(2, anomalie.getCodePTT());
			prepare.setString(3, anomalie.getCodeMat());
			prepare.setInt(4, anomalie.getNumTrim());
			prepare.setInt(5, anomalie.getAnTrim());
			prepare.setString(6, anomalie.getCodeDev());



 
			ResultSet result = prepare.executeQuery();
	
			if (result.first()) {
				System.out.println("result first");
				anomalie = new Anomalie(
						result.getNString("CODE_SOC"),
						result.getNString("CODE_PTT"),
						result.getNString("CODE_MAT"),
						result.getInt("NUM_TRIM_TRAIT"),
						result.getInt("AN_TRIM_TRAIT"),
						result.getInt("NB_DOSS_ANO"),
						result.getInt("MONTANT_ANO"),
						result.getString("CODE_DEV")
						);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select Anomalie KO : " + e);

		}
		System.out.println("return anomalie : " + anomalie.toString());

		return anomalie;
	}

	//====== CREATE : Create a anomalie =================
	public Anomalie create(Anomalie anomalie) { 
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLCREATE);
			prepare.setString(1, anomalie.getCodeSoc());
			prepare.setString(2, anomalie.getCodePTT());
			prepare.setString(3, anomalie.getCodeMat());
			prepare.setInt(4, anomalie.getNumTrim());
			prepare.setInt(5, anomalie.getAnTrim());
			prepare.setInt(6, anomalie.getNbDoss());
			prepare.setInt(7, anomalie.getMtAno());
			prepare.setString(8, anomalie.getCodeDev());

			prepare.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Insert Anomalie KO : " + e);
		}
		return anomalie;
	}


	//====== DELETE delete a anomalie =================
	public  void delete(Anomalie anomalie) { 

		try {
			System.out.println("Connect");

			PreparedStatement prepare = this.connect
			.prepareStatement(SQLDELETE);
			prepare.setString(1, anomalie.getCodeSoc());
			prepare.setString(2, anomalie.getCodePTT());
			prepare.setString(3, anomalie.getCodeMat());
			prepare.setInt(4, anomalie.getNumTrim());
			prepare.setInt(5, anomalie.getAnTrim());
			prepare.setString(6, anomalie.getCodeDev());	
			
			prepare.executeUpdate();
 		}
		catch (SQLException e) { 
			System.out.println("Delete Anomalie KO : " + e);
		}
		System.out.println("anomalie delete ");
	}

	//=======DELETEALL : To delete all anomalies
	public  void deleteAll(Anomalie anomalie) { };



	//====== FINDALL : get all  anomalies =================
	public List<Anomalie> findAll() { 

		List<Anomalie> listAnomalie = new ArrayList<Anomalie>();
		Anomalie anomalie;
		
		try {
			System.out.println("Connect");

			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
					.executeQuery(SQLFINDALL);


			while (result.next()) {
				anomalie = new Anomalie(
						result.getNString("CODE_SOC"),
						result.getNString("CODE_PTT"),
						result.getNString("CODE_MAT"),
						result.getInt("NUM_TRIM_TRAIT"),
						result.getInt("AN_TRIM_TRAIT"),
						result.getInt("NB_DOSS_ANO"),
						result.getInt("MONTANT_ANO"),
						result.getString("CODE_DEV")
						);
				listAnomalie.add(anomalie);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select ListAnomalie KO : " + e);
		}
		return listAnomalie;
	};





	//====== UPDATE : update a  anomalie =================


	public Anomalie update(Anomalie anomalie) { 

		try {
			System.out.println("Connect");

			PreparedStatement prepare =this.connect
			.prepareStatement(SQLUPDATE);
			prepare.setInt(1, anomalie.getNbDoss());
			prepare.setInt(2, anomalie.getMtAno());
			prepare.setString(3, anomalie.getCodeSoc());
			prepare.setString(4, anomalie.getCodePTT());
			prepare.setString(5, anomalie.getCodeMat());
			prepare.setInt(6, anomalie.getNumTrim());
			prepare.setInt(7, anomalie.getAnTrim());
			prepare.setString(8, anomalie.getCodeDev());	

			prepare.executeUpdate();

			
			anomalie = this.find(anomalie); 
 

		}
		catch (SQLException e) { 
			System.out.println("Update Anomalie KO : " + e);

		}
		System.out.println("anomalie update "  );
		
		return anomalie;

	};
	
	
	
}
