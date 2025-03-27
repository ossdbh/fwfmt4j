package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;

@FwfInstance
public class NonLombokDTO3 extends NonLombokDTO2 {

    @FwfAttribute(format = "%4.4s")
    private String str3;
    @FwfAttribute(format = "%3d")
    private Integer int3;

    public NonLombokDTO3() {
        super();
    }

    public NonLombokDTO3(String str2, Integer int1, String str3, Integer int3) {
        super(str2, int1);
        this.str3 = str3;
        this.int3 = int3;
    }

    public void SET_str3(String s) {
        this.str3 = s;
    }
    public String GET_str3() {
        return this.str3;
    }

    public void SET_int3(int i) {
        this.int3 = i;
    }
    public Integer GET_int3() {
        return this.int3;
    }
}
