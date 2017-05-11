package zombies.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import zombies.web.persistence.PersistentMission;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "MISSION")
public abstract class Mission implements PersistentMission{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MISSION_ID", nullable = false)
	private Long id;

	@Column(name = "MISSION_NAME")
	private String name;

	@Column(name = "MISSION_DESCRIPTION")
	private String description;

	@Column(name = "MISSION_IMAGE")
	private String image;
	
	@Column(name = "MISSION_ORDER")
	private Integer order;
	
	@Column(name = "MISSION_HAS_TO_SHOW")
	private Boolean hasToShow;

	/**********************************************************************************************
	 * GETTERS AND SETTERS
	 *********************************************************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
		Mission other = (Mission) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	
}
