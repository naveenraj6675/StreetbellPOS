package com.example.streetbellpos.views.base;

import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.example.streetbellpos.R;


public abstract class StreetbellViewModelActivity extends StreetbellppCompatActivity {
    private ViewGroup mBaseView;
    private View mLoaderView;
    private boolean progressShown = false;
    private ProgressBar mLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        initProgress();
    }

//    protected void setUpViewModel(StreetbellBaseViewModel baseViewModel) {
//        baseViewModel.getLoadingLiveData().observe(this, loaderStatus -> {
//            switch (loaderStatus.getLoader()) {
//                case LOADER_STATUS.NONE:
//                    hideProgress();
//                    break;
//                case LOADER_STATUS.SUCCESS:
//                    hideProgress();
//                    break;
//                case LOADER_STATUS.LOADING:
//                    showProgress();
//                    break;
//                case LOADER_STATUS.FAILED:
//                    hideProgress();
//                    break;
//            }
//        });
//
//        baseViewModel.getErrorModelLiveData().observe(this, errorModel -> {
//            showSnackbar(errorModel.getMessage());
//        });
//
//        baseViewModel.getErrorModelLiveData().observe(this, observer -> {
//            if ( observer != null)
//                showSnackbar(observer.getMessage());
//        });
//
//        baseViewModel.getLogoutLiveData().observe(this, observer ->{
//            showAlertDialogOk("", observer, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    redirectToSplash();
//                }
//            });
//        });
//
//        initObservers();
//    }


    public void initProgress() {
        mBaseView = this.findViewById(android.R.id.content);
        mLoaderView = View.inflate(this, R.layout.loader, null);
        mLoader = mLoaderView.findViewById(R.id.loader);
        if (color > 0) {
            mLoader.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        mLoaderView.setOnTouchListener((v, event) -> true);
    }

    public void showProgress() {
        hideKeyboard();
        if (!progressShown) {
            mBaseView.addView(mLoaderView);
            progressShown = true;
        }
    }

    public void hideProgress() {
        if (progressShown) {
            mBaseView.removeView(mLoaderView);
            progressShown = false;
        }
    }

    public abstract void initObservers();

}