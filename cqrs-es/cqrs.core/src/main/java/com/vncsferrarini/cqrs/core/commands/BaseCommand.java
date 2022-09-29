package com.vncsferrarini.cqrs.core.commands;

import com.vncsferrarini.cqrs.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(final String id) {
        super(id);
    }
}
