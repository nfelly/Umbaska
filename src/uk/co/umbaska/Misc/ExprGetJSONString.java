/*
 * ExprGetJSONString.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import java.util.Scanner;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.json.*;


public class ExprGetJSONString extends SimpleExpression<String>{

    private Expression<String> input;
    private Expression<String> string;

    public Class<? extends String> getReturnType() {

        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
        this.input = (Expression<String>) args[0];
        this.string = (Expression<String>) args[1];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "get json string";
    }

    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String in = this.input.getSingle(arg0);
        String s = this.string.getSingle(arg0);

        if (in == null){
            return null;
        } else if (s == null) {
        	return null;
        }
        Scanner scanner = new Scanner(in);
        JSONArray array = new JSONArray(scanner.next());
        JSONObject object = array.getJSONObject(0);
        String out = object.getString(s);
        scanner.close();
        System.out.println(out);
        return new String[] { out };
    }

}