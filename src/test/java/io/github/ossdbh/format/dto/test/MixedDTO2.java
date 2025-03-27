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
public class MixedDTO2 {
    @FwfAttribute(format = "%3.3s")
    private String str1;

    // Format String uses integer but sources defaultValue as String
    // Ultimately formatter would have to format a String as an integer if
    // the attribute is set to null by the user.
    // Java's Formatter would fail to do so
    @FwfAttribute(format = "%3d")
    private Integer int1;
}
