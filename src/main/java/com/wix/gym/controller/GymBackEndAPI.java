package com.wix.gym.controller;


import com.wix.gym.model.Class;
import com.wix.gym.model.Customer;
import com.wix.gym.repository.ClassRepository;
import com.wix.gym.repository.CustomerRepository;
import com.wix.gym.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GymBackEndAPI {

    @Autowired
    ClassRepository classRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmailService emailService;


    @GetMapping("/classes")
    public List<Class> getAllClasses(){
        return classRepository.findAll();
    }

    @GetMapping("/available")
    public Map<Integer,Class> getAvailableClasses() {
        HashMap<Integer,Class> result = new HashMap<>();
        List<Class> all = classRepository.findAll();
        for (Class m_class : all){
            int diff = m_class.getMaxParticNum() - m_class.getBookers().size();
            if (diff > 0)
                result.put(diff,m_class);
        }
        return result;
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hi there";
    }

    @PostMapping("/book")
    public ResponseEntity<Boolean> bookClass( @Valid @RequestBody HashMap body){
        Customer customer;
        String className;
        if (!(body.containsKey("id") && body.containsKey("fullName") && body.containsKey("name") && body.containsKey("email")))
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
        try {
            Long id = (Long)body.get("id");
            customer = new Customer(id,(String)body.get("fullName"),body.get("email").toString());
            className = body.get("name").toString();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Class> founds = classRepository.findByName(className);
        Set<Class> available = new HashSet<>(getAvailableClasses().values());
        for (Class found : founds){
            if(available.contains(found)){
                found.book(customer);
                customerRepository.save(customer);
                classRepository.save(found);
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/wait")
    public ResponseEntity<Boolean> waitClass( @Valid @RequestBody HashMap body){
        Customer customer;
        String className;
        if (!(body.containsKey("id") && body.containsKey("fullName") && body.containsKey("name") && body.containsKey("email")))
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
        try {
            Long id = (Long)body.get("id");
            customer = new Customer(id,(String)body.get("fullName"),body.get("email").toString());
            className = body.get("name").toString();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Class> founds = classRepository.findByName(className);
        Set<Class> available = new HashSet<>(getAvailableClasses().values());
        List<Class> fullClasses = founds.stream().filter(m_class -> !available.contains(m_class)).collect(Collectors.toList());
        if (fullClasses.size() > 0){
            fullClasses.get(0).waitList(customer);
            classRepository.save(fullClasses.get(0));
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
    }
    @PostMapping("/customer")
    public Long createPupil(@Valid @RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }

    @PostMapping("/cancel")
    public ResponseEntity<Boolean> cancelClass( @Valid @RequestBody HashMap body){
        Customer customer;
        String className;
        if (!(body.containsKey("id") && body.containsKey("fullName") && body.containsKey("name") && body.containsKey("email")))
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
        try {
            Long id = (Long)body.get("id");
            customer = new Customer(id,(String)body.get("fullName"),body.get("email").toString());
            className = body.get("name").toString();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Class> founds = classRepository.findByName(className);
        for (Class m_class : founds){
            if (m_class.getBookers().contains(customer)){
                Customer enrolledCustomer = m_class.cancel(customer);
                if (enrolledCustomer != null){
                    emailService.sendTextEmail(enrolledCustomer.getEmail(),m_class.getName());
                }
                classRepository.save(m_class);
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_MODIFIED);
    }



}
