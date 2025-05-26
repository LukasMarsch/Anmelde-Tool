package org.jufe.anmeldetool.controller.open;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.service.EventService;
import org.jufe.anmeldetool.service.MailService;
import org.jufe.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.jufe.anmeldetool.controller.ControllerConstants.*;

@SuppressWarnings("unused")
@Controller
@RequiredArgsConstructor
@RequestMapping("/anmeldung")
public class AnmeldungController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AnmeldungRepository anmeldungRepository;

    private final EventService eventService;

    private final EventRepository eventRepository;

    private final MailService emailService;

    @ModelAttribute(name = ENTITY_ANMELDUNG)
    public Anmeldung setUpForm() {
        Event nextEvent = eventService.getNextEvent();
        eventRepository.save(nextEvent);
        LOGGER.trace(() -> String.format("Set up form with next Event: %s", nextEvent));
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
    public ModelAndView postForm(@ModelAttribute(name = ENTITY_ANMELDUNG) Anmeldung anmeldung, Model model) {
        LOGGER.trace(() -> String.format("save %s from model as %s", ENTITY_ANMELDUNG, anmeldung));
        MessageStore messages = new MessageStore();
        try {

            anmeldungRepository.save(anmeldung);
            messages.put(ENTITY_SUCCESS, true);
            messages.put(ENTITY_MESSAGE, "Deine Anmeldung wurde gespeichert.");
        } catch (Exception e) {
            messages.put(ENTITY_SUCCESS, false);
            messages.put(ENTITY_MESSAGE, "Die Anmeldung konnte nicht gespeichert werden.");
            LOGGER.error(e::getMessage);
        }
        try {
            emailService.sendConfirmationMail(anmeldung.getMail(), anmeldung.getEvent(), anmeldung);
        } catch (MessagingException e) {
            messages.put(ENTITY_SUCCESS, false);
            messages.put(ENTITY_MESSAGE,
                    "Wir konnten dir leider keine Email-schicken. Bitte überprüfe deine Email-Adresse oder kontaktieren das JuFe-Team.");
            LOGGER.error(e::getMessage);
        }
        // todo: auf anreise weiterleiten
        messages.put(ENTITY_ANMELDUNG, anmeldung);
        return new ModelAndView(VIEW_ANMELDE_FORMULAR, messages);
    }

}
