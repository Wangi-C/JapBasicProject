package jpabasic.reserve.domain;

import jakarta.persistence.*;
import jpabasic.common.Address;

@Entity
@Table(name = "writer")
@SecondaryTables({
        @SecondaryTable(name = "writer_address",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "writer_id", referencedColumnName = "id")
        ),
        @SecondaryTable(name = "writer_intro",
               pkJoinColumns = @PrimaryKeyJoinColumn(name = "writer_id", referencedColumnName = "id")
        )
})
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Embedded
    private Intro intro;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="address1",
                               column = @Column(table = "writer_address", name = "addr1")),
            @AttributeOverride(name="address2",
                               column = @Column(table = "writer_address", name = "addr2")),
            @AttributeOverride(name = "zipcode",
                               column = @Column(table = "writer_address"))
    })
    private Address address;

    public Writer(int id, String name, Intro intro) {
        this.id = id;
        this.name = name;
        this.intro = intro;
    }

    public Writer() {

    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro=" + intro +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Intro getIntro() {
        return intro;
    }
}
