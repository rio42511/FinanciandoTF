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

import pe.edu.upc.entities.Bank;
import pe.edu.upc.service.IBankService;

@Controller
@RequestMapping("/banks")
public class BankController {
	@Autowired
	private IBankService bService;

	@GetMapping("/new")
	public String newBank(Model model) {
		model.addAttribute("bank", new Bank());
		return "bank/bank";
	}

	@PostMapping("/save")
	public String saveBank(@Valid @ModelAttribute(value = "bank") Bank bank, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "bank/bank";

		} else {
			bService.insert(bank);
			model.addAttribute("mensaje", "Se realizo bien !!");
			status.setComplete();
			return "redirect:/banks/list";
		}
	}

	@GetMapping("/list")
	public String listBank(Model model) {
		try {
			model.addAttribute("listaBank", bService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "bank/listBank";
	}

	@RequestMapping("/delete")
	public String deleteBank(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				bService.delete(id);
				model.put("mensaje", "Se elimino correctamente");

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}

		return "redirect:/banks/list";
	}

	@GetMapping("/detalle/{id}")
	public String detailsBank(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Bank> bank = bService.listarId(id);
			if (!bank.isPresent()) {
				model.addAttribute("info", "Banco no existe");
				return "redirect:/banks/list";
			} else {
				model.addAttribute("bank", bank.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/bank/update";
	}

	@GetMapping("/listFind")
	public String listBankFind(Model model) {
		try {
			model.addAttribute("bank", new Bank());
			model.addAttribute("listaBank", bService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "bank/find";
	}

	@RequestMapping("/find")
	public String findByBank(Map<String, Object> model, @ModelAttribute Bank bank) throws ParseException {

		List<Bank> listBanks;
		bank.setNameBank(bank.getNameBank());
		listBanks = bService.findByName(bank.getNameBank());
		if (listBanks.isEmpty()) {
			listBanks = bService.findByNameLikeIgnoreCase(bank.getNameBank());
		}
		if (listBanks.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaBank", listBanks);
		return "bank/find";

	}

}
