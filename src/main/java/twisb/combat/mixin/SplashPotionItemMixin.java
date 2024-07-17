package twisb.combat.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SplashPotionItem.class)
public abstract class SplashPotionItemMixin {

    @Redirect(method="use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    private void injected(World instance, PlayerEntity source, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        return;
    }
}
