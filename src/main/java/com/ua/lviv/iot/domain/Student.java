package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "student")
    private List<Rent> rents;
    @OneToMany(mappedBy = "student")
    private List<Repair> repairs;
    @ManyToOne
    @JoinColumn(name = "student_card_id", referencedColumnName = "id", nullable = false)
    private StudentCard studentCard;
    @ManyToMany
    @JoinTable(name = "student_has_equipment", catalog = "", schema = "mydb", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"))
    private Set<Equipment> equipments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public Collection<Rent> getRentsById() {
        return rents;
    }

    public void setRentsById(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Repair> getRepairsById() {
        return repairs;
    }

    public void setRepairsById(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setStudent_id(Set<Equipment> student_id) {
        this.equipments = student_id;
    }
}
