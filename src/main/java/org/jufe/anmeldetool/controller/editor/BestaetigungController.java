package org.jufe.anmeldetool.controller.editor;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.message.MessageStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("bestaetigen")
public class BestaetigungController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AnmeldungRepository anmeldungRepository;

    @PostMapping("/{id}")
    public String confirmTeilnahme(@PathVariable UUID id, Model model) {
        LOGGER.debug(() -> String.format("Anmeldung bestätigen: %s", id));
        MessageStore messageStore = new MessageStore();
        UUID event = null;
        try {
            Anmeldung am = anmeldungRepository.getReferenceById(id);
            am.setBestaetigt(TRUE);
            anmeldungRepository.save(am);
            event = am.getEvent()
                      .getId();
            messageStore.put(ENTITY_SUCCESS, TRUE.toString());
            messageStore.put(ENTITY_MESSAGE, MESSAGE_TEILNAHME_CONFIRMED);
        } catch (EntityNotFoundException e) {
            messageStore.put(ENTITY_SUCCESS, FALSE.toString());
            messageStore.put(ENTITY_MESSAGE, e.getMessage());
        }
        model.addAllAttributes(messageStore);
        if (event != null)
            return REDIRECT_EVENTDETAIL.formatted(event);
        return REDIRECT_EVENT;
    }

}
