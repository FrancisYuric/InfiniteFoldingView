package francis.ciruy.com.infinitefoldingview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import francis.ciruy.com.infinitefoldingview.controller.viewController.ContactViewController;
import francis.ciruy.com.infinitefoldingview.entity.BaseContactEntity;
import francis.ciruy.com.infinitefoldingview.entity.OnItemChildViewClickListener;

public class CustomContactViewAdapter<T extends BaseContactEntity> extends RecyclerView.Adapter {
    private List<T> list;
    private ContactViewController contactViewController;
    private OnItemChildViewClickListener onItemChildViewClickListener;


    public void setOnItemChildViewClickListener(OnItemChildViewClickListener onItemChildViewClickListener) {
        this.onItemChildViewClickListener = onItemChildViewClickListener;
    }

    public CustomContactViewAdapter(Context context, List<T> list) {
        super();
        this.list = list;
    }

    public CustomContactViewAdapter registerViewController(ContactViewController contactViewController) {
        this.contactViewController = contactViewController;
        return this;
    }

    public class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
            View right = contactViewController.view(itemView).rightView();
            if(right!= null) {
                right.setOnClickListener(v -> {
                    if (onItemChildViewClickListener != null && getAdapterPosition() >= 0) {
                        onItemChildViewClickListener.onItemChildViewClick(v, VH.this.getAdapterPosition(),
                                1, list.get(VH.this.getAdapterPosition()));
                    }
                });
            }
            itemView.setOnClickListener(v -> {
                if (onItemChildViewClickListener != null && getAdapterPosition() >= 0) {
                    onItemChildViewClickListener.onItemChildViewClick(v, VH.this.getAdapterPosition(),
                            0, list.get(VH.this.getAdapterPosition()));
                }
            });
        }
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(contactViewController.layout(), parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        contactViewController.view(holder.itemView).visit(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}