
package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import crud.model.Declarer;


public class DeclarerDAO extends DAO<Declarer> {

	private final static String 
					SQLFIND = "SELECT * "
							+ "FROM DECLARER "
							+ "WHERE CODE_SOC = ? AND CODE_PTT = ? "
							+ "AND CODE_REGROUP_MAT = ?  AND  NUM_TRIM_DEC= ? "
							+ "AND  AN_TRIM_DEC= ? AND CODE_DEV = ? ";
 	
	private final static String 
					SQLFINDALL = "SELECT * "
							+ "FROM DECLARER "
							+ "ORDER BY  CODE_SOC,  CODE_PTT, CODE_REGROUP_MAT, NUM_TRIM_DEC, "
							+ "AN_TRIM_DEC";
	
	private final static String 
					SQLCREATE = "INSERT INTO DECLARER "
							+ "(CODE_SOC,  CODE_PTT, CODE_REGROUP_MAT, NUM_TRIM_DEC, "
							+ "AN_TRIM_DEC,  NB_DOSS_DEC, MONTANT_DEC, CODE_UTIL, "
							+ "DATE_VALID, DATE_DEC, CODE_DEV) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	private final static String 
					SQLDELETE = "DELETE FROM DECLARER "
							+ "WHERE CODE_SOC = ? AND CODE_PTT = ? "
							+ "AND CODE_REGROUP_MAT = ?  AND  NUM_TRIM_DEC= ? "
							+ "AND  AN_TRIM_DEC= ? AND CODE_DEV = ? ";
	
	private final static String 
					SQLUPDATE = "UPDATE DECLARER "
							+ "SET NB_DOSS_DEC	= ? , "
							+ "MONTANT_DEC = ? , "
							+ "CODE_UTIL = ?, "
							+ "DATE_VALID = ? ,"
							+ "DATE_DEC = ? "
							+ "WHERE CODE_SOC = ? AND CODE_PTT = ? "
							+ "AND CODE_REGROUP_MAT = ?  AND  NUM_TRIM_DEC= ? "
							+ "AND  AN_TRIM_DEC= ? AND CODE_DEV = ? ";
	
	
	//====== FIND : get a Declarer =================
	public Declarer find(Declarer declarer) {

 
		try {
 
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLFIND);
			prepare.setString(1, declarer.getCodeSoc());
			prepare.setString(2, declarer.getCodePTT());
			prepare.setString(3, declarer.getCodeRegroupMat());
			prepare.setInt(4, declarer.getNumTrimDec());
			prepare.setInt(5, declarer.getAnTrimDec());
			prepare.setString(6, declarer.getCodeDev());



 
			ResultSet result = prepare.executeQuery();
	
			if (result.first()) {
				System.out.println("result first");
				declarer = new Declarer(
						result.getNString("CODE_SOC"),
 						result.getNString("CODE_PTT"),
 						result.getNString("CODE_REGROUP_MAT"),
						result.getInt("NUM_TRIM_DEC"),
						result.getInt("AN_TRIM_DEC"),
						result.getInt("NB_DOSS_DEC"),
						result.getInt("MONTANT_DEC"),
						result.getString("CODE_UTIL"),
						result.getDate("DATE_VALID"),
						result.getDate("DATE_DEC"),
						result.getString("CODE_DEV")
						);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select Declarer KO : " + e);

		}
		System.out.println("return Declarer : " + declarer.toString());

		return declarer;
	}

	//====== CREATE : Create a Declarer =================
	public Declarer create(Declarer declarer) { 
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement(SQLCREATE);
			prepare.setString(1, declarer.getCodeSoc());
			prepare.setString(2, declarer.getCodePTT());
			prepare.setString(3, declarer.getCodeRegroupMat());
 			prepare.setInt(4, declarer.getNumTrimDec());
			prepare.setInt(5, declarer.getAnTrimDec());
			prepare.setInt(6, declarer.getNbDossDec());
			prepare.setInt(7, declarer.getMtDec());
			prepare.setString(8, declarer.getCodeUtil());
			prepare.setDate(9, declarer.getDateValid());
			prepare.setDate(10, declarer.getDateDec());
			prepare.setString(11, declarer.getCodeDev());
			
			prepare.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Insert Declarer KO : " + e);
		}
		return declarer;
	}


	//====== DELETE delete a Declarer =================
	public  void delete(Declarer declarer) { 

		try {
			 

			PreparedStatement prepare = this.connect
			.prepareStatement(SQLDELETE);
			prepare.setString(1, declarer.getCodeSoc());
			prepare.setString(2, declarer.getCodePTT());
			prepare.setString(3, declarer.getCodeRegroupMat());
			prepare.setInt(4, declarer.getNumTrimDec());
			prepare.setInt(5, declarer.getAnTrimDec());
			prepare.setString(6, declarer.getCodeDev());
			
			prepare.executeUpdate();
 		}
		catch (SQLException e) { 
			System.out.println("Delete Declarer KO : " + e);
		}
		System.out.println("Declarer delete ");
	}

	//=======DELETEALL : To delete all Declarers
	public  void deleteAll(Declarer Declarer) { };



	//====== FINDALL : get all  Declarers =================
	public List<Declarer> findAll() { 

		List<Declarer> listDeclarer = new ArrayList<Declarer>();
		Declarer declarer;
		
		try {
			System.out.println("Connect");

			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
					.executeQuery(SQLFINDALL);


			while (result.next()) {
				declarer = new Declarer(
						result.getNString("CODE_SOC"),
 						result.getNString("CODE_PTT"),
 						result.getNString("CODE_REGROUP_MAT"),
						result.getInt("NUM_TRIM_DEC"),
						result.getInt("AN_TRIM_DEC"),
						result.getInt("NB_DOSS_DEC"),
						result.getInt("MONTANT_DEC"),
						result.getString("CODE_UTIL"),
						result.getDate("DATE_VALID"),
						result.getDate("DATE_DEC"),
						result.getString("CODE_DEV")
						);
				listDeclarer.add(declarer);
			}
		}
		catch (SQLException e) { 
			System.out.println("Select ListDeclarer KO : " + e);
		}
		return listDeclarer;
	};





	//====== UPDATE : update a  Declarer =================


	public Declarer update(Declarer declarer) { 

		try {
			System.out.println("Connect");

			PreparedStatement prepare =this.connect
			.prepareStatement(SQLUPDATE);
			prepare.setInt(1, declarer.getNbDossDec());
			prepare.setInt(2, declarer.getMtDec());
			prepare.setString(3, declarer.getCodeUtil());
			prepare.setDate(4, declarer.getDateValid());
			prepare.setDate(5, declarer.getDateDec());
			prepare.setString(6, declarer.getCodeSoc());
			prepare.setString(7, declarer.getCodePTT());
			prepare.setString(8, declarer.getCodeRegroupMat());
			prepare.setInt(9, declarer.getNumTrimDec());
			prepare.setInt(10, declarer.getAnTrimDec());
			prepare.setString(11, declarer.getCodeDev());
			 
			prepare.executeUpdate();

			
			declarer = this.find(declarer); 
 

		}
		catch (SQLException e) { 
			System.out.println("Update Declarer KO : " + e);

		}
		System.out.println("Declarer update "  );
		
		return declarer;

	};
	
	
	
}


