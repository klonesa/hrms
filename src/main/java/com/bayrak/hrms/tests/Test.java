package com.bayrak.hrms.tests;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Map;

@Configuration
public class Test {

    final
    Cloudinary cloudinary;

    public Test(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {


            File file = new File("C:\\Users\\bedirhan\\Pictures\\1.jpg");

//            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//            System.out.println(uploadResult);

//            System.out.println(uploadResult.get("secure_url"));

        };
    }

}
