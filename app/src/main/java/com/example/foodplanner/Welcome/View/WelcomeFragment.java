package com.example.foodplanner.Welcome.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Home;
import com.example.foodplanner.R;
import com.example.foodplanner.home.View.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class WelcomeFragment extends Fragment {
    private View overlayView;
    private TextView tv_signIn ;
    private Button btn_guest ,btn_google ,btn_facebook , btn_mail ;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_signIn = view.findViewById(R.id.tv_welcome_signIn);
        btn_guest = view.findViewById(R.id.btn_guest);
        btn_google = view.findViewById(R.id.btn_welcome_google);
        btn_facebook = view.findViewById(R.id.btn_welcome_facebook);
        btn_mail = view.findViewById(R.id.btn_welcome_email);
        firebaseAuth = FirebaseAuth.getInstance();


        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment2);
            }
        });

        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_loginFragment2);
            }
        });

        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transFragment = fragManager.beginTransaction();
                transFragment.add(R.id.homeFragment, homeFragment, "DYNAMIC");
                transFragment.commit();*/
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