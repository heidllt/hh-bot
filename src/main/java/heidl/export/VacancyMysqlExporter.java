package heidl.export;


import heidl.entity.VacancyEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class VacancyMysqlExporter implements DatabaseExporter<VacancyEntity>{


    protected static final String BASE_DIR = "/home/heid/javajob/projects/piggrabber/archive/spring/";
    protected static final String TABLE = "vacancy2";
    Connection con;


    public VacancyMysqlExporter() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://0.0.0.0/pymsql?" +
                    "user=admin&password=admin&characterEncoding=utf8");
            con.setAutoCommit(true);
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver not found in classpath");
        } catch (SQLException se) {
            System.out.println("Error on creating connection");
        }
    }

    @Override
    public Class<VacancyEntity> getEntityClass() {
        return VacancyEntity.class;
    }

    @Override
    public void export(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public String getBaseDir() {
        return BASE_DIR;
    }

    @Override
    public Connection getConnection() {
        return con;
    }
}
