package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@FwfInstance
public class Chld extends Prnt {
    @FwfAttribute(format = "%-3.3s")
    private String childName;

    @FwfAttribute(format = "%3s")
    private String skipThis = "";

    @FwfAttribute(format = "%3d")
    private int childAge;

    @Builder(builderMethodName = "chldBuilder")
    public Chld(String parentName, int parentAge, String childName, String skipThis, int childAge) {
        super(parentName, parentAge);
        this.childName = childName;
        this.skipThis = skipThis;
        this.childAge = childAge;
    }
}
