package com.example.streetbellpos.managers.helper;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No Internet Connection";
    }
}
