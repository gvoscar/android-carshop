package com.gvoscar.apps.carshop.loader.ui;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.home.HomeActivity;
import com.gvoscar.apps.carshop.loader.presenters.LoaderPresenter;
import com.gvoscar.apps.carshop.loader.presenters.LoaderPresenterImpl;
import com.gvoscar.apps.carshop.login.ui.LoginActivity;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.permissions.CameraPermitDialog;
import com.jrummyapps.android.animations.Technique;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoaderActivity extends AppCompatActivity implements LoaderView {

    private final static String TAG = LoaderActivity.class.getSimpleName();

    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.containerLoader)
    ConstraintLayout containerLoader;

    private boolean isAnimation = false;
    private LoaderPresenter mPresenter;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        ButterKnife.bind(this);

        onFullscreenUI();

        // Iniciar presentador
        initPresenter();

        // Inciar
        mTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // SimpleLog.i(TAG, "Segundos restantes: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                SimpleLog.i(TAG, "La animacion termino");
                mPresenter.checkAuth();
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimer.start();
        // Comprobar animacion no iniciada.
        if (!isAnimation) {
            isAnimation = true;
            // Primera animación.
            onFirstAnimation();
        }

        /*
        if (CameraPermitDialog.isPermissionGranted(this)) {

        } else {
            new CameraPermitDialog().show(getFragmentManager(), "permisoCamara");
        }
         */
    }

    /**
     * Primera animación.
     */
    private void onFirstAnimation() {
        Technique.PULSE.getComposer()
                .delay(100)
                .duration(800)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // Segunda animación.
                        onSecondAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .playOn(imgLogo);
    }

    /**
     * Segunda animación.
     */
    private void onSecondAnimation() {
        Technique.PULSE.getComposer()
                .duration(800)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // Tercera animación.
                        onThirdAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .playOn(imgLogo);
    }

    /**
     * Tercera animación.
     */
    public void onThirdAnimation() {
        Technique.PULSE.getComposer()
                .duration(800)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // Indicar que la animacion ha terminado.
                        isAnimation = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .playOn(imgLogo);
    }

    private void initPresenter() {
        mPresenter = new LoaderPresenterImpl(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Eliminar barra de estado y barra de navegación.
     * <p>
     * Tenga en cuenta que algunas de estas constantes son nuevas a
     * partir de la API 16 (Jelly Bean) y API 19 (KitKat).
     * Es seguro utilizarlos, ya que están en línea en tiempo de
     * compilación y no hacer nada en los dispositivos anteriores.
     */
    private void onFullscreenUI() {
        containerLoader.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return (keyCode == KeyEvent.KEYCODE_BACK);
    }

    @Override
    public void unAuthenticated() {
        SimpleLog.d(TAG, "unAuthenticated");
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onAuthenticated() {
        SimpleLog.d(TAG, "unAuthenticated");
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}