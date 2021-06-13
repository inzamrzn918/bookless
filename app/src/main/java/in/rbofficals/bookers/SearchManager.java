package in.rbofficals.bookers;

import android.content.Context;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import in.rbofficals.bookers.Model.Books;

public class SearchManager {
    private Context context;
    private String searchText;

    public SearchManager(Context context) {
        this.context = context;
    }

    public ArrayList<Books> search(String s) throws ExecutionException, InterruptedException {
       return new BackgroundTask().execute("https://google.com/search?q="+s).get();
    }


}
