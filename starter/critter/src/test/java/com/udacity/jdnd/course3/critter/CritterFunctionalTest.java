package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetController;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleController;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a set of functional tests to validate the basic capabilities desired for this application.
 * Students will need to configure the application to run these tests by adding application.properties file
 * to the test/resources directory that specifies the datasource. It can run using an in-memory H2 instance
 * and should not try to re-use the same datasource used by the rest of the app.
 *
 * These tests should all pass once the project is complete.
 */
@Transactional
@SpringBootTest(classes = CritterApplication.class)
public class CritterFunctionalTest {

    @Autowired
    private UserController userController;

    @Autowired
    private PetController petController;

    @Autowired
    private ScheduleController scheduleController;
    @Autowired
    private WeekController weekController;

    @BeforeEach
    public void setupWeek(){
        weekController.saveWeek(initializeWeek(1,"Monday"));
        weekController.saveWeek(initializeWeek(2,"Tuesday"));
        weekController.saveWeek(initializeWeek(3,"Wednesday"));
        weekController.saveWeek(initializeWeek(4,"Thursday"));
        weekController.saveWeek(initializeWeek(5,"Friday"));
        weekController.saveWeek(initializeWeek(6,"Saturday"));
        weekController.saveWeek(initializeWeek(7,"Sunday"));

    }

    @Test
    public void testCreateCustomer(){
        Customer customer = createCustomer();
        Customer newCustomer = userController.saveCustomer(customer);
        Customer retrievedCustomer = userController.getAllCustomers().get(0);
        Assertions.assertEquals(newCustomer.getName(), customer.getName());
        Assertions.assertEquals(newCustomer.getId(), retrievedCustomer.getId());
        Assertions.assertTrue(retrievedCustomer.getId() > 0);
    }

    @Test
    public void testCreateEmployee(){
        Employee employee = createEmployee();
        Employee newEmployee = userController.saveEmployee(employee);
        Employee retrievedEmployee = userController.getEmployee(newEmployee.getId());
        Assertions.assertEquals(employee.getSkills(), newEmployee.getSkills());
        Assertions.assertEquals(newEmployee.getId(), retrievedEmployee.getId());
        Assertions.assertTrue(retrievedEmployee.getId() > 0);
    }

    @Test
    public void testAddPetsToCustomer() {
        Customer customer = createCustomer();
        Customer newCustomer = userController.saveCustomer(customer);

        Pet pet = createPet();
        pet.setOwner(newCustomer);
        Pet newPet = petController.savePet(pet);

        //make sure pet contains customer id
        Pet retrievedPet = petController.getPet(newPet.getId());
        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getOwner().getId(), newCustomer.getId());

        //make sure you can retrieve pets by owner
        List<Pet> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertEquals(newPet.getId(), pets.get(0).getId());
        Assertions.assertEquals(newPet.getName(), pets.get(0).getName());

