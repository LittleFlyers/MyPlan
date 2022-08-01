package com.zpf.myplan.ui.widget.navigation;

import android.content.res.XmlResourceParser;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationMenu {
    private List<BottomMenuItem> mItems;

    public BottomNavigationMenu() {
        mItems = new ArrayList<>();
    }

    public void addItem(XmlResourceParser parser) {
        String icon = parser.getAttributeValue(parser.getAttributeNamespace(0), "icon");
        String id = parser.getAttributeValue(parser.getAttributeNamespace(0), "id");
        String title = parser.getAttributeValue(parser.getAttributeNamespace(0), "title");
        BottomMenuItem item = new BottomMenuItem();
        item.setIcon(Integer.parseInt(icon.substring(1)));
        item.setId(Integer.parseInt(id.substring(1)));
        item.setTitle(Integer.parseInt(title.substring(1)));
        mItems.add(item);
    }

    public List<BottomMenuItem> getItems() {
        return mItems;
    }
}
