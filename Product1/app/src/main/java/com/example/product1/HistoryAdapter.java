package com.example.product1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private  List<History> historyList;
    private Context context;


    public HistoryAdapter(Context context, List<History> List) {
        this.context = context;
        this.historyList = List;

    }

    @Override
    public int getCount() {
        return this.historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        Viewholder viewholder;
        if(view == null){
            viewholder = new Viewholder();

            //convert resoure to view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom, null);

            //Anh xa
            viewholder.tvnameKH = view.findViewById(R.id.tvKH);
            viewholder.tvnameSP = view.findViewById(R.id.tvSP);
            viewholder.tvDate = view.findViewById(R.id.tvDate);
            viewholder.tvTongtien = view.findViewById(R.id.tvTong);



            view.setTag(viewholder);
        }
        else {
            viewholder = (Viewholder) view.getTag();
        }

        History history = historyList.get(i);

        viewholder.tvnameKH.setText( history.getNameCus()+"");
        viewholder.tvnameSP.setText( history.getNameSP()+"");
        viewholder.tvDate.setText( history.getDatetime()+"");
        viewholder.tvTongtien.setText( history.getTongtien() +"");


        return view;
    }
    class Viewholder{
        TextView tvnameKH, tvnameSP, tvDate, tvTongtien;
    }
}
