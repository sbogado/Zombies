package zombies.web.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import zombies.model.authentication.UserCredential;
import zombies.model.model.Mission;
import zombies.model.model.Player;
import zombies.model.model.PlayerMission;
import zombies.model.model.PlayerMissionId;
import zombies.model.service.MissionService;
import zombies.model.service.PlayerLevelService;
import zombies.model.service.PlayerService;
import zombies.model.service.UserCredentialService;
import zombies.web.AbstractController;

@ManagedBean
@SessionScoped
public class RegistrationCtrl extends AbstractController {

	@ManagedProperty(value = "#{userCredentialServiceImpl}")
	private UserCredentialService userCredentialService;
	
	@ManagedProperty(value = "#{playerLevelServiceImpl}")
	private PlayerLevelService playerLevelService;
	
	@ManagedProperty(value = "#{playerServiceImpl}")
	private PlayerService playerService;
	
	@ManagedProperty(value = "#{missionServiceImpl}")
	private MissionService missionService;

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
			getUser().setPlayer(getPlayerService().update(getUser().getPlayer()));
			setUser(getUserCredentialService().update(getUser()));
			initializePlayerMissions(getUser().getPlayer());
			getUser().setPlayer(getPlayerService().update(getUser().getPlayer()));
			setPlayer(getUser().getPlayer());
		} catch (Exception e1) {
			logger.log(Level.SEVERE,"Could not persist player",e1);
			addErrorMessage("Ocurrio un error, no se pudo registrar el usuario", "Ocurrio un error, no se pudo registrar el usuario");
			FacesContext.getCurrentInstance().validationFailed();
		}
	}

	private Player initializePLayer() {
		Player player = new Player();
		player.setName(getUsername());
		player.setAim(Player.INITIAL_AIM);
		player.setScene(Player.INITIAL_SCENE);
		player.setExperience(Player.INITIAL_EXPERIENCE);
		player.setHitRecovery(Player.INITIAL_HIT_RECOVERY);
		player.setTotalLife(Player.INITIAL_TOTAL_LIFE);
		player.setMoney(Player.INITIAL_MONEY);
		player.setMovement(Player.INITIAL_MOVEMENT);
		player.setSpeedRecharge(Player.INITIAL_SPEED_RECHARGE);
		player.setSkillPoints(Player.INITIAL_SKILL_POINTS);
		
		
		initializePlayerLevel(player);
		
		return player;
	}

	private void initializePlayerMissions(Player player) {
		player.setMissions(new ArrayList<PlayerMission>());
		PlayerMission actualPlayerMission;
		List<Mission> missions;
		try {
			missions = getMissionService().findMissionsToShow();
			for(Mission mission : missions){
				actualPlayerMission = new PlayerMission();
				actualPlayerMission.setMission(mission);
				actualPlayerMission.setIsAcomplished(false);
				actualPlayerMission.setId(new PlayerMissionId(player.getId(),mission.getId()));
				player.getMissions().add(actualPlayerMission);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Could not initialize player missions",e);
		}

	}

	private void initializePlayerLevel(Player player) {
		try {
			player.setPlayerLevel(getPlayerLevelService().findByNumber(Player.INITIAL_PLAYER_LEVEL));
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Could not retrieve first Level",e);
		}
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

	public MissionService getMissionService() {
		return missionService;
	}

	public void setMissionService(MissionService missionService) {
		this.missionService = missionService;
	}
	
	public UserCredentialService getUserCredentialService() {
		return userCredentialService;
	}

	public void setUserCredentialService(UserCredentialService userCredentialService) {
		this.userCredentialService = userCredentialService;
	}
	
	public PlayerLevelService getPlayerLevelService() {
		return playerLevelService;
	}

	public void setPlayerLevelService(PlayerLevelService playerLevelService) {
		this.playerLevelService = playerLevelService;
	}
	
	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
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
