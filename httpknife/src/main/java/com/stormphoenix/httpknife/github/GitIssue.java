package com.stormphoenix.httpknife.github;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Quinn on 10/4/15.
 */
public class GitIssue implements Serializable {

    private static final long serialVersionUID = 3212003836354165758L;

    private long id;
    private Date closedAt;
    private Date createdAt;
    private Date updatedAt;
    private int comments;
    private int number;
    private List<GitLabel> gitLabels;
    private String body;
    private String bodyHtml;
    private String bodyText;
    private String htmlUrl;
    private String state;
    private String title;
    private String url;
    private GitUser assignee;
    private GitUser user;

    public GitIssue() {
    }

    public Date getClosedAt() {
        return this.closedAt;
    }

    public GitIssue setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public GitIssue setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public GitIssue setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public int getComments() {
        return this.comments;
    }

    public GitIssue setComments(int comments) {
        this.comments = comments;
        return this;
    }

    public int getNumber() {
        return this.number;
    }

    public GitIssue setNumber(int number) {
        this.number = number;
        return this;
    }

    public List<GitLabel> getGitLabels() {
        return this.gitLabels;
    }

    public GitIssue setGitLabels(List<GitLabel> gitLabels) {
        this.gitLabels = gitLabels != null?new ArrayList(gitLabels):null;
        return this;
    }

    public String getBody() {
        return this.body;
    }

    public GitIssue setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBodyHtml() {
        return this.bodyHtml;
    }

    public GitIssue setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
        return this;
    }

    public String getBodyText() {
        return this.bodyText;
    }

    public GitIssue setBodyText(String bodyText) {
        this.bodyText = bodyText;
        return this;
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public GitIssue setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    public String getState() {
        return this.state;
    }

    public GitIssue setState(String state) {
        this.state = state;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public GitIssue setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public GitIssue setUrl(String url) {
        this.url = url;
        return this;
    }

    public GitUser getAssignee() {
        return this.assignee;
    }

    public GitIssue setAssignee(GitUser assignee) {
        this.assignee = assignee;
        return this;
    }

    public GitUser getUser() {
        return this.user;
    }

    public GitIssue setUser(GitUser user) {
        this.user = user;
        return this;
    }

    public long getId() {
        return this.id;
    }

    public GitIssue setId(long id) {
        this.id = id;
        return this;
    }

    public String toString() {
        return "GitIssue " + this.number;
    }
}
