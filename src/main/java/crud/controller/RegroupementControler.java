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

import crud.model.Regroupm;
import crud.service.RegroupementService;
import io.swagger.annotations.Api;

@Path("/CreditHabitat/Regroupement")
@Api(value="/CreditHabitat/Regroupement")
public class RegroupementControler {

	
	@Inject
	private RegroupementService regroupmService;
	
	@GET
 	@Path("/{Regroupement}")
	public Regroupm findRegroupementByName (@PathParam(value="Regroupement") String r) {
 		return regroupmService.findRegroupm(r);
	}

	@GET
 	public List<Regroupm> findAllRegroupm () {
 		return regroupmService.findAllRegroupm();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	public void createMateriel (Regroupm regroupm) {
		System.out.println("create ");
		regroupmService.createRegroupm(regroupm);
	}
	
	@DELETE
 	@Path("/{Regroupm}")
 	public void deleteRegroupm (@PathParam(value="Regroupm") String regroupm) {
		System.out.println("delete m ");
		regroupmService.deleteRegroupm(regroupm);
	}
	
	@PUT
 	public Regroupm UpdateMateriel (Regroupm regroupm) {
		System.out.println("update ");
		return regroupmService.updateRegroupm(regroupm);
	}
}


 