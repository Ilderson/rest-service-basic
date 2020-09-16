package com.br.crud.rest.example.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

	public static Properties getProp() throws IOException
    {
        Properties props = new Properties();
        InputStream i = String.class.getResourceAsStream("../resources/application.properties");
        props.load(i);
        return props;
      
    }
}