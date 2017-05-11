package zombies.model.authentication;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import zombies.model.model.Player;

@Entity
@Table(name = "USER_CREDENTIAL")
public class UserCredential implements Serializable {

	private static final long serialVersionUID = 714843320082164397L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false)
	private Long id;

	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "USER_PASSWORD")
	private String password;
	
	@Column(name = "USER_EMAIL")
	private String email;
	
	@Column(name = "USER_ENABLED")
	private Boolean enabled;

	@Column(name = "USER_PASSWORD_CHANGED")
	private Date passwordChanged;

	@Column(name = "USER_LAST_LOGIN")
	private Date lastLogin;

	@OneToOne
	@JoinColumn(name = "PLAYER_ID", nullable = false)
	private Player player;


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Date getPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(Date passwordChanged) {
		this.passwordChanged = passwordChanged;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof UserCredential))
			return false;
		return ((UserCredential) obj).getId().equals(this.getId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}