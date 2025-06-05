package borknbeans.lightweightinventorysorting.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import borknbeans.lightweightinventorysorting.LightweightInventorySorting;
import net.fabricmc.loader.api.FabricLoader;

public class Config {
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "lightweight-inventory-sorting.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static SortType sortType = SortType.INDEX;
    public static ButtonSize buttonSize = ButtonSize.LARGE;
    public static boolean reverseSort = false;

    public static int xOffsetInventory = 0;
    public static int yOffsetInventory = 0;
    public static int xOffsetContainer = 0;
    public static int yOffsetContainer = 0;

    public static int sortDelay = 0;

    private static class ConfigData {
        SortType sortType;
        ButtonSize buttonSize;
        boolean reverseSort;
        int xOffsetInventory;
        int yOffsetInventory;
        int xOffsetContainer;
        int yOffsetContainer;
        int sortDelay;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                sortType = data.sortType == null ? SortType.INDEX : data.sortType;
                buttonSize = data.buttonSize == null ? ButtonSize.MEDIUM : data.buttonSize;
                reverseSort = data.reverseSort;
                xOffsetInventory = data.xOffsetInventory;
                yOffsetInventory = data.yOffsetInventory;
                xOffsetContainer = data.xOffsetContainer;
                yOffsetContainer = data.yOffsetContainer;
                sortDelay = data.sortDelay;
            } catch (IOException e) {
                LightweightInventorySorting.LOGGER.error("Failed to load config file (resetting to default): {}", e.getMessage());
                save();
            }
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            ConfigData data = new ConfigData();
            data.sortType = sortType;
            data.buttonSize = buttonSize;
            data.reverseSort = reverseSort;
            data.xOffsetInventory = xOffsetInventory;
            data.yOffsetInventory = yOffsetInventory;
            data.xOffsetContainer = xOffsetContainer;
            data.yOffsetContainer = yOffsetContainer;
            data.sortDelay = sortDelay;
            GSON.toJson(data, writer);
        } catch (IOException e) {
            LightweightInventorySorting.LOGGER.error("Failed to save config file: {}", e.getMessage());
        }
    }
}
