package com.n1sh6r.net.github;

/**
 * Created by nishimuradaiji on 2014/07/15.
 */
public class RepositoryInfo {
    private int id = 0;
    private String name = null;
    private String full_name = null;
    private UserInfo owner = null;
    //private boolean private = false;
    private String html_url = null;
    private String description = null;
    private boolean fork = false;
    private String url = null;
    private String forks_url = null;
    private String keys_url = null;
    private String collaborators_url = null;
    private String teams_url = null;
    private String hooks_url = null;
    private String issue_events_url = null;
    private String branches_url = null;
    private String tags_url = null;
    private String blogs_url = null;
    private String git_tags_url = null;
    private String git_refs_url = null;
    private String trees_url = null;
    private String statuses_url = null;
    private String languages_url = null;
    private String stargazers_url = null;
    private String contributors_url = null;
    private String subscribers_url = null;
    private String subscription_url = null;
    private String commits_url = null;
    private String git_commits_url = null;
    private String comments_url = null;
    private String issue_comment_url = null;
    private String contents_url = null;
    private String compare_url = null;
    private String merges_url = null;
    private String archive_url = null;
    private String downloads_url = null;
    private String issues_url = null;
    private String pulls_url = null;
    private String milestones_url = null;
    private String notifications_url = null;
    private String labels_url = null;
    private String releases_url = null;
    private String createed_at = null;
    private String updated_at = null;
    private String pushed_at = null;
    private String git_url = null;
    private String ssh_url = null;
    private String clone_url = null;
    private String svn_url = null;
    private String homepage = null;
    private int size = 0;
    private int stargazers_count = 0;
    private int watchers_count = 0;
    private String language = null;
    private boolean has_issues = false;
    private boolean has_downloads = false;
    private boolean has_wiki = false;
    private int forks_count = 0;
    private String mirror_url = null;
    private int open_issues_count = 0;
    private int forks = 0;
    private int open_issues = 0;
    private int watchers = 0;
    private String default_branch = null;
    private float score = 0;

    public String getName() { return name; }
    public String getDefaultBranchName() { return default_branch; }
    public UserInfo getOwner() { return owner; }
}

