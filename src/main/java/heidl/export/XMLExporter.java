package heidl.export;

import heidl.entity.EntityProcessor;
import heidl.entity.VacancyEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


public interface XMLExporter<T extends EntityProcessor> extends Exporter<T> {
    static final String DATE_FORMAT = "yyyy-MM-dd";

    Element getRoot();
    Document getDoc();
    default String getDateFormat() { return DATE_FORMAT; }
    String getBaseDir();
    public void reset();

    default void add(T e) {
        Element row = getDoc().createElement("row");
        for(Field efield : e.getFields()) {
            try {
                String fieldName = efield.getName();
                String origFieldName = fieldName;
                fieldName = "get"+fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Element column = getDoc().createElement(origFieldName);
                Method reflectedField = VacancyEntity.class
                        .getDeclaredMethod(fieldName, null);
                Object o = reflectedField.invoke(e);
                if (o instanceof Date) {
                    column.setTextContent( new SimpleDateFormat(DATE_FORMAT).format(o) );
                } else {
                    column.setTextContent( o.toString() );
                }
                row.appendChild(column);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        getRoot().appendChild(row);
    }

    @Override
    default void export(String prefix) {
        try {
            File file = new File(getBaseDir() + prefix+".xml");
            TransformerFactory
                    .newInstance()
                    .newTransformer()
                    .transform(new DOMSource(getDoc()), new StreamResult(file));
        } catch (Exception e) {
            System.out.println("Write failed");
        }
    }

}
