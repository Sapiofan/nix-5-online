package realization;

import annotations.PropertyKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class AppProperties {
    public <T> T initialize(Class<T> clazz){
        T object;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
            ReadFile readFile = new ReadFile();
            Properties properties = readFile.readFile("app.properties");

            Class<?> c = object.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                    String props = properties.getProperty(propertyKey.value());
                    field.setAccessible(true);
                    castObject(object, field, props);
                }
            }
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void castObject(Object object, Field field, String props) {
        Class<?> type = field.getType();
        try {
            if (Byte.class == type || Byte.TYPE == type) field.set(object, Byte.parseByte(props));
            else if (Short.class == type || Short.TYPE == type) field.set(object, Short.parseShort(props));
            else if (Integer.class == type || Integer.TYPE == type) field.set(object, Integer.parseInt(props));
            else if (Long.class == type || Long.TYPE == type) field.set(object, Long.parseLong(props));
            else if (String.class == type) field.set(object, props);
            else if (Boolean.class == type || Boolean.TYPE == type) field.set(object, Boolean.parseBoolean(props));
            else if (Float.class == type || Float.TYPE == type) field.set(object, Float.parseFloat(props));
            else if (Double.class == type || Double.TYPE == type) field.set(object, Double.parseDouble(props));
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
