package zombies.web.game;

import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JFrame;

import com.uqbar.vainilla.DesktopGameLauncher;

import zombie.DesktopGameLauncherNoVisible;
import zombie.SonidoAparte;
import zombie.Zombies;
import zombies.model.model.Mission;
import zombies.model.model.Player;
import zombies.model.model.PlayerMission;
import zombies.model.service.PlayerMissionService;
import zombies.model.service.PlayerService;
import zombies.web.AbstractController;
import zombies.web.auth.AuthenticationCtrl;
import zombies.web.persistence.GameStarter;
import zombies.web.persistence.PersistentPlayer;

@ManagedBean
@SessionScoped
public class GameStarterCtrl extends AbstractController implements GameStarter{

	@ManagedProperty(value = "#{playerServiceImpl}")
	private PlayerService playerService;
	
	@ManagedProperty(value = "#{playerMissionServiceImpl}")
	private PlayerMissionService playerMissionService;

	@ManagedProperty(value = "#{authenticationCtrl}")
	private AuthenticationCtrl authenticationCtrl;
	
	private PlayerMission playerMission;

	public void start() {

		if (getAuthenticationCtrl().getUser() != null) {
			startGame();
		}else{
			addWarningMessage("Es necesario estar logueado primero", "Es necesario estar logueado primero");
			FacesContext.getCurrentInstance().validationFailed();
		}
	}

	private void startGame() {
		Zombies game = new Zombies();
		game.setGameStarter(this);
		game.setPlayer(getAuthenticationCtrl().getUser().getPlayer());
		
		SonidoAparte sonido = new SonidoAparte();
		new DesktopGameLauncherNoVisible(sonido).launch();

		Zombies.soundPlayer = sonido.getSoundPlayer();

		DesktopGameLauncher launcher = new DesktopGameLauncher(game);
		game.setWindow(launcher);
		launcher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		launcher.launch();
	}

	public void update(PersistentPlayer player){
		try {
			getPlayerMission().setIsAcomplished(true);
			getPlayerMissionService().update(getPlayerMission());
			Player persistedPlayer = getPlayerService().update((Player) player);
			getAuthenticationCtrl().getUser().setPlayer(persistedPlayer);
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Could not update Player", e);
		}
	}
	
	/***********************************
	 * GETTERS AND SETTERS
	 *******************/

	public PlayerMissionService getPlayerMissionService() {
		return playerMissionService;
	}

	public void setPlayerMissionService(PlayerMissionService playerMissionService) {
		this.playerMissionService = playerMissionService;
	}
	
	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public AuthenticationCtrl getAuthenticationCtrl() {
		return authenticationCtrl;
	}

	public void setAuthenticationCtrl(AuthenticationCtrl authenticationCtrl) {
		this.authenticationCtrl = authenticationCtrl;
	}

	public Mission getMission() {
		return playerMission.getMission();
	}
	
	public PlayerMission getPlayerMission() {
		return playerMission;
	}

	public void setPlayerMission(PlayerMission playerMission) {
		this.playerMission = playerMission;
	}

}
