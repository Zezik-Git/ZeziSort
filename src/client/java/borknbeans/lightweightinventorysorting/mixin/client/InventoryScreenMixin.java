package borknbeans.lightweightinventorysorting.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import borknbeans.lightweightinventorysorting.LightweightInventorySortingClient;
import borknbeans.lightweightinventorysorting.config.Config;
import borknbeans.lightweightinventorysorting.sorting.SortButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends HandledScreen<PlayerScreenHandler> {

    @Unique
    private SortButton sortButton;

    public InventoryScreenMixin(PlayerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        int size = Config.buttonSize.getButtonSize();
        sortButton = new SortButton(0, 0, size, size, Text.literal("S"), 9, 35);
        setButtonCoordinates(size);

        this.addDrawableChild(sortButton);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (sortButton != null) {
            setButtonCoordinates(Config.buttonSize.getButtonSize());
            sortButton.render(context, mouseX, mouseY, delta);
        }
    }

    // This override is NOT an ideal solution as it could lead to conflicts with other mods
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (LightweightInventorySortingClient.sortKeyBind.matchesKey(keyCode, scanCode)) {
            sortButton.onClick(0f, 0f); // Simulate a click
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void setButtonCoordinates(int size) {
        sortButton.setX(this.x + this.backgroundWidth - 20 + Config.xOffsetInventory + 12 - size);
        sortButton.setY(this.height / 2 - 15 + Config.yOffsetInventory + 12 - size);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (LightweightInventorySortingClient.sortKeyBind.matchesMouse(button)) {
            sortButton.onClick(0f, 0f); // Simulate a click
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    private void setButtonCoordinates() {
        sortButton.setX(this.x + this.backgroundWidth - 20 + Config.xOffsetInventory);
        sortButton.setY(this.height / 2 - 15 + Config.yOffsetInventory);
    }
}
