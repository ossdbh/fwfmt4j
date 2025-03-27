package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;
import io.github.ossdbh.format.annotation.FwfNestedAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FwfInstance
public class Child1DTO {
    @FwfAttribute(format = "%-10s")
    private String child1DTOStr1;

    @FwfNestedAttribute
    private ChildOfChild11DTO childOfChild11DTO;

    @FwfAttribute(format = "%2d")
    private Integer child1DTOInt1;
}
