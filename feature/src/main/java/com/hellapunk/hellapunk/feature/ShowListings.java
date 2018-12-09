package com.hellapunk.hellapunk.feature;

public class ShowListings {
    private String showTitle, showVenue, showDate, punklogo;

    public ShowListings(String showTitle, String showVenue, String showDate, String punklogo) {
        this.showTitle = showTitle;
        this.showVenue = showVenue;
        this.showDate = showDate;
        this.punklogo = punklogo;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public String getShowVenue() {
        return showVenue;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getPunklogo() {
        return punklogo;
    }
}
