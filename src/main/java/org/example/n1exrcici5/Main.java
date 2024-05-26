package org.example.n1exrcici5;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        Person person = new Person("John", "Doe", 30);

        //  File file = new File("src/main/resources/person.ser");

        serializeObject(person, "person.ser");

        Person deserializedPerson = (Person) deserializeObject("person.ser");

        // Imprimir los datos del objeto deserializado
        if (deserializedPerson != null) {
            System.out.println("Name: " + deserializedPerson.getName());
            System.out.println("Last Name: " + deserializedPerson.getLastName());
            System.out.println("Age: " + deserializedPerson.getAge());
        }
    }


    private static void serializeObject(Object object, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(object);
            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error while serializing");
        }
    }

    private static Object deserializeObject(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("Object deserialized successfully.");
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while deserializing");
            return null;
        }
    }

}
