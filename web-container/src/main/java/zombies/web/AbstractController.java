package zombies.web;

import java.util.logging.Logger;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class AbstractController {

	public static final Logger logger = Logger.getLogger(AbstractController.class.getName());

	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		return (T) FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{" + beanName + "}", Object.class);
	}

	public void addErrorMessage(String title, String description) {
		addMessage(title,description,FacesMessage.SEVERITY_ERROR);
	}
	
	public void addWarningMessage(String title, String description) {
		addMessage(title,description,FacesMessage.SEVERITY_WARN);
	}

	public void addInfoMessage(String title, String description) {
		addMessage(title,description,FacesMessage.SEVERITY_INFO);
	}
	

	public void addFatalMessage(String title, String description) {
		addMessage(title,description,FacesMessage.SEVERITY_FATAL);
	}
	
	
	private void addMessage(String title, String description,Severity severity) {
		FacesMessage msg = createMessage(title, description);
		msg.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private FacesMessage createMessage(String title, String description) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary(title);
		msg.setDetail(description);
		return msg;
	}
	
	public void redirect(String uri) {
		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();
		nav.performNavigation(uri);
	}

}
