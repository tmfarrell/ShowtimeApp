package libs;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * SeriesData:  dictionary data structure to facilitate parsing, storage
 *              and display of series XML, as extracted from the TVDB.
 */
public class SeriesData extends LinkedHashMap<String, String> {

    /* Fields */
    //tag labels of interest to be extracted from TVDB XML
    ArrayList<String> tags = new ArrayList<String>();

    public final static String TVDB_API_KEY = "531FC560FB062CB3";


    /* Constructors */
    //no arg constructor: adds default tag labels
    public SeriesData() {
        tags.add("seriesname");
        tags.add("seriesid");
        tags.add("genre");
        tags.add("overview");
        tags.add("status");
        tags.add("dayofweek");
        tags.add("time");
        tags.add("network");
        tags.add("runtime");
        tags.add("rating");
        tags.add("ratingcount");
        tags.add("actors");
        tags.add("contentrating");
        tags.add("firstaired");
        tags.add("id");
    }

    public SeriesData(String [] _tags) {
        this();                         //add default tags
        for(String tag : _tags)          //then add input tags
            tags.add(tag);
    }


    /* Methods */
    //inserts XML from a theTVDB XML string
    public void insertSelectXML(String[] strs) {
        String key, value, key_dummy;                   //dummy vars to hold tokens

        for(int i = 1; i < strs.length - 1; i += 2) {   //foreach token pair

            //clean tokens and add to dummies
            key   = strs[i].toLowerCase().replaceAll("\\W*", "");
            value = strs[i+1].replaceAll("</[a-zA-Z]*", "");

            //process dummies further
            if(key.contains("_"))
                key = key.substring(key.indexOf('_') + 1);
            if(value.contains("_"))
                value = value.substring(0, value.indexOf('_'));

            //if key token is in tag labels and if value of key not yet init
            if(tags.contains(key) && this.get(key) == null)
                this.put(key, value);

            else if (key.contains("episodenumber")) {
                key = key + " " + value;

                i = i + 2;

                key_dummy   = strs[i].toLowerCase().replaceAll("\\W*", "");
                value       = strs[i+1].replaceAll("</[a-zA-Z]*", "");

                if(key_dummy.contains("firstaired")) {
                    value = key_dummy + " on " + value;
                    this.put(key, value);
                }
            }
        }
    }

    //inserts XML from a theTVDB XML string
    public void insertAllXML(String[] strs) {
        String key, value;                              //dummy vars to hold tokens

        for(int i = 1; i < strs.length - 1; i += 2) {   //foreach token pair

            //clean tokens and add to dummies
            key   = strs[i].toLowerCase().replaceAll("\\W*", "");
            value = strs[i+1].replaceAll("</[a-zA-Z]*", "");

            //insert into dict
            this.put(key, value);
        }
    }


    //prints entries in clean format
    @Override
    public String toString() {
        String str = "";

        for (String tag : tags) {
            if (this.get(tag) != null)
                str += tag + ": " + this.get(tag) + "\n\n";
        }

        Iterator entries = this.entrySet().iterator();
        while(entries.hasNext()) {
            LinkedHashMap.Entry entry = (LinkedHashMap.Entry) entries.next();

            if (!tags.contains(entry.getKey().toString()))
                str += entry.getKey().toString() + ": " + entry.getValue().toString() + "\n\n";
        }

        if(str != "")
            return str;
        else
            return "\nThere were no results for your search.";
    }
}

