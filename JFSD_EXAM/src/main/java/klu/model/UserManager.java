package klu.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import klu.repository.UserRepository;

@Service
public class UserManager {
@Autowired
UserRepository UR;
public String saveUser(User U) {
	try {
		
         if (UR.validateUsername(U.username)>0) 
             throw new Exception( "Username already exists. Please choose a different one.");
         
		UR.save(U);
		return " New User has been added";
		
	} catch (Exception e) {
		return e.getMessage();
		
	}
}
public String loginUser(String uname,String pwd) {
	try {
		if(UR.valdateCredentials(uname, pwd)==0) {
			throw new Exception("401") ;
		}
		return "200";
	} catch (Exception e) {
		return e.getMessage();
		// TODO: handle exception
	}
}
// Inner classes for request and response can be moved to separate files if preferred
public static class LoginRequest {
    private String username;
    private String password;
    private String captcha;

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCaptcha() { return captcha; }
    public void setCaptcha(String captcha) { this.captcha = captcha; }
}
public static class ResponseMessage {
    private int status;
    private String message;

    // Getters and Setters
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
}
