package service;

import annotations.Mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Mapping {
    public <T> T mapper(Class<T> clazz, String[] values){
        T object;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            int counter = 0;
            for (Field field : fields) {
                if (field.isAnnotationPresent(Mapper.class)) {
                    field.setAccessible(true);
                    castObject(object, field, values[counter]);
                    counter++;
                }
            }
            return object;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
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
