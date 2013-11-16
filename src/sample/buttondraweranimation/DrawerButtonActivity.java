package sample.buttondraweranimation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;

public class DrawerButtonActivity extends Activity {
	boolean showOptions = false;

	float animationListFromY, animationListToY, animationThumbnailsFromX,
			animationThumbnailsToX, animationThumbnailsFromY,
			animationThumbnailsToY, animationLocationFromX,
			animationLocationToX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_drawer_button);
		Log.e("DrawerButtonActivity", "onCreate");

		initUI();
	}

	private void initUI() {
		Button btnShowOptions = (Button) findViewById(R.id.btn_show_options);

		btnShowOptions.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startAnimate();
			}
		});

	}

	// public void showOptionsClicked(View view) {
	// Log.e("DrawerButtonActivity", "showOptionsClicked");
	// startAnimate();
	// }

	public void startAnimate() {
		Animation animationFade;
		int duration = 250;

		ImageView fadeSampler = (ImageView) findViewById(R.id.img_android);

		Button btnShowLocation = (Button) findViewById(R.id.btn_show_location);
		Button btnShowThumbnails = (Button) findViewById(R.id.btn_show_thumbnails);
		Button btnShowList = (Button) findViewById(R.id.btn_show_list);

		Button[] imageArray = { btnShowList, btnShowThumbnails, btnShowLocation };

		if (showOptions == false) {

			animationListFromY = 0.0f;
			animationListToY = -150.0f;
			animationThumbnailsFromX = 0.0f;
			animationThumbnailsToX = -100.0f;
			animationThumbnailsFromY = 0.0f;
			animationThumbnailsToY = -100.0f;
			animationLocationFromX = 0.0f;
			animationLocationToX = -150.0f;

			animationFade = AnimationUtils.loadAnimation(this,
					R.anim.animation_fade_in);

			showOptions = true;
		} else {

			animationListFromY = -150.0f;
			animationListToY = 0.0f;
			animationThumbnailsFromX = -100.0f;
			animationThumbnailsToX = 0.0f;
			animationThumbnailsFromY = -100.0f;
			animationThumbnailsToY = 0.0f;
			animationLocationFromX = -150.0f;
			animationLocationToX = 0.0f;

			animationFade = AnimationUtils.loadAnimation(this,
					R.anim.animation_fade_out);

			showOptions = false;

		}

		// new TranslateAnimation(xFrom,xTo,yFrom,yTo)

		TranslateAnimation animationList = new TranslateAnimation(0.0f, 0.0f,
				animationListFromY, animationListToY);
		TranslateAnimation animationThumbnails = new TranslateAnimation(
				animationThumbnailsFromX, animationThumbnailsToX,
				animationThumbnailsFromY, animationThumbnailsToY);
		TranslateAnimation animationLocation = new TranslateAnimation(
				animationLocationFromX, animationLocationToX, 0.0f, 0.0f);

		// animation repeat count
		animationList.setRepeatCount(0);
		animationThumbnails.setRepeatCount(0);
		animationLocation.setRepeatCount(0);

		// repeat animation (left to right, right to left )
		animationList.setRepeatMode(1);
		animationThumbnails.setRepeatMode(1);
		animationLocation.setRepeatMode(1);

		animationList.setFillAfter(true);
		animationThumbnails.setFillAfter(true);
		animationLocation.setFillAfter(true);

		// animation duration
		animationList.setDuration(duration);
		animationThumbnails.setDuration(duration);
		animationLocation.setDuration(duration);

		// Create animation set for multiple animation parameters
		AnimationSet animationSetList = new AnimationSet(true);
		AnimationSet animationSetThumbnails = new AnimationSet(true);
		AnimationSet animationSetLocation = new AnimationSet(true);

		animationSetList.addAnimation(animationList);
		animationSetList.addAnimation(animationFade);
		animationSetList.setFillAfter(true);

		animationSetThumbnails.addAnimation(animationThumbnails);
		animationSetThumbnails.addAnimation(animationFade);
		animationSetThumbnails.setFillAfter(true);

		animationSetLocation.addAnimation(animationLocation);
		animationSetLocation.addAnimation(animationFade);
		animationSetLocation.setFillAfter(true);

		// Start animation
		imageArray[0].startAnimation(animationSetList);
		imageArray[1].startAnimation(animationSetThumbnails);
		imageArray[2].startAnimation(animationSetLocation);

		fadeSampler.startAnimation(animationFade);
	}

}
