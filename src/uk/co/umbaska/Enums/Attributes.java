package uk.co.umbaska.Enums;

import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.IAttribute;

/**
 * Created by Zachary on 6/30/2015.
 */
public enum Attributes {
    ATTACK_DAMAGE(GenericAttributes.e), KNOCKBACK_RESISTANCE(GenericAttributes.c), MOVEMENT_SPEED(GenericAttributes.d), FOLLOW_RANGE(GenericAttributes.b);

    public IAttribute attibValue;
    Attributes(IAttribute value){
        this.attibValue = value;
    }
}
