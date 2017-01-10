package d.ligaetc;

import android.content.Context;
import android.content.res.AssetManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Andrei on 10.01.2017.
 */

public class TimetableClass {
    Document xdoc;
    NodeList subjects_list;
    Node day_node;

    public void OpenTimetable(Context context, int day, String period) {
        if (period == "predare") {
            if (day > 0 && day < 6) {
                try {
                    AssetManager mgr = context.getAssets();
                    InputStream is = mgr.open("orar_pi_2.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    xdoc = dBuilder.parse(is);
                    xdoc.normalize();
                    day_node = xdoc.getElementsByTagName("z_" + day).item(0);
                    subjects_list = day_node.getChildNodes();
                    for(int i = 0; i< subjects_list.getLength(); i++){
                        Node subject_node = subjects_list.item(i);
                        if(subject_node.getNodeType()==Node.ELEMENT_NODE){
                            Element element_subject = (Element) subject_node;
                            element_subject.getAttribute("ora_i");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
