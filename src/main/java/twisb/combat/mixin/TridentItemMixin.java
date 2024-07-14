package twisb.combat.mixin;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin extends Item {

    private static final Logger LOGGER = LoggerFactory.getLogger("twisb-combat");

    private static final float EXTRA_REACH = 1.0F;

    public TridentItemMixin(Settings settings) {
        super(settings);
    }

    @Redirect(
            method = "createAttributeModifiers",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/component/type/AttributeModifiersComponent$Builder;build()Lnet/minecraft/component/type/AttributeModifiersComponent;"
            )
    )
    private static AttributeModifiersComponent addExtraReach(AttributeModifiersComponent.Builder builder) {
        return builder.add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                     new EntityAttributeModifier(
                         Identifier.ofVanilla("extended_reach"),
                         EXTRA_REACH,
                         EntityAttributeModifier.Operation.ADD_VALUE),
                     AttributeModifierSlot.MAINHAND).build();
    }

}
