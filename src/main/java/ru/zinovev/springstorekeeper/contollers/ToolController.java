package ru.zinovev.springstorekeeper.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zinovev.springstorekeeper.models.Tool;
import ru.zinovev.springstorekeeper.services.ToolService;

@Controller
@RequestMapping("/tool")
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping()
    public String ShowAll(Model model) {
        model.addAttribute("tools", toolService.findAll());
        return "tool/showall";
    }

    @GetMapping("/{id}")
    public String id(@PathVariable("id") int id, Model model) {
        model.addAttribute("tool", toolService.findOne(id));
        return "tool/showone";
    }

    @GetMapping("/new")
    public String newTool(@ModelAttribute("tool") Tool tool) {
        return "tool/new";
    }

    @PostMapping()
    public String createTool(@ModelAttribute("tool") @Valid Tool tool,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "tool/new";
        toolService.saveTool(tool);
        return "redirect:/tool";
    }

    @GetMapping("/{id}/edit")
    public String editTool(Model model, @PathVariable("id") int id) {
        model.addAttribute("tool", toolService.findOne(id));
        return "tool/edit";
    }

    @PatchMapping("/{id}")
    public String updateTool(@ModelAttribute("tool") @Valid Tool tool, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "tool/edit";

        toolService.updateTool(id, tool);
        return "redirect:/tool";
    }

    @DeleteMapping("/{id}")
    public String deleteTool(@PathVariable("id") int id) {
        toolService.deleteTool(id);
        return "redirect:/tool";
    }
    
}
