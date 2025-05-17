package org.jufe.anmeldetool.controller.administration;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController extends Constants {

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

    @GetMapping("/{id}")
    public String getIdForm(@PathVariable("id") UUID id, Model model, HttpSession session) {
        Optional<Event> e = eventService.getEventById(id);
        if (e.isEmpty()) {
            return REDIRECT_EVENT;
        }
        model.addAttribute(ENTITY_EVENT, e.get());
        return VIEW_EVENT_DETAIL;
    }
}
