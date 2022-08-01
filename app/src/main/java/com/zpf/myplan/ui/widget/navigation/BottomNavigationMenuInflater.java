package com.zpf.myplan.ui.widget.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class BottomNavigationMenuInflater {
    private static final String TAG = "BottomNavigationMenuInflater";

    private final Context mContext;

    public BottomNavigationMenuInflater(Context context) {
        mContext = context;
    }

    @SuppressLint("ResourceType")
    public void inflate(int menuRes,BottomNavigationMenu menu) {
        XmlResourceParser parser = null;
        try {
            parser = mContext.getResources().getXml(menuRes);
            String tagName;
            int eventType = parser.getEventType();
            do {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("menu")) {
                        // Go to next tag
                        eventType = parser.next();
                        break;
                    }
                }
                eventType = parser.next();
            } while (eventType != XmlPullParser.END_DOCUMENT);
            boolean reachedEndOfMenu = false;
            while (!reachedEndOfMenu) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        if (tagName.equals("item")) {
                            menu.addItem(parser);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if ("menu".equals(tagName)){
                            reachedEndOfMenu = true;
                        }
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        throw new RuntimeException("Unexpected end of document");
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
