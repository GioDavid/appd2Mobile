package com.mobile.appd2.MVPAppd2.CustomViews;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.appd2.MVPAppd2.Clases.PlanVo;
import com.mobile.appd2.MVPAppd2.R;

/**
 * Created by david on 14/2/16.
 */
public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public PlanVo planVo;

    public CustomDialog(Activity a, PlanVo plan) {
        super(a);
        this.c = a;
        this.planVo = plan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_yes:
                Toast.makeText(
                        c,
                        planVo.toString(),
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
