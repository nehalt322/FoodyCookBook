package com.example.foodycookbook.Search;

public class SearchModal {
    String menu,category,downloadURL;

    public SearchModal(String menu, String category, String downloadURL) {
        this.menu = menu;
        this.category = category;
        this.downloadURL = downloadURL;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
