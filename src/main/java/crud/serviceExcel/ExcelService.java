package crud.serviceExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Workbook;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

 
public class ExcelService {

	
	
	   public  void writeFile() throws Exception {
		   
//		   	Chargement du fichier "modèle"
		   	System.out.println("Exemple" + System.getProperty("user.dir"));
		   	
		      InputStream xlsRefStream = new FileInputStream(
		      		"C:\\Users\\Utilisateur.UTILISA-1R91N3M\\Documents\\Workspaces\\exemple.xls");
  		      Workbook refWorkbook = Workbook.getWorkbook(xlsRefStream);
		   
		   
		   
		    // Création du fichier de sortie
		      File outFile = new File("creditHabitat.xlsx");
		       WritableWorkbook outWorkbook =   Workbook.createWorkbook(outFile, refWorkbook);
		      
		      // Récupération de l'onglet courant (le premier onglet)
		      WritableSheet out = outWorkbook.getSheet(0);
		      

		      // insert "4" dans la cellule A1
		      Number number = new Number(0, 0, 4);
		      
		      // insert "6" dans la cellule B1
		      number = new Number(0, 1, 6);
		      out.addCell(number);

		       // etc ... boucle pour finir de remplir les cellules
		       for(int i = 2; i < 10; i++){
		          number = new Number(0, i, i*2);
		          out.addCell(number);
		        }

		       // Toutes les cellules sont remplies :
		       // Sauvegarde le fichier
		       outWorkbook.write();
		       outWorkbook.close();
				
	   }

	
}
