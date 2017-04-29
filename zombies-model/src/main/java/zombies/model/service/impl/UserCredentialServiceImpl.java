package zombies.model.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zombies.model.authentication.UserCredential;
import zombies.model.dao.DaoGeneric;
import zombies.model.dao.UserCredentialDao;
import zombies.model.service.UserCredentialService;

@Service
public class UserCredentialServiceImpl extends GenericABMService<UserCredential> implements Serializable,UserCredentialService {

	
	private static final long serialVersionUID = -741243545533573744L;
	
	@Autowired
	UserCredentialDao dao;

	public UserCredential findByName(String playerName) throws Exception{
		return ((UserCredentialDao) getDao()).findByName(playerName);
	}

	@Override
	public DaoGeneric<UserCredential> getDao() {
		return dao;
	}

}
