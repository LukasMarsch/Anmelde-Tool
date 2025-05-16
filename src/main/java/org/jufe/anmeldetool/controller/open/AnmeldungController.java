package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.service.EventService;
import org.jufe.anmeldetool.service.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/anmeldung")
public class AnmeldungController extends Constants {

    private final AnmeldungRepository anmeldungRepository;

    private final EventService eventService;

    private final EventRepository eventRepository;

    private static final Logger LOGGER = LogManager.getLogger();

    @ModelAttribute(name = ENTITY_ANMELDUNG)
    public Anmeldung setUpForm() {
        Event nextEvent = eventService.getNextEvent();
        eventRepository.save(nextEvent);
        LOGGER.trace(() -> String.format("Set up form with next Event: %s", nextEvent.toString()));
        return new Anmeldung(nextEvent);
    }

    @GetMapping
    public String getForm(Model model) {
        model.addAttribute(ENTITY_EVENT, eventService.getNextEvent());
        LOGGER.trace(() -> String.format("add %s to model as %s", ENTITY_EVENT, eventService.getNextEvent()));
        return VIEW_ANMELDE_FORMULAR;
    }

    @PostMapping
    @Transactional
    public String postForm(@ModelAttribute(name = ENTITY_ANMELDUNG) Anmeldung anmeldung, Model model) {
        LOGGER.trace(() -> String.format("save %s from model as %s", ENTITY_ANMELDUNG, anmeldung));
        MessageStore messages = new MessageStore();
        try {
            anmeldungRepository.save(anmeldung);
            messages.put(ENTITY_SUCCESS, TRUE.toString());
            messages.put(ENTITY_MESSAGE, "Deine Anmeldung wurde gespeichert.");
        } catch (Exception e) {
            messages.put(ENTITY_SUCCESS, FALSE.toString());
            messages.put(ENTITY_MESSAGE, "Die Anmeldung konnte nicht gespeichert werden.");
            LOGGER.error(e::getMessage);
        }
        messages.addToModel(model);
        return REDIRECT_ANMELDUNG;
    }

}
