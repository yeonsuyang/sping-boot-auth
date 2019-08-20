package com.example.auth;


import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RequiredArgsConstructor@RunWith(SpringRunner.class)
@SpringBootTest
public class PwEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
        System.out.printf("secretId : %s\n", passwordEncoder.encode("ys"));
    }
}
