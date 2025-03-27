package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;
import lombok.Builder;
import lombok.Getter;

@Getter
@FwfInstance
public class Teen extends Chld {
    @FwfAttribute(format = "%10.10s")
    private String address;
    @FwfAttribute(format = "%03d")
    private Integer fld3;
    @FwfAttribute(format = "%4.4s")
    private String fld4;
    @FwfAttribute(format="%03d")
    private Integer fld5;

    @Builder(builderMethodName = "teenBuilder")
    public Teen(String parentName, int parentAge, String childName, String skipThis, int childAge, String address, Integer fld3, String fld4, Integer fld5) {
        super(parentName, parentAge, childName, skipThis, childAge);
        this.address = address;
        this.fld3 = fld3;
        this.fld4 = fld4;
        this.fld5 = fld5;
    }
}
