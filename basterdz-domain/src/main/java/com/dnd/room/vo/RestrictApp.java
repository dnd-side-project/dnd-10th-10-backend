package com.dnd.room.vo;

import lombok.Getter;

@Getter
public enum RestrictApp {
    INSTAGRAM("instagram"),
    YOUTUBE("youtube"),
    TIKTOK("tiktok"),
    NETFLIX("netflix")
    ;

    private final String appName;

    RestrictApp(String appName) {
        this.appName = appName;
    }
}
