package org.jufe.anmeldetool.controller.open;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.controller.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PublicController extends Constants {

    @GetMapping
    public String index() {
        return REDIRECT_ANMELDUNG;
    }

}