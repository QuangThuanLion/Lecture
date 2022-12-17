package com.boot.project.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OutPutStudent {
    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(30);
        student.setName("Quang Thuan");

        String directoryWriteFile = "/Users/admin/Documents/DATA/files/student.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(directoryWriteFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
