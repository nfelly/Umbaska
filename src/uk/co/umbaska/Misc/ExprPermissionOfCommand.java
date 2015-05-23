/*
 * ExprPermissionOfCommand.class - Made by nfell2009
 * nfell2009.uk (C) nfell2009 | 2014 - 2015
 * Submitted to: Umbaska
 * 
*/

package uk.co.umbaska.Misc;

import java.lang.reflect.Field;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Event;


public class ExprPermissionOfCommand extends SimpleExpression<String>{

    private Expression<String> cmd;

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
        this.cmd = (Expression<String>) args[0];
        return true;
    }

    @Override
    public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
        return "permission of cmd";
    }

	@Override
    @javax.annotation.Nullable
    protected String[] get(Event arg0) {

        String c = this.cmd.getSingle(arg0);
        Field f = null;
		try {
			f = Bukkit.getServer().getClass().getDeclaredField("SimpleCommandMap");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
        f.setAccessible(true);
        SimpleCommandMap cmap = null;
		try {
			cmap = (SimpleCommandMap) f.get(Bukkit.getServer());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
        Command ccc = cmap.getCommand(c);
        String out = ccc.getPermission();
        return new String[] { out };
    }

}