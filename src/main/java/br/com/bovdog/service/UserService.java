package br.com.bovdog.service;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.bovdog.bean.User;
import br.com.bovdog.dao.UserDAO;

@Path("/UserService")

public class UserService {
	
	@GET
	@Path("/users")
	@Produces("applicaion/json")
	public List<User> getAllUsers(){
		UserDAO dao = new UserDAO();
		return dao.getAllUsers();
	}
	
	@POST
	@Path("/users/byusername")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public User getUserByUserName(@FormParam(value = "insertedUserName") String insertedUserName){
		UserDAO dao = new UserDAO();
		return dao.getUserByUserName(insertedUserName);
	}
	
	@POST
	@Path("/users/create")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public User createUser(@FormParam(value = "idUser")int idUser,
																@FormParam(value = "userFullName")String userFullName,
																@FormParam(value = "userName")String userName,
																@FormParam(value = "userPassword")String userPassword){

		User user = new User();
		user.setIdUser(idUser);
		user.setUserFullName(userFullName);
		user.setUserName(userName);
		user.setUserPassword(userPassword);	
		
		UserDAO dao = new UserDAO();
		dao.createUser(user);
		return user;
	}
}