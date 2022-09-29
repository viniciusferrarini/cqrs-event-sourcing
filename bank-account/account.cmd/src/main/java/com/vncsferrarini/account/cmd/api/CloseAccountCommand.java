package com.vncsferrarini.account.cmd.api;

import com.vncsferrarini.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(final String id) {
        super(id);
    }
}