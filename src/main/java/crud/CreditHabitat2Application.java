package crud;

import org.glassfish.jersey.jackson1.Jackson1Feature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import crud.controller.AnomalieControler;
import crud.controller.CORSResponseFilter;
import crud.controller.DeclarerControler;
import crud.controller.DonneesControler;
import crud.controller.MaterielControler;
import crud.controller.RegroupementControler;

//@ApplicationPath("/rest")/*
public class CreditHabitat2Application extends ResourceConfig {
	public CreditHabitat2Application(){
 		    this.register(LoggingFeature.class); 
		    this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
		    this.register(MaterielControler.class);
		    this.register(AnomalieControler.class);
		    this.register(RegroupementControler.class);
		    this.register(DonneesControler.class);
		    this.register(DeclarerControler.class);


		    this.register(CORSResponseFilter.class);

		    this.register(new DependencyBinder());
		    this.register(Jackson1Feature.class);
		    
		  }
}
