package com.hippo.merry;

import java.util.ArrayList;
import java.util.List;

import com.hippo.merry.gallery.GalleryActivity;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {
	/**
	 * Views
	 */
	private GridView contentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		contentView = (GridView) findViewById(R.id.fullscreen_gridview);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Set up the user interaction to manually show or hide the system UI.
		List<String> content = new ArrayList<String>();
		content.add("Beijing");
		content.add("Travel");
		content.add("Memory");
		content.add("Some voice");
		content.add("Some words");
		content.add("Choice");

		GridViewAdapter gvAdapter = new GridViewAdapter(MainActivity.this,
				content);
		contentView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		contentView.setBackgroundColor(Color.TRANSPARENT);
		contentView.setAdapter(gvAdapter);
		contentView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = null;
				if (arg2 <= 2) {
					intent = new Intent(MainActivity.this,
							GalleryActivity.class);
					intent.putExtra("index", arg2+1);
				} else if (arg2 == 3)
					intent = new Intent(MainActivity.this, VideoActivity.class);
				else if (arg2 == 4)
					intent = new Intent(MainActivity.this, LetterActivity.class);
				else if (arg2 == 5)
					intent = new Intent(MainActivity.this,
							SelectorActivity.class);

				MainActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.hyperspace_in,
						R.anim.hyperspace_out);
			}
		});

	}

}
