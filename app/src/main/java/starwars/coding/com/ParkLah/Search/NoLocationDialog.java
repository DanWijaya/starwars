package starwars.coding.com.ParkLah.Search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import starwars.coding.com.ParkLah.CarparkDetail.CarparkDetailActivity;
import starwars.coding.com.ParkLah.R;

public class NoLocationDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.e("Error", "No Location error");
        final Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(R.string.nulllocation);

        return builder.create();

    }
}