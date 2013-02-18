package com.hippo.merry;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	private VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_video);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		videoView = (VideoView) this.findViewById(R.id.videoview);
		MediaController mc = new MediaController(this);
		videoView.setMediaController(mc);
		videoView.setVideoPath("mnt/sdcard/Video/t.mp4");
		videoView.requestFocus();
		videoView.start();
		Toast.makeText(VideoActivity.this, "本来想录一个视频，怎奈你老公实在短期内没有找到能录的东西:(",
				Toast.LENGTH_SHORT).show();
	}

}
