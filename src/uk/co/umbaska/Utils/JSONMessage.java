package uk.co.umbaska.Utils;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary on 5/25/2015.
 */
@SuppressWarnings("unused")
public class JSONMessage {
    private final List<MessagePart> messageParts;
    private String jsonString;
    private boolean dirty;
    private ChatSerializer nmsChatSerializer = new ChatSerializer();
    private NBTTagCompound nmsTagCompound = new NBTTagCompound();

	private PacketPlayOutChat nmsPacketPlayOutChat = new PacketPlayOutChat();

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONMessage(String firstPartText)
    {
        this.messageParts = new ArrayList();
        this.messageParts.add(new MessagePart(firstPartText));
        this.jsonString = null;
        this.dirty = false;
    }

    public JSONMessage color(ChatColor color)
    {
        if (!color.isColor()) {
            throw new IllegalArgumentException(color.name() + " is not a color");
        }
        latest().color = color;
        this.dirty = true;
        return this;
    }

    public JSONMessage style(ChatColor... styles)
    {
        for (ChatColor style : styles) {
            if (!style.isFormat()) {
                throw new IllegalArgumentException(style.name() + " is not a style");
            }
        }
        latest().styles = styles;
        this.dirty = true;
        return this;
    }

    public JSONMessage file(String path)
    {
        onClick("open_file", path);
        return this;
    }

    public JSONMessage link(String url)
    {
        onClick("open_url", url);
        return this;
    }

    public JSONMessage suggest(String command)
    {
        onClick("suggest_command", command);
        return this;
    }

    public JSONMessage command(String command)
    {
        onClick("run_command", command);
        return this;
    }

    public JSONMessage achievementTooltip(String name)
    {
        onHover("show_achievement", "achievement." + name);
        return this;
    }


    public JSONMessage itemTooltip(String itemJSON)
    {
        onHover("show_item", itemJSON);
        return this;
    }

    public JSONMessage tooltip(String text)
    {
        return tooltip(text.split("\\n"));
    }

    public JSONMessage tooltip(List<String> lines)
    {
        return tooltip((String[])lines.toArray());
    }

    public JSONMessage tooltip(String... lines)
    {
        if (lines.length == 1) {
            onHover("show_text", lines[0]);
        } else {
            itemTooltip(makeMultilineTooltip(lines));
        }
        return this;
    }

    public JSONMessage then(Object obj)
    {
        this.messageParts.add(new MessagePart(obj.toString()));
        this.dirty = true;
        return this;
    }

    public String toJSONString()
    {
        if ((!this.dirty) && (this.jsonString != null)) {
            return this.jsonString;
        }
        StringWriter string = new StringWriter();
        JsonWriter json = new JsonWriter(string);
        try
        {
            if (this.messageParts.size() == 1)
            {
                latest().writeJson(json);
            }
            else
            {
                json.beginObject().name("text").value("").name("extra").beginArray();
                for (MessagePart part : this.messageParts) {
                    part.writeJson(json);
                }
                json.endArray().endObject();
                json.close();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("invalid message");
        }
        this.jsonString = string.toString();
        this.dirty = false;
        return this.jsonString;
    }

    public void send(Player... players)
    {
        try
        {
//            Object serialized = Reflection.getMethod(this.nmsChatSerializer, "a", new Class[] { String.class }).invoke(null, new Object[] { toJSONString() });
//            Object packet = this.nmsPacketPlayOutChat.getConstructor(new Class[] { Reflection.nmsClass("IChatBaseComponent") }).newInstance(new Object[] { serialized });
//            Reflection.sendPacket(packet, players);
            for (Player p : players) {
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(
                        new PacketPlayOutChat(ChatSerializer.a(toString())));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String toOldMessageFormat()
    {
        StringBuilder result = new StringBuilder();
        for (MessagePart part : this.messageParts) {
            result.append(part.color).append(part.text);
        }
        return result.toString();
    }

    private MessagePart latest()
    {
        return (MessagePart)this.messageParts.get(this.messageParts.size() - 1);
    }

    private String makeMultilineTooltip(String[] lines)
    {
        StringWriter string = new StringWriter();
        JsonWriter json = new JsonWriter(string);
        try
        {
            json.beginObject().name("id").value(1L);
            json.name("tag").beginObject().name("display").beginObject();
            json.name("Name").value("\\u00A7f" + lines[0].replace("\"", "\\\""));
            json.name("Lore").beginArray();
            for (int i = 1; i < lines.length; i++)
            {
                String line = lines[i];
                json.value(line.isEmpty() ? " " : line.replace("\"", "\\\""));
            }
            json.endArray().endObject().endObject().endObject();
            json.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("invalid tooltip");
        }
        return string.toString();
    }

    private void onClick(String name, String data)
    {
        MessagePart latest = latest();
        latest.clickActionName = name;
        latest.clickActionData = data;
        this.dirty = true;
    }

    private void onHover(String name, String data)
    {
        MessagePart latest = latest();
        latest.hoverActionName = name;
        latest.hoverActionData = data;
        this.dirty = true;
    }
}
