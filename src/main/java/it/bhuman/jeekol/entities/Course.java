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

import java.util.Set;

/**
 *
 * @author uji
 * 
 * TEST: questo deve diventare un @Entity
 * http://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html
 * 
 */
public class Course
{
    final private long id;
    
    private String name;
    private int    year;
    
    Set<Student> attendees;

    public Course(long id, String name, int year)
    {
        this.id = id;
        
        this.name = name;
        this.year = year;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }
    
    /**
     * @return the attendees
     * 
     * questa campo deve mappare una relazione 1:N unidirezionale
     * 
     */
    public Set<Student> getAttendees()
    {
        return attendees;
    }
    
    /**
     * @param attendees the attendees to set
     */
    public void setAttendees(Set<Student> attendees)
    {
        this.attendees = attendees;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year)
    {
        this.year = year;
    }
}