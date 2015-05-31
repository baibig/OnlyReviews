package com.baibig.onlyreviews.data;

import android.util.Log;

import com.baibig.onlyreviews.model.ReviewsRSSFeed;
import com.baibig.onlyreviews.model.ReviewsRSSItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HJ on 2015/5/17.
 */
public class ReviewsRSSHandler extends DefaultHandler {
    ReviewsRSSFeed feed;
    ReviewsRSSItem item;
    String lastElementName="";
    final int RSS_TITILE=1;
    final int RSS_LINK=2;
    final int RSS_DESCRIPTION=3;
    final int RSS_CREATOR=4;
    final int RSS_PUBDATE=5;
    final int RSS_CONTENT_ENCODED=6;
    int currentState=0;
    boolean isItem=false;
    private Stack<String> stack=new Stack<>();

    public ReviewsRSSHandler(){}

    @Override
    public void startDocument() throws SAXException {
        feed=new ReviewsRSSFeed();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Log.i("tag",localName.toString());
        if (localName.equals("channel")|localName.equals("rss")){
            currentState=0;
            return;
        }
        if (localName.equals("item")){
            item=new ReviewsRSSItem();
            isItem=true;
            return;
        }
        if (localName.equals("title")){
            currentState=RSS_TITILE;
            return;
        }
        if (localName.equals("link")){
            currentState=RSS_LINK;
            return;
        }
        if (localName.equals("description")){
            currentState=RSS_DESCRIPTION;
            stack.clear();
            return;
        }
        if (localName.equals("creator")){
            currentState=RSS_CREATOR;
            return;
        }
        if (localName.equals("pubDate")){
            currentState=RSS_PUBDATE;
            return;
        }
        if (localName.equalsIgnoreCase("encoded")){
            stack.clear();
            currentState=RSS_CONTENT_ENCODED;
            return;
        }
        currentState=0;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("item")){
            feed.addItem(item);
            isItem=false;
            return;
        }
        if (localName.equals("description")&&isItem){
            String sb=null;
            Log.i("des",stack.size()+"");
            int i=stack.size();
            while (!stack.empty()){
                if (stack.peek()!=null&&!stack.peek().equals("\n")) {
                    if (stack.size()==4){
                        item.setComment(stack.pop());
                        stack.pop();
                        String regex="http://movie.douban.com/subject/[0-9].*";
                        String movie=stack.pop();
                        Pattern pattern=Pattern.compile(regex);
                        Matcher matcher=pattern.matcher(movie);
                        if (matcher.find()){
                            pattern=Pattern.compile("[0-9].*");
                            Matcher m=pattern.matcher(matcher.group());
                            if (m.find()){
                                item.setMovie_id(m.group());
                            }

                        }


                    }else {
                        sb = stack.pop() + sb;
                    }
                }
                else
                    stack.pop();
            }
            currentState=0;
            item.setDescription(sb);
            return;
        }
        if (localName.equalsIgnoreCase("encoded")&&isItem){
            String s=null;
            while (!stack.isEmpty()){
                if (!stack.peek().equals("\n")){
                    s=stack.pop()+s;
                }else {
                    stack.pop();
                }
            }
            Document doc= Jsoup.parseBodyFragment(s);
            Element body=doc.body();
            Elements imgs=body.getElementsByTag("img");
            Element img=imgs.first();
            item.setImg(img.attr("src"));
            Elements creators=body.getElementsByTag("a");
            Element creator=creators.first();
            item.setCreator_url(creator.attr("href"));
            currentState=0;
            item.setContent_encoded(s);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isItem) {
            String s = new String(ch, start, length);

            switch (currentState) {
                case RSS_TITILE:
                    item.setTitle(s);
                    currentState = 0;
                    break;
                case RSS_LINK:
                    item.setLink(s);
                    currentState = 0;
                    break;
                case RSS_DESCRIPTION:
                    stack.push(s);
                    //Log.i("characters", s);
                    break;
                case RSS_CREATOR:
                    item.setCreator(s);
                    currentState = 0;
                    break;
                case RSS_PUBDATE:
                    item.setPubDate(s);
                    currentState = 0;
                    break;
                case RSS_CONTENT_ENCODED:
                    stack.push(s);
                    Log.i("encoded",s);
                    break;
                default:
                    break;
            }
        }
    }

    public ReviewsRSSFeed getFeed(){
        return feed;
    }

}
