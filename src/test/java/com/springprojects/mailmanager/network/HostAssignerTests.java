package com.springprojects.mailmanager.network;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class HostAssignerTests {

    @Test
    public void gmailSMTPHost(){
        assertThat(HostAssigner.assignSMTPHost("ex@gmail.com")).isEqualTo("smtp.gmail.com");
    }
    @Test
    public void greenmailSMTPHost(){
        assertThat(HostAssigner.assignSMTPHost("ex@localhost")).isEqualTo("localhost");
    }
    @Test
    public void gmailPOP3Host(){
        assertThat(HostAssigner.assignPOP3Host("ex@gmail.com")).isEqualTo("pop.gmail.com");
    }
}

