package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;
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
public class Child2DTO {
    @FwfAttribute(format = "%10s")
    private String child2DTOStr1;

    @FwfAttribute(format = "%2d")
    private Integer child2DTOInt1;
}
