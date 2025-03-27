package io.github.ossdbh.format.dto.test;

import io.github.ossdbh.format.annotation.FwfAttribute;
import io.github.ossdbh.format.annotation.FwfInstance;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FwfInstance
public class MixedDTO {
    @FwfAttribute(format = "%3.3s")
    private String str1;

    @FwfAttribute(format = "%2.2s")
    private Integer int1;
}
