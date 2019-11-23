package com.example.streetbellpos.views.main;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.room.Room;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.SpinnerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.helpers.FormValidator;
import com.example.streetbellpos.models.gson.BookingDetails;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.room.BookingDatabase;
import com.example.streetbellpos.views.MainActivity;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends StreetbellppCompatActivity {

    @BindView(R.id.date_tv)
    TextView mDataTV;
    @BindView(R.id.name1_et)
    EditText mName1ET;
    @BindView(R.id.price_spinner)
    Spinner mPriceSpinner;
    @BindView(R.id.proof_spinner)
    Spinner mProofSpinner;
    @BindView(R.id.proof_et)
    EditText mProofET;
    @BindView(R.id.issue_ticket_btn)
    Button mIssueTicketBtn;



    private Products mProducts;
    Calendar mcurrentDate = Calendar.getInstance();
    int mYear = mcurrentDate.get(Calendar.YEAR);
    int mMonth = mcurrentDate.get(Calendar.MONTH);
    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
    Date time_date = Calendar.getInstance().getTime();
    String time = String.valueOf(time_date.getTime());


    private String price, date, errorMsg, orderId;
    private String[] mpriceName = new String[0];
    private ArrayList<String> mProofNameList = new ArrayList<>();
    private String[] mProofName = new String[0];
    private ArrayList<String> mPriceList = new ArrayList<>();
    private BookingDetails mBookingDetails;
    private BookingDatabase bookingDatabase;


    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void initObservers() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        mBookingDetails = new BookingDetails();
        bookingDatabase = Room.databaseBuilder(this, BookingDatabase.class, "bookingDatabase").allowMainThreadQueries().build();

        initViews();

    }

    private void initViews() {
        date = mYear + "-" + mMonth + "-" + mDay;
        mDataTV.setText(date);


        if (getIntent() != null) {
            mProducts = Products.parse(getIntent().getStringExtra(StreetBellConstants.PRODUCT));
        }

        for (int i = 0; i < mProducts.getPriceList().size(); i++) {
            mPriceList.add(mProducts.getPriceList().get(i).getType() + " " + mProducts.getPriceList().get(i).getPrice());
        }
        mProofNameList.add("Adhaar(UID)");
        mProofNameList.add("Passport");
        mProofNameList.add("Driving License");
        mProofNameList.add("Income Tax PAN Card");

        mProofName = mProofNameList.toArray(mProofName);
        mpriceName = mPriceList.toArray(mpriceName);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mPriceSpinner.setAdapter(spinnerAdapter);
        mPriceSpinner.setTag(spinnerAdapter);


        SpinnerAdapter proofSpinnerAdapter = new SpinnerAdapter(this, 0, mProofName, mProofName[0]);
        mProofSpinner.setAdapter(proofSpinnerAdapter);
        mProofSpinner.setTag(proofSpinnerAdapter);


    }

    @OnClick(R.id.date_tv)
    void onDateClicked() {

        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                mDataTV.setText(selectedyear + "-" + selectedmonth + "-" + selectedday);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();
    }


    @OnClick({R.id.issue_ticket_btn})
    void onIssueTicketClicked() {
        orderId = "S" + getSharedPrefManager().getPreference(StreetBellConstants.USER_ID) + "-" + time + getSharedPrefManager().getPreference(StreetBellConstants.USER_ID);

        if (validateFormData()) {
            setBooking();
            bookingDatabase.bookingDao().insert(mBookingDetails);

            showAlertDialogOk("POS", "Your order placed successfullt", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        } else {
            showAlertDialogOk("POS", errorMsg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }


    }


    private boolean validateFormData() {
        boolean formOk = true;
        String proof = mProofET.getText().toString().trim();
        String name = mName1ET.getText().toString().trim();

        errorMsg = "";
        if (!FormValidator.requiredField(proof, 1)) {
            formOk = false;
            errorMsg = getString(R.string.enter_prrof_no);
        } else if (!FormValidator.requiredField(name, 1)) {
            formOk = false;
            errorMsg = getString(R.string.enter_name);
        }

        return formOk;
    }

    public int sumDigits(CharSequence s) {
        int total = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                total += Character.getNumericValue(c);
        }
        return total;
    }


    private void setBooking() {

        mBookingDetails.setId(Integer.parseInt(mProducts.getpId()));

        mBookingDetails.setuId(getSharedPrefManager().getPreference(StreetBellConstants.USER_ID));

        mBookingDetails.setIntSellerid(getSharedPrefManager().getPreference(StreetBellConstants.SHOP_ID));

        mBookingDetails.setApporweb("OFFAPP");

        mBookingDetails.setIntPid(mProducts.getpId());

        mBookingDetails.setFloatUnitprice(mProducts.getPrice());

        mBookingDetails.setOrderStatus("1");

        mBookingDetails.setStrDate(date);

        mBookingDetails.setIntCreated(sumDigits(time));

        mBookingDetails.setCurrencyCode(mProducts.getCurrencyCode());

        mBookingDetails.setCurrencySymbol(mProducts.getCurrencySymbol());

        mBookingDetails.setOrderid(orderId);

        mBookingDetails.setGtotal("");

        mBookingDetails.setStotal("");

        mBookingDetails.setGstpercent("");

        mBookingDetails.setOthercharges("");

        mBookingDetails.setEnablegst("");

        mBookingDetails.setGstamount("");

        mBookingDetails.setBookingdate(mDataTV.getText().toString());

        mBookingDetails.setCustomername("");

        mBookingDetails.setCustomercat("");

        if (mProofSpinner.getSelectedItem().toString().equals("Adhaar(UID)")) {
            mBookingDetails.setProofdoc("1");
        } else if (mProofSpinner.getSelectedItem().toString().equals("Passport")) {
            mBookingDetails.setProofdoc("2");
        } else if (mProofSpinner.getSelectedItem().toString().equals("Driving License")) {
            mBookingDetails.setProofdoc("3");
        } else if (mProofSpinner.getSelectedItem().toString().equals("Income Tax PAN Card")) {
            mBookingDetails.setProofdoc("4");
        }

        mBookingDetails.setPdocnumber(mProofET.getText().toString());

        mBookingDetails.setShoppingAmount("");

        mBookingDetails.setIntStatus("");

        mBookingDetails.setStrUserip(getLocalIpAddress());

        mBookingDetails.setUsecod("");

        mBookingDetails.setAddonInclude("0");

        mBookingDetails.setAddonGstamount("0");

        mBookingDetails.setAddonStotal("0");

        mBookingDetails.setAddonTotal("0");


    }
}
