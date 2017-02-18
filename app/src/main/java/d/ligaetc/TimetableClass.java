package d.ligaetc;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.Subject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Andrei on 10.01.2017.
 */

public class TimetableClass {
    private String G="",g="";

    public ArrayList<Materie> OpenTimetable(Context context, int day) {
        ArrayList<Materie> materieArrayList = new ArrayList<Materie>();
        checkStudentGroup(context);
        try {
            AssetManager mgr = context.getAssets();
            InputStream istream = mgr.open("orar_pi_2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document xdoc;
            xdoc = dBuilder.parse(istream);
            xdoc.normalize();
            Node nodeofDay = xdoc.getElementsByTagName("z_" + day).item(0);
            NodeList listOfSubjects = nodeofDay.getChildNodes();
            for (int i = 0; i < listOfSubjects.getLength(); i++) {
                if (listOfSubjects.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Node nodeOfSubject = listOfSubjects.item(i);
                    Element elementOfSubject = (Element) nodeOfSubject;
                    if(checkElementofSubject(elementOfSubject))
                    materieArrayList.add(initializeSubject(elementOfSubject));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if(checkStudentGroup(context))
            //materieArrayList = removeSubjectsFromOtherGroups(materieArrayList);
        return materieArrayList;
    }

    private Materie initializeSubject(Element element) {
        String nume = element.getAttribute("nume");
        String tip = element.getAttribute("tip");
        String prof = element.getAttribute("prof");
        String ora_i = element.getAttribute("ora_i");
        String ora_f = element.getAttribute("ora_f");
        String sala = element.getAttribute("sala");
        String G = element.getAttribute("G");
        String g = element.getAttribute("g");
        return new Materie(nume, tip, prof, ora_i, ora_f, sala, G, g);
    }

    private boolean checkStudentGroup(Context context) {
        try {
            File file = context.getFileStreamPath("PROFIL.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            Node n = doc.getElementsByTagName("profil").item(0);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) n;
                G = element.getAttribute("G");
                g = element.getAttribute("g");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*private ArrayList<Materie> removeSubjectsFromOtherGroups(ArrayList<Materie> materieArrayList) {
        for (int i = 0; i < materieArrayList.size(); i++) {
            Materie materie = materieArrayList.get(i);
            if(!materie.getG().isEmpty()&&!materie.getG().equals(G)){
                materieArrayList.remove(i);
                i--;
            } else if(materie.getG().equals(G)){
               if(!materie.getg().isEmpty()&&!materie.getg().equals(g)){
                    materieArrayList.remove(i);
                    i--;
                }
            }
        }
        return materieArrayList;
    }
*/
    private boolean checkElementofSubject(Element e) {
        if(e.getAttribute("G").equals(G)||e.getAttribute("G").isEmpty())
            if(e.getAttribute("g").isEmpty()||e.getAttribute("g").equals(g))
                return true;
        return false;
    }
}
