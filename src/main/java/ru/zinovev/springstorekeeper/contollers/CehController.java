package ru.zinovev.springstorekeeper.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zinovev.springstorekeeper.models.Ceh;
import ru.zinovev.springstorekeeper.services.CehService;

@Controller
@RequestMapping("/ceh")
public class CehController {

    private final CehService cehService;

    @Autowired
    public CehController(CehService cehService) {
        this.cehService = cehService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("cehs", cehService.findAll());
        return "showall";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("ceh", cehService.findOne(id));
        return "showone";
    }

    @GetMapping("/new")
    public String newCeh(@ModelAttribute("ceh") Ceh ceh) {
        return "new";
    }

    @PostMapping()
    public String createCeh(@ModelAttribute("ceh") @Valid Ceh ceh,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        cehService.saveCeh(ceh);
        return "redirect:/ceh";
    }

    @GetMapping("/{id}/edit")
    public String editCeh(Model model, @PathVariable("id") int id) {
        model.addAttribute("ceh", cehService.findOne(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateCeh(@ModelAttribute("ceh") @Valid Ceh ceh, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";

        cehService.updateCeh(id, ceh);
        return "redirect:/ceh/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteCeh(@PathVariable("id") int id) {
        cehService.deleteCeh(id);
        return "redirect:/ceh";
    }

}
