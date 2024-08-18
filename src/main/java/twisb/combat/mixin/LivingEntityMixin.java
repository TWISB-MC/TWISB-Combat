package twisb.combat.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twisb.combat.TWISBcombat;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract boolean removeStatusEffect(RegistryEntry<StatusEffect> effect);

    public boolean wasWet;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
        wasWet = false;
    }

    @Inject(
            method = "baseTick",
            at = @At("TAIL")
    )
    private void injected(CallbackInfo ci) {
//        if (wasWet & !this.isWet()) {
//            this.addStatusEffect(new StatusEffectInstance(TWISBcombat.WET, 200));
//        } else if (this.isWet()) {
//            this.removeStatusEffect(TWISBcombat.WET);
//        }
        if (isWet() & !this.getType().isIn(TWISBcombat.CANNOT_GET_WET)) {
            this.addStatusEffect(new StatusEffectInstance(TWISBcombat.WET, 200));
        }

        wasWet = this.isWet();
    }

}
