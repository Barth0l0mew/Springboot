package org.itstep.xml_scan;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"daos.xml"});
    //вывести все экземпляры bens
        for (String bean:applicationContext.getBeanDefinitionNames()){

            System.out.println( bean);
        }
        applicationContext.getBean(Dog.class);
        applicationContext.getBean(Dog.class).sound();
       // applicationContext.getBean(Room.class).sound();
        //содержиться в контексте bean c названием cat

        System.out.println( applicationContext.containsBean("catDao"));
    }
} 