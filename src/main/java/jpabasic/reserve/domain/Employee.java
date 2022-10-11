package jpabasic.reserve.domain;

import jakarta.persistence.*;
import jpabasic.common.Address;

@Entity
public class Employee {
    @Id
    private String id;

    @Embedded
    private Address addressHome;

    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(name = "waddr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "waddr2")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "wzipcode"))
    })
    @Embedded
    private Address addressWork;

    protected Employee() {}

    public Employee(String id, Address addressHome, Address addressWork) {
        this.id = id;
        this.addressHome = addressHome;
        this.addressWork = addressWork;
    }

    public String getId() {
        return id;
    }

    public Address getAddressHome() {
        return addressHome;
    }

    public Address getAddressWork() {
        return addressWork;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", addressHome=" + addressHome +
                ", addressWork=" + addressWork +
                '}';
    }
}
