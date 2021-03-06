package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Users;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/users")
@Secured("ROLE_ADMIN")
public class UserController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserService uService;

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new Users());
		return "user/user";
	}

	@PostMapping("/save")
	public String saveUser(@Valid Users user, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "user/user";
		} else {
			String bcryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(bcryptPassword);
			int rpta = uService.insert(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "user/user";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaUsuarios", uService.list());

		return "user/listUser";
	}

	@GetMapping("/list")
	public String listUser(Model model) {
		try {
			model.addAttribute("user", new Users());
			model.addAttribute("listaUsuarios", uService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "user/listUser";
	}

}
