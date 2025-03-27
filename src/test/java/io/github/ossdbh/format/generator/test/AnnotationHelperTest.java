package io.github.ossdbh.format.generator.test;

import io.github.ossdbh.format.dto.test.Child1DTO;
import io.github.ossdbh.format.dto.test.Child2DTO;
import io.github.ossdbh.format.dto.test.ChildOfChild11DTO;
import io.github.ossdbh.format.dto.test.ParentDTO;
import io.github.ossdbh.format.helper.AnnotationHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Formatter;

public class AnnotationHelperTest {

    private ParentDTO parentDTO;

    @Before
    public void setup() {
        this.parentDTO = ParentDTO.builder()
                .parentDTOStr1("hello parentDTOStr1")
                .child1DTO(Child1DTO.builder()
                        .child1DTOStr1("hi childDTOStr1")
                        .child1DTOInt1(10)
                        .childOfChild11DTO(ChildOfChild11DTO.builder()
                                .childOfChild1DTOStr1("hi hichildOfChild1DTOStr1")
                                .childOfChild1DTOInt1(20)
                                .build())
                        .build())
                .child2DTO(Child2DTO.builder()
                        .child2DTOStr1("hi child2DTOStr1")
                        .child2DTOInt1(30)
                        .build())
                .parentDTOInt1(40)
                .build();
    }

    @Test
    public void testInferGetter() {
        String attribute = "parentDTOStr1";
        System.out.println(AnnotationHelper.inferGetter(attribute));

        Class c = this.parentDTO.getClass();
        try {
            Method getter = c.getMethod(AnnotationHelper.inferGetter(attribute));
            Object value = getter.invoke(this.parentDTO);
            // System.out.println("Method " + attribute + " returned: " + value.toString());
            Assert.assertTrue(value.toString().equals("hello parentDTOStr1"));
        } catch (NoSuchMethodException e) {
            Assert.assertTrue(false);
        } catch (InvocationTargetException e) {
            Assert.assertTrue(false);
        } catch (IllegalAccessException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSomething() {
        String s1 = "hello";
        String s2 = "abcdefghijklmnopqrstuvwxyz";
        String s3 = "well";
        Formatter f = new Formatter();
        Assert.assertTrue("hello,abcdefghijkl,  well".equals(f.format("%-5s,%-12.12s,%6s", s1,s2,s3).toString()));
    }
}
