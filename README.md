# fwfmt4j

Fixed Width format becomes tedious to handle when the number of attributes in a BEAN/POJO/DTO
is too large and/or when the BEAN/POJO/DTO is nested. 
Imagine you need to print 50 plus attributes in fixed width format. You would have to create a
fixed width format String that is 150 characaters at the least long in the following way
```
String fmt = "%-14s%8s%-9.9s%.....%20s%12s"
String.format(fmt, <call 50 plus getters>);
```
If the number of attributes is larger or if your BEAN/POJO/DTO is nested things could get more daunting
Moreover fixing a format String somewhere in the middle of 50 attributes would mean you would have 
to count manually into the format String and then fix it. This generally is time consuming. 

This framework/API is taking an attempt to make this process simpler by using an annotation driven solution approach. 
Users can annotate their BEAN/POJO/DTOs indicating which fields should be considered for fixed-width generation and which fields should be ignored.
The framework/API is very tiny and only a 100 lines of source code generates format strings from an BEAN/POJO/DTO.


### Build workspace
- git clone this repository, the current active branch is develop
  - git clone -b develop https://github.com/ossdbh/fwfmt4j.git
  - you would need a personal access token to checkout the source code
  - else you can download a zip version of the workspace
- Run the following command to build and install the framework/API on your local
  - mvn clean install

The framework/API installs with the following maven groupId, artifactId and version
```
    <dependency>
        <groupId>io.github.ossdbh</groupId>
        <artifactId>fwfmt4j</artifactId>
        <version>1.0</version>
    </dependency>
```

### Annotations
The framework/API allows the user to annotate classes and fields that the user wants printed using
fixed width format strings.

The framework/API currently supports the following annotations
- @FwfInstance
  - This is a class level annotation and tells the framework/API that the class has FwfAttribute annotated attributes
- @FwfAttribute
  - This is an attribute level annotation and tells the framework/API to process the attribute, discover its getter method and then to invoke the method
- @FwfNestedAttribute
  - This is an attribute level annotation and tells the framework/API to process this attribute as a nested BEAN/POJO/DTO. The framework traverses into that attribute and recurses.

### Sample usage
To use the framework/API kindly add the following dependency into your source pom 

```
         <dependency>
            <groupId>io.github.ossdbh</groupId>
            <artifactId>fwfmt4j</artifactId>
            <version>1.0-java8</version>
        </dependency>
```

Here is a sample nested BEAN/POJO/DTO configured using fwfmt4j annotations

#### Parent Child BEAN/POJO/DTOs nested

ParentDTO that has a nested ChildDTO
```
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FwfInstance
public class ParentDTO {
    @FwfAttribute(format = "%-3.3s")
    private String parentDTOStr1;

    @FwfNestedAttribute
    private Child1DTO child1DTO;

    @FwfAttribute(format = "%4d")
    private Integer parentDTOInt1;

    @FwfAttribute(format = "%-4s")
    private Integer parentDTOInt2;

    @FwfAttribute(format = "%04d")
    private Integer parentDTOInt3;
}
```
Child BEAN/POJO/DTO that has no other nested attributes
```
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FwfInstance
public class Child1DTO {
    @FwfAttribute(format = "%-10s")
    private String child1DTOStr1;

    @FwfNestedAttribute
    private ChildOfChild11DTO childOfChild11DTO;

    @FwfAttribute(format = "%4s")
    private Integer child1DTOInt1;
}
```
Lets create a new ParentDTO instance
```
    this.parentDTO = ParentDTO.builder()
         .parentDTOStr1("l")
          .child1DTO(Child1DTO.builder()
                  .child1DTOStr1("level2")                      
                  .child1DTOInt1(10)
                  .build())
          .parentDTOInt1(40)
          .parentDTOInt2(50)
          .parentDTOInt3(123)
          .build();
```
The framework/API can now be invoked in the following way 
```
String fwFormattedRecord = FWRecordGenerator.generateFW(this.parentDTO);
System.out.println(fwFormattedRecord);
```
This should print the following string to console
```
l  level2        level32010    level230   4050  0123
```

##### Skipping fields in nested BEAN/POJO/DTOs
There could be times where for a particular field you would want the field to be fixed width
printed for reportA while you would not want the field printed for reportB

The framework/API supports a rudimentary xPath style syntax to skip fields
For example in the BEAN/POJO/DTO structure above if you want to skip fields child1DTOInt1 in Child1DTO class
and skip field parentDTOInt2 in ParentDTO then you define and pass these fields as Strings through a List
into the framework/API call.
The notation to follow is one of the following depending on the level of nesting
- Class.NestedClass.Attribute
- Class.NestedClass.NestedClass.Attribute
- Class.Attribute

