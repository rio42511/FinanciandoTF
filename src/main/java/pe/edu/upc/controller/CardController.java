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

import pe.edu.upc.entities.Card;
import pe.edu.upc.service.IBankService;
import pe.edu.upc.service.ICardService;

@Controller
@RequestMapping("/cards")
public class CardController {

	@Autowired
	private ICardService cService;
	@Autowired
	private IBankService bService;

	@GetMapping("/new")
	public String newCard(Model model) {
		model.addAttribute("card", new Card());
		model.addAttribute("listaBank", bService.list());
		return "card/card";
	}

	@PostMapping("/save")
	public String saveCard(@Valid @ModelAttribute(value = "card") Card card, BindingResult result, Model model,
			SessionStatus status) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaBank", bService.list());
			return "card/card";
		} else {
			cService.insert(card);
			model.addAttribute("mensaje", "Se realizo bien!!");
			status.setComplete();
			return "redirect:/cards/list";
		}
	}

	@GetMapping("/list")
	public String listCard(Model model) {
		try {
			model.addAttribute("listaTarjetas", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "card/listCard";

	}

	@RequestMapping("/delete")
	public String deleteCard(Map<String, Object> model, @RequestParam(value = "id") Integer id) {

		try {
			if (id != null && id > 0) {
				cService.delete(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}

		return "redirect:/cards/list";

	}

	@GetMapping("/detalle/{id}")
	public String detailsCard(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Card> card = cService.listarId(id);
			if (!card.isPresent()) {
				model.addAttribute("info", "Tarjeta no existe");
				return "redirect:/cards/list";
			} else {
				model.addAttribute("listaBank", bService.list());
				model.addAttribute("card", card.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/card/update";
	}

	@GetMapping("/listFind")
	public String listCardFind(Model model) {
		try {
			model.addAttribute("card", new Card());
			model.addAttribute("listaTarjetas", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}
		return "card/find";
	}

	@RequestMapping("/find")
	public String findByCard(Map<String, Object> model, @ModelAttribute Card card) throws ParseException {

		List<Card> listCards;
		card.setNumberCard(card.getNumberCard());
		listCards = cService.findByName(card.getNumberCard());
		if (listCards.isEmpty()) {
			listCards = cService.findByNameLikeIgnoreCase(card.getNumberCard());
		}
		if (listCards.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTarjetas", listCards);
		return "card/find";

	}

}
