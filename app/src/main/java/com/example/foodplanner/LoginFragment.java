package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    EditText mail , password ;
    Button btn_login ,btn_google ,btn_facebook;
    TextView tv_signUp ;
    private FirebaseAuth firebaseAuth ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mail = view.findViewById(R.id.et_login_Email);
        password = view.findViewById(R.id.et_login_Password);
        btn_login = view.findViewById(R.id.btn_login_Login);
        btn_google = view.findViewById(R.id.btn_login_google);
        btn_facebook = view.findViewById(R.id.btn_login_facebook);
        tv_signUp = view.findViewById(R.id.tv_login_signUp);
        firebaseAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailOfUser = mail.getText().toString();
                String passwordOfUser = password.getText().toString();
                if (!mailOfUser.isEmpty() && Patterns.EMAIL_ADDRESS
                        .matcher(mailOfUser).matches()) {
                    if (!passwordOfUser.isEmpty()) {
                        firebaseAuth.signInWithEmailAndPassword(mailOfUser, passwordOfUser)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(getContext(), "Login Successful",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getActivity(), Home.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(), "E-mail or Password is incorrect, try to type it again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }else {
                        password.setError("Password can't empty");
                    }
                }else if (mailOfUser.isEmpty()) {
                    mail.setError("Email can't empty");
                } else {
                    mail.setError("Enter valid email");
                }
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

    }
}