package cl.developteam.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.developteam.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static Map<String, String> codigosDeReinicio = new HashMap<>();

	public String createUser(String email, String password) throws SQLException {
		String existingPassword = userRepository.getUserPasswordByEmail(email);
		if (existingPassword != null) {
			return "ERROR: Usuario ya se encuentra registrado";
		} else {
			userRepository.saveNewUser(email, password);
			return "OK: El usuario ha sido registrado exitosamente";
		}
	}

	public String userLogin(String email, String password) throws SQLException {
		String existingPassword = userRepository.getUserPasswordByEmail(email);
		if (existingPassword != null) {
			if (existingPassword.equals(password)) {
				return "OK: Login exitoso";
			} else {
				return "ERROR: Password incorrecta";
			}
		} else {
			return "ERROR: El usuario no se encuentra registrado";
		}
	}

	public String requestResetPassword(String email) throws SQLException {
		String existingPassword = userRepository.getUserPasswordByEmail(email);
		if (existingPassword != null) {
			String codigoReinicio = Math.round(Math.random() * 9999) + "";
			while (codigoReinicio.length() != 4) {
				codigoReinicio = Math.round(Math.random() * 9999) + "";
			}
			codigosDeReinicio.put(email, codigoReinicio);
			return "OK: Codigo de reinicio de password enviado" + "(Simulacion codigo=" + codigoReinicio + ")";

		} else {
			return "ERROR: Direccion de correo no encontrada";
		}
	}

	public String requestResetPassword(String email, String newPassword, String codigo) throws SQLException {
		String existingPassword = userRepository.getUserPasswordByEmail(email);
		if (existingPassword != null) {
			if (codigo.equals(codigosDeReinicio.get(email))) {
				userRepository.updateUser(email, newPassword);
				return "OK: Contraseña modificada";
			} else {
				return "ERROR: El codigo de reinicio no es correcto";
			}

		} else {
			return "ERROR: email no esta registrado";
		}
	}

	public String changePassword(String email, String password, String newPassword) throws SQLException {
		String existingPassword = userRepository.getUserPasswordByEmail(email);
		if (existingPassword != null) {
			if (existingPassword.equals(password)) {
				userRepository.updateUser(email, newPassword);
				return "OK: Contraseña cambiada con éxito.";
			} else {
				return "ERROR: Usuario o contraseña incorrectos.";
			}
		} else {
			return "ERROR: Usuario no existente.";
		}
	}

}