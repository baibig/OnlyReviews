package com.baibig.onlyreviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibig.onlyreviews.model.Movie;

import java.util.List;

/**
 * Created by HJ on 2015/5/12.
 */
public class MovieAdapter extends ArrayAdapter {
    int resource;
    public MovieAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie= (Movie) getItem(position);
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(resource,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.movieIV);
            viewHolder.title= (TextView) convertView.findViewById(R.id.movieItemTitleTV);
            viewHolder.original= (TextView) convertView.findViewById(R.id.movieItemOriginalTV);
            viewHolder.rating= (TextView) convertView.findViewById(R.id.movieItemRatingTV);
            viewHolder.year= (TextView) convertView.findViewById(R.id.movieItemYear);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //viewHolder.imageView.setImageDrawable();
        viewHolder.title.setText(movie.getTitle());
        viewHolder.original.setText(movie.getOriginal_title());
        viewHolder.rating.setText(movie.getRating().getAverage()+"");
        viewHolder.year.setText(movie.getYear());
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView original;
        TextView rating;
        TextView year;
    }
}
