package cl.developteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloworldController {

	@GetMapping("/helloworlds")
	@ResponseBody
	public String getHelloworld() {
		return "Helloworld";
	}

	@GetMapping("/suma")
	@ResponseBody
	public Integer suma(@RequestParam("numero1") int numero1, @RequestParam("numero2") int numero2) {
		return numero1 + numero2;
	}

}
