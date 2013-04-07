/*
 * Copyright (C) 2013 Benedict Lau
 * 
 * All rights reserved.
 */
package com.groundupworks.partyphotobooth.kiosk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;
import com.groundupworks.partyphotobooth.R;

/**
 * Password prompt to exit Kiosk mode.
 * 
 * @author Benedict Lau
 */
public class KioskPasswordDialogFragment extends DialogFragment {

    @SuppressLint("NewApi")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = null;
        Activity activity = getActivity();

        // AlertDialog.THEME_DEVICE_DEFAULT_LIGHT only available in ICS and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            dialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        } else {
            dialogBuilder = new AlertDialog.Builder(activity);
        }

        // Inflate EditText for entering the password.
        final EditText passwordView = (EditText) LayoutInflater.from(activity).inflate(R.layout.view_password, null);

        return dialogBuilder
                .setTitle(R.string.kiosk_mode__dialog_title)
                .setView(passwordView)
                .setPositiveButton(R.string.kiosk_mode__dialog_button_positive_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                KioskActivity activity = (KioskActivity) getActivity();
                                if (activity != null) {
                                    Editable password = passwordView.getText();
                                    KioskModeHelper helper = new KioskModeHelper(activity);
                                    if (passwordView != null && helper.verifyPassword(password.toString())) {
                                        activity.exitKioskMode(helper);
                                    } else {
                                        Toast.makeText(activity, getString(R.string.kiosk_mode__error_password),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }).setNegativeButton(R.string.kiosk_mode__dialog_button_negative_text, null).create();
    }

    //
    // Public methods.
    //

    /**
     * Creates a new {@link KioskPasswordDialogFragment} instance.
     * 
     * @return the new {@link KioskPasswordDialogFragment} instance.
     */
    public static KioskPasswordDialogFragment newInstance() {
        return new KioskPasswordDialogFragment();
    }
}
