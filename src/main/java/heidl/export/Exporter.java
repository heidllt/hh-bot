package heidl.export;

import heidl.entity.EntityProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Exporter <T extends EntityProcessor>{
    public Class<T> getEntityClass();

    public default List<Field> getEntityFields() {
        return Arrays.stream(getEntityClass().getDeclaredFields())
                .filter(e->e.getAnnotation(heidl.annotations.Field.class) != null)
                .collect(Collectors.toList());
    }

    public void export();
    public void export(String prefix);
}
