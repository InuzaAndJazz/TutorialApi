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
	private static Map<String, String> codigosDeReinicio = new HashMap<>();

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

	@PostMapping("/user/requestResetPassword")
	@ResponseBody
	public String solicitudReinicioPassword(@RequestParam String email) {
		if (usuarios.containsKey(email)) {
			String codigoReinicio = Math.round(Math.random() * 9999) + "";
			codigosDeReinicio.put(email, codigoReinicio);
			return "OK: Codigo de reinicio de password enviado" + "(Simulacion codigo=" + codigoReinicio + ")";
		} else {
			return "ERROR: Direccion de correo no encontrada";
		}
	}

	@PatchMapping("/user/resetPassword")
	@ResponseBody
	public String Reinicio(@RequestParam String email, @RequestParam String newPassword, @RequestParam String codigo) {
		if (usuarios.containsKey(email)) {

			if (codigo.equals(codigosDeReinicio.get(email))) {
				usuarios.replace(email, newPassword);
				return "OK: Contraseña modificada";
			} else {
				return "ERROR: El codigo de reinicio no es correcto";
			}

		} else {
			return "ERROR: email no esta registrado";
		}

	}

}
