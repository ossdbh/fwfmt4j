package io.github.ossdbh.format.generator.test;

import io.github.ossdbh.format.dto.test.NonLombokDTO3;
import io.github.ossdbh.format.generator.FwFmt4JGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomGetterInheritanceDTOTest {

    private NonLombokDTO3 nonLombokDTO3;

    @Before
    public void setup() {
        this.nonLombokDTO3 = new NonLombokDTO3("BaseClass", 100, "DerivedClass", 1000);
    }

    @Test
    public void test1() {

        // The function expects you to pass in a String translation rule
        // that would take the attribute name and then apply String translations
        // to generate a getter method string
        // In this example 's' is the attribute value and "GET_" + s is the logical
        // getter method name for each attribute
        String format = FwFmt4JGenerator.generateFW(this.nonLombokDTO3, s -> "GET_" + s);
        Assert.assertTrue("Base100Deri1000".equals(format));
    }
}
