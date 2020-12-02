package com.sphurti.royalrajasthan;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

interface SwipeInterface {
    void requestDrag(RecyclerView.ViewHolder viewHolder);

}

public class RecyclerViewItemSwipe extends ItemTouchHelper.Callback {

    public TripItemTouchHelper tripItemTouchHelper;

    public RecyclerViewItemSwipe(TripItemTouchHelper tripItemTouchHelper) {
        this.tripItemTouchHelper = tripItemTouchHelper;
    }

    public interface TripItemTouchHelper{
        void onItemDismiss(int position);
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
       return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        tripItemTouchHelper.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
