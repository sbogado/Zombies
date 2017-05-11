package zombies.model.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlayerMissionId {

	@Column(name = "PLAYER_ID")
	private Long playerId;
	
	@Column(name = "MISSION_ID")
	private Long missionId;

	public PlayerMissionId(){
	}
	
	public PlayerMissionId(Long playerId, Long missionId){
		this.missionId = playerId;
		this.missionId = missionId;
	}
	
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getMissionId() {
		return missionId;
	}

	public void setMissionId(Long missionId) {
		this.missionId = missionId;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getMissionId() == null) ? 0 : getMissionId().hashCode());
		result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof PlayerMissionId))
			return false;
		return (((PlayerMissionId) obj).getMissionId()
				.equals(this.getMissionId())
				&& ((PlayerMissionId) obj).getPlayerId()
						.equals(this.getPlayerId()));
	}
}
