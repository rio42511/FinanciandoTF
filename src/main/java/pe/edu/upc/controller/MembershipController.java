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

import pe.edu.upc.entities.Membership;
import pe.edu.upc.service.IMembershipService;

@Controller
@RequestMapping("/memberships")
@Secured("ROLE_ADMIN")
public class MembershipController {

	@Autowired
	private IMembershipService mService;

	@GetMapping("/new")
	public String newMembership(Model model) {
		model.addAttribute("membership", new Membership());
		return "membership/membership";
	}

	@PostMapping("/save")
	public String saveMembership(@Valid @ModelAttribute(value = "membership") Membership membership,
			BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors())
			return "membership/membership";
		else {
			mService.insert(membership);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/memberships/list";
		}

	}

	@GetMapping("/list")
	public String listMembership(Model model) {
		try {
			model.addAttribute("listaMembresias", mService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "membership/listMembership";
	}

	@RequestMapping("/delete")
	public String deleteMembership(Map<String, Object> model, @RequestParam(value = "id") Integer id) {

		try {
			if (id != null && id > 0) {
				mService.delete(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");

		}
		model.put("listMembership", mService.list());
		return "redirect:/memberships/list";

	}

	@GetMapping("/detalle/{id}")
	public String detailsMembership(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Membership> membership = mService.listarId(id);
			if (!membership.isPresent()) {
				model.addAttribute("info", "Membresia no existe");
				return "redirect:/memberships/list";
			} else {
				model.addAttribute("membership", membership.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/membership/update";
	}

	@GetMapping("/listFind")
	public String listMembershipFind(Model model) {
		try {
			model.addAttribute("membership", new Membership());
			model.addAttribute("listaMembresias", mService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "membership/find";
	}

	@RequestMapping("/find")
	public String findByMembership(Map<String, Object> model, @ModelAttribute Membership membership)
			throws ParseException {

		List<Membership> listMemberships;
		membership.setNameMembership(membership.getNameMembership());
		listMemberships = mService.findByName(membership.getNameMembership());
		if (listMemberships.isEmpty()) {
			listMemberships = mService.findByNameLikeIgnoreCase(membership.getNameMembership());
		}
		if (listMemberships.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMembresias", listMemberships);
		return "membership/find";

	}

}
