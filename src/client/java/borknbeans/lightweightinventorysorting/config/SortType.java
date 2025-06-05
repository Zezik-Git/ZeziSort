package borknbeans.lightweightinventorysorting.config;

import borknbeans.lightweightinventorysorting.LightweightInventorySortingClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Comparator;

public enum SortType {
    INDEX(Comparator.comparingInt(LightweightInventorySortingClient::getCreativeIndex)),
    ALPHANUMERIC(Comparator.comparing(stack -> stack.getName().getString())),
    RAW_ID(Comparator.comparingInt(stack -> Item.getRawId(stack.getItem())));

    public final Comparator<ItemStack> comparator;

    SortType(Comparator<ItemStack> comparator) {
        this.comparator = comparator;
    }

    public int compare(ItemStack left, ItemStack right) {
        // Check for empty slots
        if (left.isEmpty() && !right.isEmpty()) {
            return 1;
        } else if (right.isEmpty() && !left.isEmpty()) {
            return -1;
        }

        int result = this.comparator.compare(left, right);
        result = Config.reverseSort ? -result : result;
        return result == 0 ? left.getCount() - right.getCount() : result;
    }
}
