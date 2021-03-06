package com.stormphoenix.ogit.mvp.ui.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.adapters.FragmentsAdapter;
import com.stormphoenix.ogit.mvp.ui.activities.base.TabPagerActivity;
import com.stormphoenix.ogit.mvp.ui.fragments.EventsFragment;
import com.stormphoenix.ogit.mvp.ui.fragments.StaredFragment;
import com.stormphoenix.ogit.mvp.ui.fragments.base.BaseFragment;
import com.stormphoenix.ogit.utils.ActivityUtils;
import com.stormphoenix.ogit.utils.ImageUtils;
import com.stormphoenix.ogit.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends TabPagerActivity<FragmentsAdapter> implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tab_layout)
    SmartTabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    // 个人信息布局
    View mUserInfoView;
    CircleImageView mHeaderImage;
    TextView mTextUsername;

    FragmentsAdapter mAdapter;
    ActionBarDrawerToggle mDrawerToggle;

    @BindView(R.id.nav_view)
    NavigationView mNavView;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        loadPagerData();
    }

    private void initViews() {
        initToolbar();
        initDrawerView();
        initUserView();
    }

    private void loadPagerData() {
        if (!TextUtils.isEmpty(PreferenceUtils.getString(this, PreferenceUtils.AVATAR_URL))) {
            setHeaderImage(PreferenceUtils.getString(this, PreferenceUtils.AVATAR_URL));
        }
        mTextUsername.setText(PreferenceUtils.getUsername(this));
        configureTabPager();
    }

    /**
     * 初始化用户头像、用户名视图
     */
    private void initUserView() {
        mUserInfoView = mNavView.getHeaderView(0);
        mHeaderImage = (CircleImageView) mUserInfoView.findViewById(R.id.img_owner_header);
        mTextUsername = (TextView) mUserInfoView.findViewById(R.id.text_owner_name);
    }

    @Override
    protected FragmentsAdapter createAdapter() {
        String[] titleList = {"Event", "Starred"};
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(EventsFragment.getInstance(PreferenceUtils.getString(this, PreferenceUtils.USERNAME)));
        fragmentList.add(StaredFragment.getInstance(PreferenceUtils.getString(this, PreferenceUtils.USERNAME)));

        mAdapter = new FragmentsAdapter(this.getSupportFragmentManager());
        mAdapter.setFragmentList(fragmentList, titleList);
        return mAdapter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeInjector() {
    }

    /**
     * 初始化标题栏
     */
    public void initToolbar() {
        mToolbar.setTitle(this.getString(R.string.ogit));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 初始化侧滑栏视图
     */
    public void initDrawerView() {
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
    }

    /**
     * 加载用户图像
     *
     * @param url 用户图片的定位符
     */
    public void setHeaderImage(String url) {
        ImageUtils.getInstance().displayImage(url, mHeaderImage);
    }

    /**
     * 创建 Toolbar中的菜单选项
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_toolbar, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchActivity.class)));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MenuItemCompat.collapseActionView(searchMenuItem);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                MenuItemCompat.collapseActionView(searchMenuItem);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                MenuItemCompat.collapseActionView(searchMenuItem);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 设置Toolbar中的菜单被点击时间
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 设置侧滑栏点击时间
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                return true;
            case R.id.nav_org:
                Bundle bundle = new Bundle();
                bundle.putInt(ToolbarActivity.TYPE, ToolbarActivity.TYPE_ORGANIZATION);
                ActivityUtils.startActivity(this, ToolbarActivity.newIntent(this, bundle));
                return true;
            case R.id.nav_exit:
                // 退出登录
                PreferenceUtils.putBoolean(this, PreferenceUtils.IS_LOGIN, false);
//                ActivityUtils.startActivity(this, LoginActivity.newIntent(this));
                finish();
            default:
                return false;
        }
    }
}
