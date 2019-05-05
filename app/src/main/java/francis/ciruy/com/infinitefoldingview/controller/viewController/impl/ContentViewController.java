package francis.ciruy.com.infinitefoldingview.controller.viewController.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import francis.ciruy.com.infinitefoldingview.R;
import francis.ciruy.com.infinitefoldingview.controller.viewController.ContactViewController;
import francis.ciruy.com.infinitefoldingview.entity.impl.DemoEntity;

public class ContentViewController extends ContactViewController<DemoEntity> {
    private ImageView icon;
    private TextView name;
    private TextView info;
    private ImageView right;

    @Override
    protected void findViewById(View rootView) {
        icon = rootView.findViewById(R.id.icon);
        name = rootView.findViewById(R.id.name);
        info = rootView.findViewById(R.id.info);
        right = rootView.findViewById(R.id.right);
    }

    @Override
    public View rightView() {
        return right;
    }

    @Override
    public int layout() {
        return R.layout.ly_my_contact_item;
    }

    @Override
    public void visit(DemoEntity obj) {
        super.visit(obj);
        right.setVisibility(obj.subDepartment.size() > 0 ? View.VISIBLE : View.INVISIBLE);
        name.setText(obj.name);
        info.setText(obj.info);
    }
}
