package zombies.model.service;

import zombies.model.authentication.UserCredential;

public interface UserCredentialService  extends GenericService<UserCredential>{

	public UserCredential findByName(String name) throws Exception;

}
