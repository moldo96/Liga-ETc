package d.ligaetc;

import android.content.Context;
import android.content.res.AssetManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.security.auth.Subject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Andrei on 10.01.2017.
 */

public class TimetableClass {
    private Document xdoc;
    private NodeList listOfSubjects;
    private Node nodeofDay;

    public ArrayList<Materie> OpenTimetable(Context context, int day) {
        ArrayList<Materie> materieArrayList = new ArrayList<Materie>();
        try {
            AssetManager mgr = context.getAssets();
            InputStream istream = mgr.open("orar_pi_2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            xdoc = dBuilder.parse(istream);
            xdoc.normalize();
            nodeofDay = xdoc.getElementsByTagName("z_" + day).item(0);
            listOfSubjects = nodeofDay.getChildNodes();
            for(int i =0; i<listOfSubjects.getLength();i++) {
                if (listOfSubjects.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Node nodeOfSubject = listOfSubjects.item(i);
                    Element elementOfSubject = (Element) nodeOfSubject;
                    materieArrayList.add(initializeSubject(elementOfSubject));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materieArrayList;
    }

    private Materie initializeSubject(Element element){
        String nume = element.getAttribute("nume");
        String tip = element.getAttribute("tip");
        String prof = element.getAttribute("prof");
        String ora_i = element.getAttribute("ora_i");
        String ora_f = element.getAttribute("ora_f");
        String sala = element.getAttribute("sala");
        Materie subject = new Materie(nume,tip,prof, ora_i,ora_f, sala);
        return subject;
    }

}
