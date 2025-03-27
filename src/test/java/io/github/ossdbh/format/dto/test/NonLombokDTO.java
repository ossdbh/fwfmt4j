package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;
import io.github.ossdbh.format.annotation.FwfNestedAttribute;

@FwfInstance
public class NonLombokDTO {

    @FwfAttribute(format = "%4.4s")
    private String str1;
    @FwfNestedAttribute
    private NestedNonLombokDTO nestedNonLombokDTO;
    @FwfAttribute(format = "%3d")
    private Integer int1;

    public void SET_str1(String s) {
        this.str1 = s;
    }
    public String GET_str1() {
        return this.str1;
    }

    public void SET_int1(int i) {
        this.int1 = i;
    }
    public Integer GET_int1() {
        return this.int1;
    }

    public void SET_nestedNonLombokDTO(NestedNonLombokDTO nestedNonLombokDTO) {
        this.nestedNonLombokDTO = nestedNonLombokDTO;
    }
    public NestedNonLombokDTO GET_nestedNonLombokDTO() {
        return this.nestedNonLombokDTO;
    }
}
