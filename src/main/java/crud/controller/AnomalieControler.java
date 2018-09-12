package crud.controller;
 

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
 import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
 import javax.ws.rs.core.MediaType;
 
 
import crud.model.Anomalie;
import crud.service.AnomalieService;
 import io.swagger.annotations.Api;

@Path("/CreditHabitat/Anomalie")
@Api(value="/CreditHabitat/Anomalie")
 
 
public class AnomalieControler {

	
	@Inject
	private AnomalieService anomalieService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	@Path("/AnomalieByKey")
	public Anomalie findAnomalieByKey (Anomalie anomalie) {
 		return anomalieService.findAnomalie(anomalie);
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public List<Anomalie> findAllAnomalie () {
 		return anomalieService.findAllAnomalie();
	}
	
//	@GET
// 	@Path("/Anomalie")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public ResponseBuilder findAllAnomalie () {
//
// 		return Response
// 				.status(200)
// 				.allow("Access-Control-Allow-Origin", "*")
// 				.entity(anomalieService.findAllAnomalie());
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	@Path("/CreateAno")
	public void CreateAnomalie (Anomalie anomalie) {
 		anomalieService.createAnomalie(anomalie);
	}
	
	@DELETE
 	@Path("/DeleteAno")
	public void DeleteAnomalie (Anomalie anomalie) {
		System.out.println("delete e ");
		anomalieService.deleteAnomalie(anomalie);
	}
	
	@PUT
 	@Path("/UpdateAno")
	public Anomalie UpdateAnomalie (Anomalie anomalie) {
		System.out.println("update ");
		return anomalieService.updateAnomalie(anomalie);
	}
}

