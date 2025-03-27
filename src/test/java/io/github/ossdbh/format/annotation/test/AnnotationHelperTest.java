package io.github.ossdbh.format.annotation.test;

import io.github.ossdbh.format.dto.test.Child1DTO;
import io.github.ossdbh.format.helper.AnnotationHelper;
import org.junit.Assert;
import org.junit.Test;

public class AnnotationHelperTest {

    @Test
    public void testIsFwfInstance() {
        Assert.assertTrue(AnnotationHelper.isFwfInstance(Child1DTO.class));
    }
}
