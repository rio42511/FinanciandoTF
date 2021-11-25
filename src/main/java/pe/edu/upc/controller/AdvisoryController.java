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

import pe.edu.upc.entities.Advisory;
import pe.edu.upc.entities.Servicio;
import pe.edu.upc.service.IAdviserService;
import pe.edu.upc.service.IAdvisoryService;
import pe.edu.upc.service.IServicioService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/advisories")
@Secured("ROLE_ADMIN")
public class AdvisoryController {

	@Autowired
	private IAdvisoryService aService;

	@Autowired
	private IUserService uService;

	@Autowired
	private IAdviserService adService;

	@Autowired
	private IServicioService sService;

	@GetMapping("/new")
	public String newAdvisory(Model model) {
		model.addAttribute("advisory", new Advisory());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("listaAsesores", adService.list());
		model.addAttribute("listaServicios", sService.list());
		return "advisory/advisory";
	}

	@PostMapping("/save")
	public String saveAdvisory(@Valid @ModelAttribute(value = "advisory") Advisory advisory, BindingResult result,
			Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaAsesores", adService.list());
			model.addAttribute("listaServicios", sService.list());
			return "advisory/advisory";
		} else {
			aService.insert(advisory);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/advisories/list";
		}

	}

	@GetMapping("/list")
	public String listAdvisory(Model model) {
		try {
			model.addAttribute("listaAsesorias", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		return "advisory/listAdvisory";
	}

	@RequestMapping("/delete")
	public String deleteAdvisory(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aService.delete(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/advisories/list";
	}

	@GetMapping("/detalle/{id}")
	public String detailsAdviser(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Advisory> advisory = aService.listarId(id);
			if (!advisory.isPresent()) {
				model.addAttribute("info", "Asesoria no existe");
				return "redirect:/advisories/list";
			} else {
				model.addAttribute("advisory", advisory.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/advisory/update";
	}

	@GetMapping("/listFind")
	public String listAdvisoryFind(Model model) {
		try {
			model.addAttribute("advisory", new Advisory());
			model.addAttribute("listaAsesorias", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		return "advisory/find";
	}

	@RequestMapping("/find")
	public String findByAdviser(Map<String, Object> model, @ModelAttribute Advisory advisory) throws ParseException {

		List<Advisory> listAdvisories;
		advisory.setCodeAdvisory(advisory.getCodeAdvisory());
		listAdvisories = aService.findByCode(advisory.getCodeAdvisory());
		if (listAdvisories.isEmpty()) {
			listAdvisories = aService.findByCodeLikeIgnoreCase(advisory.getCodeAdvisory());
		}
		if (listAdvisories.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAsesorias", listAdvisories);
		return "advisory/find";

	}
	
	
	@GetMapping("/reportes")
	public String listReports(Model model) {

		return "/reports/reports";
	}
	
	/*@RequestMapping("/reporte1")
	public String usuarioTop(Map<String,Object> model)
	{
		model.put("listaUsuarios", aService.usuarioTop());
		return "reports/usuarioTop";
	}

	@RequestMapping("/reporte2")
	public String servicioTop(Map<String,Object> model)
	{
		model.put("listaServicios", aService.servicioTop());
		return "reports/servicioTop";
	}
	*/
	@RequestMapping("/reporte3")
	public String asesorTop(Map<String,Object> model)
	{
		model.put("listaAsesores", aService.asesorTop());
		return "reports/usuarioTop";
	}
	/*
	@RequestMapping("/reporte4")
	public String usuarioTopxServicio(Map<String,Object> model)
	{
		model.put("listaUsuariosTopxServicio", aService.usuariosTopxServicio());
		return "reports/usuarioTopxServicio";
	}
	
	@RequestMapping("/reporte5")
	public String usuarioxServicio(Map<String,Object> model, @ModelAttribute Servicio servicio) throws ParseException
	{
		model.put("listaUsuariosxServicio", aService.usuariosXservicio(servicio.getNameServicio()));
		return "reports/usuarioxServicio";
	
	}
*/
}
