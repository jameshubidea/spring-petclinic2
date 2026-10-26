package org.springframework.samples.petclinic.owner;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles the pet search page and passes search results to the Thymeleaf template.
 */
@Controller
public class PetSearchController {

	private final PetSearchRepository petSearchRepository;

	public PetSearchController(PetSearchRepository petSearchRepository) {
		this.petSearchRepository = petSearchRepository;
	}

	@GetMapping("/pets/search")
	public String searchPets(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
							 Model model) {
		String normalizedKeyword = keyword.strip();
		model.addAttribute("keyword", normalizedKeyword);

		if (StringUtils.hasText(normalizedKeyword)) {
			List<PetSearchResult> results = petSearchRepository.searchPets(normalizedKeyword);
			model.addAttribute("pets", results);
			model.addAttribute("searched", true);
		}
		else {
			model.addAttribute("pets", List.of());
			model.addAttribute("searched", false);
		}

		return "pets/searchPets";
	}
}
