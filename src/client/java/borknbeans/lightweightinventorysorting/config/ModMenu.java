package borknbeans.lightweightinventorysorting.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("category.lightweight-inventory-sorting.title"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory generalSettings = builder.getOrCreateCategory(Text.translatable("category.lightweight-inventory-sorting.general"));

        generalSettings.addEntry(entryBuilder.startTextDescription(Text.translatable("category.lightweight-inventory-sorting.sort-options"))
                .build());

        generalSettings.addEntry(entryBuilder.startEnumSelector(
                        Text.translatable("category.lightweight-inventory-sorting.sort-type"),
                        SortType.class,
                        Config.sortType
                ).setDefaultValue(SortType.INDEX)
                .setSaveConsumer(newValue -> Config.sortType = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.sort-type-tooltip"))
                .build());

        generalSettings.addEntry(entryBuilder.startBooleanToggle(
                        Text.translatable("category.lightweight-inventory-sorting.reverse-sort"),
                        Config.reverseSort
                ).setDefaultValue(false)
                .setSaveConsumer(newValue -> Config.reverseSort = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.reverse-sort-tooltip"))
                .build());

        generalSettings.addEntry(entryBuilder.startIntField(
                        Text.translatable("category.lightweight-inventory-sorting.sort-delay"),
                        Config.sortDelay
                ).setDefaultValue(0)
                .setSaveConsumer(newValue -> Config.sortDelay = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.sort-delay-tooltip"))
                .build());

        generalSettings.addEntry(entryBuilder.startTextDescription(Text.translatable("category.lightweight-inventory-sorting.button-options"))
                .build());

        generalSettings.addEntry(entryBuilder.startEnumSelector(
                        Text.translatable("category.lightweight-inventory-sorting.button-size"),
                        ButtonSize.class,
                        Config.buttonSize
                ).setDefaultValue(ButtonSize.LARGE)
                .setSaveConsumer(newValue -> Config.buttonSize = newValue)
                .build());

        generalSettings.addEntry(entryBuilder.startIntField(
                    Text.translatable("category.lightweight-inventory-sorting.inventory-x"),
                    Config.xOffsetInventory
                ).setDefaultValue(0)
                .setSaveConsumer(newValue -> Config.xOffsetInventory = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.inventory-x-tooltip"))
                .build());

        generalSettings.addEntry(entryBuilder.startIntField(
                        Text.translatable("category.lightweight-inventory-sorting.inventory-y"),
                        Config.yOffsetInventory
                ).setDefaultValue(0)
                .setSaveConsumer(newValue -> Config.yOffsetInventory = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.inventory-y-tooltip"))
                .build());

        generalSettings.addEntry(entryBuilder.startIntField(
                        Text.translatable("category.lightweight-inventory-sorting.container-x"),
                        Config.xOffsetContainer
                ).setDefaultValue(0)
                .setSaveConsumer(newValue -> Config.xOffsetContainer = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.container-x-tooltip"))
                .build());

        generalSettings.addEntry(entryBuilder.startIntField(
                        Text.translatable("category.lightweight-inventory-sorting.container-y"),
                        Config.yOffsetContainer
                ).setDefaultValue(0)
                .setSaveConsumer(newValue -> Config.yOffsetContainer = newValue)
                .setTooltip(Text.translatable("category.lightweight-inventory-sorting.container-y-tooltip"))
                .build());

        builder.setSavingRunnable(Config::save);

        return builder.build();
    }
}
