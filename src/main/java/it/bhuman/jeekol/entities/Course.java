/*
 * Copyright (C) 2014 B Human Srl.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package it.bhuman.jeekol.entities;

import it.bhuman.jeekol.entities.Student.Gender;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author uji
 *
 */
@Entity
@Table(name = "course")
@NamedQueries({
    @NamedQuery(name = "Course.findAll",
            query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById",
            query = "SELECT c FROM Course c WHERE c.id = :id")})
public class Course implements Serializable {

    private static final long serialVersionUID = 5557373315902868965L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @ManyToMany(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns
            = @JoinColumn(name = "idcourse", referencedColumnName = "id"),
            inverseJoinColumns
            = @JoinColumn(name = "idstudent", referencedColumnName = "id")
    )
    private Set<Student> attendees;

    @Transient
    private int maleStudents;

    @Transient
    private int femaleStudents;

    public Course() {
    }

    public Course(long id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the attendees
     */
    @JsonIgnore
    public Set<Student> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees the attendees to set
     */
    @JsonIgnore
    public void setAttendees(Set<Student> attendees) {
        this.attendees = attendees;
    }

    public int getMaleStudents() {
        return maleStudents;
    }

    public int getFemaleStudents() {
        return femaleStudents;
    }

    public void setGendersNumber() {
        maleStudents = 0;
        femaleStudents = 0;
        for (Student s : attendees) {
            if (s.getGender().equals(Gender.MALE)) {
                ++maleStudents;
            } else if (s.getGender().equals(Gender.FEMALE)) {
                ++femaleStudents;
            }
        }
    }
}
