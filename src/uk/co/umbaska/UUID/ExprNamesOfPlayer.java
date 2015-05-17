/*
 * ExprNamesOfPlayer.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.UUID;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ExprNamesOfPlayer extends SimpleExpression<String>{

    private Expression<String> uuid;

    public Class<? extends String> getReturnType() {

        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false; //Silly Nfell is Silly
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.uuid = (Expression<String>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "return names of player";
    }

    @javax.annotation.Nullable
    private static String NAME_HISTORY_URL = "https://api.mojang.com/user/profiles/";
    private static final JSONParser jsonParser = new JSONParser();
    protected String[] get(Event arg0) {
        String uuid = this.uuid.getSingle(arg0);
        JSONArray array = null;
        try
        {
            HttpURLConnection connection = (HttpURLConnection)new URL(NAME_HISTORY_URL + uuid.toString().replace("-", "") + "/names").openConnection();
            array = (JSONArray)jsonParser.parse(new InputStreamReader(connection.getInputStream()));
        }
        catch (Exception ioe)
        {
        }
        String arr = array.toString();
        arr = arr.replace("{", "");
        arr = arr.replace("}", "");
        arr = arr.replace("[", "");
        arr = arr.replace("]", "");
        arr = arr.replace("\"", "");
        String[] ar = arr.split(",");
        Integer size = ar.length;
        String a = null;
        Integer c = -1;
        String[] name = null;
        for (int i = 0; i < size; ++i) {
            name = ar[i].split(":");
            if (name[0].toString().equalsIgnoreCase("name")) {
                ++c;
                if (a == null) {
                    a = name[1];
                } else {
                    a = a + ", " + name[1];
                }
            }
        }
        return new String[] { a };
    }
}