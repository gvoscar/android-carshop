package com.gvoscar.apps.carshop.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.home.HomeActivity;
import com.gvoscar.apps.carshop.login.presenters.LoginPresenter;
import com.gvoscar.apps.carshop.login.presenters.LoginPresenterImpl;
import com.gvoscar.apps.carshop.logs.SimpleLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.btnGoogle)
    SignInButton btnGoogle;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.containerLogin)
    ConstraintLayout containerLogin;


    private GoogleSignInClient mGoogleSignInClient;
    private LoginPresenter mPresenter;
    private boolean hasActionMain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        // Configurar proveedor de autenticacion de Google.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnGoogle.setSize(SignInButton.SIZE_WIDE);
        btnGoogle.setColorScheme(SignInButton.COLOR_DARK);

        initPresenter();

        enableInputs();
    }

    private void initPresenter() {
        this.mPresenter = new LoginPresenterImpl(this, this);
        this.mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
    }

    @Override
    public void enableInputs() {
        this.btnGoogle.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        this.btnGoogle.setEnabled(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        showMessage(message);
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.btnGoogle)
    public void onClickBtnGoogle() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }


    private void showMessage(String str) {
        Snackbar.make(this.containerLogin, str, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            try {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = task.getResult(ApiException.class);
                this.mPresenter.signinWithGoogle(account);
            } catch (ApiException e) {
                SimpleLog.e(TAG, "onActivityResult().   Operación abortada   " + e.getLocalizedMessage(), e.getCause());
                showMessage("La firma de certificado no tiene autorización");
            }

        }
    }
}