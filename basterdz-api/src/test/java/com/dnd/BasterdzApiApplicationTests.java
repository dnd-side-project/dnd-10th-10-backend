package com.dnd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"encryptor.key=testPassword"})
public class BasterdzApiApplicationTests {

    @Test
    void contextLoads() {

    }
}
