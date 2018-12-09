package com.hellapunk.hellapunk.feature;

public class WelcomeListings {
    private String welcomeTitle, welcomeSummary, welcomeMsg, welcomePic;

    public WelcomeListings(String welcomeTitle, String welcomeSummary, String welcomeMsg, String welcomePic) {
        this.welcomeTitle = welcomeTitle;
        this.welcomeSummary = welcomeSummary;
        this.welcomeMsg = welcomeMsg;
        this.welcomePic = welcomePic;
    }

    public String getWelcomeTitle() {
        return welcomeTitle;
    }

    public String getWelcomeSummary() {
        return welcomeSummary;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String getWelcomePic() {
        return welcomePic;
    }

}
