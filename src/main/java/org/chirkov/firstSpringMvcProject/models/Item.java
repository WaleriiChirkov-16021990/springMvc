package org.chirkov.firstSpringMvcProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
//import org.hibernate.annotations.Table;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "tem name schould not be empty")
    @Column(name = "item_name")
    private String itemName;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(String itemName, Person owner) {
        this.itemName = itemName;
        this.owner = owner;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
