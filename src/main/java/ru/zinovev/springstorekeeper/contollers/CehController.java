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
    public String cehAll(Model model) {
        model.addAttribute("cehs", cehService.findAll());
        return "ceh/all";
    }

    @GetMapping("/{id}")
    public String cehById(@PathVariable("id") int id, Model model) {
        model.addAttribute("ceh", cehService.findOne(id));
        return "ceh/id";
    }

    @GetMapping("/newceh")
    public String newCeh(@ModelAttribute("ceh") Ceh ceh) {
        return "ceh/newceh";
    }

    @PostMapping()
    public String createCeh(@ModelAttribute("ceh") @Valid Ceh ceh,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "ceh/newceh";

        cehService.saveCeh(ceh);
        return "redirect:/ceh";
    }

    @GetMapping("/{id}/editceh")
    public String editCeh(Model model, @PathVariable("id") int id) {
        model.addAttribute("ceh", cehService.findOne(id));
        return "ceh/editceh";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ceh") @Valid Ceh ceh, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "ceh/editceh";

        cehService.updateCeh(id, ceh);
        return "redirect:/ceh/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteCeh(@PathVariable("id") int id) {
        cehService.deleteCeh(id);
        return "redirect:/ceh";
    }

}
