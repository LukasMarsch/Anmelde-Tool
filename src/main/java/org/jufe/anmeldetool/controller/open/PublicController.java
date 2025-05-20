package org.jufe.anmeldetool.controller.open;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.jufe.anmeldetool.controller.ControllerConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PublicController {

    private final EventService eventService;

    private final EventRepository eventRepository;

    @ModelAttribute(name = ENTITY_ANMELDUNG)
    public Anmeldung setUpForm() {
        Event nextEvent = eventService.getNextEvent();
        eventRepository.save(nextEvent);
        return new Anmeldung(nextEvent);
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