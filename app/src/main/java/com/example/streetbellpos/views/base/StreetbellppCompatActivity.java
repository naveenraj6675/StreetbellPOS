package com.example.streetbellpos.views.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.managers.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;

public abstract class StreetbellppCompatActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getName();
    protected int color;

    private ViewGroup mBaseView = null;
    private View mLoaderView = null;
    private boolean progressShown = false;

    private boolean doNotShowProgress = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String colorString = getSharedPrefManager().getPreferenceDefNull(StreetBellConstants.APP_COLOR);
        if (colorString != null) {
            color = Color.parseColor(colorString);
            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
            //TODO - figure out how to change color of all views
        }
        initProgress();
    }

    //Initializing the progress view
    private void initProgress() {
        mBaseView = this.findViewById(android.R.id.content);
        mLoaderView = View.inflate(this, R.layout.loader, null);
    }

    //To show loader progress
    protected void showProgress() {
        hideKeyboard();
        if (!doNotShowProgress) {
            if (!progressShown) {
                mBaseView.addView(mLoaderView);
                progressShown = true;
            }
        }
    }

    //To hide loader progress
    protected void hideProgress() {
        if (progressShown) {
            mBaseView.removeView(mLoaderView);
            progressShown = false;
        }
    }

    public void setDoNotShowProgress(boolean doNotShowProgress) {
        this.doNotShowProgress = doNotShowProgress;
    }

    //Call this method while setting up Viewmodel to init progress
//    protected void setUpLoader(MBodyBaseViewModel viewModel) {
//        viewModel.getLoadingLiveData().observe(this, loaderStatus -> {
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
//
//        });
//
//        viewModel.getErrorModelLiveData().observe(this, observer -> {
//            if (observer != null)
//                showSnackbar(observer.getMessage());
//        });
//
//        viewModel.getLogoutLiveData().observe(this, observer -> {
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
//
//    }

    public abstract void initObservers();


//    private void redirectToSplash() {
//        Intent intent = new Intent(this, SplashActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }


    public SharedPrefManager getSharedPrefManager() {
        return SharedPrefManager.getInstance(this);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }


    public void showSnackbar(String message) {
        SpannableStringBuilder snackbarMessage = new SpannableStringBuilder(message);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), snackbarMessage,
                Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
        TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    public void setColor(String colorString) {
        if (colorString != null) {
            color = Color.parseColor(colorString);
            getSharedPrefManager().setPreference(StreetBellConstants.APP_COLOR, colorString);
        } else {
            color = 0;
            getSharedPrefManager().removePreference(StreetBellConstants.APP_COLOR);
        }
    }

    // To show AlertDialogOk
    protected void showAlertDialogOk(String title, String message, DialogInterface.OnClickListener listener) {
        if (!isFinishing()) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("OK", listener);
            AlertDialog mAlertDialog = builder.create();
            mAlertDialog.setCanceledOnTouchOutside(false);
            mAlertDialog.setCancelable(false);
            mAlertDialog.show();
        }
    }

    protected void showConfirmation(String negativeText, String positiveText, String title, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        if (title != null)
            builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, listener);
        builder.setNegativeButton(negativeText, negativeListener);
        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
        Button cancelBtn = mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        cancelBtn.setTextColor(getResources().getColor(R.color.error));
    }


}
