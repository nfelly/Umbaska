package uk.co.umbaska.JSON;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import uk.co.umbaska.Utils.JSONMessage;

/**
 * Created by Zachary on 5/25/2015.
 */
public class ExprJsonMessage extends SimplePropertyExpression<String, JSONMessage> {
    protected String getPropertyName()
        {
        return "json equivalent";
        }

    public JSONMessage convert(String s)
        {
        return new JSONMessage(s);
        }

    public Class<? extends JSONMessage> getReturnType() {
        return JSONMessage.class;
    }
}
