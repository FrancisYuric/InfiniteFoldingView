package francis.ciruy.com.infinitefoldingview.controller.viewController;

import android.util.Log;
import android.view.View;

import francis.ciruy.com.infinitefoldingview.entity.BaseContactEntity;
import francis.ciruy.com.infinitefoldingview.entity.IVisitor;

public abstract class ContactViewController<MODEL extends BaseContactEntity> extends BaseViewController
        implements IVisitor<MODEL> {
    protected Boolean hasNextOrChild;

    public View rightView() {
        return null;
    }

    @Override
    public ContactViewController rootView(View view) {
        return (ContactViewController) super.rootView(view);
    }

    @Override
    protected abstract void findViewById(View rootView);

    protected ContactViewController() {

    }

    public ContactViewController view(View view) {
        this.rootView = view;
        findViewById(view);
        return this;
    }

    protected ContactViewController hasNextOrChild(Boolean isLast) {
        this.hasNextOrChild = isLast;
        return this;
    }


    public View view() {
        return rootView;
    }

    public abstract int layout();

    @Override
    public void visit(MODEL obj) {
        if(rootView == null) {
            Log.e("CiruyError", "Please Call method rootView() first!");
        }
    }
}