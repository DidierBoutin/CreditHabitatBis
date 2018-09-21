package crud;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import crud.repository.AnomalieDAO;
import crud.repository.DeclarerDAO;
import crud.repository.DonneesDAO;
import crud.repository.MaterielDAO;
import crud.repository.RegroupementDAO;
import crud.service.AnomalieService;
import crud.service.AnomalieServiceImpl;
import crud.service.DeclarerService;
import crud.service.DeclarerServiceImpl;
import crud.service.DonneesService;
import crud.service.DonneesServiceImpl;
import crud.service.MaterielService;
import crud.service.MaterielServiceImpl;
import crud.service.RegroupementService;
import crud.service.RegroupementServiceImpl;
import crud.serviceExcel.ExcelService;
import crud.serviceMail.EmailService;

public class DependencyBinder extends AbstractBinder {
	 @Override
	    protected void configure() {
	       bind(MaterielServiceImpl.class).to(MaterielService.class);
	       bind(AnomalieServiceImpl.class).to(AnomalieService.class);
	       bind(RegroupementServiceImpl.class).to(RegroupementService.class);
	       bind(DonneesServiceImpl.class).to(DonneesService.class);
	       bind(DeclarerServiceImpl.class).to(DeclarerService.class);
	       bind(EmailService.class).to(EmailService.class);
	       bind(ExcelService.class).to(ExcelService.class);


	       bind(MaterielDAO.class).to(MaterielDAO.class);
	       bind(AnomalieDAO.class).to(AnomalieDAO.class);
	       bind(RegroupementDAO.class).to(RegroupementDAO.class);
	       bind(DonneesDAO.class).to(DonneesDAO.class);
	       bind(DeclarerDAO.class).to(DeclarerDAO.class);


	    }
}
