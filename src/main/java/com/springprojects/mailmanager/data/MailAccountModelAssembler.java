package com.springprojects.mailmanager.data;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.springprojects.mailmanager.controller.LoginController;
import com.springprojects.mailmanager.serialization.MailAccount;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public
class MailAccountModelAssembler implements RepresentationModelAssembler<MailAccount, EntityModel<MailAccount>> {

    @Override
    public EntityModel<MailAccount> toModel(MailAccount mailAccount) {

        return EntityModel.of(mailAccount,
                linkTo(methodOn(LoginController.class).getOne(mailAccount.getLogin())).withSelfRel(),
                linkTo(methodOn(LoginController.class).getAll()).withRel("mailAccounts"));
    }
}
