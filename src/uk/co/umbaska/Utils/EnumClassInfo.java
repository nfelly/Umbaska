package uk.co.umbaska.Utils;


import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings({"unchecked", "rawtypes"})
public class EnumClassInfo<E extends Enum<E>>
{
    private final Class<E> enumType;
    private final String codeName;
    private final ClassInfo<E> classInfo;
    private final HashMap<String, String> synonyms = new HashMap();


	private EnumClassInfo(Class<E> enumType, String codeName)
    {
        this.enumType = enumType;
        this.codeName = codeName;
        this.classInfo = new ClassInfo(enumType, codeName);
    }


	public static <E extends Enum<E>> EnumClassInfo<E> create(Class<E> enumType, String codeName)
    {
        return new EnumClassInfo(enumType, codeName);
    }

    public EnumClassInfo<E> addSynonym(String regex, String actualValue)
    {
        this.synonyms.put(regex, actualValue);
        return this;
    }

    public EnumClassInfo<E> after(String... after)
    {
        this.classInfo.after(after);
        return this;
    }

    public EnumClassInfo<E> before(String... before)
    {
        this.classInfo.before(before);
        return this;
    }

    public void register()
    {
        Classes.registerClass(this.classInfo.user(new String[] { this.codeName + "s?" }).parser(new Parser<E>()
        {
            public E parse(String s, ParseContext parseContext)
            {
                if (s.startsWith(EnumClassInfo.this.codeName + ":")) {
                    s = s.substring(EnumClassInfo.this.codeName.length() + 1, s.length());
                }
                try
                {
                    for (Map.Entry<String, String> p : EnumClassInfo.this.synonyms.entrySet()) {
                        if (s.matches((String)p.getKey())) {
                            return Enum.valueOf(EnumClassInfo.this.enumType, (String)p.getValue());
                        }
                    }
                    return Enum.valueOf(EnumClassInfo.this.enumType, s.replace(" ", "_").toUpperCase().trim());
                }
                catch (IllegalArgumentException e) {}
                return null;
            }

            public String toString(E e, int i)
            {
                return e.toString();
            }

            public String toVariableNameString(E e)
            {
                return EnumClassInfo.this.codeName + ':' + e.toString();
            }

            public String getVariableNamePattern()
            {
                return EnumClassInfo.this.codeName + ":.+";
            }
        }).serializer(new EnumSerializer(this.enumType)));
    }
}
