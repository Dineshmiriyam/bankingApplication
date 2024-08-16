package javaproject.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javaproject.entities.User;
import javaproject.services.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public String createAccount(@RequestParam String accountNumber, Principal principal) {
        // Get the authenticated user
        User user = (User) ((Authentication) principal).getPrincipal();
        accountService.createAccount(user, accountNumber);
        return "redirect:/accounts";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountId, @RequestParam Double amount) {
        accountService.deposit(accountId, amount);
        return "redirect:/accounts";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam Double amount) {
        accountService.withdraw(accountId, amount);
        return "redirect:/accounts";
    }

    @GetMapping("/{accountId}")
    public String getBalance(@PathVariable Long accountId, Model model) {
        Double balance = accountService.getBalance(accountId);
        model.addAttribute("balance", balance);
        return "account";
    }
}
