package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;

@FwfInstance
public class NestedNonLombokDTO {

    @FwfAttribute(format = "%4.4s")
    private String str_nested;
    @FwfAttribute(format = "%3d")
    private Integer int_nested;

    public void SET_str_nested(String s) {
        this.str_nested = s;
    }
    public String GET_str_nested() {
        return this.str_nested;
    }

    public void SET_int_nested(int i) {
        this.int_nested = i;
    }
    public Integer GET_int_nested() {
        return this.int_nested;
    }
}
