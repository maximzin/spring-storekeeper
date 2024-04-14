package ru.zinovev.springstorekeeper.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zinovev.springstorekeeper.models.Ceh;
import ru.zinovev.springstorekeeper.models.Closet;
import ru.zinovev.springstorekeeper.models.ClosetType;
import ru.zinovev.springstorekeeper.models.Cell;
import ru.zinovev.springstorekeeper.services.CehService;
import ru.zinovev.springstorekeeper.services.CellService;
import ru.zinovev.springstorekeeper.services.ClosetService;
import ru.zinovev.springstorekeeper.services.ClosetTypeService;

@Controller
@RequestMapping("/closet")
public class ClosetController {

    private final ClosetService closetService;

    private final ClosetTypeService closetTypeService;

    private final CehService cehService;

    private final CellService cellService;

    @Autowired
    public ClosetController(ClosetService closetService,
                            ClosetTypeService closetTypeService,
                            CehService cehService,
                            CellService cellService) {
        this.closetService = closetService;
        this.closetTypeService = closetTypeService;
        this.cehService = cehService;
        this.cellService = cellService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("closets", closetService.findAll());
        return "closet/showall";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id,
                          @ModelAttribute("cell") Cell cell,
                          Model model) {
        model.addAttribute("closet", closetService.findOne(id));
        model.addAttribute("cells", cellService.findByIdCloset(id));
        return "closet/showone";
    }

    @GetMapping("/new")
    public String newCloset(Model model, @ModelAttribute("closet") Closet closet,
                            @ModelAttribute("type") ClosetType closetType, @ModelAttribute("ceh") Ceh ceh) {
        model.addAttribute("types", closetTypeService.findAll());
        model.addAttribute("cehs", cehService.findAll());
        return "closet/new";
    }

    @PostMapping()
    public String createCloset(@ModelAttribute("closet") @Valid Closet closet,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "closet/new";

        closetService.saveCloset(closet);
        return "redirect:/closet";
    }

    @GetMapping("/{id}/edit")
    public String editCloset(Model model, @ModelAttribute("closet") Closet closet,
                             @ModelAttribute("type") ClosetType closetType,
                             @ModelAttribute("ceh") Ceh ceh,
                             @PathVariable("id") int id) {
        model.addAttribute("closet", closetService.findOne(id));
        model.addAttribute("types", closetTypeService.findAll());
        model.addAttribute("cehs", cehService.findAll());
        return "closet/edit";
    }

    @PatchMapping("/{id}")
    public String updateCloset(@ModelAttribute("closet") @Valid Closet closet, BindingResult bindingResult,
                                   @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "closet/edit";

        closetService.updateCloset(id, closet);
        return "redirect:/closet/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteCloset(@PathVariable("id") int id) {
        closetService.deleteCloset(id);
        return "redirect:/closet";
    }
    
}
