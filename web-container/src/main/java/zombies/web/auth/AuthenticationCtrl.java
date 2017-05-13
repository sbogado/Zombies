package zombies.web.auth;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import zombies.model.authentication.UserCredential;
import zombies.model.service.UserCredentialService;
import zombies.web.AbstractController;
import zombies.web.missions.MissionsCtrl;

@ManagedBean
@SessionScoped
public class AuthenticationCtrl extends AbstractController {

	@ManagedProperty(value = "#{userCredentialServiceImpl}")
	private UserCredentialService userCredentialService;
	
	@ManagedProperty(value = "#{missionsCtrl}")
	private MissionsCtrl missionsCtrl;

	private UserCredential user;
	private String username;
	private String password;

	public void saveOperation() {
		try {
			setUser(getUserCredentialService().findByName(getUsername()));
			if (!getUser().getPassword().equals(getPassword())) {
				addErrorMessage("Password Incorrecto", "Password Incorrecto");
				FacesContext.getCurrentInstance().validationFailed();
			}else{
				addInfoMessage("Bienvenido "+getUser().getName(), "Bienvenido "+getUser().getName());
				initializeMissionCtrl();
			}
		} catch (Exception e) {
			addErrorMessage("El usuario no existe", "El usuario no existe");
			FacesContext.getCurrentInstance().validationFailed();
		}
	}

	private void initializeMissionCtrl() {
		getMissionsCtrl().initialize(getUser().getPlayer());;
	}

	public void logout() {
		setUser(null);
	}

	public void initialize() {
		setPassword("");
		setUsername("");
	}

	public UserCredential getUser() {
		return user;
	}

	public void setUser(UserCredential user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserCredentialService getUserCredentialService() {
		return userCredentialService;
	}

	public void setUserCredentialService(UserCredentialService userCredentialService) {
		this.userCredentialService = userCredentialService;
	}

	public MissionsCtrl getMissionsCtrl() {
		return missionsCtrl;
	}

	public void setMissionsCtrl(MissionsCtrl missionsCtrl) {
		this.missionsCtrl = missionsCtrl;
	}

}
