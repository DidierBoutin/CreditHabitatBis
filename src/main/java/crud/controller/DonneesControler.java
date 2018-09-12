package crud.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
 import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
	
	
	
	
}
