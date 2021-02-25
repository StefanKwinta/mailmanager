package com.springprojects.mailmanager.controller;


import com.springprojects.mailmanager.data.MailAccountsRepository;
import com.springprojects.mailmanager.data.MailAccountModelAssembler;
import com.springprojects.mailmanager.exceptions.MailAccountNotFoundException;
import com.springprojects.mailmanager.mail.MailInbox;
import com.springprojects.mailmanager.security.PasswordEncryptor;
import com.springprojects.mailmanager.serialization.MailAccount;
import com.springprojects.mailmanager.serialization.MailContent;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class MailManagerController {
    private final MailAccountsRepository repository;
    private final MailAccountModelAssembler assembler;
    private final PasswordEncryptor passwordEncryptor;

    MailManagerController(MailAccountsRepository repository, MailAccountModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        this.passwordEncryptor = new PasswordEncryptor();
    }

    @GetMapping("/mailmanager/inbox/{login}")
    public CollectionModel<MailContent> getOne(@PathVariable String login) {

        MailAccount mailAccount = repository.findById(login)
                .orElseThrow(() -> new MailAccountNotFoundException(login));
        ArrayList<MailContent> inbox = (ArrayList<MailContent>) MailInbox.getInbox(mailAccount.getLogin(),passwordEncryptor.decrypt(mailAccount.getPassword()));
        return CollectionModel.of(inbox,linkTo(methodOn(MailManagerController.class).getOne(login)).withSelfRel());
    }
}
