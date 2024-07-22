package org.itstep.example;

import org.springframework.stereotype.Component;

@Component
public class FullName {
    public String composeFullName (String firstName, String secondName){
        return firstName+" "+secondName;
    }
}
