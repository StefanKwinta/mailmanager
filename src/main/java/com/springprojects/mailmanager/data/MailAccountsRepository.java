package com.springprojects.mailmanager.data;

import com.springprojects.mailmanager.model.MailAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailAccountsRepository extends JpaRepository<MailAccount, String> {

}