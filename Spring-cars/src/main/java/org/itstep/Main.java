package org.itstep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itstep.firm.FirmJson;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        FirmJson[] firms = null;
        try {
            firms = mapper.readValue(new File("C:/Users/gk/Springboot/Spring-cars/src/main/resources/cars.json"), FirmJson[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(firms[0]);
    }
}
