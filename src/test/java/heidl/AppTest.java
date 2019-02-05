package heidl;



import heidl.export.VacancyMysqlExporter;

import heidl.pagination.SimplePaginator;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Unit test for simple App.
 */
public class AppTest 
{


    @Test
    public void testMysqlExporter()
    {
        VacancyMysqlExporter exporter = new VacancyMysqlExporter();
        exporter.export();
    }

}
