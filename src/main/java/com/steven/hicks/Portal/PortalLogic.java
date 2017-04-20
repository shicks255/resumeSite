package com.steven.hicks.Portal;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.FileRequest;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.UserAvatar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PortalLogic
{

    public static String saveAvater(File file, FileRequest fr, User user) throws IOException
    {
        StringBuilder errorMessage = new StringBuilder("");
        if (file == null || !file.isFile())
            errorMessage.append("No valid file was selected");

        byte[] bytes = new byte[ (int)file.length()];

        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            inputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        UserAvatar avatar = new UserAvatar();
        avatar.setPicture(bytes);

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(avatar);

        user.setAvatarObjectId(avatar.getObjectId());
        session.update(user);

        session.getTransaction().commit();
        session.close();
        factory.close();

        File tempFile = file.getAbsoluteFile();
        tempFile.delete();
        file.delete();

        return errorMessage.toString();
    }

}
