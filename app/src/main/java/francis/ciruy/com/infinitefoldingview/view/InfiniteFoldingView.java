package francis.ciruy.com.infinitefoldingview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import francis.ciruy.com.infinitefoldingview.R;
import francis.ciruy.com.infinitefoldingview.adapter.CustomContactViewAdapter;
import francis.ciruy.com.infinitefoldingview.controller.viewController.ContactViewController;
import francis.ciruy.com.infinitefoldingview.controller.viewController.impl.ContentViewController;
import francis.ciruy.com.infinitefoldingview.controller.viewController.impl.TitleViewController;
import francis.ciruy.com.infinitefoldingview.demoGenerator.ContactGenerator;
import francis.ciruy.com.infinitefoldingview.entity.BaseContactEntity;
import francis.ciruy.com.infinitefoldingview.entity.OnItemChildViewClickListener;
import francis.ciruy.com.infinitefoldingview.util.ContactUtil;

/**
 * @author: Ciruy
 * @E-mail: 398712739@qq.com
 * @Blog: https://blog.csdn.net/qq_31433709
 */
public class InfiniteFoldingView extends LinearLayout  {
    public enum CLICK_POS{
        //点击位置
        TITLE,
        CONTENT,
        RIGHT_BTN,
    }
    private LinearLayout titleView;
    private RecyclerView contentView;
    private ProgressBar loading;

    private ContactViewController contactViewController;
    private ContactViewController contactTitleController;

    private Boolean isDemoView;
    private CustomContactViewAdapter adapter;
    private Context context;
    private ContactUtil contactUtil;

    private OnItemChildViewClickListener onItemChildViewClickListener;

    public InfiniteFoldingView(Context context) {
        this(context, null);
    }

    public InfiniteFoldingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InfiniteFoldingView onItemChildViewClickListener(OnItemChildViewClickListener onItemChildViewClickListener) {
        this.onItemChildViewClickListener = onItemChildViewClickListener;
        return this;
    }

    public InfiniteFoldingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttributeSet(attrs);
        LayoutInflater.from(context).inflate(R.layout.ly_infinte_view, this, true);
        initView();
        initController();
        initData();
    }

    private void initData() {
        if (isDemoView) {
            rootContentEntity(ContactGenerator.createDemoOriginEntity());
            refreshTitle();
        }
    }

    private void refreshTitle() {
        final List<BaseContactEntity> titleList = contactUtil.getTitleList();
        titleView.removeAllViews();
        for (int i = 0; i < titleList.size(); ++i) {
            BaseContactEntity baseContactEntity = titleList.get(i);
            View childRootView = LayoutInflater.from(context).inflate(contactTitleController.layout(), null, false);
            contactTitleController.view(childRootView).visit(baseContactEntity);
            final int finalI = i;
            childRootView.setOnClickListener(v -> {
                if(onItemChildViewClickListener != null) {
                    onItemChildViewClickListener.onItemChildViewClick(childRootView, -1, CLICK_POS.TITLE.name(), baseContactEntity);
                }
                contactUtil.clicked(contactUtil.getTitleList().get(finalI));
                refreshTitle();
                adapter.setList(contactUtil.getLast().subDepartment);
            });
            titleView.addView(childRootView);
        }
    }
    public InfiniteFoldingView rootContentEntity(BaseContactEntity baseContactEntity) {
        initContent(baseContactEntity);
        return this;
    }

    private void initContent(BaseContactEntity baseContactEntity) {
        if(contactTitleController == null) {
            contactTitleController = new TitleViewController();
        }
        if(contactViewController == null) {
            contactViewController = new ContentViewController();
        }

        this.contactUtil = new ContactUtil(baseContactEntity);
        titleView.removeAllViews();
        View view = LayoutInflater.from(context).inflate(contactTitleController.layout(), null, false);
        contactTitleController.view(view).visit(baseContactEntity);
        view.setOnClickListener(v -> {contactUtil.clicked(baseContactEntity);
            if(onItemChildViewClickListener != null) {
                onItemChildViewClickListener.onItemChildViewClick(view, -1, CLICK_POS.TITLE.name(), baseContactEntity);
            }});
        titleView.addView(view);
        if (adapter == null) {
            adapter = new CustomContactViewAdapter(context, baseContactEntity.subDepartment);
            adapter.registerViewController(contactViewController);
            adapter.setOnItemChildViewClickListener((childView, position, action, obj) -> {
                BaseContactEntity baseContactEntity1 = (BaseContactEntity) obj;
                if (baseContactEntity1.subDepartment == null || baseContactEntity1.subDepartment.size() <= 0) {

                    if(onItemChildViewClickListener != null) {
                        onItemChildViewClickListener.onItemChildViewClick(childView, -1, CLICK_POS.CONTENT.name(), baseContactEntity);
                    }
                    return;
                }
                contactUtil.clicked(baseContactEntity1);
                refreshTitle();
                adapter.setList(contactUtil.getLast().subDepartment);
            });
            contentView.setAdapter(adapter);
        } else {
            adapter.setList(baseContactEntity.subDepartment);
        }
    }


    private void setContentValue(BaseContactEntity baseContactEntity) {
        contactUtil = new ContactUtil(baseContactEntity);
    }

    public InfiniteFoldingView titleViewController(ContactViewController titleViewController) {
        this.contactTitleController = titleViewController;
        return this;
    }

    public InfiniteFoldingView contentViewController(ContactViewController contentViewController) {
        this.contactViewController = contentViewController;
        return this;
    }

    protected void initAttributeSet(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.InfiniteFoldingView);
            isDemoView = array.getBoolean(R.styleable.InfiniteFoldingView_isDemoView, false);
            array.recycle();
        }
    }

    private void initView() {
        titleView = findViewById(R.id.titleView);
        contentView = findViewById(R.id.contentView);
        loading = findViewById(R.id.loading);
        initRecyclerView();
    }

    public void setAdapter(CustomContactViewAdapter adapter) {
        this.adapter = adapter;
        contentView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        contentView.setLayoutManager(new LinearLayoutManager(context, VERTICAL, false));
        contentView.addItemDecoration(new SpaceItemDecoration(2));
    }

    private void initController() {
        if (isDemoView) {
            contactTitleController = new TitleViewController();
            contactViewController = new ContentViewController();
        }
    }

}
