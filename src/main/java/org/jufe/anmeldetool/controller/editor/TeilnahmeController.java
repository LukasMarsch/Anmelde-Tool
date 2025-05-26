package org.jufe.anmeldetool.controller.editor;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.anmeldetool.service.TeilnehmerAnmeldungService;
import org.jufe.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.jufe.anmeldetool.controller.ControllerConstants.*;

@SuppressWarnings("unused")
@Controller
@RequiredArgsConstructor
@RequestMapping(ENTITY_TEILNEHMER)
public class TeilnahmeController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AnmeldungRepository anmeldungRepository;

    private final TeilnehmerAnmeldungService tnAnService;

    @PostMapping("/{id}")
    public String confirmTeilnahme(@ModelAttribute(name = ENTITY_TEILNEHMER) Teilnehmer teilnehmer, @PathVariable UUID id, Model model) {
        LOGGER.warn(() -> String.format("Anmeldung bestätigen: %s", teilnehmer));
        MessageStore messageStore = new MessageStore();
        try {
            tnAnService.create(teilnehmer, id);
            messageStore.put(ENTITY_SUCCESS, TRUE.toString());
            messageStore.put(ENTITY_MESSAGE, MESSAGE_TEILNAHME_CONFIRMED);
        } catch (EntityNotFoundException e) {
            messageStore.put(ENTITY_SUCCESS, FALSE.toString());
            messageStore.put(ENTITY_MESSAGE, e.getMessage());
        }
        model.addAllAttributes(messageStore);
        return REDIRECT_EVENT;
    }

}
