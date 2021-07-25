package com.bayrak.hrms.tests;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class Test {

    @Autowired
    Cloudinary cloudinary;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {


            File file = new File("C:\\Users\\bedirhan\\Pictures\\1.jpg");

//            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//
//            System.out.println(uploadResult.get("secure_url"));

        };
    }

}
