package zombies.web.auth;

import java.util.Date;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import zombies.model.authentication.UserCredential;
import zombies.model.model.Player;
import zombies.model.service.UserCredentialService;
import zombies.web.AbstractController;

@ManagedBean
@SessionScoped
public class RegistrationCtrl extends AbstractController {

	
	
	private static final int INITIAL_TOTAL_LIFE = 100;

	private static final double INITIAL_HIT_RECOVERY = 0.5;

	private static final int INITIAL_EXPERIENCE = 0;

	private static final int INITIAL_SCENE = 1;

	private static final int INITIAL_AIM = 1;

	private static final int INITIAL_LEVEL = 1;

	@ManagedProperty(value = "#{userCredentialServiceImpl}")
	private UserCredentialService userCredentialService;

	private UserCredential user;
	private Player player;
	private String username;
	private String password;
	private String repeatPassword;
	private String email;

	public void initialize(){
		setUsername("");
		setPassword("");
		setRepeatPassword("");
		setEmail("");
	}
	
	public void saveOperation() {
		if (!getPassword().equals(getRepeatPassword())) {
			addErrorMessage("Las contraseñas no coinciden", "Las contraseñas no coinciden");
			FacesContext.getCurrentInstance().validationFailed();
		} else {
			try {
				getUserCredentialService().findByName(getUsername());
				addErrorMessage("Nombre de usuario no disponible", "Nombre de usuario no disponible");
				FacesContext.getCurrentInstance().validationFailed();
			} catch (Exception e) {
				setUser(new UserCredential());
				getUser().setEnabled(true);
				getUser().setName(getUsername());
				getUser().setEmail(getEmail());
				getUser().setPassword(getPassword());
				getUser().setPasswordChanged(new Date());
				getUser().setPlayer(initializePLayer());
				persistUser();
				addInfoMessage("Su cuenta ha sido creada", "Su cuenta ha sido creada");
			}
		}
	}

	private void persistUser() {
		try {
			setUser(getUserCredentialService().update(getUser()));
		} catch (Exception e1) {
			logger.log(Level.SEVERE,"Could not persist",e1);
		}
	}

	private Player initializePLayer() {
		Player player = new Player();
		player.setName(getUsername());
		player.setActualLevel(INITIAL_LEVEL);
		player.setAim(INITIAL_AIM);
		player.setScene(INITIAL_SCENE);
		player.setExperience(INITIAL_EXPERIENCE);
		player.setHitRecovery(INITIAL_HIT_RECOVERY);
		player.setTotalLife(INITIAL_TOTAL_LIFE);
		
		return player;
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
