package jp.curigeo.net.github;

/**
 * Created by nishimuradaiji on 2014/07/15.
 */
public class UserInfo {
    private String login = null;
    private int id = 0;
    private String avatar_url = null;
    private String gravatar_id = null;
    private String url = null;
    private String html_url = null;
    private String followers_url = null;
    private String following_url = null;
    private String gists_url = null;
    private String starred_url = null;
    private String subscriptions_url = null;
    private String organizations_url = null;
    private String repos_url = null;
    private String events_url = null;
    private String received_events_url = null;
    private String type = null;
    private boolean site_admin = false;
    private float score = 0;

    public String   getName() { return login; }
    public int      getId() { return id; }
    public String   getAvatarUrl() { return avatar_url; }
    public String   getUrl() { return url; }
    public String   getHtmlUrl() { return html_url; }
    public String   getFollowersUrl() { return followers_url; }
    public String   getFollowingUrl() { return following_url; }
    public String   getGistsUrl() { return gists_url; }
    public String   getStarredUrl() { return starred_url; }
    public String   getSubscriptionsUrl() { return subscriptions_url; }
    public String   getOrganizationsUrl() { return organizations_url; }
    public String   getReposUrl() { return repos_url; }
    public String   getEventsUrl() { return events_url; }
    public String   getReceivedEventsUrl() { return received_events_url; }
    public String   getType() { return type; }
    public boolean  getSiteAdmin() { return site_admin; }
    public float    getScore() { return score; }
}
