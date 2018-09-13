package crud.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
 import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import crud.dto.AvaliderBoxRow;
import crud.dto.AvaliderRow;
import crud.model.Declarer;
 import crud.service.DeclarerService;
import io.swagger.annotations.Api;

@Path("/CreditHabitat/Declarer")
@Api(value="/CreditHabitat/Declarer")
public class DeclarerControler {

	@Inject
	private DeclarerService declarerService;
	
	@PUT
	@Path("/FindByKey")
	@Consumes(MediaType.APPLICATION_JSON)
 	public Declarer findByKey (Declarer declarer) {
		System.out.println("declarer"); 
		System.out.println(declarer); 

		return declarerService.find(declarer);
	}

	@GET
 	public List<Declarer> findAll () {
 		return declarerService.findAll();
	}
	
	@GET
	@Path("/AvaliderBox")
 	public List<AvaliderBoxRow> findAvaliderBox () {
 		return declarerService.findAvaliderBox();
	}
	
	@GET
	@Path("/{soc}/{an}/{trim}")
 	public List<AvaliderRow> findAvaliderRow (	@PathParam(value = "soc") String soc, 
 												@PathParam(value = "an") int an, 
 												@PathParam(value = "trim") int trim ) {
 		System.out.println("soc"); 		System.out.println(soc);
 		System.out.println("an"); 		System.out.println(an);
 		System.out.println("trim"); 		System.out.println(trim);

		return declarerService.findAvalider(soc, an, trim);
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	public void CreateAnomalie (Declarer declarer) {
 		declarerService.create(declarer);
	}
	
	@PUT
	@Path("/DeleteByKey")
 	public void Delete (Declarer declarer) {
		System.out.println("delete e ");
		declarerService.delete(declarer); 
	}
	
	@PUT
 	@Path("/UpdateByKey")
	public Declarer UpdateAnomalie (Declarer declarer) {
		System.out.println("update ");
		return declarerService.update(declarer);
	}
	
	
	
	
}