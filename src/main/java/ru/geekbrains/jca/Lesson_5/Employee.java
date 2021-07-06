package ru.geekbrains.jca.Lesson_5;

import java.text.DecimalFormat;

public class Employee {

    private static int counter;         // счетчик созданных сотрудников

    private String firstName;           // имя
    private String lastName;            // фамилия
    private int age;                    // возраст
    private String sex;                 // пол
    private String email;               // электронная почта
    private long phoneNumber;           // номер телефона
    private String position;            // должность
    private int salary;                 // зарплата

    public Employee(String firstName, String lastName, int age, String sex, String email, long phoneNumber, String position, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.salary = salary;
        counter++;                  // инкремент счетчика сотрудников
    }

    /* метод для вывода на экран полной информации о сотруднике */
    public void printAbout() {
        System.out.printf("Full name: %s %s\n", firstName, lastName);
        System.out.printf("Age: %d\n", age);
        System.out.printf("E-mail: %s\n", email);
        System.out.printf("Phone: +7 %d\n", phoneNumber);
        System.out.printf("Position: %s\n", position);
        System.out.printf("Salary: %d$\n", salary);
        System.out.printf("Retirement status: ");
        if (isRetired()) System.out.printf("Retired\n\n");
        else System.out.printf("%d years before retirement\n\n", beforeRetirementCounter());

    }

    /* геттер, возвращает true, если сотрудник является пенсионером */
    public boolean isRetired() {
        if (this.sex.equals("male") && this.age > 62) return true;
        if (this.sex.equals("female") && this.age > 59) return true;
        return false;
    }

    /* метод вычисляет количество лет до пенсии */
    public int beforeRetirementCounter() {
        if (this.sex.equals("male")) return 63 - this.age;
        else return 60 - this.age;
    }

    /* прочие геттеры */

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public static int getCounter() {
        return counter;
    }
}
