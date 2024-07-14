package twisb.combat.mixin;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import twisb.combat.TWISBcombat;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {

    @Redirect(
            method="update",
            at = @At(value="INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;canFoodHeal()Z",
                    ordinal = 0
            )
    )
    private boolean injected(PlayerEntity player) {
        if (player.getWorld().getGameRules().getBoolean(TWISBcombat.FAST_REGEN_FOOD)) {
            return true;
        } else {
            return false;
        }
    }
}
