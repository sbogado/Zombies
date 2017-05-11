package zombies.model.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER_LEVEL")
public class PlayerLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAYER_LEVEL_ID", nullable = false)
	private Long id;
	
	@Column(name = "PLAYER_LEVEL_NUMBER")
	private Integer number;
	
	@Column(name = "PLAYER_LEVEL_EXPERIENCE_TO_NEXT_LEVEL")
	private Integer experienceToNextLevel;
	
	@Column(name = "PLAYER_LEVEL_SKILL_POINTS")
	private Integer skillPoints;
	
	@OneToOne(cascade = { CascadeType.MERGE})
	@JoinColumn(name = "PLAYER_LEVEL_NEXT_LEVEL_ID")
	private PlayerLevel nextLevel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getExperienceToNextLevel() {
		return experienceToNextLevel;
	}

	public void setExperienceToNextLevel(Integer experienceToNextLevel) {
		this.experienceToNextLevel = experienceToNextLevel;
	}

	public Integer getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(Integer skillPoints) {
		this.skillPoints = skillPoints;
	}
	
	public PlayerLevel getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(PlayerLevel nextLevel) {
		this.nextLevel = nextLevel;
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
		PlayerLevel other = (PlayerLevel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
