package cl.developteam.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.developteam.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	@ResponseBody
	public String crearUsuario(@RequestParam String email, @RequestParam String password) throws SQLException {
		return userService.createUser(email, password);
	}

	@PostMapping("/user/login")
	@ResponseBody
	public String loginDeUsuario(@RequestParam String email, @RequestParam String password) throws SQLException {
		return userService.userLogin(email, password);
	}

	@PostMapping("/user/requestResetPassword")
	@ResponseBody
	public String solicitudReinicioPassword(@RequestParam String email) throws SQLException {
		return userService.requestResetPassword(email);
	}

	@PatchMapping("/user/resetPassword")
	@ResponseBody
	public String reinicio(@RequestParam String email, @RequestParam String newPassword, @RequestParam String codigo) throws SQLException {
		return userService.requestResetPassword(email, newPassword, codigo);
	}

	@PatchMapping("/user/changePassword")
	@ResponseBody
	public String cambioPassword(@RequestParam String email, @RequestParam String password, @RequestParam String newPassword) throws SQLException {
		return userService.changePassword(email, password, newPassword);
	}

}
