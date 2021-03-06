package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.httpknife.github.GitBlob;
import com.stormphoenix.httpknife.github.GitBranch;
import com.stormphoenix.httpknife.github.GitTree;
import com.stormphoenix.httpknife.github.GitUser;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by StormPhoenix on 17-3-10.
 * StormPhoenix is a intelligent Android developer.
 *
 * 用于获取某一个仓库的信息
 */

public interface RepoApi {
        /**
     * 获取项目分支
     *
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/branches")
    Observable<Response<List<GitBranch>>> loadBranches(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contributors?&per_page=10")
    Observable<Response<List<GitUser>>> loadContributors(@Path("owner") String owner, @Path("repo") String repository, @Query("page") String page);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}")
    Observable<Response<GitTree>> loadRepoTree(@Path("owner") String owner, @Path("repo") String repo, @Path("sha") String sha);
}
