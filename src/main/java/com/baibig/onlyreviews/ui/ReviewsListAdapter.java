package com.baibig.onlyreviews.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.baibig.onlyreviews.R;
import com.baibig.onlyreviews.model.ReviewsRSSItem;

import java.util.List;

/**
 * Created by HJ on 2015/5/17.
 */
public class ReviewsListAdapter extends ArrayAdapter {
    int resourceid;
    public ReviewsListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceid=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReviewsRSSItem item= (ReviewsRSSItem) getItem(position);
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(resourceid,null);
            viewHolder.title= (TextView) convertView.findViewById(R.id.reviews_list_item_title_tv);
            viewHolder.author= (TextView) convertView.findViewById(R.id.reviews_list_item_author_tv);
            viewHolder.time= (TextView) convertView.findViewById(R.id.reviews_list_item_time_tv);
            viewHolder.description= (TextView) convertView.findViewById(R.id.reviews_list_item_description_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(item.getTitle());
        viewHolder.author.setText(item.getCreator());
        viewHolder.time.setText(item.getPubDate());
        viewHolder.description.setText(item.getDescription());
        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView author;
        TextView time;
        TextView description;
    }
}
