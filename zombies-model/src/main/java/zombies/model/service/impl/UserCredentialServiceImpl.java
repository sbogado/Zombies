package zombies.model.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.authentication.UserCredential;
import zombies.model.dao.DaoGeneric;
import zombies.model.dao.DaoUserCredential;
import zombies.model.service.UserCredentialService;

@Service
public class UserCredentialServiceImpl extends GenericABMService<UserCredential> implements Serializable,UserCredentialService {

	
	private static final long serialVersionUID = -741243545533573744L;
	
	@Autowired
	DaoUserCredential dao;

	public UserCredential findByName(String playerName) throws Exception{
		return ((DaoUserCredential) getDao()).findByName(playerName);
	}

	@Override
	public DaoGeneric<UserCredential> getDao() {
		return dao;
	}

}
