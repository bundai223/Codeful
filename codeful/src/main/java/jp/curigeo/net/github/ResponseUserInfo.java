package jp.curigeo.net.github;

import java.util.List;

/**
 * Created by daiji on 2014/07/15.
 */
/*
{
  "total_count": 1,
  "incomplete_results": false,
  "items": [
    {
      "login": "bundai223",
      "id": 1002198,
      "avatar_url": "https://avatars.githubusercontent.com/u/1002198?",
      "gravatar_id": "1b0d2c38d97f5cb0b252d6f851bea28e",
      "url": "https://api.github.com/users/bundai223",
      "html_url": "https://github.com/bundai223",
      "followers_url": "https://api.github.com/users/bundai223/followers",
      "following_url": "https://api.github.com/users/bundai223/following{/other_user}",
      "gists_url": "https://api.github.com/users/bundai223/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/bundai223/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/bundai223/subscriptions",
      "organizations_url": "https://api.github.com/users/bundai223/orgs",
      "repos_url": "https://api.github.com/users/bundai223/repos",
      "events_url": "https://api.github.com/users/bundai223/events{/privacy}",
      "received_events_url": "https://api.github.com/users/bundai223/received_events",
      "type": "User",
      "site_admin": false,
      "score": 38.44821
    }
  ]
}
 */
public class ResponseUserInfo {
    public class UserInfo {
        String login = null;
        int id = 0;
        String avatar_url = null;
        String gravatar_id = null;
        String url = null;
        String html_url = null;
        String followers_url = null;
        String following_url = null;
        String gists_url = null;
        String starred_url = null;
        String subscriptions_url = null;
        String organizations_url = null;
        String repos_url = null;
        String events_url = null;
        String received_events_url = null;
        String type = null;
        boolean site_admin = false;
        float score = 0;
    };

    private int total_count = 0;
    private boolean incompleteresults = false;
    private List<UserInfo> items = null;
}
