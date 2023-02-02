package demo;

public class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;

    }

    public void voice() {

    }

    @Override
    public String toString() {
        return name;

    }
}
