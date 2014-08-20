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
      "id": 21485409,
      "name": "Codeful",
      "full_name": "bundai223/Codeful",
      "owner": {
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
        "site_admin": false
      },
      "private": false,
      "html_url": "https://github.com/bundai223/Codeful",
      "description": "",
      "fork": false,
      "url": "https://api.github.com/repos/bundai223/Codeful",
      "forks_url": "https://api.github.com/repos/bundai223/Codeful/forks",
      "keys_url": "https://api.github.com/repos/bundai223/Codeful/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/bundai223/Codeful/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/bundai223/Codeful/teams",
      "hooks_url": "https://api.github.com/repos/bundai223/Codeful/hooks",
      "issue_events_url": "https://api.github.com/repos/bundai223/Codeful/issues/events{/number}",
      "events_url": "https://api.github.com/repos/bundai223/Codeful/events",
      "assignees_url": "https://api.github.com/repos/bundai223/Codeful/assignees{/user}",
      "branches_url": "https://api.github.com/repos/bundai223/Codeful/branches{/branch}",
      "tags_url": "https://api.github.com/repos/bundai223/Codeful/tags",
      "blobs_url": "https://api.github.com/repos/bundai223/Codeful/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/bundai223/Codeful/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/bundai223/Codeful/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/bundai223/Codeful/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/bundai223/Codeful/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/bundai223/Codeful/languages",
      "stargazers_url": "https://api.github.com/repos/bundai223/Codeful/stargazers",
      "contributors_url": "https://api.github.com/repos/bundai223/Codeful/contributors",
      "subscribers_url": "https://api.github.com/repos/bundai223/Codeful/subscribers",
      "subscription_url": "https://api.github.com/repos/bundai223/Codeful/subscription",
      "commits_url": "https://api.github.com/repos/bundai223/Codeful/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/bundai223/Codeful/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/bundai223/Codeful/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/bundai223/Codeful/issues/comments/{number}",
      "contents_url": "https://api.github.com/repos/bundai223/Codeful/contents/{+path}",
      "compare_url": "https://api.github.com/repos/bundai223/Codeful/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/bundai223/Codeful/merges",
      "archive_url": "https://api.github.com/repos/bundai223/Codeful/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/bundai223/Codeful/downloads",
      "issues_url": "https://api.github.com/repos/bundai223/Codeful/issues{/number}",
      "pulls_url": "https://api.github.com/repos/bundai223/Codeful/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/bundai223/Codeful/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/bundai223/Codeful/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/bundai223/Codeful/labels{/name}",
      "releases_url": "https://api.github.com/repos/bundai223/Codeful/releases{/id}",
      "created_at": "2014-07-04T04:30:28Z",
      "updated_at": "2014-07-07T02:50:37Z",
      "pushed_at": "2014-07-14T16:58:06Z",
      "git_url": "git://github.com/bundai223/Codeful.git",
      "ssh_url": "git@github.com:bundai223/Codeful.git",
      "clone_url": "https://github.com/bundai223/Codeful.git",
      "svn_url": "https://github.com/bundai223/Codeful",
      "homepage": null,
      "size": 212,
      "stargazers_count": 0,
      "watchers_count": 0,
      "language": "Java",
      "has_issues": true,
      "has_downloads": true,
      "has_wiki": true,
      "forks_count": 0,
      "mirror_url": null,
      "open_issues_count": 0,
      "forks": 0,
      "open_issues": 0,
      "watchers": 0,
      "default_branch": "master",
      "score": 15.291584
    },
}
 */
public class ResponseSearchRepository {

    private int total_count = 0;
    private boolean incompleteresults = false;
    private List<RepositoryInfo> items = null;

    public ResponseSearchRepository(List<RepositoryInfo> repositoryList) {
        items = repositoryList;
        total_count = items.size();
        incompleteresults = false;
    }

    public int getTotalCount() { return total_count; }
    public boolean isIncompletResult() { return incompleteresults; }
    public List<RepositoryInfo> getRepositories() { return items; }
}
