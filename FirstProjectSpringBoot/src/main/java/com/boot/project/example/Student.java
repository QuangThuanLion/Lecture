package com.boot.project.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

public class Student implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int age;

    @JsonIgnore
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Student)) {
            return false;
        }

        Student student = (Student) obj;
        if (this.age == student.age && this.name == student.name) {
            return true;
        }

        return false;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
