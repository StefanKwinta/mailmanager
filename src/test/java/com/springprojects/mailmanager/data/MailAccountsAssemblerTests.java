package com.springprojects.mailmanager.data;


import com.springprojects.mailmanager.model.MailAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class MailAccountsAssemblerTests {
    @Autowired
    MailAccountsRepository mailAccountsRepository;
    @Autowired
    MailAccountModelAssembler mailAccountModelAssembler;

    @Test
    public void notNullTest() throws Exception {
        assertNotNull(mailAccountModelAssembler);
    }

    @Test
    public void modelNotNullTest() throws Exception {
        assertNotNull(mailAccountModelAssembler);
        MailAccount mailAccount = new MailAccount("testlogin","testpassword");
        EntityModel<MailAccount> entityModel = mailAccountModelAssembler.toModel(mailAccount);
        assertNotNull(entityModel);
    }

    @Test
    public void modelReturnTest() throws Exception {
        assertNotNull(mailAccountModelAssembler);
        MailAccount mailAccount = new MailAccount("testlogin","testpassword");
        EntityModel<MailAccount> entityModel = mailAccountModelAssembler.toModel(mailAccount);
        assert (entityModel.getContent().equals(mailAccount));
    }

}
