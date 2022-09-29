package com.vncsferrarini.account.cmd.domain;

import com.vncsferrarini.account.cmd.api.OpenAccountCommand;
import com.vncsferrarini.account.common.events.AccountClosedEvent;
import com.vncsferrarini.account.common.events.AccountOpenedEvent;
import com.vncsferrarini.account.common.events.FundsDepositedEvent;
import com.vncsferrarini.account.common.events.FundsWithdrawEvent;
import com.vncsferrarini.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
            .id(command.getId())
            .accountHolder(command.getAccountHolder())
            .created(new Date())
            .accountType(command.getAccountType())
            .openingBalance(command.getOpeningBalance())
            .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be deposited into a closed account!");
        }

        if (amount <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than 0!");
        }

        raiseEvent(FundsDepositedEvent.builder()
            .id(this.id)
            .amount(amount)
            .build());
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be withdraw from a closed account!");
        }
        raiseEvent(FundsWithdrawEvent.builder()
            .id(this.id)
            .amount(amount)
            .build());
    }

    public void apply(FundsWithdrawEvent event) {
        if (this.balance <= 0) {
            throw new IllegalStateException("Account balance is negative! You cannot withdraw");
        }
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("The bank account has already been closed");
        }
        raiseEvent(AccountClosedEvent.builder()
            .id(this.id)
            .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}