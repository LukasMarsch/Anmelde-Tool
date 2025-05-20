package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.jufe.anmeldetool.controller.ControllerConstants.REDIRECT_ANMELDUNG;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PublicController {

    private final EventService eventService;

    @GetMapping
    public String index() {
        return REDIRECT_ANMELDUNG;
    }

}