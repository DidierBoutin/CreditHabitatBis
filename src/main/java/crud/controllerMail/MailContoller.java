package crud.controllerMail;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import crud.serviceMail.EmailService;
import io.swagger.annotations.Api;


@Path("/CreditHabitat/Mail")
@Api(value="/CreditHabitat/Mail")
public class MailContoller {

	@Inject
	private EmailService emailService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
 	public void SendMail (String email) {
 		emailService.envoyer(email);
	}
}