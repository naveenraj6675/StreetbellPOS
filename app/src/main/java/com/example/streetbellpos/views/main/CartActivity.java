package com.example.streetbellpos.views.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.SpinnerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.helpers.FormValidator;
import com.example.streetbellpos.lib.DeviceListActivity;
import com.example.streetbellpos.lib.IPrintToPrinter;
import com.example.streetbellpos.lib.WoosimPrnMng;
import com.example.streetbellpos.models.gson.BookingDetails;
import com.example.streetbellpos.models.gson.Printable;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.room.BookingDatabase;
import com.example.streetbellpos.utils.Tools;
import com.example.streetbellpos.utils.printerFactory;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends StreetbellppCompatActivity {


    private static final int REQUEST_CONNECT = 100;
    @BindView(R.id.mainView)
    ConstraintLayout mMainLL;


    @BindView(R.id.date_tv)
    TextView mDataTV;
    @BindView(R.id.name1_et)
    EditText mName1ET;
    @BindView(R.id.name2_et)
    EditText mName2ET;
    @BindView(R.id.name3_et)
    EditText mName3ET;
    @BindView(R.id.name4_et)
    EditText mName4ET;
    @BindView(R.id.name5_et)
    EditText mName5ET;
    @BindView(R.id.name6_et)
    EditText mName6ET;
    @BindView(R.id.name7_et)
    EditText mName7ET;
    @BindView(R.id.name8_et)
    EditText mName8ET;
    @BindView(R.id.name9_et)
    EditText mName49ET;
    @BindView(R.id.name10_et)
    EditText mName10ET;
    @BindView(R.id.price_spinner)
    Spinner mPriceSpinner;
    @BindView(R.id.proof_spinner)
    Spinner mProofSpinner;
    @BindView(R.id.visitor1_spinner)
    Spinner mVisitorSpinner1;
    @BindView(R.id.visitor2_spinner)
    Spinner mVisitorSpinner2;
    @BindView(R.id.visitor3_spinner)
    Spinner mVisitorSpinner3;
    @BindView(R.id.visitor4_spinner)
    Spinner mVisitorSpinner4;
    @BindView(R.id.visitor5_spinner)
    Spinner mVisitorSpinner5;
    @BindView(R.id.visitor6_spinner)
    Spinner mVisitorSpinner6;
    @BindView(R.id.visitor7_spinner)
    Spinner mVisitorSpinner7;
    @BindView(R.id.visitor8_spinner)
    Spinner mVisitorSpinner8;
    @BindView(R.id.visitor9_spinner)
    Spinner mVisitorSpinner9;
    @BindView(R.id.proof_et)
    EditText mProofET;
    @BindView(R.id.visitor1_ll)
    LinearLayout visitor1LL;
    @BindView(R.id.visitor2_ll)
    LinearLayout visitor2LL;
    @BindView(R.id.visitor3_ll)
    LinearLayout visitor3LL;
    @BindView(R.id.visitor4_ll)
    LinearLayout visitor4LL;
    @BindView(R.id.visitor5_ll)
    LinearLayout visitor5LL;
    @BindView(R.id.visitor6_ll)
    LinearLayout visitor6LL;
    @BindView(R.id.visitor7_ll)
    LinearLayout visitor7LL;
    @BindView(R.id.visitor8_ll)
    LinearLayout visitor8LL;
    @BindView(R.id.visitor9_ll)
    LinearLayout visitor9LL;
    @BindView(R.id.issue_ticket_btn)
    Button mIssueTicketBtn;
    @BindView(R.id.add_more_btn)
    Button mAddMoreBtn;
    private WoosimPrnMng mPrnMng = null;


    private Products mProducts;
    Calendar mcurrentDate = Calendar.getInstance();
    int mYear = mcurrentDate.get(Calendar.YEAR);
    private UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    int mMonth = mcurrentDate.get(Calendar.MONTH);
    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
    Date time_date = Calendar.getInstance().getTime();
    String time = String.valueOf(time_date.getTime());


    private String price, date, errorMsg, orderId;
    private String[] mpriceName = new String[0];
    int clickCount = 0;
    private Printable mPrintable;
    private ArrayList<String> mProofNameList = new ArrayList<>();
    private String[] mProofName = new String[0];
    private ArrayList<String> mPriceList = new ArrayList<>();
    private ArrayList<String> mProofType = new ArrayList<>();
    private ArrayList<String> mProofNumbers = new ArrayList<>();
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

    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e("this", "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }

    @OnClick(R.id.date_tv)
    void onDateClicked() {

        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                mDataTV.setText(selectedyear + "-" + selectedmonth + 1 + "-" + selectedday);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();
    }

    private void initViews() {
        date = mYear + "-" + mMonth + 1 + "-" + mDay;
        mDataTV.setText(date);


        if (getIntent() != null) {
            mProducts = Products.parse(getIntent().getStringExtra(StreetBellConstants.PRODUCT));
        }

        orderId = "S-" + getSharedPrefManager().getPreference(StreetBellConstants.USER_ID) + time;

        for (int i = 0; i < mProducts.getPriceList().size(); i++) {
            mPriceList.add(mProducts.getPriceList().get(i).getType() + " " + mProducts.getPriceList().get(i).getPrice());
        }
        mProofNameList.add("Adhaar(UID)");
        mProofNameList.add("Passport");
        mProofNameList.add("Driving License");
        mProofNameList.add("Income Tax PAN Card");
        mPriceList.add(0, "Select Price");


        mProofName = mProofNameList.toArray(mProofName);

        mpriceName = mPriceList.toArray(mpriceName);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mPriceSpinner.setAdapter(spinnerAdapter);

        SpinnerAdapter spinnerAdapter1 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner1.setAdapter(spinnerAdapter1);

        SpinnerAdapter spinnerAdapter2 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner2.setAdapter(spinnerAdapter2);

        SpinnerAdapter spinnerAdapter3 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner3.setAdapter(spinnerAdapter3);

        SpinnerAdapter spinnerAdapter4 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner4.setAdapter(spinnerAdapter4);

        SpinnerAdapter spinnerAdapter5 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner5.setAdapter(spinnerAdapter5);

        SpinnerAdapter spinnerAdapter6 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner6.setAdapter(spinnerAdapter6);

        SpinnerAdapter spinnerAdapter7 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner7.setAdapter(spinnerAdapter7);

        SpinnerAdapter spinnerAdapter8 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner8.setAdapter(spinnerAdapter8);

        SpinnerAdapter spinnerAdapter9 = new SpinnerAdapter(this, 0, mpriceName, mpriceName[0]);
        mVisitorSpinner9.setAdapter(spinnerAdapter9);

        SpinnerAdapter proofSpinnerAdapter = new SpinnerAdapter(this, 0, mProofName, mProofName[0]);
        mProofSpinner.setAdapter(proofSpinnerAdapter);
        mProofSpinner.setTag(proofSpinnerAdapter);


    }

    @OnClick(R.id.add_more_btn)
    void onAddmoreBtnClicked() {
        if (mAddMoreBtn.getText().equals("Add more visitor")) {
            clickCount++;

            if (clickCount == 1) {
                visitor1LL.setVisibility(View.VISIBLE);
            } else if (clickCount == 2) {
                visitor2LL.setVisibility(View.VISIBLE);
            } else if (clickCount == 3) {
//                mAddMoreBtn.setText("Delete Visitor");
                visitor3LL.setVisibility(View.VISIBLE);
            } else if (clickCount == 4) {
                visitor4LL.setVisibility(View.VISIBLE);

            } else if (clickCount == 5) {
                visitor5LL.setVisibility(View.VISIBLE);
            } else if (clickCount == 6) {
                visitor6LL.setVisibility(View.VISIBLE);
            } else if (clickCount == 7) {
                visitor7LL.setVisibility(View.VISIBLE);

            } else if (clickCount == 8) {
                visitor8LL.setVisibility(View.VISIBLE);

            } else if (clickCount == 9) {
                visitor9LL.setVisibility(View.VISIBLE);
                mAddMoreBtn.setText("Delete Visitor");
            }
        } else if (mAddMoreBtn.getText().equals("Delete Visitor")) {
            clickCount--;

            if (clickCount == 8) {
                visitor9LL.setVisibility(View.GONE);
            } else if (clickCount == 7) {
                visitor8LL.setVisibility(View.GONE);

            } else if (clickCount == 6) {
//                mAddMoreBtn.setText("Add more visitor");
                visitor7LL.setVisibility(View.GONE);
            } else if (clickCount == 5) {
                visitor6LL.setVisibility(View.GONE);
            } else if (clickCount == 4) {
                visitor5LL.setVisibility(View.GONE);
            } else if (clickCount == 3) {
                visitor4LL.setVisibility(View.GONE);
            } else if (clickCount == 2) {
                visitor3LL.setVisibility(View.GONE);
            } else if (clickCount == 1) {
                visitor2LL.setVisibility(View.GONE);
            } else if (clickCount == 0) {
                mAddMoreBtn.setText("Add more visitor");
                visitor1LL.setVisibility(View.GONE);
            }
        }
    }

    @OnClick({R.id.issue_ticket_btn})
    void onIssueTicketClicked() {
        hideKeyboard();

        if (mPriceList.size() > 0) {
            mPriceList.clear();
        }

        if (mPriceSpinner.getVisibility() == View.VISIBLE) {
            mPriceList.add(mPriceSpinner.getSelectedItem().toString());
        } else if (visitor1LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner1.getSelectedItem().toString());
        } else if (visitor2LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner2.getSelectedItem().toString());
        } else if (visitor3LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner3.getSelectedItem().toString());
        } else if (visitor4LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner4.getSelectedItem().toString());
        } else if (visitor5LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner5.getSelectedItem().toString());
        } else if (visitor6LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner6.getSelectedItem().toString());
        } else if (visitor7LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner7.getSelectedItem().toString());
        } else if (visitor8LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner8.getSelectedItem().toString());
        } else if (visitor9LL.getVisibility() == View.VISIBLE) {
            mPriceList.add(mVisitorSpinner9.getSelectedItem().toString());
        }


        if (validateFormData()) {
            if (!Tools.isBlueToothOn(this)) return;
            //Pick a Bluetooth device
            Intent i = new Intent(this, DeviceListActivity.class);
            startActivityForResult(i, REQUEST_CONNECT);
        } else {
            showSnackbar(errorMsg);
        }

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
        } else if (mPriceSpinner.getSelectedItem().toString().equals("Select Price")) {
            formOk = false;
            errorMsg = "Please select price";
        } else if (visitor1LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner1.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor2 price ";
            }
        } else if (visitor2LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner2.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor3 price ";
            }
        } else if (visitor3LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner3.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor4 price ";
            }
        } else if (visitor4LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner4.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor5 price ";
            }
        } else if (visitor5LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner5.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor5 price ";
            }
        } else if (visitor6LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner6.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor7 price ";
            }
        } else if (visitor7LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner7.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor8 price ";
            }
        } else if (visitor8LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner8.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor9 price ";
            }
        } else if (visitor9LL.getVisibility() == View.VISIBLE) {

            if (mVisitorSpinner9.getSelectedItem().toString().equals("Select Price")) {
                formOk = false;
                errorMsg = "Please select visitor10 price ";
            }
        }



        return formOk;
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

        mBookingDetails.setAddOnServicesList(mProducts.getAddonList());

        mBookingDetails.setIntStatus("");

        mBookingDetails.setStrUserip(getLocalIpAddress());

        mBookingDetails.setUsecod("");

        mBookingDetails.setAddonInclude("0");

        mBookingDetails.setAddonGstamount("0");

        mBookingDetails.setAddonStotal("0");

        mBookingDetails.setAddonTotal("0");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CONNECT && resultCode == RESULT_OK) {
            try {
                //Get device address to print to.
                String blutoothAddr = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                //The interface to print text to thermal printers.
                mPrintable = new Printable("", "", "", mProducts.getCatName(), "CASH", mProofType, mProofNumbers, mPriceList);
                IPrintToPrinter testPrinter = new TestPrinter(this, mName1ET.getText().toString(), mPriceSpinner.getSelectedItem().toString(), "Street Bell"
                        , orderId, mDataTV.getText().toString(), mProofSpinner.getSelectedItem().toString(), mProofET.getText().toString(), getSharedPrefManager().getPreference(StreetBellConstants.USER_MOBILE), getSharedPrefManager().getPreference(StreetBellConstants.USER_ID), mPrintable);

                //Connect to the printer and after successful connection issue the print command.
                mPrnMng = printerFactory.createPrnMng(this, blutoothAddr, testPrinter);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if (mPrnMng != null) mPrnMng.releaseAllocatoins();
        super.onDestroy();
    }
}
