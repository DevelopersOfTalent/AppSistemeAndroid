package com.example.arranque1.appsisteme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sergio Cano on 01/06/2016.
 */
public class LogAdapter extends BaseAdapter{
    Context context;
    List<Log> logList;
    LayoutInflater inflater;

    public LogAdapter(Context context, List<Log> logList) {
        this.context = context;
        this.logList = logList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        Log log = logList.get(position);
        return log;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView stateText, stateDate;
        View itemView;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        itemView = inflater.inflate(R.layout.list_view_row_log, parent, false);
        stateText = (TextView)itemView.findViewById(R.id.state);
        stateDate = (TextView)itemView.findViewById(R.id.date);

        Log log = (Log)getItem(position);
        stateText.setText(log.getState());
        stateDate.setText(log.getDate());
        return itemView;
    }
}
