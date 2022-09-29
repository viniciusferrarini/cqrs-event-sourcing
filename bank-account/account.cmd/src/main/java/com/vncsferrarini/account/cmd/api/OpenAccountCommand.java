package com.vncsferrarini.account.cmd.api;

import com.vncsferrarini.account.common.dto.AccountType;
import com.vncsferrarini.cqrs.core.commands.BaseCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
