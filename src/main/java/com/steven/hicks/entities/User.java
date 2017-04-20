package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.Utilities.PasswordUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    private String userName = "";

    @Column
    private int userRoleId;

    @Column
    private String hashedPasswordAndSalt = "";

    @Column
    private String emailAddress = "";

    @Column
    private String firstName = "";

    @Column
    private String lastName = "";

    @Column
    private String role  ="";

    @Column
    private Integer avatarObjectId;

    @Override
    public String toString()
    {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName.equals(user.userName);
    }

    @Override
    public int hashCode()
    {
        return userName.hashCode();
    }

    public static User getUser(String userName)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = session.get(User.class, userName);

        session.close();
        sessionFactory.close();

        return user;
    }

    public static void createUser(String userName, String password, String emailAddress, String firstName, String lastName)
    {
        User user = new User();
        user.setUserName(userName);
        user.setEmailAddress(emailAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole("user");

        UserRole userRole = new UserRole();
        userRole.setRole("user");
        userRole.setUserName(userName);

        String digestedPassword = "";
        digestedPassword = PasswordUtil.digestPassword(password);

        if (digestedPassword.length() > 0)
        {
            user.setHashedPasswordAndSalt(digestedPassword);

            HibernateUtil.createItem(userRole);
            user.setUserRoleId(userRole.getId());
            HibernateUtil.createItem(user);
        }
    }

    public UserAvatar getAvatar()
    {
        return UserAvatar.getAvatar(avatarObjectId);
    }

    public String getFirstAndLastName()
    {
        return firstName + " " + lastName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getUserRoleId()
    {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    public String getHashedPasswordAndSalt()
    {
        return hashedPasswordAndSalt;
    }

    public void setHashedPasswordAndSalt(String hashedPasswordAndSalt)
    {
        this.hashedPasswordAndSalt = hashedPasswordAndSalt;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Integer getAvatarObjectId()
    {
        return avatarObjectId;
    }

    public void setAvatarObjectId(Integer avatarObjectId)
    {
        this.avatarObjectId = avatarObjectId;
    }
}
