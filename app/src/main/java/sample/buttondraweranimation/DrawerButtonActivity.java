package sample.buttondraweranimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.view.View.*;
import static android.view.animation.Animation.AnimationListener;

public class DrawerButtonActivity extends ActionBarActivity{
	boolean showOptions = false;

    private ImageView fadeSampler;
    private ImageButton btnShowOptions,btnShareFacebook, btnShareTwitter, btnShareEmail;
    private RelativeLayout mRelativeLayoutDrawerButton;

	float animationBtnShareFacebookFromY, animationBtnShareFacebookToY,
            animationBtnShareTwitterFromX, animationBtnShareTwitterToX, animationBtnShareTwitterFromY, animationBtnShareTwitterToY,
            animationBtnShareEmailFromX, animationBtnShareEmailToX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_drawer_button);

        mRelativeLayoutDrawerButton = (RelativeLayout) findViewById(R.id.layout_drawer_button);

        fadeSampler = (ImageView) findViewById(R.id.img_android);

        btnShowOptions = (ImageButton) findViewById(R.id.btn_show_options);
        btnShareFacebook = (ImageButton) findViewById(R.id.btn_share_fb);
        btnShareTwitter = (ImageButton) findViewById(R.id.btn_share_twitter);
        btnShareEmail = (ImageButton) findViewById(R.id.btn_share_email);
        btnShowOptions.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getResources().getString(R.string.txt_btn_show_options_clicked));
                startAnimate();
            }
        });
	}

	public void startAnimate() {
		Animation animationFade;
		int duration = 250;

        ImageButton[] imageButtonArray = { btnShareFacebook, btnShareTwitter, btnShareEmail };

		if (!showOptions) {

            // Set animation to show the buttons
            animationBtnShareFacebookFromY = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareFacebookToY = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareTwitterFromX = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareTwitterToX = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareTwitterFromY = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareTwitterToY = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareEmailFromX = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareEmailToX = -(getResources().getDimension(R.dimen.btn_animation_dist_end));

			animationFade = AnimationUtils.loadAnimation(this, R.anim.animation_fade_in);

			showOptions = true;
		} else {

            // Set animation to hide the buttons
            animationBtnShareFacebookFromY = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareFacebookToY = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareTwitterFromX = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareTwitterToX = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareTwitterFromY = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareTwitterToY = getResources().getDimension(R.dimen.btn_animation_dist_start);
            animationBtnShareEmailFromX = -(getResources().getDimension(R.dimen.btn_animation_dist_end));
            animationBtnShareEmailToX = getResources().getDimension(R.dimen.btn_animation_dist_start);

			animationFade = AnimationUtils.loadAnimation(this, R.anim.animation_fade_out);

			showOptions = false;
		}

		// new TranslateAnimation(xFrom,xTo,yFrom,yTo)
		TranslateAnimation animationBtnShareFacebook = new TranslateAnimation(0.0f, 0.0f,
                animationBtnShareFacebookFromY, animationBtnShareFacebookToY);
		TranslateAnimation animationBtnShareTwitter = new TranslateAnimation(
                animationBtnShareTwitterFromX, animationBtnShareTwitterToX,
                animationBtnShareTwitterFromY, animationBtnShareTwitterToY);
		TranslateAnimation animationBtnShareEmail = new TranslateAnimation(
                animationBtnShareEmailFromX, animationBtnShareEmailToX, 0.0f, 0.0f);

		// animation repeat count
        animationBtnShareFacebook.setRepeatCount(0);
        animationBtnShareTwitter.setRepeatCount(0);
        animationBtnShareEmail.setRepeatCount(0);

		// repeat animation (left to right, right to left )
        animationBtnShareFacebook.setRepeatMode(1);
        animationBtnShareTwitter.setRepeatMode(1);
		animationBtnShareEmail.setRepeatMode(1);

        animationBtnShareFacebook.setFillAfter(true);
        animationBtnShareTwitter.setFillAfter(true);
        animationBtnShareEmail.setFillAfter(true);

		// animation duration
        animationBtnShareFacebook.setDuration(duration);
        animationBtnShareTwitter.setDuration(duration);
        animationBtnShareEmail.setDuration(duration);

		// Create animation set for multiple animation parameters
		AnimationSet animationSetBtnShareFacebook = new AnimationSet(true);
		AnimationSet animationSetBtnShareTwitter = new AnimationSet(true);
		AnimationSet animationSetBtnShareEmail = new AnimationSet(true);

        animationSetBtnShareFacebook.addAnimation(animationBtnShareFacebook);
        animationSetBtnShareFacebook.addAnimation(animationFade);
        animationSetBtnShareFacebook.setFillAfter(true);

        animationSetBtnShareTwitter.addAnimation(animationBtnShareTwitter);
        animationSetBtnShareTwitter.addAnimation(animationFade);
        animationSetBtnShareTwitter.setFillAfter(true);

        animationSetBtnShareEmail.addAnimation(animationBtnShareEmail);
        animationSetBtnShareEmail.addAnimation(animationFade);
        animationSetBtnShareEmail.setFillAfter(true);

		// Start animation
        imageButtonArray[0].startAnimation(animationSetBtnShareFacebook);
        imageButtonArray[1].startAnimation(animationSetBtnShareTwitter);
        imageButtonArray[2].startAnimation(animationSetBtnShareEmail);

		fadeSampler.startAnimation(animationFade);
	}

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
