package com.springprojects.mailmanager.data;


import com.springprojects.mailmanager.model.MailAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailAccountsRepositoryTests {
    @Autowired
    MailAccountsRepository mailAccountsRepository;
    @Autowired
    MailAccountModelAssembler mailAccountModelAssembler;

    @Test
    public void notNullTest() throws Exception {
        assertNotNull(mailAccountsRepository);
    }

    @Test
    public void addingToRepositoryTest() throws Exception {
        MailAccount newMailAccount1 = new MailAccount("login1","password1");
        MailAccount newMailAccount2 = new MailAccount("login2","password2");
        MailAccount newMailAccount3 = new MailAccount("login3","password3");
        mailAccountsRepository.save(newMailAccount1);
        mailAccountsRepository.save(newMailAccount2);
        mailAccountsRepository.save(newMailAccount3);
        List<MailAccount> mailAccounts = mailAccountsRepository.findAll();
        assert(mailAccounts.toArray().length == 3);
        assert(mailAccounts.toArray()[0].equals(newMailAccount1));
        assert(mailAccounts.toArray()[1].equals(newMailAccount2));
        assert(mailAccounts.toArray()[2].equals(newMailAccount3));
        MailAccount newMailAccount4 = new MailAccount("login4","password4");
        mailAccountsRepository.save(newMailAccount4);
        mailAccounts = mailAccountsRepository.findAll();
        assert(mailAccounts.toArray().length == 4);
        assert(mailAccounts.toArray()[0].equals(newMailAccount1));
        assert(mailAccounts.toArray()[1].equals(newMailAccount2));
        assert(mailAccounts.toArray()[2].equals(newMailAccount3));
        assert(mailAccounts.toArray()[3].equals(newMailAccount4));
    }
}
