package zombies.model.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import zombies.web.persistence.PersistentPlayer;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable,PersistentPlayer {
	
	private static final long serialVersionUID = 1300776856528206676L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAYER_ID", nullable = false)
	private Long id;
	
	@Column(name = "PLAYER_NAME")
	private String name;
	
	@Column(name = "PLAYER_LEVEL")
	private Integer actualLevel;
	
	@Column(name = "PLAYER_EXPERIENCE")
	private Integer experience;
	
	@Column(name = "PLAYER_ESCENE")
	private Integer scene;
	
	@Column(name = "PLAYER_AIM")
	private Integer aim;
	
	@Column(name = "PLAYER_HIT_RECOVERY")
	private Double hitRecovery;
	
	@Column(name = "PLAYER_TOTAL_LIFE")
	private Integer totalLife;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getActualLevel() {
		return actualLevel;
	}

	public void setActualLevel(Integer actualLevel) {
		this.actualLevel = actualLevel;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer escene) {
		this.scene = escene;
	}

	public Integer getAim() {
		return aim;
	}

	public void setAim(Integer aim) {
		this.aim = aim;
	}

	public Double getHitRecovery() {
		return hitRecovery;
	}

	public void setHitRecovery(Double hitRecovery) {
		this.hitRecovery = hitRecovery;
	}

	public Integer getTotalLife() {
		return totalLife;
	}

	public void setTotalLife(Integer totalLife) {
		this.totalLife = totalLife;
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
		Player other = (Player) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
