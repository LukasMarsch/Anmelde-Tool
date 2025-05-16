package org.jufe.anmeldetool.controller.open;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.service.AnmeldungService;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/anmeldung")
public class AnmeldungController extends Constants {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AnmeldungService anmeldungService;

    private final EventService eventService;

    @ModelAttribute(name = ENTITY_ANMELDUNG)
    public Anmeldung setUpForm() {
        return new Anmeldung(eventService.getNextEvent());
    }

    @GetMapping
    public String getForm(Model model, HttpSession session) {
        model.addAttribute(ENTITY_EVENT, eventService.getNextEvent());
        return VIEW_ANMELDE_FORMULAR;
    }

    @PostMapping
    public String postForm(@ModelAttribute(name = ENTITY_ANMELDUNG) Anmeldung anmeldung, Model model) {
        try {
            LOGGER.info(() -> String.format("save Anmeldung: %s", anmeldung));
            anmeldungService.save(anmeldung);

            model.addAttribute(ENTITY_SUCCESS, true);
            model.addAttribute(ENTITY_MESSAGE, MESSAGE_ANMELDUNG_SAVED);

            Object needsShuttle = model.getAttribute("needsShuttle");
            if (null != needsShuttle && (boolean) needsShuttle) {
                return REDIRECT_SHUTTLE;
            } else {
                model.addAttribute(ENTITY_MESSAGE, "Anmeldung noch nicht gespeichert, Implementation fehlt");
            }

        } catch (Exception e) {
            model.addAttribute(ENTITY_SUCCESS, false);
            model.addAttribute(ENTITY_MESSAGE, e.getLocalizedMessage());
        }
        return REDIRECT_ANMELDUNG;
    }

}