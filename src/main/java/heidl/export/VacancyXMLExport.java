package heidl.export;

import heidl.entity.VacancyEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

public class VacancyXMLExport implements XMLExporter<VacancyEntity>{


    protected static final String BASE_DIR = "/home/heid/javajob/projects/piggrabber/archive/spring/";
    protected static final String DATE_FORMAT = "yyyy-MM-dd";

    protected Document doc;
    protected Element data;
    protected Transformer transformer;

    public VacancyXMLExport() {
        init();
    }

    public void init() {
        try {
            doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            transformer = TransformerFactory
                    .newInstance()
                    .newTransformer();
            data = doc.createElement("rows");
            doc.appendChild(data);
        } catch (Exception e) {}
    }

    @Override
    public Document getDoc() {
        return doc;
    }

    @Override
    public Element getRoot() {
        return data;
    }

    @Override
    public String getDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public String getBaseDir() {
        return BASE_DIR;
    }

    @Override
    public Class<VacancyEntity> getEntityClass() {
        return VacancyEntity.class;
    }

    @Override
    public void export() { throw new UnsupportedOperationException("Non supported"); }

    @Override
    public void reset() {
        init();
    }
}
