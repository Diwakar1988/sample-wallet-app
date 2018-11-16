package com.github.diwakar1988.noon.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonViewHolder;
import com.github.diwakar1988.noon.common.NoonViewModel;
import com.github.diwakar1988.noon.common.ViewModel;
import com.github.diwakar1988.noon.databinding.ToolbarActionItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class ToolbarActionsAdapter extends RecyclerView.Adapter<ToolbarActionsAdapter.ToolbarActionViewHolder> {

    public enum ActionType{
        ADD_MONEY,
        SEND_MONEY,
        SCAN_QR,
        REQUEST_MONEY
    }
    public static class Action implements ViewModel{
        private ActionType type;
        private int iconRes;
        private String title;

        private Action(ActionType type, int iconRes, String title) {
            this.type = type;
            this.iconRes = iconRes;
            this.title = title;
        }

        public ActionType getType() {
            return type;
        }

        public int getIconRes() {
            return iconRes;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public void clean() {

        }
        public void clicked(View view){
            //Launch main action from here
            Toast.makeText(view.getContext(), getType().toString()+" Pressed", Toast.LENGTH_SHORT).show();
        }
    }
    private List<Action> items;

    public ToolbarActionsAdapter(Context context) {
        items = new ArrayList<>();
        items.add(new Action(ActionType.ADD_MONEY, R.drawable.ic_noon_logo,context.getString(R.string.add_money)));
        items.add(new Action(ActionType.SEND_MONEY, R.drawable.ic_noon_logo,context.getString(R.string.send_money)));
        items.add(new Action(ActionType.SCAN_QR, R.drawable.ic_noon_logo,context.getString(R.string.scan_qr)));
        items.add(new Action(ActionType.REQUEST_MONEY, R.drawable.ic_noon_logo,context.getString(R.string.request_money)));
    }

    @NonNull
    @Override
    public ToolbarActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ToolbarActionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.toolbar_action_item, parent, false);
        return new ToolbarActionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolbarActionViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ToolbarActionViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ToolbarActionViewHolder extends NoonViewHolder<Action>{

        private ToolbarActionItemBinding binding;

        public ToolbarActionViewHolder(ToolbarActionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Action data) {
            binding.setAction(data);
        }

        @Override
        public void unbind() {
            binding.getAction().clean();
        }
    }
}
