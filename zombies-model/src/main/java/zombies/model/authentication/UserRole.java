package zombies.model.authentication;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {


	private static final long serialVersionUID = 2539041754899558288L;

	@Id
	@Column(name = "ROLE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ROLE_DESC")
	private String description;
	
	@Column(name = "ROLE_HOME_URL")
	private String homeURL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getHomeURL() {
		return homeURL;
	}

	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof UserRole))
			return false;
		return ((UserRole) obj).getId().equals(this.getId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}