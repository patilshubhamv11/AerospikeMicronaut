package com.restapi.Service;

import com.restapi.Email.EmailDetails;
import com.restapi.Model.Employee;
import com.restapi.Repository.EmployeeRepository;
import jakarta.inject.Inject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.restapi.Configuration.aeroMapperConfig;

import java.util.List;
import java.util.Properties;

public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    aeroMapperConfig mapper;
    @Inject
    EmailService emailService;

    static final Object BOOTSTRAP_SERVERS = "localhost:9092";
    static final String TOPIC = "employee";


    @Override
    

    public String addEmployee(Employee employee) {
        mapper.getMapper().save(employee);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Employee Data Added..!="+" "+employee);
        kafkaProducer.send(record);
        kafkaProducer.close();
        EmailService.sendEmail(new EmailDetails("Employee Information !!", "Congratulations "+employee.getName()+","+'\n'+"Your Employee information added successfully."+'\n'+"Your Employee Id is "+employee.getId()+".", employee.getEmail()));
        return "Employee with ID:- "+employee.getId()+" saved successfully..";
    }
    @Override
    public List<Employee> getAllEmployee(){
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"all Employees data"+" "+Employee.class);
        kafkaProducer.send(record);
        kafkaProducer.close();

        //EmailService.sendEmail(new EmailDetails("Employee Information Alert !!!", "Congratulations, Employee Info updated "+Employee.class.getName()+", Your Employee Id is "+Employee.class.getField(getAllEmployee().), employee.getEmail()));
        return mapper.getMapper().scan(Employee.class);
    }
    @Override
    public Employee findById(int id) {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Get Employee Data by Id..!="+" "+id);
        kafkaProducer.send(record);
        kafkaProducer.close();

        return mapper.getMapper().read(Employee.class, id);

    }
    @Override
    public String updateEmployee(Employee employee, int id) {
        Employee p = mapper.getMapper().read(Employee.class,id);
        // p.setId(employee.getId());
        p.setName(employee.getName());
        p.setEmail(employee.getEmail());
        p.setJoiningDate(employee.getJoiningDate());
        p.setSal(employee.getSal());
       // p.setDepartment(employee.getDepartment());


        mapper.getMapper().save(p);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Employee Data Updated"+" "+employee);
        kafkaProducer.send(record);
        kafkaProducer.close();
        EmailService.sendEmail(new EmailDetails("Employee Update Alert !!!", "Congratulations,"+'\n'+" Your information updated Successfully "+employee.getName(), employee.getEmail()));
        return "Employee Updated..!="+p.getId();
    }
    @Override
    public String deleteById(int id) {
        mapper.getMapper().delete(Employee.class,id);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer(producerProperties);


        ProducerRecord<Integer, String> record = new ProducerRecord<>(TOPIC,"Message-"+"Data of Employee "+id+" Deleted Successfully ");
        kafkaProducer.send(record);
        kafkaProducer.close();
//        EmailService.sendEmail(new EmailDetails("Employee Information Alert !!!", "Congratulations, Employee Info added "+Employee.class.getName()+"Employee deleted Id"+Employee.class,id));
        return "Data of employee with employee ID:-"+id+" Deleted Successfully";

    }
}
