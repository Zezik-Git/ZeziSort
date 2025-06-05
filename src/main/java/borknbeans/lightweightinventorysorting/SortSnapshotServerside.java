package borknbeans.lightweightinventorysorting;

import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class SortSnapshotServerside {
    public List<ItemStack> inventory;

    public SortSnapshotServerside decode(String compressedData) {
        try {
            var decoded = Base64.getDecoder().decode(compressedData);
            var bais = new ByteArrayInputStream(decoded);
            var gzipIn = new GZIPInputStream(bais);
            
            var buffer = new byte[1024];
            var baos = new ByteArrayOutputStream();
            int len;
            while ((len = gzipIn.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            gzipIn.close();
            
            var json = baos.toString();
            
            var gson = new GsonBuilder()
                .registerTypeAdapter(ItemStack.class, new ItemStackDeserializer())
                .create();
            return gson.fromJson(json, SortSnapshotServerside.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to decompress inventory data", e);
        }
    }

    private static class ItemStackDeserializer implements JsonDeserializer<ItemStack> {
        @Override
        public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            var obj = json.getAsJsonObject();
            
            if (obj.has("empty") && obj.get("empty").getAsBoolean()) {
                return ItemStack.EMPTY;
            }

            var id = obj.get("id").getAsString();
            var count = obj.get("count").getAsInt();
            
            // TODO: Handle item components - idk how to do this
            var item = Registries.ITEM.get(Identifier.of(id));
            var stack = new ItemStack(item);
            stack.setCount(count);

            return stack;
        }
    }
} 