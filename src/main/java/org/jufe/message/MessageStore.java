package org.jufe.message;

import org.springframework.ui.Model;

import java.util.HashMap;

public class MessageStore extends HashMap<String, Object> {

    public void addToModel(Model model) {
        model.addAllAttributes(this);
    }

}
