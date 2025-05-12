package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("anmelden")
public class FormController {
    @PostMapping()
    public String saveAnmeldung(@ModelAttribute Anmeldung anmeldung, Model model) {
        var needsShuttle = model.getAttribute("needsShuttle");
        if(null != needsShuttle && (boolean) needsShuttle) {
            return Constants.REDIRECT_SHUTTLE;
        } else {
            model.addAttribute("messageStore", List.of("Anmeldung noch nicht gespeichert, Implementation fehlt"));
        }
        return "redirect:/app";
    }
}