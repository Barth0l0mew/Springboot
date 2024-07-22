package org.itstep.traffic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application implements CommandLineRunner {
    @Autowired
    public TrafficLight trafficLight;
    @Autowired
    public Driver driver;
    public static void main(String[] args) {

        SpringApplication.run(org.itstep.traffic.Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trafficLight.switchRed();
        trafficLight.switchYellow();
        trafficLight.switchGreen();

    }
}
