package com.d288.vacationbookings.bootstrap;

import com.d288.vacationbookings.dao.CustomerRepository;
import com.d288.vacationbookings.dao.DivisionRepository;
import com.d288.vacationbookings.entities.Customer;
import com.d288.vacationbookings.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // sample division set to New York
        Division new_york = divisionRepository.findById(31L).orElse(null);

        // check if customer repository only has 1 customer in database, then add 5 sample customers
        if (customerRepository.count() == 1) {

            Customer customer1 = new Customer(
                    "Donny",
                    "Hamato",
                    "270 Lafayette Street",
                    "10012",
                    "888-TURTLES");

            Customer customer2 = new Customer(
                    "Ralph",
                    "Hamato",
                    "270 Lafayette Street",
                    "10012",
                    "888-TURTLES");

            Customer customer3 = new Customer(
                    "Leo",
                    "Hamato",
                    "270 Lafayette Street",
                    "10012",
                    "888-TURTLES");

            Customer customer4 = new Customer(
                    "Mikey",
                    "Hamato",
                    "270 Lafayette Street",
                    "10012",
                    "888-TURTLES");

            Customer customer5 = new Customer(
                    "Splinter",
                    "Hamato",
                    "270 Lafayette Street",
                    "10012",
                    "888-TURTLES");

            customer1.setDivision(new_york);
            customer2.setDivision(new_york);
            customer3.setDivision(new_york);
            customer4.setDivision(new_york);
            customer5.setDivision(new_york);

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);
        }
    }
}
