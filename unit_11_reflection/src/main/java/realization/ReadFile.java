package realization;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFile {
    public Properties readFile(String url){
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream input = classLoader.getResourceAsStream(url)){
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
