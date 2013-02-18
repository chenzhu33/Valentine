package com.hippo.merry;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LetterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_letter);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		ListView listView = (ListView) findViewById(R.id.letter_text);
		List<String> l = new ArrayList<String>();
		l.add("亲爱的大河马老婆：");
		l.add("       又是一个深夜里的三点钟，每天匆匆忙忙的完成一点点内容，虽然到现在依然看着很粗糙。不管怎么说，它表达了我的想法。");
		l.add("       我们快恋爱五年了，五年里我们不停的吵架，甚至前几天还在吵架，但是我们仍然在一起。五年里，你带给了我很多快乐，我铭记在心。前几年是你在爱我，所以我想说，接下来，让我来爱你、照顾你吧：）");
		l.add("       算来，这应该是我们过的第五个情人节，我们大四。我不是一个浪漫的人，所以一直不懂怎么给你一个浪漫的理想的情人节。但是两个人在一起过日子，还是实际点好，所以选我没错儿的，我会赚钱不会花钱，嘿嘿。");
		l.add("       外面还在放烟火，有一个画面一直在我的脑子里，前年的大年夜，我和你在外面放烟花，我从后面抱住你，说我爱你。现在我也想说...");
		l.add("       我爱你，我爱你，我爱你！！！");
		l.add("       我知道结婚不是一件随便的事，但是这一生我认定你了，不管上辈子是我欠你的还是你欠我的，总之这辈子咱俩没跑儿了。所以你答应也得答应，不答应也得答应，嘿嘿！");
		l.add("       不过不管怎么样，还需要你做一个选择......--->>>");

		MyListAdapter mlAdapter = new MyListAdapter(l, LetterActivity.this);
		listView.setAdapter(mlAdapter);
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(5000);
		set.addAnimation(animation);
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(5000);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 1.0f);
		listView.setLayoutAnimation(controller);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 7) {
					Intent intent = new Intent(LetterActivity.this,
							SelectorActivity.class);
					LetterActivity.this.startActivity(intent);
					overridePendingTransition(R.anim.hyperspace_in,
							R.anim.hyperspace_out);
				}

			}
		});
	}

	public class MyListAdapter extends BaseAdapter {

		private List<String> list = null;
		private LayoutInflater inflater;

		public MyListAdapter(List<String> list, Context context) {
			this.list = list;
			this.inflater = LayoutInflater.from(context);

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				view = inflater.inflate(R.layout.adapter_listview, null);
			} else {
				view = convertView;
			}

			TextView textView = (TextView) view.findViewById(R.id.list_text);

			if (list != null) {
				String infoVo = list.get(position);
				textView.setText(infoVo);
			}
			return view;
		}

	}

}
