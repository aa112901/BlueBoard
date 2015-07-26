package org.succlz123.AxBTube.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import org.succlz123.AxBTube.R;
import org.succlz123.AxBTube.ui.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {
	private final static int DRAWER_LAYOUT_HEADER_VIEW_HEIGHT_DPI = 180;

	@Bind(R.id.drawer_layout)
	public DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		if (navigationView != null) {
			setupDrawerContent(navigationView);
		}

		FragmentManager fm = getSupportFragmentManager();
		if (fm.findFragmentByTag(MainFragment.FRAGMENT_TAG) == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.fragment_content, new MainFragment(), MainFragment.FRAGMENT_TAG);
			ft.commitAllowingStateLoss();
			fm.executePendingTransactions();
		}
	}

	private void setupDrawerContent(NavigationView navigationView) {
		navigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem) {
						menuItem.setChecked(true);
						mDrawerLayout.closeDrawers();
						return true;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}