```
String fwFormattedRecord = FWRecordGenerator.generateFW(this.parentDTO, 
                           List.of("ParentDTO.parentDTOInt2", "ParentDTO.Child1DTO.child1DTOInt1");
System.out.println(fwFormattedRecord);
```

#### Specifying default values
Default values can be specified using the following syntax

```
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FwfInstance
public class MixedDTO {
    @FwfAttribute(format = "%3.3s", defaultValue="1234Get")
    private String str1;

    @FwfAttribute(format = "%2.2s", defaultValue = "1000000")
    private Integer int1;
}
```

The framework/API for now uses apache commons StringUtils and for any String attribute
whose value is null the API runs the following translation/substituion internally

```
formatter.format(e.getFmt(), StringUtils.defaultIfBlank(data, ""));
```

For non-string attributes the framework/API ignores defaultValues as there could potentially be
type mismatches across reading defaultValue as String and the data type of the attribute
This could lead to illegal formatting exception.

Kindly refer to the following unit test that explains various scenarios as far as attributes
and default value is concerned and what is supported and what is not

```
src/test/java/io/github/ossdbh/format/dto/test/MixedDTO.java
```

At a future date the framework/API may be extended to address the gaps mentioned
in the unit test in a more holistic manner.

#### Parent Child BEAN/POJO/DTOs through inheritance

The framework/API also supports arbitrary levels of inheritance
If you are using lombok builders then you need to ensure that 
the inherited lombok builders / constructors have been wired correctly.

Here is an example
Class Prnt base class
```
@Getter
@AllArgsConstructor
@ToString
public class Prnt {
    @FwfAttribute(format = "%-5.5s")
    private String parentName = "";

    @FwfAttribute(format = "%4d")
    private int parentAge = 0;
}
```
Class chld inherits parent
```
@Getter
@ToString
@FwfInstance
public class Chld extends Prnt {
    @FwfAttribute(format = "%-3.3s")
    private String childName;

    @FwfAttribute(format = "%3d")
    private int childAge;

    @Builder(builderMethodName = "chldBuilder")
    public Chld(String parentName, int parentAge, String childName, int childAge) {
        super(parentName, parentAge);
        this.childName = childName;
        this.childAge = childAge;
    }
}
```
Class Teen inherits Chld
```
@Getter
@FwfInstance
public class Teen extends Chld {
    @FwfAttribute(format = "%10.10s")
    private String address;

    @Builder(builderMethodName = "teenBuilder")
    public Teen(String parentName, int parentAge, String childName, int childAge, String address) {
        super(parentName, parentAge, childName, childAge);
        this.address = address;
    }
}
```
Create a new instance of Teen
```
           Teen t = Teen.teenBuilder()
                .parentName("Andrea")
                .parentAge(38)
                .childName("Emma")
                .childAge(6)
                .address("ashda skjdfg skjd skjdhfksdh kjgs")
                .build();
```
Now invoke the framework/API in the following way
```
String fwFormattedRecord = FWRecordGenerator.generateFW(t);
System.out.println(fwFormattedRecord);
```
This should print the following format String
```
Andre  38Emm  6ashda skjd
```

##### Skipping fields in inherited BEAN/POJO/DTOs
Incase of BEAN/POJO/DTOs that follow an inheritance tree skipping fields follows the same convention. 
Inheritance actually does not create instances of the base class type when the derived class instance is instantiated. 
All attributes upto the base  class actually exist in a flat hierarchy effectively belonging to the derived class type.
Even then to make things simpler the same dotted notation to specify a skipped field in a derived or a base class is used
by the framework/API.

Lets say in the example above you want to skip the fields skipThis in the Chld class and fld3 in the Teen class.
To do so use the following to invoke the framework/API

```
String fwFormattedRecord = FWRecordGenerator.generateFW(t, List.of("Teen.Chld.skipThis", "Teen.fld3"));
System.out.println(fwFormattedRecord);
```

### Exception handling
The framework/API throws FwFmt4jException runtime exception after internally catching the following exceptions 
while discovering and invoking reflective attributes and methods
- NoSuchMethodException
- InvocationTargetException
- IllegalAccessException

Your API call should be wrapped to either catch or re-throw FwFmt4jException

### Non Lombok BEAN/POJO/DTO;
The framework/API also supports non lombok BEAN/POJO/DTOs that are flat, nested as well as inherited
Kindly refer to the following unit tests to check on example usage

```
src/test/java/io/github/ossdbh/format/generator/test/CustomGetterDTOTest.java
src/test/java/io/github/ossdbh/format/generator/test/CustomGetterInheritanceDTOTest.java
```

### Future Work
- Add support for default values
- Add support for value translators
