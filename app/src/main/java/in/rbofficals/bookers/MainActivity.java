package in.rbofficals.bookers;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import in.rbofficals.bookers.Model.Books;

public class MainActivity extends AppCompatActivity {

    private EditText searchEdittext;
    private SearchManager searchManager;
    private String searchText;
    private ImageButton btn_search;
    private List<Books> list;
    private Adapter adapter;
    private RecyclerView booksView;
    private TextView no_item;
    private ProgressBar pbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEdittext = findViewById(R.id.searchEdittext);
        searchManager = new SearchManager(this);
        btn_search = findViewById(R.id.btn_search);
        list  = new ArrayList<>();
        booksView = findViewById(R.id.book_view);
        booksView.setLayoutManager(new LinearLayoutManager(this));
        no_item = findViewById(R.id.no_item);
        pbr = findViewById(R.id.pbr);


        btn_search.setOnClickListener(v -> {
            list.clear();
            no_item.setVisibility(View.GONE);
            pbr.setVisibility(View.VISIBLE);
            pbr.setProgress(0);
            searchText = searchEdittext.getText().toString();
            if (searchText.length() < 4) {
                searchEdittext.setError("Minimum 3 Character required to search");
                no_item.setText("Minimum 3 Character required to search");
                no_item.setVisibility(View.VISIBLE);
                pbr.setProgress(30);
                return;
            }
            pbr.setProgress(50);
            try {
                list = searchManager.search(searchText+" pdf");
                adapter = new Adapter(getApplicationContext(),list);
                booksView.setAdapter(adapter);
                pbr.setProgress(100);
                adapter.notifyDataSetChanged();
                pbr.setVisibility(View.GONE);
                if(list.size()<1){
                    no_item.setVisibility(View.VISIBLE);
                }

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                no_item.setVisibility(View.VISIBLE);
                no_item.setText(e.getMessage());
                pbr.setProgress(100);
                pbr.setVisibility(View.GONE);

            }
        });
    }

}