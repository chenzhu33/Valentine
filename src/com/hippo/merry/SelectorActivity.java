package com.hippo.merry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectorActivity extends Activity {
	RelativeLayout rl;
	TextView t;
	Button b1, b2;
	int[] colors = { Color.BLACK, Color.WHITE, Color.RED, Color.BLUE,
			Color.GREEN, Color.YELLOW };

	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			if (message.what < 100)
				rl.setBackgroundColor(colors[message.what]);
			else if (message.what == 100) {
				t.setText("嫁给我吧！");
				t.setVisibility(View.VISIBLE);
				b1.setVisibility(View.VISIBLE);
				b2.setVisibility(View.VISIBLE);
				t.setTextSize(message.what / 4);
			} else if (message.what == 10086) {
				rl.setBackgroundColor(colors[0]);
				t.setVisibility(View.VISIBLE);
				t.setText("情人节快乐");
				t.setTextSize(80);
			} else
				t.setTextSize(message.what / 4);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_selector);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		rl = (RelativeLayout) findViewById(R.id.selector_bg);
		t = (TextView) findViewById(R.id.select_text);
		b1 = (Button) findViewById(R.id.yesbutton);
		b2 = (Button) findViewById(R.id.nobutton);
		t.setVisibility(View.GONE);
		b1.setVisibility(View.GONE);
		b2.setVisibility(View.GONE);
		new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (i < 100) {
					try {
						Thread.sleep(3000 / (i + 1));
						handler.sendEmptyMessage(i % colors.length);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
				handler.sendEmptyMessage(10086);
				try {
					Thread.sleep(6000);
					handler.sendEmptyMessage(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(100);
				i = 101;
				while (i < 400) {
					try {
						Thread.sleep(50);
						handler.sendEmptyMessage(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i += 8;
				}
			}
		}).start();

		b1.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectorActivity.this,
						ResultActivity.class);
				SelectorActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.hyperspace_in,
						R.anim.hyperspace_out);
			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(SelectorActivity.this, "亲，你按错键了= =！",
						Toast.LENGTH_LONG).show();
			}
		});

	}
}
