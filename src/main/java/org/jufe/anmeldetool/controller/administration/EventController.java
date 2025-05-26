package org.jufe.anmeldetool.controller.administration;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.service.EventService;
import org.jufe.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.jufe.anmeldetool.controller.ControllerConstants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final EventService eventService;

    @ModelAttribute(name = ENTITY_EVENT)
    public Event setUpForm() {
        return new Event();
    }

    @GetMapping
    public String getListForm(Model model, HttpSession session) {
        model.addAttribute(ENTITY_EVENT_LISTE, eventService.getAllEvents());
        return VIEW_EVENT_DASHBOARD;
    }

    @PostMapping
    public String postForm(@ModelAttribute(name = ENTITY_EVENT) Event event, Model model) {
        LOGGER.info(() -> String.format("%s", event));
        return "true";
    }

    @GetMapping("/{id}")
    public ModelAndView getIdForm(@PathVariable("id") UUID id, HttpSession session) {
        Optional<Event> e = eventService.getEventById(id);
        var model = new MessageStore();
        if (e.isEmpty()) {
            return new ModelAndView(REDIRECT_EVENT, new HashMap<>());
        }
        model.put(ENTITY_EVENT, e.get());
        LOGGER.debug(e.get()
                      .getBestaetigt());
        return new ModelAndView(VIEW_EVENT_DETAIL, model);
    }

}
