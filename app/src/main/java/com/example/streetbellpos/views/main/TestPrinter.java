package com.example.streetbellpos.views.main;

import android.content.Context;
import android.widget.Toast;

import com.example.streetbellpos.R;
import com.example.streetbellpos.lib.IPrintToPrinter;
import com.example.streetbellpos.lib.WoosimPrnMng;
import com.example.streetbellpos.lib.printerWordMng;
import com.example.streetbellpos.models.gson.Printable;
import com.example.streetbellpos.utils.printerFactory;
import com.woosim.printer.WoosimCmd;

import java.util.ArrayList;

public class TestPrinter implements IPrintToPrinter {

    private Context context;
    private String personName, amount, shopName, orderId, date, idProofType, idProofNumber, phoneNumber, issuesBy, totalAmount;
    private Printable mPrintable;


    public TestPrinter(Context context, String name, String amount, String shopName, String orderId, String date, String idProofType, String idProofNumber, String phoneNumber, String issuesBy, Printable printable) {
        this.context = context;
        this.personName = name;
        this.amount = amount;
        this.orderId = orderId;
        this.shopName = shopName;
        this.date = date;
        this.idProofType = idProofType;
        this.idProofNumber = idProofNumber;
        this.mPrintable = printable;
        this.issuesBy = issuesBy;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void printContent(WoosimPrnMng prnMng) {
        printerWordMng wordMng = printerFactory.createPaperMng(context);

//        prnMng.printStr("StreetBell", 2, WoosimCmd.ALIGN_CENTER,true);
//        prnMng.printStr("Collect your Ticket", 1, WoosimCmd.ALIGN_CENTER,false);
//        prnMng.printStr(wordMng.getHorizontalUnderline());
//
//        prnMng.printStr(wordMng.autoWordWrap("2-Second line that is very very long line to check word wrap"),
//                1, WoosimCmd.ALIGN_LEFT,true);
//        prnMng.printStr(wordMng.getHorizontalUnderline());
//        prnMng.printStr("3-Third line",1, WoosimCmd.ALIGN_LEFT,false);
//        prnMng.printNewLine();
//        prnMng.printStr("Footer",1, WoosimCmd.ALIGN_CENTER,false);

        //You can also print a logo
        //prnMng.printBitmap("/sdcard/test/001.png", IBixolonCanvasConst.CMaxChars_2Inch);

        //Any finalization, you can call it here or any where in your running activity.
        printEnded(prnMng);

        prnMng.printStr("Streetbell", 2, WoosimCmd.ALIGN_CENTER, true);
        prnMng.printStr("Collect your Ticket", 1, WoosimCmd.ALIGN_CENTER, false);
        prnMng.printNewLine();

        prnMng.printStr(wordMng.autoWrap("TICKET"), 1, WoosimCmd.ALIGN_LEFT, true);
        if (orderId != null)
            prnMng.printStr(wordMng.autoWrap(orderId), 1, WoosimCmd.ALIGN_LEFT, false);

        if (amount != null) {
            prnMng.printStr(wordMng.autoWrap("RS : INR " + amount.replaceAll("([a-z,A-Z])", "")), 1, WoosimCmd.ALIGN_LEFT, true);
            prnMng.printStr(wordMng.autoWrap("For :" + personName.replace("\\d+", "")), 1, WoosimCmd.ALIGN_LEFT, true);
        }

        if (date != null) {
            prnMng.printStr(wordMng.autoWrap("On :" + date), 1, WoosimCmd.ALIGN_LEFT, true);
        }


        prnMng.printNewLine();


        if (personName != null) {
            prnMng.printStr(wordMng.autoWrap("Guest:" + personName), 1, WoosimCmd.ALIGN_LEFT, false);

        }
        prnMng.printStr(wordMng.autoWrap("Payment Mode: Cash"), 1, WoosimCmd.ALIGN_LEFT, false);

        if (idProofType != null) {
            prnMng.printStr(wordMng.autoWrap("ID Proof Type:" + idProofType), 1, WoosimCmd.ALIGN_LEFT, false);
        }
        if (idProofNumber != null) {
            prnMng.printStr("ID Proof Number:" + idProofNumber, 1, WoosimCmd.ALIGN_LEFT, false);

        }
        prnMng.printStr("SI NO\tParticular\tAmount", 1, WoosimCmd.ALIGN_CENTER, true);


        for (int i = 0; i < mPrintable.getPriceList().size(); i++) {
            if (mPrintable.getPriceList().get(i).contains("Indian Adult")) {
                prnMng.printStr(wordMng.autoWrap(i + 1 + "\t" + "IA\t " + amount.replaceAll("([a-z,A-Z])", "")), 1, WoosimCmd.ALIGN_CENTER, false);
            } else if (mPrintable.getPriceList().get(i).contains("Indian Child")) {
                prnMng.printStr(wordMng.autoWrap(i + 1 + "\t" + "IC\t " + amount.replaceAll("([a-z,A-Z])", "")), 1, WoosimCmd.ALIGN_CENTER, false);
            } else if (mPrintable.getPriceList().get(i).contains("Foreign Adult")) {
                prnMng.printStr(wordMng.autoWrap(i + 1 + "\t" + "FA\t " + amount.replaceAll("([a-z,A-Z])", "")), 1, WoosimCmd.ALIGN_CENTER, false);
            } else if (mPrintable.getPriceList().get(i).contains("Foreign Child")) {
                prnMng.printStr(wordMng.autoWrap(i + 1 + "\t" + "FC\t " + amount.replaceAll("([a-z,A-Z])", "")), 1, WoosimCmd.ALIGN_CENTER, false);
            } else {
                prnMng.printNewLine();
            }

//           if(){
//               prnMng.printStr(wordMng.autoWrap("IC"+amount.replaceAll("([a-z,A-Z])","") +  " 1" + " NIL"),1,WoosimCmd.ALIGN_LEFT,false);
//
//           }else {
//               prnMng.printStr(wordMng.autoWrap("IC  NILL"),1,WoosimCmd.ALIGN_LEFT,false);
//           }
//
//           if(mPrintable.getPriceList().get(i).contains("Foreign Adult")){
//               prnMng.printStr(wordMng.autoWrap("FA"+amount.replaceAll("([a-z,A-Z])","") +  " 1" + " NIL"),1,WoosimCmd.ALIGN_LEFT,false);
//           }else {
//               prnMng.printStr(wordMng.autoWrap("FA  NILL"),1,WoosimCmd.ALIGN_LEFT,false);
//           }
//
//           if(mPrintable.getPriceList().get(i).contains("Foreign Child")){
//               prnMng.printStr(wordMng.autoWrap("FC"+amount.replaceAll("([a-z,A-Z])","") +  " 1" + " NIL"),1,WoosimCmd.ALIGN_LEFT,false);
//           }else {
//               prnMng.printStr(wordMng.autoWrap("FA  NILL"),1,WoosimCmd.ALIGN_LEFT,false);
//           }
        }

        prnMng.printStr("SubTotal" + getTotalAmount(mPrintable.getPriceList()), 1, WoosimCmd.ALIGN_RIGHT, false);


        prnMng.printStr(wordMng.getHorizontalUnderline());


        if (phoneNumber != null) {
            prnMng.printStr(wordMng.autoWrap("Phone number :" + phoneNumber), 1, WoosimCmd.ALIGN_LEFT, false);

        }
        prnMng.printStr("Ticket Issued by:" + issuesBy, 1, WoosimCmd.ALIGN_LEFT, false);


        prnMng.printStr("Counter Number : ", 1, WoosimCmd.ALIGN_CENTER, false);
        prnMng.printStr("ISSUED ON" + mPrintable.getOn(), 1, WoosimCmd.ALIGN_CENTER, false);
        prnMng.printStr(context.getString(R.string.to_book), 1, WoosimCmd.ALIGN_CENTER, false);
        prnMng.printStr(context.getString(R.string.for_quries), 1, WoosimCmd.ALIGN_CENTER, false);

        prnMng.printStr(context.getString(R.string.ticket_issues_desc), 1, WoosimCmd.ALIGN_CENTER, false);
        prnMng.printStr(context.getString(R.string.thank_you), 1, WoosimCmd.ALIGN_CENTER, false);


//        prnMng.printBitmap(img,100);

        prnMng.printNewLine();


        prnMng.printStr("Powered by Streetbell.com", 1, WoosimCmd.ALIGN_CENTER, true);
//        prnMng.printStr("Footer",1, WoosimCmd.ALIGN_CENTER);

        //You can also print a logo
        //prnMng.printBitmap("/sdcard/test/001.png", IBixolonCanvasConst.CMaxChars_2Inch);

        //Any finalization, you can call it here or any where in your running activity.
        printEnded(prnMng);
    }

    @Override
    public void printEnded(WoosimPrnMng prnMng) {
        //Do any finalization you like after print ended.
        if (prnMng.printSucc()) {
            Toast.makeText(context, R.string.print_succ, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, R.string.print_error, Toast.LENGTH_LONG).show();
        }
    }

    private String getTotalAmount(ArrayList<String> priceList) {
        int total;
        for (int i = 0; i < priceList.size(); i++) {
            total = Integer.parseInt(priceList.get(i).replaceAll("([a-z,A-Z])", ""));
            totalAmount = totalAmount + total;


        }

        return totalAmount;
    }
}
