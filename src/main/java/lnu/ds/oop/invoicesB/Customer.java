package lnu.ds.oop.invoicesB;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Customer {
    //For relational table we need customer #
    String name;
    private String street;
    private String city;
    private String state;
    private  String zip;

    public String format() {
        return String.format("%s%n%n%s, %s - %s", name, street, city,state, zip);
    }
}
