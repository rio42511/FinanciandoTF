package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Adviser;
import pe.edu.upc.service.IAdviserService;

@Controller
@RequestMapping("/advisers")
@Secured("ROLE_ADMIN")
public class AdviserController {

	@Autowired
	private IAdviserService aService;

	@GetMapping("/new")
	public String newAdviser(Model model) {
		model.addAttribute("adviser", new Adviser());
		return "adviser/adviser";
	}

	@PostMapping("/save")
	public String saveAdviser(@Valid @ModelAttribute(value = "adviser") Adviser adviser, BindingResult result,
			Model model, SessionStatus status) throws Exception {
		if (result.hasErrors())
			return "adviser/adviser";
		else {
			aService.insert(adviser);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/advisers/list";
		}
	}

	@GetMapping("/list")
	public String listAdviser(Model model) {
		try {
			model.addAttribute("listaAsesores", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		return "adviser/listAdviser";
	}

	@RequestMapping("/delete")
	public String deleteAdviser(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aService.delete(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/advisers/list";
	}

	@GetMapping("/detalle/{id}")
	public String detailsAdviser(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Adviser> adviser = aService.listarId(id);
			if (!adviser.isPresent()) {
				model.addAttribute("info", "Asesor no existe");
				return "redirect:/advisers/list";
			} else {
				model.addAttribute("adviser", adviser.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/adviser/update";
	}

	@GetMapping("/listFind")
	public String listAdviserFind(Model model) {
		try {
			model.addAttribute("adviser", new Adviser());
			model.addAttribute("listaAsesores", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		return "adviser/find";
	}

	@RequestMapping("/find")
	public String findByAdviser(Map<String, Object> model, @ModelAttribute Adviser adviser)
			throws ParseException {

		List<Adviser> listAdvisers;
		adviser.setNameAdviser(adviser.getNameAdviser());
		listAdvisers = aService.findByName(adviser.getNameAdviser());
		if (listAdvisers.isEmpty()) {
			listAdvisers = aService.findByNameLikeIgnoreCase(adviser.getNameAdviser());
		}
		if (listAdvisers.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAsesores", listAdvisers);
		return "adviser/find";

	}

}
