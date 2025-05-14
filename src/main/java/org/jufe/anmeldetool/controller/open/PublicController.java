package org.jufe.anmeldetool.controller.open;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/anmeldung")
public class PublicController {

    private final EventService eventService;

    @ModelAttribute
    public Anmeldung setUpForm() {
        return new Anmeldung();
    }

    @GetMapping
    public String getForm(Model model, HttpSession session) {
        LocalDate von = LocalDate.of(2018, 1, 1);
        Event e = Event.builder()
                       .von(von)
                       .id(UUID.randomUUID())
                       .build();
        model.addAttribute(Constants.ENTITY_ANMELDUNG, new Anmeldung());
        session.setAttribute(Constants.ENTITY_EVENT, e.getId());
        return Constants.VIEW_ANMELDE_FORMULAR;
    }

}