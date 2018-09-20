package crud.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
import crud.model.Donnees;
 import crud.service.DonneesService;
import io.swagger.annotations.Api;

@Path("/CreditHabitat/Donnees")
@Api(value="/CreditHabitat/Donnees")
public class DonneesControler {

	@Inject
	private DonneesService donneesService;
	
	@PUT
	@Path("/FindByKey")
	@Consumes(MediaType.APPLICATION_JSON)
 	public Donnees findByKey (Donnees donnee) {
		System.out.println("donnee"); 
		System.out.println(donnee); 

		return donneesService.find(donnee);
	}

	@GET
 	public List<Donnees> findAll () {
 		return donneesService.findAll();
	}
	
	@GET
	@Path("/AvaliderBox")
 	public List<AvaliderBoxRow> findAvaliderBox () {
 		return donneesService.findAvaliderBox();
	}
	
	@GET
	@Path("/{soc}/{an}/{trim}")
 	public List<AvaliderRow> findAvaliderRow (	@PathParam(value = "soc") String soc, 
 												@PathParam(value = "an") int an, 
 												@PathParam(value = "trim") int trim ) {
 		

		return donneesService.findAvalider(soc, an, trim);
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	public void CreateAnomalie (Donnees donnee) {
 		donneesService.create(donnee);
	}
	
	@PUT
	@Path("/DeleteByKey")
 	public void Delete (Donnees donnee) {
		System.out.println("delete e ");
		donneesService.delete(donnee); 
	}
	
	@PUT
 	@Path("/UpdateByKey")
	public Donnees UpdateAnomalie (Donnees donnee) {
		System.out.println("update ");
		return donneesService.update(donnee);
	}
	
	@DELETE
	@Path("/{soc}/{an}/{trim}/{regroup}")
	@Consumes(MediaType.APPLICATION_JSON)
 	public void deleteAval  (@PathParam(value="soc") String soc,
 							@PathParam(value="an") int an,
 							@PathParam(value="trim") int trim,
 							@PathParam(value="regroup") String regroup
 							) {
 		  donneesService.deleteAval(soc, an, trim, regroup);
	}
	
	
	
	
}
