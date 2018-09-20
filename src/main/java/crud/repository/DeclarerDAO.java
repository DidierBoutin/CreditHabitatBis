
package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
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
	
	//resquete de selection "DTO" pour la liste d'affichage
	// de l'écran A AVALIDER ===> DONNESS
	private final static String 
					SQLVALIDES = 
							"SELECT DEP.NOM_DEP, DEP.CODE_PTT, " 
									+ "DECL1.NB_DOSS_DEC, DECL1.MONTANT_DEC, " 
									+ "DECL2.NB_DOSS_DEC, DECL2.MONTANT_DEC, "
									+ "DECL1.MONTANT_DEC + DECL2.MONTANT_DEC "
									+ "FROM DECLARER DECL1, DECLARER DECL2, DEPARTEMENT DEP, "
									+ "TOTPROD TOT "
									+ "WHERE DECL1.CODE_SOC = DECL2.CODE_SOC "
									+ "AND DECL1.CODE_PTT = DECL2.CODE_PTT "
									+ "AND DECL1.NUM_TRIM_DEC = DECL2.NUM_TRIM_DEC " 
									+ "AND DECL1.AN_TRIM_DEC = DECL2.AN_TRIM_DEC " 
		 							+ "AND DECL1.CODE_DEV = DECL2.CODE_DEV "
		 							+ "AND DECL1.CODE_SOC = TOT.CODE_SOC "
 									+ "AND DECL1.NUM_TRIM_DEC = TOT.NUM_TRIM_TRAIT " 
									+ "AND DECL1.AN_TRIM_DEC = TOT.AN_TRIM_TRAIT " 
		 							+ "AND DECL1.CODE_DEV = TOT.CODE_DEV "
									+ "AND DECL1.CODE_DEV = 'EUR' "
									+ "AND DECL1.CODE_PTT = DEP.CODE_PTT "
									+ "AND (DECL1.CODE_REGROUP_MAT = 'RM1' "
									+ "AND DECL2.CODE_REGROUP_MAT = 'RM2') "
									+ "AND DECL1.CODE_SOC = ? "
									+ "AND DECL1.AN_TRIM_DEC = ? "
									+ "AND DECL1.NUM_TRIM_DEC = ?  "
									
									+ " UNION "
									
									+ "SELECT DEP.NOM_DEP, DEP.CODE_PTT, " 
									+ "DECL1.NB_DOSS_DEC, DECL1.MONTANT_DEC, 0, 0, DECL1.MONTANT_DEC " 
									+ "FROM DECLARER DECL1, DEPARTEMENT DEP, TOTPROD TOT " 
									+ "WHERE DECL1.CODE_PTT = DEP.CODE_PTT " 
									+ "AND DECL1.CODE_REGROUP_MAT = 'RM1' " 
		 							+ "AND DECL1.CODE_DEV = 'EUR' "
		 							+ "AND DECL1.CODE_SOC = TOT.CODE_SOC "
		 							+ "AND DECL1.NUM_TRIM_DEC = TOT.NUM_TRIM_TRAIT " 
									+ "AND DECL1.AN_TRIM_DEC = TOT.AN_TRIM_TRAIT " 
		 							+ "AND DECL1.CODE_DEV = TOT.CODE_DEV "
									+ "AND DECL1.CODE_SOC = ? "
									+ "AND DECL1.AN_TRIM_DEC = ? " 
									+ "AND DECL1.NUM_TRIM_DEC = ? " 
									+ "AND NOT EXISTS "  
									+ "(SELECT * FROM DECLARER DECL2 " 
									+ "WHERE DECL1.CODE_PTT = DECL2.CODE_PTT " 
									+ "AND  DECL1.CODE_SOC = DECL2.CODE_SOC "
									+ "AND DECL1.NUM_TRIM_DEC = DECL2.NUM_TRIM_DEC "
									+ "AND DECL1.AN_TRIM_DEC = DECL2.AN_TRIM_DEC " 
									+ "AND DECL1.CODE_DEV = DECL2.CODE_DEV "
									+ "AND DECL2.CODE_REGROUP_MAT = 'RM2') " 

									+ "UNION " 
								
							 		+ "SELECT DEP.NOM_DEP, DEP.CODE_PTT, 0, 0, "
									+ "DECL1.NB_DOSS_DEC, DECL1.MONTANT_DEC, DECL1.MONTANT_DEC " 
									+ "FROM DECLARER DECL1, DEPARTEMENT DEP, TOTPROD TOT "
									+ "WHERE  DECL1.CODE_PTT = DEP.CODE_PTT "
									+ "AND DECL1.CODE_SOC = TOT.CODE_SOC "
									+ "AND DECL1.NUM_TRIM_DEC = TOT.NUM_TRIM_TRAIT " 
									+ "AND DECL1.AN_TRIM_DEC = TOT.AN_TRIM_TRAIT " 
		 							+ "AND DECL1.CODE_DEV = TOT.CODE_DEV "
									+ "AND DECL1.CODE_REGROUP_MAT = 'RM2' "
		 							+ "AND DECL1.CODE_DEV = 'EUR' "
									+ "AND DECL1.CODE_SOC = ? "
									+ "AND DECL1.AN_TRIM_DEC = ? "
									+ "AND DECL1.NUM_TRIM_DEC = ? "
									+ "AND NOT EXISTS "
									+ "(SELECT * FROM DECLARER DECL2 "
									+ "WHERE DECL1.CODE_PTT = DECL2.CODE_PTT "
									+ "AND  DECL1.CODE_SOC = DECL2.CODE_SOC "
									+ "AND DECL1.NUM_TRIM_DEC = DECL2.NUM_TRIM_DEC " 
									+ " AND DECL1.AN_TRIM_DEC = DECL2.AN_TRIM_DEC " 
									+ "AND DECL1.CODE_DEV = DECL2.CODE_DEV "
									+ "AND DECL2.CODE_REGROUP_MAT = 'RM1') "
									+ " ORDER BY 2";
	
	//requete de selection "DTO" 
	// pour  la box de selection de  l'ecran A VALIDER
	private final static String 
				SQLPERIODEVAL = 
						  "SELECT DECL.CODE_SOC,  " 
						+ "DECL.AN_TRIM_DEC , DECL.NUM_TRIM_DEC, TOT.TOT_PROD " 
						+ "FROM DECLARER DECL, TOTPROD TOT " 
						+ "WHERE DECL.CODE_SOC = TOT.CODE_SOC " 
						+ "AND DECL.NUM_TRIM_DEC = TOT.NUM_TRIM_TRAIT " 
						+ "AND DECL.AN_TRIM_DEC = TOT.AN_TRIM_TRAIT " 
						+  "AND DECL.CODE_DEV = TOT.CODE_DEV "
						+  "AND DECL.CODE_DEV = 'EUR' "
						+ "GROUP BY (DECL.CODE_SOC, DECL.NUM_TRIM_DEC, " 
						+ "DECL.AN_TRIM_DEC,  TOT.TOT_PROD) " 
						+ "ORDER BY  DECL.CODE_SOC, DECL.AN_TRIM_DEC DESC, " 
						+ "DECL.NUM_TRIM_DEC";  

	
	private final static String 
	SQLINSERTAVAL = "INSERT INTO DECLARER " + 
			"(CODE_SOC ,CODE_PTT ,CODE_REGROUP_MAT ,NUM_TRIM_DEC ,AN_TRIM_DEC ,"
			+ "NB_DOSS_DEC ,MONTANT_DEC ,CODE_UTIL ,DATE_VALID ,DATE_DEC ,CODE_DEV ) " 
			+ "SELECT  DONN.CODE_SOC, DONN.CODE_PTT,  MAT.CODE_REGROUP_MAT, DONN.NUM_TRIM_TRAIT, "
			+ "DONN.AN_TRIM_TRAIT, " 
			+ "SUM(DONN.NB_DOSS), SUM(DONN.MONTANT), ? , ? , NULL, 'EUR' "  
			+ "FROM DONNEES DONN, MATERIEL MAT " 
 			+ "WHERE DONN.CODE_SOC   = ? "  
			+ "AND DONN.AN_TRIM_TRAIT = ? "  
			+ "AND DONN.NUM_TRIM_TRAIT = ? " 
			+ "AND DONN.CODE_MAT  =  MAT.CODE_MAT "  
			+ "AND CODE_REGROUP_MAT = ? " 
			+ "GROUP BY ( DONN.CODE_SOC, DONN.CODE_PTT,  MAT.CODE_REGROUP_MAT,  "
			+ "DONN.NUM_TRIM_TRAIT, DONN.AN_TRIM_TRAIT) ";
		

	
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


	//====== SUITE A VALIDATION SUR ECRAN A VALIDER =================
		public  void validSave(String soc, int an, int trim, String regroup) { 
			
 			
			Date dateSQL = new  Date(Calendar.getInstance().getTime().getTime());
 
 			
			System.out.println("g");
			
 			System.out.println("datejour");  
			System.out.println(dateSQL);  
			System.out.println(dateSQL.toString());  

			
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement(SQLINSERTAVAL);
				prepare.setString(1, "UTIL");
				prepare.setDate(2, dateSQL);
				prepare.setString(3, soc);
	 			prepare.setInt(4, an);
				prepare.setInt(5, trim);
				prepare.setString(6, regroup);

				
				prepare.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println("validation KO : " + e);
			}
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
 
			PreparedStatement prepare =this.connect.prepareStatement(SQLUPDATE);
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
	
	//====== RECUP INFO POUR ECRAN "A VALIDER" =================
		public List<AvaliderRow> findAvalider(String soc, int an, int trim) { 

			List<AvaliderRow> listAvalider = new ArrayList<AvaliderRow>();
 			AvaliderRow avaliderRow = new AvaliderRow();
			System.out.println("findAvalider");
			
			System.out.println("soc"); 			System.out.println(soc);
			System.out.println("an"); 			System.out.println(an);
			System.out.println("trim"); 			System.out.println(trim);


			try {
				System.out.println("SQLVALIDES");	System.out.println(SQLVALIDES);


				PreparedStatement prepare =this.connect.prepareStatement(SQLVALIDES);
//				prepare.setString(1, soc);prepare.setString(4, soc);prepare.setString(7, soc);
//				prepare.setInt(2, an);prepare.setInt(5, an);prepare.setInt(8, an);
//				prepare.setInt(3, trim);prepare.setInt(6, an);prepare.setInt(9, an);
				prepare.setString(1, soc);
				prepare.setInt(2, an); 
				prepare.setInt(3, trim);
				prepare.setString(4, soc);
				prepare.setInt(5, an); 
				prepare.setInt(6, trim);
				prepare.setString(7, soc);
				prepare.setInt(8, an); 
				prepare.setInt(9, trim);
				ResultSet result = prepare.executeQuery(); 

				System.out.println("result");	System.out.println(result);

				while (result.next()) {
					System.out.println("next");
					avaliderRow = new AvaliderRow(
							result.getNString(1),
							result.getNString(2),
							result.getInt(3),
							result.getInt(4),
							result.getInt(5),
							result.getInt(6),
							result.getInt(7) 
							);
					listAvalider.add(avaliderRow);
				}
			}
			catch (SQLException e) { 
				System.out.println("Select ListDeclarer KO : " + e);
			}
			return listAvalider;
		};

		//====== RECUP DTO POUR BOX DE PAGE A VALIDER =================
		public List<AvaliderBoxRow> findAvaliderBox() { 

			List<AvaliderBoxRow> listAvaliderBoxRow= new ArrayList<AvaliderBoxRow>();
			AvaliderBoxRow avaliderBoxRow;
			
			try {
 
				ResultSet result = this.connect
						.createStatement(
								ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE)
						.executeQuery(SQLPERIODEVAL);

					
				while (result.next()) {
					avaliderBoxRow = new AvaliderBoxRow(
							result.getNString(1),
	 						result.getInt(2),
	 						result.getInt(3),
							result.getInt(4)
							 
							);
					listAvaliderBoxRow.add(avaliderBoxRow);
				}
			}
			catch (SQLException e) { 
				System.out.println("Select ListDeclarer KO : " + e);
			}
			return listAvaliderBoxRow;
		};

	
}


