package com.vncsferrarini.cqrs.core.infrastructure;

import com.vncsferrarini.cqrs.core.commands.BaseCommand;
import com.vncsferrarini.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
