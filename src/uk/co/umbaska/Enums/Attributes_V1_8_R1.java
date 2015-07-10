package uk.co.umbaska.Enums;


import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.IAttribute;

public enum Attributes_V1_8_R1 {
    ATTACK_DAMAGE(GenericAttributes.e), KNOCKBACK_RESISTANCE(GenericAttributes.c), MOVEMENT_SPEED(GenericAttributes.d), FOLLOW_RANGE(GenericAttributes.b);

    private final IAttribute attibValue;
    private Attributes_V1_8_R1(IAttribute value){
        this.attibValue = value;
    }
    public IAttribute getAttribute(){
        return this.attibValue;
    }
}
