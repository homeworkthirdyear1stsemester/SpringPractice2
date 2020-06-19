package com.kkh.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        try {
            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            // read JSON file and map/convert to JAVA POJO:
            // data.smaple-list.json
            Student student = mapper.readValue(new File("data/sample-lite.json"), Student.class);

            // print first name and last name
            System.out.println("First name = " + student.getFirstName());
            System.out.println("Last name = " + student.getLastName());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
