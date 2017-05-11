package zombies.web.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailvalidator")
public class EmailValidator implements Validator, Serializable {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 468173952807139163L;

	private static final String EMAIL_REGEX =
			"^([0-9a-zA-Z]([-.a-zA-Z_0-9])*[0-9a-zA-Z._-])+@([0-9a-zA-Z][-a-zA-Z_0-9]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9}$";

	public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
		String email = (String) obj;
		if (email != null) {
			if (!email.matches(EMAIL_REGEX)) {
				FacesMessage message = new FacesMessage("El formato del email es incorrecto",
						"El formato del email es incorrecto");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
}