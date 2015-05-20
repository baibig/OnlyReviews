package com.baibig.onlyreviews.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.baibig.onlyreviews.R;
import com.baibig.onlyreviews.app.AppController;
import com.baibig.onlyreviews.model.Actor;
import com.baibig.onlyreviews.model.Movie;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Created by HJ on 2015/5/19.
 */
public class MovieListAdapter extends ArrayAdapter {
    int resource;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public MovieListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie= (Movie) getItem(position);
        StringBuilder catSb=new StringBuilder();
        StringBuilder directorsSb=new StringBuilder();
        StringBuilder actorsSb=new StringBuilder();
        Vector<String> generss=new Vector<String>(Arrays.asList(movie.getGenres()));
        for (String s:generss){
            catSb.append(s);
        }
        Actor[] directors=movie.getDirectors();
        for (Actor a:directors){
            directorsSb.append(a.getName());
        }
        Actor[] casts=movie.getCasts();
        for (Actor a:casts){
            actorsSb.append(a.getName());
        }
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(resource,null);
            viewHolder.img= (NetworkImageView) convertView.findViewById(R.id.top_list_item_img);
            viewHolder.name= (TextView) convertView.findViewById(R.id.top_list_item_name);
            viewHolder.category= (TextView) convertView.findViewById(R.id.top_list_item_category);
            viewHolder.director= (TextView) convertView.findViewById(R.id.top_list_item_director);
            viewHolder.actors= (TextView) convertView.findViewById(R.id.top_list_item_actors);
            viewHolder.year= (TextView) convertView.findViewById(R.id.top_list_item_year);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setImageUrl(movie.getImages().getLarge(),imageLoader);
        viewHolder.name.setText(movie.getTitle());
        viewHolder.category.setText(catSb);
        viewHolder.director.setText(directorsSb);
        viewHolder.actors.setText(actorsSb);
        viewHolder.year.setText(movie.getYear());
        return convertView;
    }
    class ViewHolder{
        NetworkImageView img;
        TextView name;
        TextView category;
        TextView director;
        TextView actors;
        TextView year;
    }
}
