package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app")
public class PublicController {
    @GetMapping()
    public String getForm(Model model) {
        model.addAttribute("anmeldung", new Anmeldung());
        // todo: return view
        return "";
    }
}