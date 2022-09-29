package com.vncsferrarini.account.cmd.api;

import com.vncsferrarini.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
