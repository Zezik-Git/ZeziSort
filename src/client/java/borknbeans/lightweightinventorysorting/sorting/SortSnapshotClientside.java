package borknbeans.lightweightinventorysorting.sorting;

import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class SortSnapshotClientside {
    private final List<ItemStack> inventory;

    public SortSnapshotClientside(List<ItemStack> inventory) {
        this.inventory = inventory;
    }

    public String encode() {
        var gson = new GsonBuilder()
            .registerTypeAdapter(ItemStack.class, new ItemStackSerializer())
            .create();
        var json = gson.toJson(this);
        
        try {
            var baos = new ByteArrayOutputStream();
            var gzipOut = new GZIPOutputStream(baos);
            gzipOut.write(json.getBytes());
            gzipOut.close();
            
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to compress inventory data", e);
        }
    }

    private static class ItemStackSerializer implements JsonSerializer<ItemStack> {
        @Override
        public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
            var json = new JsonObject();
            
            if (src.isEmpty()) {
                json.addProperty("empty", true);
                return json;
            }

            json.addProperty("id", Registries.ITEM.getId(src.getItem()).toString());
            json.addProperty("count", src.getCount());

            return json;
        }
    }
} 