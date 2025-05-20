package org.jufe.anmeldetool.controller.editor;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.Result.Result;
import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.jufe.anmeldetool.service.TeilnehmerService;
import org.jufe.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.jufe.anmeldetool.controller.ControllerConstants.*;

@SuppressWarnings("unused")
@Controller
@RequiredArgsConstructor
@RequestMapping(REQUEST_MAPPING_TEILNEHMER)
public class TeilnahmeController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TeilnehmerService teilnehmerService;

    @PostMapping
    public String confirmTeilnahme(@ModelAttribute(name = ENTITY_TEILNEHMER) Teilnehmer teilnehmer, Model model) {
        LOGGER.warn(() -> String.format("Anmeldung bestätigen: %s", teilnehmer));
        Result<Teilnehmer> result = teilnehmerService.toTeilnehmer(teilnehmer);
        MessageStore messageStore = new MessageStore();
        if (result.isPreset()) {
            messageStore.put(ENTITY_SUCCESS, TRUE.toString());
            messageStore.put(ENTITY_MESSAGE, MESSAGE_TEILNAHME_CONFIRMED);
        } else {
            messageStore.put(ENTITY_SUCCESS, FALSE.toString());
            messageStore.put(ENTITY_MESSAGE, result.getCause()
                                                   .getMessage());
        }
        model.addAllAttributes(messageStore);
        return REDIRECT_EVENT;
    }

}
