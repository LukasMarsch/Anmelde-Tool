package org.jufe.anmeldetool.controller.open;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.anmeldung.Mahlzeit;
import org.jufe.anmeldetool.entity.anmeldung.MahlzeitConverter;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.service.AbwesendService;
import org.jufe.anmeldetool.service.EventService;
import org.jufe.anmeldetool.service.MailService;
import org.jufe.anmeldetool.service.ShuttleService;
import org.jufe.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.jufe.anmeldetool.controller.ControllerConstants.*;

@SuppressWarnings("unused")
@Controller
@RequiredArgsConstructor
@RequestMapping("/anmeldung")
public class AnmeldungController {

    public static final String ENTITY_ABWESEND = "abwesend";

    private static final Logger LOGGER = LogManager.getLogger();

    private final AnmeldungRepository anmeldungRepository;

    private final EventService eventService;

    private final EventRepository eventRepository;

    private final MailService emailService;

    private final ShuttleService shuttleService;

    private final AbwesendService abwesendService;

    @ModelAttribute(name = ENTITY_ANMELDUNG)
    public Anmeldung setUpAnmeldung() {
        Event nextEvent = eventService.getNextEvent();
        LOGGER.trace(() -> String.format("Set up form with next Event: %s", nextEvent));
        return new Anmeldung(nextEvent);
    }

    @ModelAttribute(name = ENTITY_EVENT_TAGE)
    public List<LocalDate> setUpTage() {
        Event nextEvent = eventService.getNextEvent();
        List<LocalDate> eventTage = new ArrayList<>(4);
        /* @formatter:off */
        for (LocalDate i = nextEvent.getVon();
                !i.equals(nextEvent.getBis().plusDays(1));
                i = i.plusDays(1))
        {
            eventTage.add(i);
        }
        /* @formatter:on */
        return eventTage;
    }

    @GetMapping
    public String getForm(Model model) {
        Event e = eventService.getNextEvent();
        model.addAttribute(ENTITY_EVENT, e);
        model.addAttribute(ENTITY_HALTESTELLEN_HIN, shuttleService.getAllHaltestellenAmTag(e.getVon()));
        model.addAttribute(ENTITY_HALTESTELLEN_RUECK, shuttleService.getAllHaltestellenAmTag(e.getBis()));
        LOGGER.trace(() -> String.format("add %s to model as %s", ENTITY_EVENT, eventService.getNextEvent()));
        return VIEW_ANMELDE_FORMULAR;
    }

    @PostMapping
    public ModelAndView postForm(@ModelAttribute(name = ENTITY_ANMELDUNG) Anmeldung anmeldung,
            @ModelAttribute(name = ENTITY_ANY_ABWESEND) boolean anyAbwesend, HttpServletRequest request, Model model) {
        LOGGER.trace(() -> String.format("save %s from model as %s", ENTITY_ANMELDUNG, anmeldung));
        MessageStore messages = new MessageStore();
        saveAnmeldung(anmeldung, messages);
        LOGGER.debug(() -> model);
        if (anyAbwesend) {
            saveAbwesend(anmeldung, request.getParameterMap()
                                           .get(ENTITY_ABWESEND), messages);
        }
        sendMail(anmeldung, messages);
        messages.put(ENTITY_ANMELDUNG, anmeldung);
        model.addAttribute(ENTITY_EVENT, eventService.getNextEvent());
        model.addAttribute(ENTITY_ANMELDUNG, new Anmeldung(eventService.getNextEvent()));
        return new ModelAndView(VIEW_ANMELDE_FORMULAR, new HashMap<>());
    }

    private void saveAbwesend(Anmeldung anmeldung, String[] values, MessageStore messages) {
        MahlzeitConverter mc = new MahlzeitConverter();
        LOGGER.debug(() -> String.format("%s abwesend values to save", values.length));
        for (String value : values) {
            ImmutablePair<LocalDate, Mahlzeit> pair = mc.convert(value);
            if (pair != null)
                abwesendService.create(anmeldung, pair.getLeft(), pair.getRight());
        }
    }

    private void saveAnmeldung(Anmeldung anmeldung, MessageStore messages) {
        try {
            anmeldungRepository.save(anmeldung);
            messages.put(ENTITY_SUCCESS, true);
            messages.put(ENTITY_MESSAGE, "Deine Anmeldung wurde gespeichert.");
        } catch (Exception e) {
            messages.put(ENTITY_SUCCESS, false);
            messages.put(ENTITY_MESSAGE, "Die Anmeldung konnte nicht gespeichert werden.");
            LOGGER.error(e::getMessage);
        }
    }

    private void sendMail(Anmeldung anmeldung, MessageStore messages) {
        try {
            emailService.sendConfirmationMail(anmeldung.getMail(), anmeldung.getEvent(), anmeldung);
        } catch (MessagingException e) {
            messages.put(ENTITY_SUCCESS, false);
            messages.put(ENTITY_MESSAGE,
                    "Wir konnten dir leider keine Email-schicken. Bitte überprüfe deine Email-Adresse oder kontaktieren das JuFe-Team.");
            LOGGER.error(e::getMessage);
        }
    }

}
