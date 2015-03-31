package main.java.cz2006project.mojojo.Boundary;

/**
 * Created by srishti on 30/3/15.
*/

        import android.app.AlertDialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.ActionBarActivity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.parse.LogInCallback;
        import com.parse.ParseException;
        import com.parse.ParseFile;
        import com.parse.ParseImageView;
        import com.parse.ParseUser;
        import com.parse.SaveCallback;

        import java.util.HashMap;

        import cz2006project.mojojo.R;
        import main.java.cz2006project.mojojo.Control.ParseTables;
        import main.java.cz2006project.mojojo.Control.SampleApplication;
        import main.java.cz2006project.mojojo.MaterialEditText;


public class ProfileFragment extends Fragment {

    private static final String USER_UID = "USERNAME";
    private static final String USER_PASSWORD = "xxxxxxxxx";
    private static final String USER_QUALIFICATIONS = "QUALIFICATIONS";
    private static final String USER_AUTH = "authData";
    private static final String FB_APP_ID = "90313744064438";
    private EditText ePassword, eQualificaton, eInstitute, eNewPassword, eConfirmPassword;//eInterests
    private TextView tEmail, tFullName;
    private ImageButton editPassword, editQualification, editInstitute, canEditInstitute, canEditQualifiacaton, canEditPassword; //editInterest
    private View.OnClickListener oclEdit, oclSubmit, oclCancelEdit, oclPasswordEdit, oclPasswordSubmit, oclCancelPasswordEdit;
    private View rootView;
    private LinearLayout passwordContainer;
    private View newpassFormContainer;
    private ParseImageView imageProfile;
    private Bundle fbParams;
    private HashMap<String, String> userInfo;

    public ProfileFragment() {
        // Required empty public constructor
    }

    private static void slide_down(Context context, View v) {
        Animation a = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        if (a != null)
            a.reset();
        if (v != null) {
            v.clearAnimation();
            v.setVisibility(View.VISIBLE);
            v.startAnimation(a);
        }
    }

    private static void slide_up(Context context, View v) {

        Animation a = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        if (a != null)
            a.reset();
        if (v != null) {
            v.clearAnimation();
            if (a != null) {
                v.startAnimation(a);
            }
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        int p = getActivity().getResources().getColor(R.color.accountColorPrimary);
        int s = getActivity().getResources().getColor(R.color.accountColorPrimaryDark);
        SampleApplication.setCustomTheme((ActionBarActivity) getActivity(), p, s);
        userInfo = new HashMap<>();
        init();
        try {
            fetchInfoFromParse();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private void fetchInfoFromParse() throws Exception {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.get(ParseTables.Users.USERNAME) != null)
                tEmail.setText(currentUser.getString(ParseTables.Users.USERNAME));


            if (currentUser.get(ParseTables.Users.NAME) != null)
                tFullName.setText(currentUser.getString(ParseTables.Users.NAME));

        } else {
            //TODO: handle errors if any generated
        }


    }

    private void init() {

        tFullName = (TextView) rootView.findViewById(R.id.account_info_fullname);
        tEmail = (TextView) rootView.findViewById(R.id.account_info_email);

        ePassword = (MaterialEditText) rootView.findViewById(R.id.account_info_password);
        editPassword = (ImageButton) rootView.findViewById(R.id.edit_password_button);


        newpassFormContainer = rootView.findViewById(R.id.new_password_form_container);

        eNewPassword = (MaterialEditText) rootView.findViewById(R.id.account_info_new_password);
        eConfirmPassword = (MaterialEditText) rootView.findViewById(R.id.account_info_confirm_password);


        passwordContainer = (LinearLayout) rootView.findViewById(R.id.account_info_container_password);

        canEditPassword = (ImageButton) rootView.findViewById(R.id.cancel_edit_password_button);

        ePassword.setEnabled(false);
        eNewPassword.setEnabled(false);
        eConfirmPassword.setEnabled(false);



        oclPasswordEdit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ePassword.setEnabled(true);
                eNewPassword.setEnabled(true);
                eConfirmPassword.setEnabled(true);
                ePassword.setFocusable(true);
                ePassword.setFocusableInTouchMode(true);
                ePassword.setHint(getActivity().getString(R.string.old_password));
                canEditPassword.setVisibility(View.VISIBLE);
                slide_down(getActivity(), newpassFormContainer);
                ImageButton clicked = (ImageButton) rootView.findViewById(v.getId());
                clicked.setImageResource(R.drawable.tick);
                editPassword.setOnClickListener(oclPasswordSubmit);
            }
        };

        oclPasswordSubmit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canEditPassword.setVisibility(View.INVISIBLE);
                ePassword.setFocusable(false);
                ePassword.setEnabled(false);
                ePassword.setFocusableInTouchMode(false);
                eNewPassword.setEnabled(false);
                eConfirmPassword.setEnabled(false);
                ePassword.setHint(getActivity().getString(R.string.password));
                changePassword();
                slide_up(getActivity(), newpassFormContainer);
                ((ImageButton) v).setImageResource(R.drawable.pencil);
                editPassword.setOnClickListener(oclPasswordEdit);

            }
        };

        oclCancelPasswordEdit = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                editPassword.setImageResource(R.drawable.pencil);
                ePassword.setFocusable(false);
                ePassword.setEnabled(false);
                ePassword.setFocusableInTouchMode(false);
                eNewPassword.setEnabled(false);
                eConfirmPassword.setEnabled(false);
                ePassword.setHint(getActivity().getString(R.string.password));
                slide_up(getActivity(), newpassFormContainer);
                editPassword.setImageResource(R.drawable.pencil);
                editPassword.setOnClickListener(oclPasswordEdit);

            }
        };

        editPassword.setOnClickListener(oclPasswordEdit);
        canEditPassword.setOnClickListener(oclCancelPasswordEdit);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

    }


    private boolean changePassword() {
        String oldPassword, newPassword, confirmPassword;


        oldPassword = ePassword.getText().toString();
        newPassword = eNewPassword.getText().toString();
        confirmPassword = eConfirmPassword.getText().toString();

        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {

            Toast.makeText(getActivity(), "Please enter the Old Password and the new password twice.", Toast.LENGTH_LONG).show();
            ePassword.setText("");
            eNewPassword.setText("");
            eConfirmPassword.setText("");
            return false;

        }

        if (!(newPassword.equals(confirmPassword))) {

            Toast.makeText(getActivity(), "New passwords don't match", Toast.LENGTH_LONG).show();

            eNewPassword.setText("");
            eConfirmPassword.setText("");
            return false;
        }

        ParseUser cu = ParseUser.getCurrentUser();
        authenticate(cu.getUsername(), oldPassword, newPassword);
        return true;
    }


    public void authenticate(String username, String oldPassword, final String newPassword) {
        ParseUser.logInInBackground(
                username, oldPassword,
                new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            parseUser.setPassword(newPassword);
                            parseUser.saveEventually(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(getActivity(), "Updated Password Successfully", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Unable to update password : " + e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Authentication Failed")
                                    .setCancelable(true)
                                    .setMessage("Old password is Incorrect!! Password not changed.")
                                    .show();
                        }
                    }
                }
        );
    }


}





