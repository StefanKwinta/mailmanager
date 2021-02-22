package com.springprojects.mailmanager.controller;

import com.springprojects.mailmanager.data.AccountsRepository;
import com.springprojects.mailmanager.data.MailAccountModelAssembler;
import com.springprojects.mailmanager.exceptions.MailAccountNotFoundException;
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
    private final AccountsRepository  repository;
    private final MailAccountModelAssembler assembler;

    LoginController(AccountsRepository repository,MailAccountModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
//    @GetMapping("/login")
//    public String getMapingMailManager(@ModelAttribute MailAccount logindata, Model model) {
//        model.addAttribute("logindata", new MailAccount());
//        model.addAttribute("subjects", "");
//        return "login";
//    }
//    @PostMapping("/login")
//    public String postMapingMailManager(@ModelAttribute MailAccount logindata, Model model,
//                                        @RequestParam(value = "send", required = false) String send,
//                                        @RequestParam(value = "fetch", required = false) String fetch) {
//        if(fetch != null && fetch.equals("Fetch")){
//            model.addAttribute("subjects", EmailRecive.read(logindata.getLogin(),logindata.getPassword()));
//        }else if(fetch != null && send.equals("Send")){
//            SendMail.send(logindata.getLogin(),logindata.getPassword(),logindata.getAddress());
//        }
//        model.addAttribute("logindata", logindata);
//        return "login";
//    }


    @GetMapping("/login")
    public CollectionModel<EntityModel<MailAccount>> getAll() {
        List<EntityModel<MailAccount>> mailAccounts = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(mailAccounts,linkTo(methodOn(LoginController.class).getAll()).withSelfRel());
    }



    @GetMapping("/login/{id}")
    public EntityModel<MailAccount> getOne(@PathVariable Long id) {

        MailAccount mailAccount = repository.findById(id) //
                .orElseThrow(() -> new MailAccountNotFoundException(id));

        return assembler.toModel(mailAccount);
    }

    @PutMapping("/login/{id}")
    MailAccount replaceMailAccount(@RequestBody MailAccount newMailAccount, @PathVariable Long id) {

        return repository.findById(id)
                .map(mailAccount -> {
                    mailAccount.setLogin(newMailAccount.getLogin());
                    mailAccount.setPassword(newMailAccount.getPassword());
                    return repository.save(mailAccount);
                })
                .orElseGet(() -> {
                    newMailAccount.setId(id);
                    return repository.save(newMailAccount);
                });
    }



    @DeleteMapping("/login/{id}")
    void deleteMailAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/login")
    ResponseEntity<?> newMailAccount(@RequestBody MailAccount newMailAccount) {

        EntityModel<MailAccount> entityModel = assembler.toModel(repository.save(newMailAccount));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
}
