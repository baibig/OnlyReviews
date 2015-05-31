package com.baibig.onlyreviews.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.baibig.onlyreviews.app.AppController;
import com.baibig.onlyreviews.data.Constant;
import com.baibig.onlyreviews.data.ReviewsRSSHandler;
import com.baibig.onlyreviews.model.Actor;
import com.baibig.onlyreviews.model.Avatars;
import com.baibig.onlyreviews.model.Movie;
import com.baibig.onlyreviews.model.MoviesTop;
import com.baibig.onlyreviews.model.Review;
import com.baibig.onlyreviews.model.ReviewsRSSFeed;
import com.baibig.onlyreviews.model.ReviewsRSSItem;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by HJ on 2015/5/26.
 */
public class MovieParser {
    public static String ID="ID";
    public static String TITLE="data-title";
    public static String SCORE="data-score";
    public static String RELEASE="data-release";
    public static String DIRECTOR="data-director";
    public static String ACTORS="data-actors";
    public final static String SELECTLISTS=".lists";
    public final static String SELECTITEMS=".list-item";
    public static void getReviewsFromXml(final String id,final ListResultCallBack listener){

        RequestQueue queue=AppController.getInstance().getRequestQueue();
        StringRequest request=new StringRequest(id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ReviewsRSSFeed feed=reviewsXmlReslove(response);
                        List<ReviewsRSSItem> list=new ArrayList<>();
                        list.addAll(feed.getAllItems());
                        listener.onListResult(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }
    public static void getMoviesFromJson(final String id,final int start,final int count,final ListResultCallBack listener){
        final ArrayList<Movie> list=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("start",start+"");
        map.put("count", count + "");
        final JSONObject params=new JSONObject(map);
        RequestQueue queue= AppController.getInstance().getRequestQueue();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, Constant.TOP250,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        MoviesTop top = gson.fromJson(response.toString(), MoviesTop.class);
                        Movie[] movies = top.getSubjects();
                        for (Movie m : movies) {
                            list.add(m);
                            Log.i("tag", m.getTitle());
                        }
                        listener.onListResult(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("start",start+"");
                params.put("count",count+"");
                return params;
            }
        };
        queue.add(request);
    }
    public static void getMoviesFromHtml(final String id, final ListResultCallBack listener){
        final List<Movie> list=new ArrayList<>();
        RequestQueue queue= AppController.getInstance().getRequestQueue();
        StringRequest request=new StringRequest(Constant.MOVIEPLAYING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        listener.onListResult(moviesHtmlResolve(response, id, list));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
    }

    public static void getReviewFromHtml(final String id, final ListResultCallBack listener){
        RequestQueue queue=AppController.getInstance().getRequestQueue();
        StringRequest request=new StringRequest(id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Review r=reviewhtmlResolves(response);
                        List<Review> list=new ArrayList<>();
                        list.add(r);
                        listener.onListResult(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
    }
    private static Review reviewhtmlResolves(String response){
        Document doc=Jsoup.parse(response);
        Element body=doc.body();
        Element content=body.getElementById("link-report");
        Elements papers=content.getElementsByAttribute("property");
        Element paper=papers.first();
        String s=paper.text();
        String sb=s.replaceAll("<br>", "\n\r");
        Review r=new Review();
        r.setContent(sb);
        return r;
    }

    private static List<Movie> moviesHtmlResolve(String response, String id, List<Movie> list) {
        Document doc= Jsoup.parse(response);
        Element body=doc.body();
        Element div=body.getElementById(id);
        Elements lists=div.select(SELECTLISTS);
        Element listss=lists.get(0);
        Elements listitems=listss.select(SELECTITEMS);
        for (Element e:listitems){
            Movie m=new Movie();
            m.setId(e.attr(ID));
            m.setTitle(e.attr(TITLE));
            if (id=="nowplaying") {
                m.setYear(e.attr(RELEASE));
                String[] gen=new String[]{"正在上映"};
                m.setGenres(gen);
                String directors=e.attr(DIRECTOR);
                String[] dirs=directors.split(" / ");
                Actor[] directs=new Actor[dirs.length];
                for (int i=0;i<directs.length;i++){
                    directs[i]=new Actor();
                    directs[i].setName(dirs[i]);
                }
                m.setDirectors(directs);
                String actors=e.attr(ACTORS);
                String[] acts=actors.split(" / ");
                Actor[] as=new Actor[acts.length];
                for (int i=0;i<as.length;i++){
                    as[i]=new Actor();
                    as[i].setName(acts[i]);
                }
                m.setCasts(as);
            }else {
                Actor[] directs=new Actor[]{new Actor()};
                directs[0].setName("");
                m.setDirectors(directs);
                Actor[] as=new Actor[]{new Actor()};
                as[0].setName("");
                m.setCasts(as);
                Elements elem=e.select(".release-date");
                Element eleme=elem.get(0);
                m.setYear(eleme.text());
                String[] gen=new String[]{"即将上映"};
                m.setGenres(gen);

            }

            Elements el=e.select("img[src]");
            Element ele=el.get(0);
            String url=ele.attr("src");
            Avatars avatars=new Avatars();
            avatars.setLarge(url);
            m.setImages(avatars);
            list.add(m);
        }
        return list;
    }


    public static ReviewsRSSFeed reviewsXmlReslove(String s) {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlReader=parser.getXMLReader();
            ReviewsRSSHandler handler=new ReviewsRSSHandler();
            xmlReader.setContentHandler(handler);
            InputSource is=new InputSource(new StringReader(s));
            xmlReader.parse(is);
            return handler.getFeed();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public interface ListResultCallBack{
        void onListResult(List<?> list);
    }
}
