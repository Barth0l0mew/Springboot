package org.itstep.app2;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// добавление в начале пути
@RequestMapping("/persons")

public class PersonController {
    private List<Person> persons = new ArrayList<>();
    {
        persons.add(new Person(1L, "ivan", "ivanov"));
        persons.add(new Person(2L, "ivan2", "ivanov2"));
        persons.add(new Person(3L, "ivan3", "ivanov3"));

    }
    //Только get запросы
    @GetMapping
    //или @GetMapping({"","/"})
    public List<Person> getAllPerson (){
        return persons;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getAllPerson (@PathVariable("id") Long id){
        Person person = persons.stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (person == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Person> createPerson (@RequestBody Person person){
       persons.add(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson (@PathVariable("id") Long id, @RequestBody Person person){
        Person personOld = persons.stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (personOld == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        personOld.setName(person.getName());
        personOld.setSurname(person.getSurname());
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson (@PathVariable("id") Long id){
        Person person = persons.stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (person == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       persons.remove(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

/*
  fetch('
http://localhost:8080/users
', {
    method: 'GET'
  })
  .then(response=>response.json());

fetch('http://localhost:8080/persons', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({"id":"5L","name": "Jane", "surname":"janesurname"})
          });

  fetch('
http://localhost:8080/persons/1
', {
    method: 'PUT',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({"name":"noname"})
  });

  fetch('
http://localhost:8080/persons/1
', {
    method: 'DELETE'
  });
 */
