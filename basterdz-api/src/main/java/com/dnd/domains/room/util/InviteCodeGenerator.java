package com.dnd.domains.room.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InviteCodeGenerator implements InviteCodeUtil{

    @Override
    public String generate() {
        return UUID.randomUUID().toString()
                .replace("-", "").substring(0, 8);
    }
}
