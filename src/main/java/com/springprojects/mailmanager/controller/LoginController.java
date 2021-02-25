package com.springprojects.mailmanager.controller;

import com.springprojects.mailmanager.data.MailAccountsRepository;
import com.springprojects.mailmanager.data.MailAccountModelAssembler;
import com.springprojects.mailmanager.exceptions.MailAccountNotFoundException;
import com.springprojects.mailmanager.security.PasswordEncryptor;
import com.springprojects.mailmanager.serialization.MailAccount;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class LoginController {
    private final MailAccountsRepository repository;
    private final MailAccountModelAssembler assembler;
    private final PasswordEncryptor passwordEncryptor;

    LoginController(MailAccountsRepository repository, MailAccountModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        this.passwordEncryptor = new PasswordEncryptor();
    }

    @GetMapping("/login")
    public CollectionModel<EntityModel<MailAccount>> getAll() {
        List<EntityModel<MailAccount>> mailAccounts = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(mailAccounts,linkTo(methodOn(LoginController.class).getAll()).withSelfRel());
    }



    @GetMapping("/login/{login}")
    public EntityModel<MailAccount> getOne(@PathVariable String login) {

        MailAccount mailAccount = repository.findById(login)
                .orElseThrow(() -> new MailAccountNotFoundException(login));
        System.out.println(passwordEncryptor.decrypt(mailAccount.getPassword()));
        return assembler.toModel(mailAccount);
    }

    @PutMapping("/login/{login}")
    MailAccount replaceMailAccount(@RequestBody MailAccount newMailAccount, @PathVariable String login) {

        return repository.findById(login)
                .map(mailAccount -> {
                    mailAccount.setLogin(newMailAccount.getLogin());
                    mailAccount.setPassword(newMailAccount.getPassword());
                    return repository.save(mailAccount);
                })
                .orElseGet(() -> {
                    newMailAccount.setLogin(login);
                    return repository.save(newMailAccount);
                });
    }



    @DeleteMapping("/login/{login}")
    void deleteMailAccount(@PathVariable String login) {
        repository.deleteById(login);
    }

    @PostMapping("/login")
    ResponseEntity<?> newMailAccount(@RequestBody MailAccount newMailAccount) {
        newMailAccount.setPassword(passwordEncryptor.encrypt(newMailAccount.getPassword()));
        EntityModel<MailAccount> entityModel = assembler.toModel(repository.save(newMailAccount));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
