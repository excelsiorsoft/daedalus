/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;


/**
 * News Headline class with story in it
 */
public class NewsStory extends NewsHeadline {
    private String story;

    public NewsStory() {
    }

    public NewsStory(String id, String headline, String date, String story) {
        super(id, headline, date);
        this.story = story;
    }

    /**
     * the body text of the Article
     *
     * @return Text of article
     */
    public String getStory() {
        return story;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsStory)) return false;
        if (!super.equals(o)) return false;

        NewsStory newsStory = (NewsStory) o;

        if (!story.equals(newsStory.story)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + story.hashCode();
        return result;
    }
}
