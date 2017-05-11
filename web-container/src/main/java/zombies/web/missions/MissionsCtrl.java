package zombies.web.missions;

import java.util.List;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import zombies.model.model.PlayerMission;
import zombies.model.service.PlayerService;
import zombies.web.AbstractController;
import zombies.web.auth.AuthenticationCtrl;

@ManagedBean
@SessionScoped
public class MissionsCtrl extends AbstractController {

	@ManagedProperty(value = "#{playerServiceImpl}")
	private PlayerService playerService;
	
	@ManagedProperty(value = "#{authenticationCtrl}")
	private AuthenticationCtrl authenticationCtrl;

	List<PlayerMission> missions;
	
	
	
	public List<PlayerMission> getMissions(){
		if(missions == null){
			try {
				setMissions(getPlayerService().findMissions(getAuthenticationCtrl().getUser().getPlayer().getId()));
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Could not retrieve Missions",e);
			}
		}
		
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

	public AuthenticationCtrl getAuthenticationCtrl() {
		return authenticationCtrl;
	}

	public void setAuthenticationCtrl(AuthenticationCtrl authenticationCtrl) {
		this.authenticationCtrl = authenticationCtrl;
	}

}
