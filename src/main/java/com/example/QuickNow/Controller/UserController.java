package com.example.QuickNow.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuickNow.Model.Order;
import com.example.QuickNow.Model.Product;
import com.example.QuickNow.Model.User;
import com.example.QuickNow.Service.OrderService;
import com.example.QuickNow.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/userReg")
	public ResponseEntity<?> resiter(@RequestBody User user)
	{
		return new ResponseEntity<User>(service.userResister(user),HttpStatus.OK);
	}
	
	@PostMapping("/userLogin")
	public ResponseEntity<?> login(@RequestBody User user)
	{
		User loginuser=service.loginuser(user);
		if(loginuser!=null) {
			return new ResponseEntity<User>(loginuser,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Invalid email or password",HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
	    User u = service.findUserById(id);
	    if (u != null) return new ResponseEntity<>(u, HttpStatus.OK);
	    else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateProfile/{id}")
	public  ResponseEntity<?> updateProfile(@PathVariable int id,@RequestBody User updata){
		
		User exits=service.UpdateProfile(id,updata);
		if(exits!=null)
		{
			return new ResponseEntity<>(exits,HttpStatus.OK);
		}
			return new ResponseEntity<>(exits,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/showuser")
	public ResponseEntity< List<User>> getAllUser()
	{
		return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id)
	{
		boolean del=service.deleteUser(id);
		
		if(del)
		{
			return new ResponseEntity<>(del,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(del,HttpStatus.NOT_FOUND);
		}
	}
}
