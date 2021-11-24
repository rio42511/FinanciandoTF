package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entities.Users;

public interface IUserService {
	public Integer insert(Users user);

	List<Users> list();

}
