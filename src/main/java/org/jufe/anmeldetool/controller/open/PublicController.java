package org.jufe.anmeldetool.controller.open;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PublicController extends Constants {

    private final EventService eventService;

    @ModelAttribute(name = ENTITY_ANMELDUNG)
    public Anmeldung setUpForm() {
        return new Anmeldung(eventService.getNextEvent());
    }

    @GetMapping("/anmeldung")
    public String getForm(Model model, HttpSession session) {
        model.addAttribute(ENTITY_EVENT, eventService.getNextEvent());
        return VIEW_ANMELDE_FORMULAR;
    }

    @GetMapping
    public String index() {
        return REDIRECT_ANMELDUNG;
    }

}