package org.itstep.example;

import org.itstep.example.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class SomeServise {
    @LogExecutionTime
    public void SomeMethod () throws InterruptedException {
        Thread.sleep(1000);
    }
}
