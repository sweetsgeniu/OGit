package com.stormphoenix.ogit.mvp.ui.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.widget.ImageView;

import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.mvp.ui.activities.base.BaseActivity;
import com.stormphoenix.ogit.shares.PreferenceUtils;
import com.stormphoenix.ogit.shares.RxJavaCustomTransformer;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.img_logo)
    ImageView mImgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLogoAnimation();
        timer();
    }

    private void startLogoAnimation() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1200);
        set.playTogether(ObjectAnimator.ofFloat(mImgLogo, "scaleX", new float[]{1.0F, 1.25F, 0.75F, 1.15F, 1.0F}),
                ObjectAnimator.ofFloat(mImgLogo, "scaleY", new float[]{1.0F, 0.75F, 1.25F, 0.85F, 1.0F}));
        set.start();
    }

    private void timer() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxJavaCustomTransformer.<Long>defaultSchedulers())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, null);

                        if (PreferenceUtils.isLogin(SplashActivity.this)) {
                            startActivity(MainActivity.newIntent(SplashActivity.this), options.toBundle());
                        } else {
                            startActivity(LoginActivity.getInstance(SplashActivity.this), options.toBundle());
                        }
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initializeInjector() {
    }
}