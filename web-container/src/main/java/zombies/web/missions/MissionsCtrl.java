package zombies.web.missions;

import java.util.List;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import zombies.model.model.Mission;
import zombies.model.service.MissionService;
import zombies.web.AbstractController;

@ManagedBean
@SessionScoped
public class MissionsCtrl extends AbstractController {

	@ManagedProperty(value = "#{missionServiceImpl}")
	private MissionService missionService;

	List<Mission> missions;
	
	public MissionService getMissionService() {
		return missionService;
	}

	public void setMissionService(MissionService missionService) {
		this.missionService = missionService;
	}
	
	public List<Mission> getMissions(){
		if(missions == null){
			try {
				setMissions(getMissionService().genericSearchAll());
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Could not retrieve Missions",e);
			}
		}
		
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
}
