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
public class ParentDTO {
    @FwfAttribute(format = "%-3.3s")
    private String parentDTOStr1;

    @FwfNestedAttribute
    private Child1DTO child1DTO;

    @FwfNestedAttribute
    private Child2DTO child2DTO;

    @FwfAttribute(format = "%5d")
    private Integer parentDTOInt1;

    @FwfAttribute(format = "%-4d")
    private Integer parentDTOInt2;

    @FwfAttribute(format = "%04d")
    private Integer parentDTOInt3;
}
