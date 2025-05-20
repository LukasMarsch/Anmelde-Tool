package org.jufe.anmeldetool.controller.administration;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.jufe.anmeldetool.controller.ControllerConstants.ENTITY_EVENT;
import static org.jufe.anmeldetool.controller.ControllerConstants.EVENT_FORMULAR;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    EventService eventService;

    private static final Logger LOGGER = LogManager.getLogger();

    @ModelAttribute(name = ENTITY_EVENT)
    public Event setUpForm() {
        return new Event();
    }

    @GetMapping
    public String getForm(Model model, HttpSession session) {
        return EVENT_FORMULAR;
    }

    public String postForm(@ModelAttribute(name = ENTITY_EVENT) Event event, Model model) {
        //try {
        LOGGER.info(() -> String.format("%s", event));

        //}
        return "true";
    }

}
