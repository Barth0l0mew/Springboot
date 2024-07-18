package org.itstep.scan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@SprinBootApplication включает  в себя : Configuration, ComponentScan
@ComponentScan
public class Application {
    private class ExampleBean{};
    @Bean
    public ExampleBean exampleBean(){
        return new ExampleBean();
    }
    private static ApplicationContext applicationContext ;
    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(Application.class);
    //вывести все экземпляры bens
        for (String bean:applicationContext.getBeanDefinitionNames()){

            System.out.println( bean);
        }
        applicationContext.getBean(Dog.class);
        applicationContext.getBean(Room.class).sound();
        //содержиться в контексте bean c названием cat

        System.out.println( applicationContext.containsBean("cat"));
    }
} 