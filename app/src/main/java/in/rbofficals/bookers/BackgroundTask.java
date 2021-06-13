package in.rbofficals.bookers;

import android.os.AsyncTask;
import android.util.Log;

import org.bouncycastle.crypto.engines.ElGamalEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import in.rbofficals.bookers.Model.Books;

public class BackgroundTask extends AsyncTask<String, Void, ArrayList<Books>> {
    private String TAG = "LOGLAVEL";
    @Override
    protected ArrayList<Books> doInBackground(String... args) {
        String url = "https://google.com/search?q="+args[0];
        ArrayList<Books> list = new ArrayList<>();

        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            Elements resultDiv = doc.select("div.tF2Cxc");
            Elements div = doc.select("div.yuRUbf");

            for(Element d : resultDiv){
                String da = d.child(0).child(0).attr("href");
                if(da.endsWith("pdf")) {
                    String title = d.child(0).child(0).child(1).text();
                    Elements decElem = d.child(1).child(0).child(0).children();

                    StringBuilder buffer = new StringBuilder();
                    for (Element de : decElem) {
                        buffer.append(de.text()).append(" ");
                    }
                    list.add(new Books(title, da, buffer.toString()));
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Books> s) {

    }
}
