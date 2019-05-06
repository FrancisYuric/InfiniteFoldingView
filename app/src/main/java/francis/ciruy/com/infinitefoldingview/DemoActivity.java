package francis.ciruy.com.infinitefoldingview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import francis.ciruy.com.infinitefoldingview.view.InfiniteFoldingView;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);
        InfiniteFoldingView infiniteFoldingView = findViewById(R.id.infiniteFoldingView);
        infiniteFoldingView.onItemChildViewClickListener((view, pos, action, entity) -> {
            if (action.equals(InfiniteFoldingView.CLICK_POS.CONTENT.name())) {
                Toast.makeText(DemoActivity.this, "点击了部门内容", Toast.LENGTH_SHORT).show();
                Log.e("Ciruy Log", "点击了部门内容");
            } else if (action.equals(InfiniteFoldingView.CLICK_POS.RIGHT_BTN.name())) {
                Toast.makeText(DemoActivity.this, "点击了部门右侧箭头", Toast.LENGTH_SHORT).show();
                Log.e("Ciruy Log", "点击了部门右侧箭头");
            } else if (action.equals(InfiniteFoldingView.CLICK_POS.TITLE.name())) {
                Toast.makeText(DemoActivity.this, "点击了部门标题", Toast.LENGTH_SHORT).show();
                Log.e("Ciruy Log", "点击了部门标题");
            }
        });
    }
}
