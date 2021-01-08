package com.example.onlinelibrary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.onlinelibrary.R;
import com.example.onlinelibrary.model.Sale;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SaleAdapter extends BaseAdapter {

private Context context;
private List<Sale>saleList;

    public SaleAdapter(Context context, List<Sale> saleList) {
        this.context = context;
        this.saleList = saleList;
    }

    @Override
    public int getCount() {
        return saleList.size();
    }

    @Override
    public Object getItem(int position) {
        return saleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SaleHolder saleHolder;

        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.lvbooksale,parent,false);
            saleHolder = new SaleHolder(convertView);
            convertView.setTag(saleHolder);
        }
        else{
            saleHolder = (SaleHolder) convertView.getTag();
        }

        Sale item = (Sale) getItem(position);
        saleHolder.bookName.setText(item.getBookName());
        saleHolder.bookCondition.setRating(item.getBookCondition());
        saleHolder.authorName.setText(item.getAuthorName());
        saleHolder.bookPrice.setText("$"+item.getBookPrice().toString());
        saleHolder.payMethod.setText(item.getPaymentMethod());
        try{
            saleHolder.endDate.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(item.getEndingDate()));
        }catch (Exception e){
            e.printStackTrace();
        }

        if(item.getAuthorSigned()){
            saleHolder.authorSigned.setText("Is signed: YES");
        }
        else{
            saleHolder.authorSigned.setText("Is signed: NO");
        }


        return convertView;
    }

    private class SaleHolder {
        public TextView bookName;
        public TextView authorName;
        public RatingBar bookCondition;
        public TextView bookPrice;
        public TextView payMethod;
        public TextView endDate;
        public TextView authorSigned;

        public SaleHolder(View convertView) {
            bookName = convertView.findViewById(R.id.tvBookName);
            authorName = convertView.findViewById(R.id.tvAuthorName);
            bookCondition = convertView.findViewById(R.id.rbBookCondition);
            bookPrice = convertView.findViewById(R.id.tvBookPrice);
            payMethod = convertView.findViewById(R.id.tvDisplayPaymentMethod);
            authorSigned = convertView.findViewById(R.id.tvAuthorSignature);
            endDate = convertView.findViewById(R.id.tvDisplayEndingDate);


        }
    }
    public void deleteBookSale(int position){
        saleList.remove(position);
    }
    }








