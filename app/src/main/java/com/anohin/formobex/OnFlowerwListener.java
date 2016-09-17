package com.anohin.formobex;

import android.view.View;

import com.anohin.formobex.model.pojo.Flower;

/**
 * Created by valery.kuznetsov on 16-Sep-16.
 */

public interface OnFlowerwListener {
    void onFlowerSelected(View view, Flower flower);
}
