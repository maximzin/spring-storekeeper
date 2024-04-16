package ru.zinovev.springstorekeeper.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zinovev.springstorekeeper.models.*;
import ru.zinovev.springstorekeeper.services.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/closet")
public class ClosetController {

    private final ClosetService closetService;

    private final ClosetTypeService closetTypeService;

    private final CehService cehService;

    private final CellService cellService;

    private final CellAndToolsService cellAndToolsService;

    private final ToolService toolService;

    @Autowired
    public ClosetController(ClosetService closetService,
                            ClosetTypeService closetTypeService,
                            CehService cehService,
                            CellService cellService,
                            CellAndToolsService cellAndToolsService,
                            ToolService toolService) {
        this.closetService = closetService;
        this.closetTypeService = closetTypeService;
        this.cehService = cehService;
        this.cellService = cellService;
        this.cellAndToolsService = cellAndToolsService;
        this.toolService = toolService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("closets", closetService.findAll());
        return "closet/showall";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id,
                          @ModelAttribute("cell") Optional<Cell> cell,
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
        int capacity = closetTypeService.getCapacity(closet.getIdType());
        closetService.saveCloset(closet, capacity);
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

    //Далее идёт функционал конкретных ячеек
    @GetMapping("/{id}/cell/{num_cell}")
    public String showOneCell(Model model,
                              @PathVariable("id") int id,
                              @PathVariable("num_cell") int num_cell,
                              @ModelAttribute("cellAndTool") CellAndTools cellAndTools) {
        model.addAttribute("closet", closetService.findOne(id));
        //model.addAttribute(cellService.findByIdCloset(id));
        Cell needCell = cellService.findOne(id, num_cell);
        model.addAttribute("cellAndTools", cellAndToolsService.findByIdCell(needCell.getId()));
        return "closet/showcell";
    }

    @DeleteMapping("/{id}/cell/{id_cell}")
    public String deleteCellAndTool(@PathVariable("id") int id,
                                    @PathVariable("id_cell") int id_cell) {
        cellAndToolsService.deleteCellAndTools(id_cell);
        return "redirect:/closet/{id}";
    }

    @GetMapping("/{id}/cell/{num_cell}/addtool")
    public String newToolToCell(Model model,
                                @PathVariable("id") int id,
                                @PathVariable("num_cell") int num_cell,
                                @ModelAttribute("cellAndTools") CellAndTools cellAndTools,
                                @ModelAttribute("tool") Tool tool) {
        model.addAttribute("cell", cellService.findOne(id, num_cell));
        model.addAttribute("tools", toolService.findAll());
        return "closet/addtool";
    }

    @PostMapping("/{id}/cell/{num_cell}/addtool")
    public String addToolToCell(@PathVariable("id") int id,
                                @PathVariable("num_cell") int num_cell,
                                @ModelAttribute("cellAndTools") @Valid CellAndTools cellAndTools,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "closet/addtool";
        cellAndToolsService.saveCellAndTools(cellAndTools);
        return "redirect:/closet/{id}";
    }
}
