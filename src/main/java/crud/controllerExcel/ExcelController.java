package crud.controllerExcel;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import crud.serviceExcel.ExcelService;
import io.swagger.annotations.Api;
 
@Path("/CreditHabitat/Excel")
@Api(value="/CreditHabitat/Excel")
public class ExcelController {

	
	@Inject
	private ExcelService exelService;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
 	public void writeFile () throws Exception {
		
 		exelService.writeFile();
	}
}
