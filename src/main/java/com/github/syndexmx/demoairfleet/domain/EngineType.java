package com.github.syndexmx.demoairfleet.domain;

import lombok.Getter;

@Getter
public enum EngineType {
    DEFAULTVALUE ("Not Defined"),
    PRATTNWHITNEY_JT8D_1 ("Pratt&Whitney JT8D-1"),
    ROLLSROYCE_TRENT_877 ("Rolls-Royce Trent 877"),
    CFM_LEAP_1B ("CFM LEAP-1B"),
    CFM56_3 ("CFM56-3"),
    PRATTNWHITNEY_PW1000G ("Pratt&Whitney PW1000G"),
    CFM56_7 ("CFM56-7"),
    ROLLSROYCE_TRENT_892 ("Rolls-Royce Trent 892"),
    GE_GE90 ("General Electric GE90");

    private String typeName;

    EngineType(String typeName) {
        this.typeName = typeName;
    }
}
