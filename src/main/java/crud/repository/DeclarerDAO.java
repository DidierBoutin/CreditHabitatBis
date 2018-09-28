
package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRowTempo;
import crud.model.Declarer;


public class DeclarerDAO  extends DAO<Declarer> {
 	
	
	private final static String 
	SQLNBREG = "SELECT COUNT(*) "
			+ "FROM REGROUPM ";
 
	
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
	
	
	
	
	//requete de selection "DTO" 
	// pour  la box de selection de  l'ecran VALIDES
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
		public  void validSave(String soc, int an, int trim ) { 
			
 			
			Date dateJourSQL = new  Date(Calendar.getInstance().getTime().getTime());
  
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement(SQLINSERTAVAL);
				prepare.setString(1, "UTIL");
				prepare.setDate(2, dateJourSQL);
				prepare.setString(3, soc);
	 			prepare.setInt(4, an);
				prepare.setInt(5, trim);
 
				
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
		public List<AvaliderRowTempo> findValides(String soc, int an, int trim) { 

			int nbReg =  findNbReg();
			
			
			//resquete de selection "DTO" pour la liste d'affichage
			// de l'écran A AVALIDER ===> DONNESS
			
			
			 String sqlvalides1 = "SELECT NOM_DEP,  CODEPTT" ;
			 String sqlvalides2A  = "";
			 String sqlvalides2B  = ", SUM(MTRG1)";
			 
			 String sqlvalides3 = "FROM (SELECT   CODE_SOC, AN_TRIM_DEC,  NUM_TRIM_DEC , " + 
									"DEP.NOM_DEP, DEP.CODE_PTT AS CODEPTT" ;
			 String sqlvalides4  = "";
			 String sqlvalides5 = " FROM DECLARER DECL, DEPARTEMENT DEP " + 
						"WHERE DECL.CODE_PTT  = DEP.CODE_PTT ) " + 
						"WHERE AN_TRIM_DEC  = ? AND NUM_TRIM_DEC = ? AND CODE_SOC = ? " + 
						"GROUP BY NOM_DEP, CODEPTT " + 
						"ORDER BY 1, 2, 3, 5 ";
		 	int j = 0;
 			for(int i=1; i < nbReg+1; i++) {
 				j = i-1;
				 sqlvalides2A = sqlvalides2A + ", SUM(NBRG" + i + "), SUM(MTRG" + i + ")" ;

				sqlvalides4 = sqlvalides4 + 
					", (CASE WHEN CODE_REGROUP_MAT = (SELECT CODE_REGROUP_MAT FROM REGROUPM "
					+ "ORDER BY CODE_REGROUP_MAT LIMIT 1 OFFSET " + j
					+ ") THEN NB_DOSS_DEC ELSE 0 END) AS NBRG" + i
					+ ", (CASE WHEN CODE_REGROUP_MAT = (SELECT CODE_REGROUP_MAT FROM REGROUPM "
					+ "ORDER BY CODE_REGROUP_MAT LIMIT 1 OFFSET " + j
					+ ") THEN MONTANT_DEC ELSE 0 END) AS MTRG" + i ;
					
			}
 			
 			for(int i=2; i < nbReg+1; i++) {
				 sqlvalides2B = sqlvalides2B + 
							" + SUM(MTRG" + i + ")" ;
			}
			
 			
			String sqlvalides = sqlvalides1 + sqlvalides2A + sqlvalides2B  
					+ sqlvalides3 + sqlvalides4 + sqlvalides5;
					

			List<AvaliderRowTempo> listAvalider = new ArrayList<AvaliderRowTempo>();
 			AvaliderRowTempo avaliderRowTempo = new AvaliderRowTempo();
			 
			try {
				PreparedStatement prepare =this.connect.prepareStatement(sqlvalides);
				prepare.setInt(1, an);
				prepare.setInt(2, trim); 
				prepare.setString(3, soc);
				 
				ResultSet result = prepare.executeQuery(); 

				System.out.println("result");	System.out.println(result);
				 
				while (result.next()) {
					System.out.println("next");
					List<Integer> listNbDoss = new ArrayList<Integer>();
					List<Integer> listMtDoss = new ArrayList<Integer>();

		 			for(int i=1; i < (2*nbReg)+1; i= i + 2) {
		 				;
		 				listNbDoss.add(result.getInt(i+2));
		 				listMtDoss.add(result.getInt(i+3));
		 				;
		 			};

		 			System.out.println(listNbDoss.get(1));
		 			System.out.println(listMtDoss.get(1));

					avaliderRowTempo = new AvaliderRowTempo(
							result.getNString(1),
							result.getNString(2),
							listNbDoss,
							listMtDoss,
							result.getInt((2*nbReg)+3)
							);
 ;
					listAvalider.add(avaliderRowTempo);
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

		
		//====== FIND NB REGROUPEMENT ==================
		public  int findNbReg() {
	 
	 			int nbReg = 0;
			
	 			
	 			
 				try {
					System.out.println("Connect");
					ResultSet result = this.connect
							.createStatement(
									ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE)
							.executeQuery(SQLNBREG);

					while (result.next()) {
						nbReg =  result.getInt(1);
	 						
	 				}
				}
				catch (SQLException e) { 
					System.out.println("Select NnReg : " + e);
				}
				return nbReg;
			};
	
}


