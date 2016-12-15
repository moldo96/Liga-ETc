package d.ligaetc;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.security.AccessController.getContext;

/**
 * Created by Andrei on 14.12.2016.
 */

public class TimeClass{

    Document xdoc;
    Calendar now= Calendar.getInstance();
    Calendar data_i, data_s;
    private NodeList vacanta, predare, sesiune2;
    boolean foundDate = false;

    public int dayExtractor() {
        int day = now.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public int hourExtractor() {
        int hour = now.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public int minuteExtractor() {
        int minute = now.get(Calendar.MINUTE);
        return minute;
    }

    public int monthExtractor() {
        int month = now.get(Calendar.MONTH);
        return month;
    }

    public String checkDay(Context context) {
        try {
            AssetManager mgr = context.getAssets();
            InputStream is = mgr.open("structura_2016.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            xdoc = dBuilder.parse(is);
            xdoc.normalize();
            data_i = new GregorianCalendar();
            data_s = new GregorianCalendar();
            predare = findNodes("predare");
            vacanta = findNodes("vacanta");
            sesiune2 = findNodes("sesiune2");

            String g = searchDateInterval(predare);
            return g;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "baaa";
    }

    private String showDate(Calendar d) {

        return d.getTime().toString();
    }

    private int getInteger(Element element, String s) {
        return Integer.parseInt(element.getAttribute(s));
    }

    private NodeList findNodes(String string_to_search) {
        return xdoc.getElementsByTagName(string_to_search);
    }

    private boolean dateIntervalFound(){
        return (data_i.before(now) && data_s.after(now));
    }

    private String searchDateInterval(NodeList nList){
        for (int  i= 0; i < predare.getLength(); i++) {
            Node n = predare.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE)
            {
                Element e = (Element) n;
                data_i.set(getInteger(e,"an_i"), getInteger(e,"luna_i")-1, getInteger(e,"zi_i"));
                data_s.set(getInteger(e,"an_s"), getInteger(e,"luna_s")-1, getInteger(e,"zi_s"));

                if (data_i.before(now) && data_s.after(now)) {
                    //tv1.setText(tv1.getText() + "" + i + data_i.getTime().toString() + " " + data_s.getTime().toString() + "\n");
                    return "yes it is "  + showDate(data_i);
                }
                else
                    //tv1.setText(tv1.getText() + "nu" + "\n");
                    return "it is not in this period: " + showDate(data_i);
            }
        }
        return "Errror";
    }
}
