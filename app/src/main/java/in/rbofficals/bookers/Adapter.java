package in.rbofficals.bookers;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.rbofficals.bookers.Model.Books;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Books> list;

    public Adapter(Context context, List<Books> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            Books book = list.get(position);
            holder.title.setText(book.getTitle());
            holder.description.setText(book.getDescription());
            holder.itemView.setOnClickListener(v->{
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.putExtra("link",book.getUrl());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_book_title);
            description = itemView.findViewById(R.id.item_description);
        }
    }
}
