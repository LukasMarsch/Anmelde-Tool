package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/anmeldung")
public class FormController {

    private final AnmeldungRepository anmeldungRepository;

    private final EventService eventService;

    @PostMapping
    public String postForm(@RequestParam(name = Constants.ENTITY_ANMELDUNG) Anmeldung anmeldung,
            @RequestParam(name = Constants.ENTITY_EVENT) UUID event, Model model) {
        try {
            anmeldungRepository.save(anmeldung);
            eventService.addAnmeldungToEventById(event, anmeldung);

            model.addAttribute(Constants.ENTITY_SUCCESS, true);
            model.addAttribute(Constants.ENTITY_MESSAGE, Constants.MESSAGE_ANMELDUNG_SAVED);

            Object needsShuttle = model.getAttribute("needsShuttle");
            if (null != needsShuttle && (boolean) needsShuttle) {
                return Constants.REDIRECT_SHUTTLE;
            } else {
                model.addAttribute(Constants.ENTITY_MESSAGE, "Anmeldung noch nicht gespeichert, Implementation fehlt");
            }

        } catch (Exception e) {
            model.addAttribute(Constants.ENTITY_SUCCESS, false);
            model.addAttribute(Constants.ENTITY_MESSAGE, e.getLocalizedMessage());
        }
        return Constants.REDIRECT_ANMELDUNG;
    }

}