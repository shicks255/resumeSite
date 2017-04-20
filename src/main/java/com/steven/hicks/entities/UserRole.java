package com.steven.hicks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRole
{

    @Id
    private String role = "";

    @Column
    private String userName = "";

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return role.equals(userRole.role);
    }

    @Override
    public int hashCode()
    {
        return role.hashCode();
    }

    @Override
    public String toString()
    {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
