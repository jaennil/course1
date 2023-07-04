package org.example;

public class Animal {
    private int id;
    private String name;
    private Breed breed;
    private Person owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Animal(int id, String name, Breed breed, Person owner) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.owner = owner;
    }
}
