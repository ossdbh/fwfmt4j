package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;

@FwfInstance
public class NonLombokDTO2 {

    @FwfAttribute(format = "%4.4s")
    protected String str2;
    @FwfAttribute(format = "%3d")
    protected Integer int2;

    public NonLombokDTO2() {}

    public NonLombokDTO2(String str2, Integer int2) {
        this.str2 = str2;
        this.int2 = int2;
    }

    public void SET_str2(String s) {
        this.str2 = s;
    }
    public String GET_str2() {
        return this.str2;
    }

    public void SET_int2(int i) {
        this.int2 = i;
    }
    public Integer GET_int2() {
        return this.int2;
    }
}
