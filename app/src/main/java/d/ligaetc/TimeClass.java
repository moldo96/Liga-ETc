package d.ligaetc;

import android.content.Context;
import android.content.res.AssetManager;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by Andrei on 14.12.2016.
 */

public class TimeClass{

    private Document xdoc;
    private Calendar now= Calendar.getInstance();
    private NodeList perioada;
    private Context context;
    private int weekOfFoundPeriod;
    private String semester;

    //private boolean foundDate = false;

    public String localdayFormat(Calendar calendar) {
        String d = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        return d;
    }

    public int dayExtractor(){
        return now.get(Calendar.DAY_OF_WEEK);
    }

    public int hourExtractor() {
        return now.get(Calendar.HOUR_OF_DAY);
    }

    public int minuteExtractor() {
        return now.get(Calendar.MINUTE);
    }

    public int monthExtractor() {
        return now.get(Calendar.MONTH);
    }

    public int weekExtractor() {
        return now.get(Calendar.WEEK_OF_YEAR);
    }

    public int getWeekOfFoundPeriod()
    {
        return weekOfFoundPeriod;
    }

    public String getHourDifference(String timeToStart, String timeToEnd){
        int actualHour = hourExtractor();
        int actualMinute = minuteExtractor();
        String[] fractions1=timeToStart.split(":");
        String[] fractions2=timeToStart.split(":");
        int hourToEnd = Integer.parseInt(fractions2[0]);
        int minuteToEnd = Integer.parseInt(fractions2[1]);
        int hourToStart = Integer.parseInt(fractions1[0]);
        int minuteToStart = Integer.parseInt(fractions1[1]);
        int hourDifference = hourToStart - actualHour - 1;
        int minutesDifference = 59 - actualMinute;
        if(hourDifference < 0 ){
            if(getIfDuringCourse(actualHour, actualMinute, hourToEnd, minuteToEnd ))
                return "ACUM";
            else return "A TRECUT";}
        return hourDifference + ":" + minutesDifference;
    }

    private boolean getIfDuringCourse(int actualHour, int actualMinute, int finalHour, int finalMinute){
        int hourDifference = finalHour - actualHour;
        int minuteDifference = hourDifference * 60 - actualMinute + finalMinute;
        if(minuteDifference > 0)
            return true;
        return false;
    }

    private int getWeekDifferenceFromFirst(Calendar firstDay, Calendar todayDate) {
        int firstDayofSemester = firstDay.get(Calendar.DAY_OF_YEAR);
        int today = todayDate.get(Calendar.DAY_OF_YEAR);
        if(today > firstDayofSemester){
            return (today - firstDayofSemester) / 7;
        }
        else if(firstDayofSemester > today){
            return (365 - firstDayofSemester + today) / 7;
        }
        return 0;
    }

    public String checkDate(Context context) {
        try {
            AssetManager mgr = context.getAssets();
            this.context = context;
            InputStream is = mgr.open("structura_2016.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            xdoc = dBuilder.parse(is);
            xdoc.normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String foundPeriod = searchInDomains(createDomainsForSearch());
        if (foundPeriod != "Did not find the period")
            return checkTime(dayExtractor(),foundPeriod);
        return foundPeriod;
    }

    private String checkTime(int day, String period){
        String dl = "";
        if(period.startsWith("predare")){
            if(day>0&&day<6){
                return getTimetableofDay(day);
            }
            return "Weekend";
        }
        else if(period.startsWith("sesiune")){return "Sesiune ";}// sesiune
        else if(period.startsWith("vacanta")){return "Vacanta";} //vacanta
        return "Did not find";
    }

    private String showDate(Calendar d) {

        return d.getTime().toString();
    }

    private int getInteger(Element element, String s) {
        return Integer.parseInt(element.getAttribute(s));
    }

    private NodeList findNodes(String s) {
        return xdoc.getElementsByTagName(s);
    }

    private boolean dateIntervalFound(Calendar c1, Calendar c2){

        return (c1.before(now) && c2.after(now));
    }

    private String searchDateInterval(NodeList nList){
        Calendar data_i, data_s;
        data_i = new GregorianCalendar();
        data_s = new GregorianCalendar();
        for (int  i= 0; i < nList.getLength(); i++) {
            Node n = nList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE)
            {
                Element e = (Element) n;
                data_i.set(getInteger(e,"an_i"), getInteger(e,"luna_i")-1, getInteger(e,"zi_i"), 0,0,0);
                data_s.set(getInteger(e,"an_s"), getInteger(e,"luna_s")-1, getInteger(e,"zi_s"), 23, 59, 59);

                if (dateIntervalFound(data_i, data_s)) {
                    weekOfFoundPeriod = Integer.parseInt(e.getAttribute("saptamana"));
                    weekOfFoundPeriod += getWeekDifferenceFromFirst(data_i,Calendar.getInstance());
                    return semester = findSemester(n);
                }
            }
        }
        return "Did not find period/semester";
    }

    private ArrayList<String> createDomainsForSearch(){
        ArrayList<String> tip = new ArrayList<String>();
        tip.add("predare");
        tip.add("sesiune");
        tip.add("vacanta");
        return tip;
    }

    private String searchInDomains(ArrayList<String> p){
        String g;
        for(int i=0;i<p.size();i++){
            perioada = findNodes(p.get(i));
            g = searchDateInterval(perioada);
            if (g.startsWith("semestrul")){
                return p.get(i);
            }
        }
        return "Did not find the period";
    }

    private String searchMethod2(){
        String g;
        perioada = findNodes("predare");
        g = searchDateInterval(perioada);
        if(g.startsWith("semestrul")) {
            return "It is during courses";
        }
        else{
            perioada = findNodes("sesiune");
            g = searchDateInterval(perioada);
            if (g.startsWith("semestrul")){
                return "It is during Exams";
            }
            else {
                perioada = findNodes("vacanta");
                g = searchDateInterval(perioada);
                if (g.startsWith("semestrul")) {
                    return "It is during holiday";
                } else {
                    return "This period was not found";
                }
            }
        }
    }

    public String findSemester(Node node) {
        return node.getParentNode().getNodeName();
    }

    private String getTimetableofDay(int day){
        String nameOfSubjects = "";
        TimetableClass tc = new TimetableClass();
        ArrayList<Materie> materieArrayList = tc.OpenTimetable(context, day);
        for(int i=0;i<materieArrayList.size();i++){
            nameOfSubjects +=  materieArrayList.get(i).getNume() + " ";
        }
        return nameOfSubjects;
    }
}
