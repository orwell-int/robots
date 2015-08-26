package orwell.tank.utils;


import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Michaël Ludmann on 04/07/15.
 */
public class IniFile {
    private Properties config;

    public IniFile(String path) {
        config = new Properties();
        load(path);
    }

    public void load(String path) {
        File data = new File(path);
        try {
            InputStream is = new FileInputStream(data);
            config.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return config.getProperty(key);
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(config.getProperty(key, Integer.toString(defaultValue)));
    }

    public int getInt(String key) {
        return Integer.parseInt(config.getProperty(key));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(config.getProperty(key, Boolean.toString(defaultValue)));
    }

}

