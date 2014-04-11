/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bhuman.jeekol.ejb;

import it.bhuman.jeekol.entities.Course;
import it.bhuman.jeekol.entities.Student;
import java.util.List;
import java.util.Set;
import javax.ejb.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Claudiu Adam
 */
@Singleton
public class CourseEJB {

    @PersistenceUnit(unitName = "jee-kol")
    EntityManagerFactory emf;

    public CourseEJB() {

    }

    public List<Course> listCourses() {
        List<Course> cl = emf.createEntityManager().createNamedQuery("Course.findAll")
                .getResultList();
        for(Course c:cl)
        {
            c.setGendersNumber();
        }
        return cl;
    }

    public Course findCourseById(long id) {
        return (Course) emf.createEntityManager().createNamedQuery("Course.findById")
                .setParameter("id", id)
                .getResultList().get(0);
    }

    public Set<Student> findStudensByCourseId(long id) {
        return findCourseById(id).getAttendees();
    }
}
