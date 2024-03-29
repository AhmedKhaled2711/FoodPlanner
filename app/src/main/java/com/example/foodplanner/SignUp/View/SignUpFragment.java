package com.example.foodplanner.SignUp.View;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.service.credentials.Action;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Home;
import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class SignUpFragment extends Fragment {
    SharedPreferences sh ;
    public static final String PREFS_NAME = "LoginPrefs";
    private EditText mail , password , confirmPassword ;
    private Button btn_signUp , btn_google ,btn_facebook ;
    private FirebaseAuth firebaseAuth ;
    private TextView tv_login ;
    private GoogleSignInClient googleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        mail = view.findViewById(R.id.et_Email);
        password = view.findViewById(R.id.et_password1);
        confirmPassword = view.findViewById(R.id.et_Password2);
        tv_login = view.findViewById(R.id.tv_signUp_login);
        btn_signUp = view.findViewById(R.id.btn_SignUp);
        btn_google = view.findViewById(R.id.btn_google);
        btn_facebook = view.findViewById(R.id.btn_facebook);


        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailOfUser = mail.getText().toString();
                String passwordOfUser = password.getText().toString();
                String conFirmPasswordOfUser = confirmPassword.getText().toString();

                if(mailOfUser.isEmpty()){
                    mail.setError("Email can't empty");
                }
                else if(passwordOfUser.isEmpty()){
                    password.setError("Password can't empty");
                }
                else if(conFirmPasswordOfUser.isEmpty()){
                    confirmPassword.setError("Confirm password can't empty");
                }else {
                    firebaseAuth.createUserWithEmailAndPassword(mailOfUser  , passwordOfUser)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()&& conFirmPasswordOfUser.equals(passwordOfUser)){
                                    Toast.makeText(getContext(), "SignUp is Successful", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
                                }else if (!conFirmPasswordOfUser.equals(passwordOfUser)) {
                                    Toast.makeText(getContext(), "Can't confirm password, Write confirm password again", Toast.LENGTH_SHORT).show();
                                } else {
                                    mail.setError("Example@example.com");
                                }
                            }
                        });
                }

            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                String s = "Google sign in successful";
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(requireActivity(),
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            startActivity(new Intent(getActivity(), Home.class));
                                            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}