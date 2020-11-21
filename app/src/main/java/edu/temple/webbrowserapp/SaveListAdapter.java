package edu.temple.webbrowserapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
class ViewHolder{
    TextView textView;
    ImageButton dialogButton;
}

public class SaveListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> savedPageTitles;

    public SaveListAdapter(Context context, ArrayList<String> savedPageTitles){
        this.savedPageTitles = savedPageTitles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return savedPageTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return savedPageTitles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder vh;
        View v = convertView;
        if(v == null) {
            vh = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.activity_saved_list, null);
            vh.textView = v.findViewById(R.id.textView);
            vh.dialogButton = v.findViewById(R.id.removeButton);
            v.setTag(vh);
        }

        ViewHolder vhs = (ViewHolder) v.getTag();
        vhs.textView.setText(getItem(position).toString());

        vhs.dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //  Set alert dialog title
                alertDialogBuilder.setTitle("Delete bookmark");

                //  Set alert dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete this bookmark?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Toast.makeText(context, "Bookmark deleted", Toast.LENGTH_SHORT).show();
                                savedPageTitles.remove(position);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                //  Create dialog from Builder class
                AlertDialog alertDialog = alertDialogBuilder.create();

                //  Display the dialog
                alertDialog.show();
            }
        });

        return v;
    }

}
