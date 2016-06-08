package com.example.arranque1.appsisteme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sergio Cano on 06/06/2016.
 */
public class LogAdapter extends ArrayAdapter {
    Context context;
    List logList;

    public LogAdapter(Context context, List logList) {
        super(context, R.layout.list_view_row_log, logList);
        this.context = context;
        this.logList = logList;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView stateText, dateText;
        View itemView;

        LayoutInflater inflater = LayoutInflater.from(context);
        itemView = inflater.inflate(R.layout.list_view_row_log, null);
        stateText = (TextView)itemView.findViewById(R.id.state);
        dateText = (TextView)itemView.findViewById(R.id.date);

        Log log = (Log)getItem(position);
        stateText.setText(log.getState());
        dateText.setText(log.getDate());

        return itemView;
    }
}
