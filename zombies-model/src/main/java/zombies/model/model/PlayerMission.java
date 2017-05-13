package zombies.model.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER_MISSION")
public class PlayerMission implements Serializable{

	private static final long serialVersionUID = 8981053859860129503L;

	@EmbeddedId
	private PlayerMissionId id;
	
	@Column(name = "IS_ACOMPLISHED")
	private Boolean isAcomplished;
	
	@ManyToOne
	@JoinColumn(name="MISSION_ID", insertable=false, updatable=false)
	private Mission mission;
	
	
	public PlayerMissionId getId() {
		return id;
	}

	public void setId(PlayerMissionId id) {
		this.id = id;
	}

	public Boolean getIsAcomplished() {
		return isAcomplished;
	}

	public void setIsAcomplished(Boolean isAcomplished) {
		this.isAcomplished = isAcomplished;
	}
	
	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerMission other = (PlayerMission) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
