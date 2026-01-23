package com.example.QuickNow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.QuickNow.Model.User;
import com.example.QuickNow.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userrepo;
	
	public User userResister(User user)
	{
		return userrepo.save(user); 
	}
	public User loginuser(User user) {
		
	User u=userrepo.findByEmail(user.getEmail());
		if(u!=null && user.getPassword().equals(u.getPassword()))
		{
			return u;
		}
		return null;
	}
	
	public User findUserById(int id) {
	    return userrepo.findById(id).orElse(null);
	}
	
	
	public User UpdateProfile(int id, User updata) {
		
		User exits=userrepo.findById(id).orElse(null);
		
		if(exits==null)
		{
			return null;
		}
		
		exits.setName(updata.getName());
		exits.setMobile(updata.getMobile());
		exits.setAddress(updata.getAddress());
		
		return userrepo.save(exits);
	}
	
	public List<User> getAllUser(){
		return userrepo.findAll();
	}
	
	public boolean deleteUser(int id) {
		
		if(userrepo.existsById(id)) {
			userrepo.deleteById(id);
			return true;
		}
		return false;
	}
	

}
