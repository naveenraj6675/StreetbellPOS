package com.example.streetbellpos.views.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.managers.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;


public abstract class StreetbellBaseFragment extends Fragment {
    protected final String TAG = this.getClass().getName();
    protected int color;
    private ViewGroup mBaseView;
    private View mLoaderView;
    private boolean progressShown = false;
    private boolean futureShowProgress = false;
    private ProgressBar mLoader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String colorString = getSharedPrefManager().getPreferenceDefNull(StreetBellConstants.APP_COLOR);
        if (colorString != null) {
            color = Color.parseColor(colorString);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initProgress(view);
        if (color > 0) {
            mLoader.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            //TODO - figure out how to change color of all views
        }
    }


    protected void initProgress(final View view) {
//        mBaseView = (ViewGroup) view;
//
//        mLoaderView = View.inflate(getActivity(), R.layout.loader, null);
//        mLoader = mLoaderView.findViewById(R.id.loader);
//        mLoaderView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//
//        if (mBaseView != null && futureShowProgress)
//            showProgress();

        mBaseView = (ViewGroup) view;

        mLoaderView = View.inflate(getActivity(), R.layout.loader, null);
        mLoaderView.setId(View.generateViewId());
        if (mBaseView != null) {
            if (futureShowProgress)
                showProgress();
        }
        initObservers();

    }


    //Call this method while setting up Viewmodel to init progress
//    protected void setUpLoader(StreetbellBaseViewModel viewModel) {
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
//            if ( observer != null) {
//                if (!observer.getMessage().equals("This exercise is already saved!"))
//                    showSnackbar(mBaseView, observer.getMessage());
//            }
//        });
//
//        viewModel.getLogoutLiveData().observe(this, observer ->{
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
//
//    private void redirectToSplash() {
//        Intent intent = new  Intent(getActivity(), SplashActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
//        startActivity(intent);
//    }

    protected abstract void initObservers();

//    abstract void onApiError(String errorMessage);

    private void showProgress() {
        hideKeyboard();
        if (mBaseView == null) {
            futureShowProgress = true;
        } else if (!progressShown) {
            mBaseView.addView(mLoaderView);
            progressShown = true;
            futureShowProgress = false;
        } else {
            futureShowProgress = false;
        }
    }

    private void hideProgress() {
        futureShowProgress = false;
        if (progressShown) {
            if (mBaseView != null) {
                mBaseView.removeView(mLoaderView);
            }
            progressShown = false;
        }
    }


    public SharedPrefManager getSharedPrefManager() {
        return SharedPrefManager.getInstance(getActivity());
    }

    public void hideKeyboard() {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = getActivity().getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(getActivity());
            }
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void showKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null && inputManager != null) {
            inputManager.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }


    public void showSnackbar(View view, String text) {
        try {
            Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary));
            TextView tv = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            tv.setGravity(Gravity.CENTER);
            snackbar.show();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    protected void showAlertDialogOk(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", listener);
        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.show();
    }

    protected void showConfirmation(String negativeText, String positiveText, String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, listener);
        builder.setNegativeButton(negativeText, (dialog, which) -> dialog.dismiss());
        AlertDialog mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();
    }
}
