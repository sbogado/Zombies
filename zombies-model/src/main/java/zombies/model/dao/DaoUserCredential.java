package zombies.model.dao;

import zombies.model.authentication.UserCredential;

public interface DaoUserCredential extends DaoGeneric<UserCredential> {

	public UserCredential findByName(String name) throws Exception;

}
