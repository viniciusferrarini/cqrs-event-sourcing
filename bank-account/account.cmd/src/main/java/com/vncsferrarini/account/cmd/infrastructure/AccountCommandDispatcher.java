package com.vncsferrarini.account.cmd.infrastructure;

import com.vncsferrarini.cqrs.core.commands.BaseCommand;
import com.vncsferrarini.cqrs.core.commands.CommandHandlerMethod;
import com.vncsferrarini.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = Collections.emptyMap();

    @Override
    public <T extends BaseCommand> void registerHandler(final Class<T> type, final CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(final BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.size() == 0) {
            throw new RuntimeException("No command handler was registered");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler!");
        }
    }
}