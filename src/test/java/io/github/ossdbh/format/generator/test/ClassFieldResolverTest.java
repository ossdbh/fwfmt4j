package io.github.ossdbh.format.generator.test;

import io.github.ossdbh.format.dto.test.ParentDTO;
import io.github.ossdbh.format.generator.ClassFieldResolver;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClassFieldResolverTest {

    @Test
    public void test() {
        ClassFieldResolver classFieldResolver = new ClassFieldResolver();
        List<Field> declaredFields = new ArrayList<>();

        // Build a list of declared fields that the class passed in as param has declared
        classFieldResolver.getClassFields(ParentDTO.class, new Stack<>(), new Stack<String>(), declaredFields, null);

        declaredFields.stream().forEach(e -> {
            System.out.println("Field: " + e.getName());
        });
    }
}
