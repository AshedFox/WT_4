package bsuir.wt.lab4.service.impl;

import bsuir.wt.lab4.service.UsersService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.validation.CredentialValidator;
import bsuir.wt.lab4.dao.DAOProvider;
import bsuir.wt.lab4.dao.DAOException;
import bsuir.wt.lab4.dao.UsersDAO;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.entity.User;

public class UsersServiceImpl implements UsersService {

	@Override
	public UserDTO authorize(String email, String password) throws ServiceException {

		if(!CredentialValidator.isCorrect(email, password)) {
			throw new ServiceException("incorrect email or password");
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UsersDAO userDAO = daoProvider.getUsersDAO();

		UserDTO userDTO;

		try {
			User user = userDAO.authorize(email, password);

			userDTO = new UserDTO(user.getId(), user.getEmail(), user.getRole());
		}catch (DAOException e){
			throw new ServiceException(e);
		}

		return userDTO;
	}


	@Override
	public void register(User user) throws ServiceException{

		if(!CredentialValidator.isCorrect(user.getEmail(), user.getPassword())) {
			throw new ServiceException("incorrect email or password");
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UsersDAO userDAO = daoProvider.getUsersDAO();

		try {
			userDAO.register(user);
		}catch (DAOException e){
			throw new ServiceException(e);
			
		}

	}
}


