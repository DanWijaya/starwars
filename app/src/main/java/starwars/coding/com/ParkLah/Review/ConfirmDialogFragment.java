package starwars.coding.com.ParkLah.Review;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import starwars.coding.com.ParkLah.CarparkDetail.CarparkDetailActivity;
import starwars.coding.com.ParkLah.R;

public class ConfirmDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(R.string.reviewconfirm)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    //@Override
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
                        Toast.makeText(context, "Review done", Toast.LENGTH_SHORT).show();
                        ((FragmentActivity) context).finish();

                    }

                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                return builder.create();

    }
}
