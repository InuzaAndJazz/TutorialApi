package cl.developteam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	private static Map<String, String> usuarios = new HashMap<>();

	@PostMapping("/user")
	@ResponseBody
	public String crearUsuario(@RequestParam String email, @RequestParam String password) {
		if (usuarios.containsKey(email)) {
			return "ERROR: Usuario ya se encuentra registrado";
		} else {
			usuarios.put(email, password);
			return "OK: El usuario ha sido registrado exitosamente";
		}
	}

	@PostMapping("/user/login")
	@ResponseBody
	public String loginDeUsuario(@RequestParam String email, @RequestParam String password) {
		if (usuarios.containsKey(email)) {
			if (usuarios.get(email).equals(password)) {
				return "OK: Login exitoso";
			} else {
				return "ERROR: Password incorrecta";
			}
		} else {
			return "ERROR: El usuario no se encuentra registrado";
		}
	}
	
	@PatchMapping("/user/changePassword")
	@ResponseBody
	public String cambioPassword(@RequestParam String email, @RequestParam String password, @RequestParam String newPassword) {
		if (usuarios.containsKey(email)) {
			if (usuarios.get(email).equals(password)) {
				usuarios.replace(email, password, newPassword);
				return "OK: Contraseña cambiada con éxito.";
			} else {
				return "ERROR: Usuario o contraseña incorrectos.";
			}
		} else {
			return "ERROR: Usuario no existente.";
		}
	}

}
