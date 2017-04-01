package com.steven.hicks.entities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileRequest
{
    private File m_uploadedFile;
    private Map<String, String> m_parameters = new HashMap<>();
    private String m_shortFilename;
    private String m_extension;

    public File getUploadedFile()
    {
        return m_uploadedFile;
    }

    public void setUploadedFile(File uploadedFile)
    {
        m_uploadedFile = uploadedFile;
    }

    public Map<String, String> getParameters()
    {
        return m_parameters;
    }

    public void setParameters(Map<String, String> parameters)
    {
        m_parameters = parameters;
    }

    public String getShortFilename()
    {
        return m_shortFilename;
    }

    public void setShortFilename(String shortFilename)
    {
        m_shortFilename = shortFilename;

        if (m_shortFilename!=null && m_shortFilename.contains(".")) m_extension = m_shortFilename.substring(m_shortFilename.lastIndexOf(".") + 1).toLowerCase();
    }

    public String getExtension()
    {
        return m_extension;
    }

    public void setExtension(String extension)
    {
        m_extension = extension;
    }
}
