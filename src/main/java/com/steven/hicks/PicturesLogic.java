package com.steven.hicks;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Steven on 0005, April 5, 2017.
 */
public class PicturesLogic
{
    public static void loadPictures(ServletContext sc)
    {
        String path = sc.getRealPath("/");

        String imageRoot = path.replace(path, "") + File.separator + "images" + File.separator;

        try
        {
            List<File> imageFiles = Files.walk(Paths.get(path + imageRoot))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> !file.getName().contains("_small"))
                    .collect(Collectors.toList());
            List<BufferedImage> images = new ArrayList<>();

            Map<File, BufferedImage> imageMap = new HashMap<>();

            for (File file : imageFiles)
            {
                BufferedImage image = ImageIO.read(file);

//                int scaledWidth = (int) (image.getWidth() * 0.15);
//                int scaledHeight = (int) (image.getHeight() * 0.15);
                int scaledWidth = (300);
                int scaledHeight = (200);
                BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, image.getType());

                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
                g2d.dispose();

//                images.add(resizedImage);
                imageMap.put(file, resizedImage);
            }


            for (Map.Entry<File, BufferedImage> entry : imageMap.entrySet())
            {
                File originalImage = (File) entry.getKey();
                String fileName = originalImage.getName();

                String[] fileNameParts = fileName.split("\\.");
                String resizedFileName = fileNameParts[0] + "_small." + fileNameParts[1];

                String path1 = originalImage.getPath().substring(0,originalImage.getPath().lastIndexOf(File.separator)) + File.separator;
                String newFileNameAndPAth = path1 + resizedFileName;

                if (!Paths.get(newFileNameAndPAth).toFile().exists())
                {
                    File resizedImageFile = new File(path1 + resizedFileName);
                    ImageIO.write(entry.getValue(), fileNameParts[1], resizedImageFile);
                }

                String balls = "balls";
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }


    }


}
