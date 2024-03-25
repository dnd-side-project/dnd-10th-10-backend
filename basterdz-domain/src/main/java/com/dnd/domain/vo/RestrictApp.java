package com.dnd.domain.vo;

import java.util.Arrays;
import java.util.Objects;

import com.dnd.common.exception.BadRequestException;
import com.dnd.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RestrictApp {

    INSTAGRAM("instagram"),
    YOUTUBE("youtube"),
    TIKTOK("tiktok"),
    NETFLIX("netflix");

    private final String appName;

    public static RestrictApp from(String appName) {
        return Arrays.stream(RestrictApp.values())
                .filter(restrictApp -> Objects.equals(restrictApp.getAppName(), appName))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(ErrorCode.UNSUPPORTED_RESTRICT_APP));
    }

    public static boolean contains(String appName) {
        return Arrays.stream(values())
            .anyMatch(app -> app.name().equalsIgnoreCase(appName));
    }
}
