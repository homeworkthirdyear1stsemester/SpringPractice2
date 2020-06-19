package com.kkh.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class FullDriver {
    public static void main(String[] args) {
        try {
            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            // read JSON file and map/convert to JAVA POJO:
            // data.smaple-full.json
            FullStudent student = mapper.readValue(new File("data/sample-full.json"), FullStudent.class);

            // print first name and last name
            System.out.println("First name = " + student.getFirstName());
            System.out.println("Last name = " + student.getLastName());

            // print out address : street and city
            Address address = student.getAddress();
            System.out.println("street = " + address.getStreet());
            System.out.println("city = " + address.getCity());

            // print out languages
            System.out.println(student.getLanguages());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
