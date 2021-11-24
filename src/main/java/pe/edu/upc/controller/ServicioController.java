package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.entities.Servicio;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.IServicioService;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

	@Autowired
	private IServicioService sService;

	@Autowired
	private ICategoryService cService;

	@GetMapping("/new")
	public String newServicio(Model model) {
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("listaCategorias", cService.list());
		return "servicio/servicio";

	}

	@PostMapping("/save")
	public String saveServicio(@Valid @ModelAttribute(value = "servicio") Servicio servicio, BindingResult result,
			Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaCategorias", cService.list());
			return "servicio/servicio";
		} else {
			sService.insert(servicio);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/servicios/list";

		}

	}

	@GetMapping("/list")
	public String listServicio(Model model) {
		try {
			model.addAttribute("listaServicios", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "servicio/listServicio";
	}

	@RequestMapping("/delete")
	public String deleteServicio(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				sService.delete(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}

		return "redirect:/servicios/list";
	}

	@GetMapping("/detalle/{id}")
	public String detailsServicio(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Servicio> servicio = sService.listarId(id);
			if (!servicio.isPresent()) {
				model.addAttribute("info", "Servicio no existe");
				return "redirect:/servicios/list";
			} else {
				model.addAttribute("listaCategorias", cService.list());
				model.addAttribute("servicio", servicio.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/servicio/update";
	}

	@GetMapping("/listFind")
	public String listServicioFind(Model model) {
		try {
			model.addAttribute("servicio", new Servicio());
			model.addAttribute("listaServicios", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		return "servicio/find";
	}

	@RequestMapping("/find")
	public String findByServicio(Map<String, Object> model, @ModelAttribute Servicio servicio) throws ParseException {

		List<Servicio> listServicios;
		servicio.setNameServicio(servicio.getNameServicio());
		listServicios = sService.findByName(servicio.getNameServicio());
		if (listServicios.isEmpty()) {
			listServicios = sService.findByNameLikeIgnoreCase(servicio.getNameServicio());
		}
		if (listServicios.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaServicios", listServicios);
		return "servicio/find";

	}

}
