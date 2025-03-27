package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Prnt {
    @FwfAttribute(format = "%-5.5s")
    private String parentName = "";

    @FwfAttribute(format = "%4d")
    private int parentAge = 0;
}
