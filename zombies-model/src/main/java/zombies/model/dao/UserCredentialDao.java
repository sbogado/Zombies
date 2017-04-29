package zombies.model.dao;

import zombies.model.authentication.UserCredential;

public interface UserCredentialDao extends DaoGeneric<UserCredential> {

	public UserCredential findByName(String name) throws Exception;

}
