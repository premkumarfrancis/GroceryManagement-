//package GroceryStore.project.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import GroceryStore.project.Model.UserImp;
//import GroceryStore.project.Model.Users;
//import GroceryStore.project.reposistory.UsersRepository;
//@Service
//public class UserDetailsServiceImp implements UserDetailsService {
//	@Autowired
//	UsersRepository usersRepository;
//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//		Users user=usersRepository.findByName(name);
//		if(user==null) {
//			throw new UsernameNotFoundException("invalid user");
//		}
//		return new UserImp(user);
//	}
//	
//	
//}
