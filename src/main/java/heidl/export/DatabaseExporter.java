package heidl.export;

import heidl.entity.EntityProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public interface DatabaseExporter<T extends EntityProcessor> extends Exporter<T> {

    public String getTable();
    public String getBaseDir();
    public Connection getConnection();


    public default String buildInsert() {
        List<Field> fields = getEntityFields();
        List<String> fieldNames = fields.stream()
                .map(e->e.getName())
                .collect(Collectors.toList());
        String insertDeclare =
                "INSERT INTO "+getTable()+" (" + String.join(",", fieldNames) + ") VALUES ";
        String insertValues = null;
        List<String> builder = new ArrayList<>();
        for(Field field : fields) {
            try {
                String getter = field.getName();
                getter = "get" + getter.substring(0, 1).toUpperCase() +
                        getter.substring(1);
                Class<?> returnType = getEntityClass().getDeclaredMethod(getter).getReturnType();
                if (CharSequence.class.isAssignableFrom(returnType) ||
                        Date.class.isAssignableFrom(returnType)) {
                    builder.add("\"{"+field.getName()+"}\"");
                } else {
                    builder.add("{"+field.getName()+"}");
                }
            } catch (Exception e) {}
        }
        insertValues = "(" + String.join(",", builder) + ")";
        return insertDeclare + insertValues;
    }

    public default void export() {
        File dir = new File(getBaseDir());
        File[] files = dir.listFiles();
        for (File file : files ) {
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
                Document doc = builder.parse(file);
                NodeList nodes = doc.getElementsByTagName("row");

                for (int i = 0; i < nodes.getLength(); i++ ) {
                    Node node = nodes.item(i);
                    NodeList columns = node.getChildNodes();
                    String result = buildInsert();
                    Map<String, String> map = new HashMap<>();
                    for (int j = 0; j < columns.getLength(); j ++) {
                        Node col = columns.item(j);
                        String content = col.getTextContent()
                                .replace("$", "")
                                .replace("\\", "")
                                .replace(",", " ")
                                .replace("\"", "");
                        map.put(col.getNodeName(), content);
                    }
                    System.out.println(result);
                    for (Field f : getEntityFields()) {
                        if (f.getName() == "text") continue;
                        System.out.printf("%s: %s\n", f.getName(), map.get(f.getName()));
                        result = result.replace("{"+f.getName()+"}", map.get(f.getName()));
                    }
                    Statement stmt = getConnection().createStatement();
                    stmt.execute(result);
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
