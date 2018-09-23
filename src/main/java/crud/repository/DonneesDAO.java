package crud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
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
					SQLDELAVAL = "DELETE FROM DONNEES "
			+ "WHERE   CODE_SOC = ? "
			+ "AND NUM_TRIM_TRAIT = ? "
			+ "AND AN_TRIM_TRAIT = ? ";
			 
	
	private final static String 
					SQLUPDATE = "UPDATE DONNEES "
							+ "SET NB_DOSS	= ? , "
							+ "MONTANT = ? "
							+ "WHERE CODE_SOC = ?  AND CODE_MAT = ? AND CODE_PTT = ? AND  NUM_TRIM_TRAIT = ? "
							+ "AND  AN_TRIM_TRAIT = ? AND CODE_DEV = ? ";
	
	

	
	
	
	private final static String 
	SQLAVALIDER = 
			   "SELECT DEP.NOM_DEP, DEP.CODE_PTT, "    
			+ "SUM(DONN1.NB_DOSS), SUM(DONN1.MONTANT), " 
            + "SUM(DONN2.NB_DOSS), SUM(DONN2.MONTANT), " 
			+ "SUM(DONN1.MONTANT)  + SUM(DONN2.MONTANT) " 
			+  "FROM DONNEES DONN1, DONNEES DONN2, DEPARTEMENT DEP, " 
			+  "TOTPROD TOT, MATERIEL MAT1, MATERIEL MAT2 " 
			+  "WHERE DONN1.CODE_SOC = DONN2.CODE_SOC " 
			+  "AND DONN1.CODE_PTT = DONN2.CODE_PTT " 
			+  "AND DONN1.NUM_TRIM_TRAIT= DONN2.NUM_TRIM_TRAIT " 
			+  "AND DONN1.AN_TRIM_TRAIT= DONN2.AN_TRIM_TRAIT " 
			+  "AND DONN1.CODE_DEV = DONN2.CODE_DEV "
			+  "AND DONN1.CODE_SOC = TOT.CODE_SOC "
			+  "AND DONN1.NUM_TRIM_TRAIT= TOT.NUM_TRIM_TRAIT "  
			+  "AND DONN1.AN_TRIM_TRAIT= TOT.AN_TRIM_TRAIT "
			+  "AND DONN1.CODE_DEV = TOT.CODE_DEV "
			+  "AND DONN1.CODE_MAT = MAT1.CODE_MAT "
			+  "AND DONN2.CODE_MAT = MAT2.CODE_MAT "
			+  "AND DONN1.CODE_DEV = 'EUR' " 
			+  "AND DONN1.CODE_PTT = DEP.CODE_PTT " 
			+  "AND (MAT1.CODE_REGROUP_MAT = 'RM1' " 
			+  "AND MAT2.CODE_REGROUP_MAT = 'RM2') " 
			+  "AND DONN1.CODE_SOC = ? "
			+  "AND DONN1.AN_TRIM_TRAIT= ? "
			+  "AND DONN1.NUM_TRIM_TRAIT= ? "  
			+  "GROUP BY  (DEP.NOM_DEP,  DONN1.CODE_PTT) "
			
			+  "UNION "
			
			+  "SELECT DEP.NOM_DEP, DEP.CODE_PTT, "  
			+  " SUM(DONN1.NB_DOSS), SUM(DONN1.MONTANT), 0, 0, "
			+ " SUM(DONN1.MONTANT) "    
			+  "FROM DONNEES DONN1, DEPARTEMENT DEP, TOTPROD TOT, MATERIEL MAT1 "  
			+  "WHERE DONN1.CODE_PTT = DEP.CODE_PTT "  
			+  "AND DONN1.CODE_MAT = MAT1.CODE_MAT "  
			+  "AND MAT1.CODE_REGROUP_MAT = 'RM1' "  
			+  "AND DONN1.CODE_DEV = 'EUR' " 
			+  "AND DONN1.CODE_SOC = TOT.CODE_SOC " 
			+  "AND DONN1.NUM_TRIM_TRAIT= TOT.NUM_TRIM_TRAIT " 
			+  "AND DONN1.AN_TRIM_TRAIT= TOT.AN_TRIM_TRAIT " 
			+  "AND DONN1.CODE_DEV = TOT.CODE_DEV " 
			+  "AND DONN1.CODE_SOC = ? "
			+  "AND DONN1.AN_TRIM_TRAIT= ? " 
			+  "AND DONN1.NUM_TRIM_TRAIT= ? "  
			+  "AND NOT EXISTS "   
			+  "(SELECT * FROM DONNEES DONN2, MATERIEL MAT2 "  
			+  "WHERE DONN1.CODE_PTT = DONN2.CODE_PTT "  
			+  "AND DONN1.CODE_SOC = DONN2.CODE_SOC " 
			+  "AND DONN1.NUM_TRIM_TRAIT= DONN2.NUM_TRIM_TRAIT "
			+  "AND DONN1.AN_TRIM_TRAIT= DONN2.AN_TRIM_TRAIT " 
			+  "AND DONN1.CODE_DEV = DONN2.CODE_DEV " 
			+  "AND DONN2.CODE_MAT = MAT2.CODE_MAT " 
			+  "AND MAT2.CODE_REGROUP_MAT = 'RM2') "  
			+  "GROUP BY  (DEP.NOM_DEP,  DONN1.CODE_PTT) "


			+  "UNION "  
		
	 		+  "SELECT DEP.NOM_DEP, DEP.CODE_PTT, 0, 0, " 
			+  "SUM(DONN1.NB_DOSS), SUM(DONN1.MONTANT), SUM(DONN1.MONTANT) "
			+  "FROM DONNEES DONN1, DEPARTEMENT DEP, TOTPROD TOT, MATERIEL MAT1 " 
			+  "WHERE  DONN1.CODE_PTT = DEP.CODE_PTT " 
			+  "AND DONN1.CODE_MAT = MAT1.CODE_MAT "  
			+  "AND DONN1.CODE_SOC = TOT.CODE_SOC " 
			+  "AND DONN1.NUM_TRIM_TRAIT= TOT.NUM_TRIM_TRAIT "   
			+  "AND DONN1.AN_TRIM_TRAIT= TOT.AN_TRIM_TRAIT "   
		    +  "AND DONN1.CODE_DEV = TOT.CODE_DEV " 
			+  "AND MAT1.CODE_REGROUP_MAT = 'RM2' " 
			+  "AND DONN1.CODE_DEV = 'EUR' " 
			+  "AND DONN1.CODE_SOC = ? " 
			+  "AND DONN1.AN_TRIM_TRAIT= ? "
			+  "AND DONN1.NUM_TRIM_TRAIT= ? "
			+  "AND NOT EXISTS " 
			+  "(SELECT * FROM DONNEES DONN2, MATERIEL MAT2 " 
			+  "WHERE DONN1.CODE_PTT = DONN2.CODE_PTT " 
			+  "AND  DONN1.CODE_SOC = DONN2.CODE_SOC " 
			+  "AND DONN1.NUM_TRIM_TRAIT= DONN2.NUM_TRIM_TRAIT " 
			+   "AND DONN1.AN_TRIM_TRAIT= DONN2.AN_TRIM_TRAIT " 
			+  "AND DONN1.CODE_DEV = DONN2.CODE_DEV " 
			+  "AND DONN2.CODE_MAT = MAT2.CODE_MAT "  
			+  "AND MAT2.CODE_REGROUP_MAT = 'RM1') " 
			+  "GROUP BY (DEP.NOM_DEP,  DONN1.CODE_PTT) "

			+   "ORDER BY 2" ;

	
	//requete de selection "DTO" 
		// pour  la box de selection de  l'ecran A VALIDER
		private final static String 
		SQLPERIODEVAL = 
		  "SELECT DONN.CODE_SOC,  " 
			+ "DONN.AN_TRIM_TRAIT , DONN.NUM_TRIM_TRAIT, TOT.TOT_PROD " 
			+ "FROM DONNEES DONN, TOTPROD TOT " 
			+ "WHERE DONN.CODE_SOC = TOT.CODE_SOC " 
			+ "AND DONN.NUM_TRIM_TRAIT = TOT.NUM_TRIM_TRAIT " 
			+ "AND DONN.AN_TRIM_TRAIT = TOT.AN_TRIM_TRAIT " 
			+  "AND DONN.CODE_DEV = TOT.CODE_DEV "
			+  "AND DONN.CODE_DEV = 'EUR' "
			+ "GROUP BY (DONN.CODE_SOC, DONN.NUM_TRIM_TRAIT, " 
			+ "DONN.AN_TRIM_TRAIT,  TOT.TOT_PROD) " 
			+ "ORDER BY  DONN.CODE_SOC, DONN.AN_TRIM_TRAIT DESC, " 
			+ "DONN.NUM_TRIM_TRAIT";  


		

	
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

 
	
	//======= Pour supprimer les DONNEES suite à une validation
	public  void deleteAval(String soc, int an, int trim) { 
		
		try {
			 
			PreparedStatement prepare = this.connect
			.prepareStatement(SQLDELAVAL);
			prepare.setString(1, soc);
			prepare.setInt(2, trim);
 			prepare.setInt(3, an);
			 
			
			prepare.executeUpdate();
 		}
		catch (SQLException e) { 
			System.out.println("Delete Donnees A AVAL KO : " + e);
		}
		System.out.println("Donnees delete  AVAL");
	};



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
	
	
	//====== RECUP INFO POUR ECRAN "A VALIDER" =================
			public List<AvaliderRow> findAvalider(String soc, int an, int trim) { 

				
				
				List<AvaliderRow> listAvalider = new ArrayList<AvaliderRow>();
	 			AvaliderRow avaliderRow = new AvaliderRow();
				System.out.println("findAvalider");
				
				System.out.println("soc"); 			System.out.println(soc);
				System.out.println("an"); 			System.out.println(an);
				System.out.println("trim"); 			System.out.println(trim);


				try {
					System.out.println("SQLAVALIDER");	System.out.println(SQLAVALIDER);


					PreparedStatement prepare =this.connect.prepareStatement(SQLAVALIDER);
//					prepare.setString(1, soc);prepare.setString(4, soc);prepare.setString(7, soc);
//					prepare.setInt(2, an);prepare.setInt(5, an);prepare.setInt(8, an);
//					prepare.setInt(3, trim);prepare.setInt(6, an);prepare.setInt(9, an);
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