        //check to make sure customer now also contains pet
        Customer retrievedCustomer = userController.getAllCustomers().get(0);
//        Assertions.assertTrue(retrievedCustomer.getPetIds() != null && retrievedCustomer.getPetIds().size() > 0);
//        Assertions.assertEquals(retrievedCustomer.getPetIds().get(0), retrievedPet.getId());
    }

    @Test
    public void testFindPetsByOwner() {
        Customer customer = createCustomer();
        Customer newCustomer = userController.saveCustomer(customer);

        Pet pet = createPet();
        pet.setOwner(newCustomer);
        Pet newPet = petController.savePet(pet);
        pet.setType("Dog");
        pet.setName("DogName");
        Pet newPet2 = petController.savePet(pet);

        List<Pet> pets = petController.getPetsByOwner(newCustomer.getId());
        Assertions.assertNotEquals(pets.size(), 0);
        Assertions.assertEquals(pets.get(0).getOwner().getId(), newCustomer.getId());
        Assertions.assertEquals(pets.get(0).getId(), newPet.getId());
    }

    @Test
    public void testFindOwnerByPet() {
        Customer customer = createCustomer();
        Customer newCustomer = userController.saveCustomer(customer);

        Pet pet = createPet();
        pet.setOwner(newCustomer);
        Pet newPet = petController.savePet(pet);

        Customer owner = userController.getOwnerByPet(newPet.getId());
        Assertions.assertEquals(owner.getId(), newCustomer.getId());
//        Assertions.assertEquals(owner.getPetI.get(0), newPet.getId());
    }

    @Test
    public void testChangeEmployeeAvailability() {
        Employee employee = createEmployee();
        Employee emp1 = userController.saveEmployee(employee);
        Assertions.assertNull(emp1.getWeekDays());
//        weekController.saveWeek(initializeWeek(1,"Monday"));
        Week week = weekController.getWeek(1);
        week.setEmployee(emp1);
        weekController.saveWeek(week);
        Assertions.assertEquals(weekController.getWeek(1).getEmployees().get(0), emp1);
    }

    @Test
    public void testFindEmployeesByServiceAndTime() {
        Employee emp1 = createEmployee();
        Employee emp2 = createEmployee();
        Employee emp3 = createEmployee();
        Week monday = weekController.getWeek(1);
        Week tuesday = weekController.getWeek(2);
        Week wednesday = weekController.getWeek(3);
        Week thursday = weekController.getWeek(4);
        Week friday = weekController.getWeek(5);
        Week saturday = weekController.getWeek(6);

        monday.setEmployee(emp1);
        tuesday.setEmployee(emp1);
        wednesday.setEmployee(emp1);
        thursday.setEmployee(emp2);
        friday.setEmployee(emp2);

        emp1.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        emp2.setSkills(Sets.newHashSet(EmployeeSkill.PETTING, EmployeeSkill.WALKING));
        emp3.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        Employee emp1n = userController.saveEmployee(emp1);
        Employee emp2n = userController.saveEmployee(emp2);
        Employee emp3n = userController.saveEmployee(emp3);

        //make a request that matches employee 1 or 2
        EmployeeRequestDTO er1 = new EmployeeRequestDTO();
        er1.setDate(LocalDate.of(2019, 12, 25)); //wednesday
        er1.setSkills(Sets.newHashSet(EmployeeSkill.PETTING));


        Week week = weekController.getWeek(3);
        Week week2 = weekController.getWeek(5);
        List <Employee> employees = week.getEmployees();
        List <Employee> employees2 = week2.getEmployees();
        Assertions.assertEquals(employees.get(0),emp1);
        Assertions.assertEquals(employees2.get(0),emp2);





    }

    @Test
    public void testSchedulePetsForServiceWithEmployee() {
        Employee employeeTemp = createEmployee();
        Week monday = weekController.getWeek(1);
        Week tuesday = weekController.getWeek(2);
        Week wednesday = weekController.getWeek(3);
        Set<Week> availability = new HashSet<Week>();
        availability.add(monday);
        availability.add(tuesday);
        availability.add(wednesday);

        employeeTemp.setWeekDays(availability);
        Employee employee = userController.saveEmployee(employeeTemp);
        Customer customer = userController.saveCustomer(createCustomer());
        Pet petTemp = createPet();
        petTemp.setOwner(customer);
        Pet pet = petController.savePet(petTemp);

        LocalDate date = LocalDate.of(2019, 12, 25);


        Schedule testSchedule = new Schedule();
        testSchedule.setPet(pet);
        testSchedule.setEmployee(employee);
        testSchedule.setDay(date);
       scheduleController.createSchedule(testSchedule);
        Schedule schedule = scheduleController.getAllSchedules().get(0);


        Assertions.assertEquals(schedule.getDay(), date);
        Assertions.assertEquals(schedule.getEmployee(), employee);
        Assertions.assertEquals(schedule.getPet(), pet);
    }



    private static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setName("TestEmployee");
        employee.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        return employee;
    }
    private static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setName("TestEmployee");
        customer.setPhoneNumber("123-456-789");
        return customer;
    }

    private static Pet createPet() {
        Pet pet = new Pet();
        pet.setName("TestPet");
        pet.setType("Cat");
        return pet;
    }
    private static Week initializeWeek(int id,String name) {
        Week week = new Week();
        week.setId(id);
        week.setName(name);
        return week;
    }

    private static EmployeeRequestDTO createEmployeeRequestDTO() {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setDate(LocalDate.of(2019, 12, 25));
        employeeRequestDTO.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));
        return employeeRequestDTO;
    }

    private static ScheduleDTO createScheduleDTO(List<Long> petIds, List<Long> employeeIds, LocalDate date, Set<EmployeeSkill> activities) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setDate(date);
        scheduleDTO.setActivities(activities);
        return scheduleDTO;
    }

    private static void compareSchedules(ScheduleDTO sched1, ScheduleDTO sched2) {
        Assertions.assertEquals(sched1.getPetIds(), sched2.getPetIds());
        Assertions.assertEquals(sched1.getActivities(), sched2.getActivities());
        Assertions.assertEquals(sched1.getEmployeeIds(), sched2.getEmployeeIds());
        Assertions.assertEquals(sched1.getDate(), sched2.getDate());
    }

}
