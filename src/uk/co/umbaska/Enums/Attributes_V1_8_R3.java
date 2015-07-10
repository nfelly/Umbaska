package uk.co.umbaska.Enums;

import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.IAttribute;

/**
 * Created by Zachary on 6/30/2015.
 */
public enum Attributes_V1_8_R3 {
    ATTACK_DAMAGE(GenericAttributes.ATTACK_DAMAGE), KNOCKBACK_RESISTANCE(GenericAttributes.c), MOVEMENT_SPEED(GenericAttributes.MOVEMENT_SPEED), FOLLOW_RANGE(GenericAttributes.FOLLOW_RANGE);

    private final IAttribute attibValue;
    private Attributes_V1_8_R3(IAttribute value){
        this.attibValue = value;
    }
    public IAttribute getAttribute(){
        return this.attibValue;
    }
}
