package com.stormphoenix.ogit.mvp.ui.fragments;

import android.os.Bundle;

import com.stormphoenix.httpknife.github.GitRepository;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.adapters.GitRepositoryAdapter;
import com.stormphoenix.ogit.adapters.base.BaseRecyclerAdapter;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.StaredPresenter;
import com.stormphoenix.ogit.mvp.presenter.base.ListItemPresenter;
import com.stormphoenix.ogit.mvp.ui.fragments.base.ListFragment;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by StormPhoenix on 17-2-27.
 * StormPhoenix is a intelligent Android developer.
 */
public class StaredFragment extends ListFragment<GitRepository> {
    @Inject
    StaredPresenter mRepositoryPresenter;

    public static StaredFragment getInstance(String username) {
        StaredFragment staredFragment = new StaredFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME, username);
        staredFragment.setArguments(bundle);
        return staredFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_recyclerview;
    }

    @Override
    public void initializeInjector() {
        DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this.getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public BaseRecyclerAdapter<GitRepository> getAdapter() {
        return new GitRepositoryAdapter(getActivity(), new ArrayList<>());
    }

    @Override
    public ListItemPresenter getListItemPresetner() {
        return mRepositoryPresenter;
    }

    @Override
    public void initListItemView() {
        super.initListItemView();
        mAdapter.addOnViewClickListener(R.id.repository_card_wrapper, mRepositoryPresenter);
    }
}
