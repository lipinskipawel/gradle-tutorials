package org.example.base;

public interface Simpsons {
    Person JOHN = new Person("John", "Carrot");
    Person MAT = new Person("Mat", "Wart");


    default Person aPerson(String name) {
        return new Person(name, "random");
    }

    default Person aPerson(String name, String lastName) {
        return new Person(name, lastName);
    }
}
