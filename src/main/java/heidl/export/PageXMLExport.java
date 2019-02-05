package heidl.export;

import heidl.entity.PageEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Document;



@Deprecated
public class PageXMLExport {

    protected static final String path = "/home/heid/javajob/projects/piggrabber/archive";
    protected static final String format = "Y-M-d";

    protected String currentDate;

    protected Document doc;
    protected Element data;


    protected Transformer transformer;

    public PageXMLExport() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            this.currentDate = sdf.format(new Date());
            DocumentBuilderFactory  dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =
                    dbf.newDocumentBuilder();
            this.doc = builder.newDocument();
            this.data = this.doc.createElement("data");
            data.setAttribute("data", this.currentDate);
            doc.appendChild(data);
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (Exception e) {
            System.out.println("XMLParserInit exception");
        }
    }

    public void add(PageEntity page) {
        List<String> links = page.getVacancyLinks();
        int pageNum = page.getPageNum();
        Element pageNode = doc.createElement("page");
        pageNode.setAttribute("num", String.valueOf(pageNum));
        for(String link : links) {
            Element vacancyNode = doc.createElement("vacancy");
            vacancyNode.setTextContent(link);
            pageNode.appendChild(vacancyNode);
        }
        data.appendChild(pageNode);
    }

    public void export() {
        try {
            File file = new File(path + "/links_" + this.currentDate + ".xml");
            transformer.transform(new DOMSource(this.doc), new StreamResult(file));
        } catch (Exception e) {
            System.out.println("Write failed");
        }
    }
}
