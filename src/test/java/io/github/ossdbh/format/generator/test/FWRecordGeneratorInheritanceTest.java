package io.github.ossdbh.format.generator.test;

import io.github.ossdbh.format.dto.test.Chld;
import io.github.ossdbh.format.dto.test.Teen;
import io.github.ossdbh.format.generator.ClassFieldResolver;
import io.github.ossdbh.format.generator.FwFmt4JGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

public class FWRecordGeneratorInheritanceTest {
    private Chld chld;
    private Teen t;

    @Before
    public void setup() {
        this.chld = Chld.chldBuilder()
                .parentName("Andrea")
                .parentAge(38)
                .childName("Emma")
                .skipThis("Well dont skip this")
                .childAge(6)
                .build();

        this.t = Teen.teenBuilder()
                .parentName("Andrea")
                .parentAge(38)
                .childName("Emma")
                .skipThis("well skip this")
                .childAge(6)
                .address("ashda skjdfg skjd skjdhfksdh kjgs")
                .fld3(1000000)
                .fld4("Hey There lets see what we got")
                .fld5(5000000)
                .build();
    }

    @Test
    public void test() {
        //System.out.println(this.chld.toString());
        String format = FwFmt4JGenerator.generateFW(this.chld);
        // System.out.println("Format: " + format);
        Assert.assertTrue("Andre  38EmmWell dont skip this  6".equals(format));
    }

    @Test
    public void test2() {
        // Run with teen instance that has 3 levels of inheritance
        String format = FwFmt4JGenerator.generateFW(this.t);
        // System.out.println("Format: " + format);
        Assert.assertTrue("Andre  38Emmwell skip this  6ashda skjd1000000Hey 5000000".equals(format));
    }

    @Test
    public void test3() {
        List<String> l = new ArrayList<>();
        l.add("Teen.Chld.skipThis"); l.add("Teen.fld3");

        // In case of inheritance you get a flat attribute space
        // Always use the classname of the instance that you are trying to fixed width format
        String format = FwFmt4JGenerator.generateFW(this.t, l);
        // System.out.println("Format: " + format);

        // This should not print "well skip this and 1000000"
        Assert.assertTrue("Andre  38Emm  6ashda skjdHey 5000000".equals(format));
    }

    @Test
    public void test4() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Teen.Chld.skipThis", 1);
        map.put("Teen.fld5", 1);
        List<Field> classFieldsList = new ArrayList<>();
        ClassFieldResolver classFieldResolver = new ClassFieldResolver();
        classFieldResolver.getClassFields(Teen.class, new Stack<Class>(), new Stack<String>(), classFieldsList, map);
    }

}
