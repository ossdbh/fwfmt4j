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
public class ChildOfChild11DTO {
    @FwfAttribute(format = "%10s")
    private String childOfChild1DTOStr1;

    @FwfAttribute(format = "%2s")
    private Integer childOfChild1DTOInt1;
}
