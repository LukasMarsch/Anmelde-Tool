package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/app")
public class PublicController {

    @GetMapping()
    public String getForm(Model model) {
        LocalDate von = LocalDate.of(2018,1,1);
        Event e = Event.builder().von(von).id(UUID.randomUUID()).build();
        model.addAttribute(Constants.ENTITY_ANMELDUNG, new Anmeldung());
        model.addAttribute(Constants.ENTITY_EVENT, e);
        return Constants.VIEW_ANMELDE_FORMULAR;
    }
}