package zombies.web.game;

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
import zombies.model.service.PlayerService;
import zombies.web.AbstractController;
import zombies.web.auth.AuthenticationCtrl;
import zombies.web.persistence.MissionObserver;

@ManagedBean
@SessionScoped
public class GameStarterCtrl extends AbstractController {

	@ManagedProperty(value = "#{playerServiceImpl}")
	private PlayerService playerService;

	@ManagedProperty(value = "#{authenticationCtrl}")
	private AuthenticationCtrl authenticationCtrl;
	
	private Mission mission;

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
		addGameObservers(game);
		game.setPlayer(getAuthenticationCtrl().getUser().getPlayer());
		
		SonidoAparte sonido = new SonidoAparte();
		new DesktopGameLauncherNoVisible(sonido).launch();

		Zombies.soundPlayer = sonido.getSoundPlayer();

		DesktopGameLauncher launcher = new DesktopGameLauncher(game);
		game.setWindow(launcher);
		launcher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		launcher.launch();
	}

	private void addGameObservers(Zombies game) {
		MissionObserver missionObserver = new MissionObserver(getMission());
		game.setMission(missionObserver);
		game.getMonsterObservers().add(missionObserver);
		game.getTimeObservers().add(missionObserver);
	}

	/***********************************
	 * GETTERS AND SETTERS
	 *******************/

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
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

}
