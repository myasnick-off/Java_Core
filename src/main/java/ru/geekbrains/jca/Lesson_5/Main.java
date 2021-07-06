package ru.geekbrains.jca.Lesson_5;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Ivan", "Petrov", 31, "male", "krezy-ilon@mail.ru", 9011234567L, "developer", 2500),
                new Employee("Petr", "Ivanov", 37, "male", "master_of_iron@mail.ru", 9089875462L, "engineer", 2100),
                new Employee("Andrey", "Kotov", 42, "male", "malevich2@mail.ru", 9026594878L, "designer", 1900),
                new Employee("Vera", "Pavlova", 27, "female", "prety_blondy@mail.ru", 9015601148L, "secretary", 1200),
                new Employee("Klava", "Markina", 63, "female", "super-klava@mail.ru", 9011234567L, "cleaner", 800)
        };

        // вывод на экран информации о сотрудниках старше 40 лет
        for (Employee employee : employees) {
            if (employee.getAge() > 40) employee.printAbout();
        }

        // вывод на экран количества созданных сотрудников
        System.out.printf("Employees total: %s", Employee.getCounter());
    }
}
