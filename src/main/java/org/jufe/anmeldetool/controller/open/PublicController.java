package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.jufe.anmeldetool.controller.ControllerConstants.REDIRECT_ANMELDUNG;

@SuppressWarnings("unused")
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PublicController {

    @GetMapping
    public String index() {
        return REDIRECT_ANMELDUNG;
    }

}
