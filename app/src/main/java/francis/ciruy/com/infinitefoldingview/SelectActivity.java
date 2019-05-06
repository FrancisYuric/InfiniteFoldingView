package francis.ciruy.com.infinitefoldingview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        SelectActivity.this
                .bindClickListener(R.id.demo, v -> startActivity(new Intent(SelectActivity.this, DemoActivity.class)))
                .bindClickListener(R.id.main, v -> startActivity(new Intent(SelectActivity.this, MainActivity.class)));
    }

    private SelectActivity bindClickListener(@IdRes int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
        return this;
    }
}
