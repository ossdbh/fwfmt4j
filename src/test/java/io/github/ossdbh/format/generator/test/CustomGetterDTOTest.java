package io.github.ossdbh.format.generator.test;

import io.github.ossdbh.format.dto.test.NestedNonLombokDTO;
import io.github.ossdbh.format.dto.test.NonLombokDTO;
import io.github.ossdbh.format.dto.test.NonLombokDTO1;
import io.github.ossdbh.format.generator.FwFmt4JGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomGetterDTOTest {

    private NonLombokDTO1 nonLombokDTO1;
    private NonLombokDTO nonLombokDTO;

    @Before
    public void setup() {
        this.nonLombokDTO1 = new NonLombokDTO1();
        this.nonLombokDTO = new NonLombokDTO();
    }

    @Test
    public void test1() {
        this.nonLombokDTO1.SET_str1("abcdefgh");
        this.nonLombokDTO1.SET_int1(10000);

        // The function expects you to pass in a String translation rule
        // that would take the attribute name and then apply String translations
        // to generate a getter method string
        // In this example 's' is the attribute value and "GET_" + s is the logical
        // getter method name for each attribute
        String format = FwFmt4JGenerator.generateFW(this.nonLombokDTO1, s -> "GET_" + s);
        System.out.println("Format: " + format);
        Assert.assertTrue("abcd10000".equals(format));
    }

    @Test
    public void test2() {
        this.nonLombokDTO.SET_str1("pqrstuvw");
        this.nonLombokDTO.SET_int1(10000000);

        String format = FwFmt4JGenerator.generateFW(this.nonLombokDTO, s -> "GET_" + s);
        // System.out.println("Format: " + format);
        Assert.assertTrue("pqrs10000000".equals(format));
    }

    @Test
    public void test3() {
        this.nonLombokDTO.SET_str1("pqrstuvw");

        NestedNonLombokDTO nestedNonLombokDTO = new NestedNonLombokDTO();
        nestedNonLombokDTO.SET_str_nested("whatisthis");
        nestedNonLombokDTO.SET_int_nested(1000);
        this.nonLombokDTO.SET_nestedNonLombokDTO(nestedNonLombokDTO);

        this.nonLombokDTO.SET_int1(10000000);

        String format = FwFmt4JGenerator.generateFW(this.nonLombokDTO, s -> "GET_" + s);
        // System.out.println("Format: " + format);
        Assert.assertTrue("pqrswhat100010000000".equals(format));
    }
}
