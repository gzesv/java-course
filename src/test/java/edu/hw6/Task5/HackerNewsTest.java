package edu.hw6.Task5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HackerNewsTest {

    @Test
    void hackerNewsTopStories_test() {
        HackerNews hackerNews = new HackerNews();

        long[] ids = hackerNews.hackerNewsTopStories();

        assertThat(ids).isNotNull();
    }

    @Test
    void news_test() {
        long newsId = 37570037;
        String expected = "JDK 21 Release Notes";
        HackerNews hackerNews = new HackerNews();

        String result = hackerNews.news(newsId);

        assertThat(result).isEqualTo(expected);
    }
}
