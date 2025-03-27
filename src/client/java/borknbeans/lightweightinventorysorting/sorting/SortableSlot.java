package borknbeans.lightweightinventorysorting.sorting;

import borknbeans.lightweightinventorysorting.config.LightweightInventorySortingConfig;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SortableSlot implements Comparable<SortableSlot> {

    private int index;
    private ItemStack stack;

    public boolean sorted;

    public SortableSlot(int index, ItemStack stack) {
        this.index = index;
        this.stack = stack;
        sorted = false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public int compareTo(@NotNull SortableSlot o) {
        // Compare the two stacks
        int result = LightweightInventorySortingConfig.sortType.compare(this.getStack(), o.getStack());

        if (result != 0) { // Items are different
            return result;
        } else if (!ItemStack.areItemsAndComponentsEqual(this.getStack(), o.getStack())) { // Items are the same, but different components (or item)
            return 0;
        } else { // Item and components is the exact same
            return o.getStack().getCount() - this.getStack().getCount();
        }
    }
}
