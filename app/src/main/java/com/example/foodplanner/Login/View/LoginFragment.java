package com.example.foodplanner.Login.View;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.foodplanner.Home;
import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginFragment extends Fragment {
    EditText mail , password ;
    Button btn_login ,btn_google ,btn_facebook;
    TextView tv_signUp ;
    private FirebaseAuth firebaseAuth ;
    private GoogleSignInClient googleSignInClient;
    public static final String SHARED_PREF = "sharedPrefs";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_login, container, false);

         checkSP();
         return view ;
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

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("652696452366-md44dnm5r175jgm9d2si8be5ut4k8kad.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });
        //to check
        //checkSP();
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

                                            SharedPreferences sharedPreferences= getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
                                            SharedPreferences.Editor editor=sharedPreferences.edit();
                                            editor.putString("name","true");
                                            editor.apply();

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

    private void checkSP() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String isLoggedIn = sharedPreferences.getString("name", "");

        if (isLoggedIn.equals("true")) {
            Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), Home.class));
            getActivity().finish();
        }
    }
}