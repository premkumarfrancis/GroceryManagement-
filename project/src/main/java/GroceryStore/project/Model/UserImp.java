//package GroceryStore.project.Model;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class UserImp implements UserDetails{
//	 private Users user;
//	 
//	 
//	 
//	public UserImp(Users user) {
//		super();
//		this.user = user;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.user.getRole().toUpperCase()));
//		return grantedAuthorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return this.user.getName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//	public Users getUserDetails() {
//		return user;
//	}
//	
//	
//	
//}
