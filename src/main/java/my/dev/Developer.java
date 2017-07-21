package my.dev;

import java.util.ArrayList;
import java.util.List;

public class Developer {

    private final String name;
    private final String lastName;


    public Developer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

}
