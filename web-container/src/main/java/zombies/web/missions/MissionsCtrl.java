package zombies.web.missions;

import java.util.List;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import zombies.model.model.Player;
import zombies.model.model.PlayerMission;
import zombies.model.service.PlayerService;
import zombies.web.AbstractController;

@ManagedBean
@SessionScoped
public class MissionsCtrl extends AbstractController {

	@ManagedProperty(value = "#{playerServiceImpl}")
	private PlayerService playerService;
	
	private Player player; 

	List<PlayerMission> missions;
	
	public void initialize(Player player){
		setPlayer(player);
		findPlayerMissions();
	}

	private void findPlayerMissions() {
		try {
			setMissions(getPlayerService().findMissions(getPlayer().getId()));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not retrieve Missions",e);
		}
	}
	
	public List<PlayerMission> getMissions(){
		return missions;
	}

	public void setMissions(List<PlayerMission> missions) {
		this.missions = missions;
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
	
}
