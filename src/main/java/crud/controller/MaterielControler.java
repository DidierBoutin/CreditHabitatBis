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

 
import crud.model.Materiel;
import crud.service.MaterielService;
import io.swagger.annotations.Api;

@Path("/CreditHabitat/Materiel")
@Api(value="/CreditHabitat/Materiel")
public class MaterielControler {

	
	@Inject
	private MaterielService materielService;
	
	@GET
 	@Path("/{Materiel}")
	public Materiel findMaterielByName (@PathParam(value="Materiel") String materiel) {
 		return materielService.findMateriel(materiel);
	}

	@GET
 	public List<Materiel> findAllMateriel () {
 		return materielService.findAllMateriel();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	public void createMateriel (Materiel materiel) {
		System.out.println("create ");
		materielService.createMateriel(materiel);
	}
	
	@DELETE
 	@Path("/{Materiel}")
 	public void deleteMateriel (@PathParam(value="Materiel") String materiel) {
		System.out.println("delete e ");
		materielService.deleteMateriel(materiel);
	}
	
	@PUT
	public Materiel UpdateMateriel (Materiel materiel) {
		System.out.println("update ");
		return materielService.updateMateriel(materiel);
	}
}


 