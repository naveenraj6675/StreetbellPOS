package com.example.streetbellpos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class StreetbellBaseViewModel extends AndroidViewModel {

    protected final String TAG = getClass().getSimpleName();


    public StreetbellBaseViewModel(@NonNull Application application) {
        super(application);
    }

//
//    public MutableLiveData<ErrorModel> getErrorModelLiveData() {
//        return errorModelLiveData;
//    }
//
//    public void checkAndDisplayError(BaseApiResponse apiResponse) {
//        String errorMessage;
//        if (apiResponse.getErrorModel().getMessage() != null) {
//            errorMessage = apiResponse.getErrorModel().getMessage();
//        }else
//            errorMessage = "We apologize. There is something wrong on our side. Please let us know by choosing Contact from the menu.";
//
//        if (apiResponse.getErrorModel().getCode() == 403 && apiResponse.getErrorModel().getMessage().equals("Signature has expired") || apiResponse.getErrorModel().getMessage().equals("Not enough or too many segments") || apiResponse.getErrorModel().getMessage().equals("Not Authenticated") ) {
//            forceLogout("Signature has expired");
//        } else {
//            errorModelLiveData.postValue(apiResponse.getErrorModel());
//        }
//    }
//
//    public void checkResponseError(Throwable e) {
//        if (e instanceof HttpException) {
//            ResponseBody errorBody = ((HttpException) e).response().errorBody();
//            if (errorBody != null) {
//                try {
//                    String jsonString = errorBody.string();
//                    if (jsonString.contains("{")) {
//                        ErrorModel errorModel = new ErrorModel(jsonString, ((HttpException) e).code());
//                        if (errorModel.getMessage().equals("Signature has expired")) {
//                            forceLogout(errorModel.getMessage());
//                        } else
//                            errorModelLiveData.postValue(errorModel);
//                    } else {
//                        errorModelLiveData.postValue(new ErrorModel(-1,e.getMessage()));
//                    }
//                } catch (IOException | JSONException exception) {
//                    errorModelLiveData.postValue(new ErrorModel(-1,e.getMessage()));
//                }
//            } else {
//                errorModelLiveData.postValue(new ErrorModel(-1,e.getMessage()));
//            }
//        } else {
//            errorModelLiveData.postValue(new ErrorModel(-1,e.getMessage()));
//        }
//    }
//
//
//    private void forceLogout(String message) {
//
//        SharedPrefManager.getInstance(getApplication()).clearPreference();
//        RetrofitManager.getInstance(getApplication()).clearAll();
//        MBodyApp.clearAll();
//
//        SharedPrefManager.getInstance(getApplication()).setPreference(MBodyConstants.VISITED_BOARDING_SCREEN, true);
//
//        logoutLiveData.postValue(message);
//    }
//
//    public MutableLiveData<LoaderStatus> getLoadingLiveData() {
//        return loadingLiveData;
//    }
//    public MutableLiveData<String> getLogoutLiveData() {
//        return logoutLiveData;
//    }

}

