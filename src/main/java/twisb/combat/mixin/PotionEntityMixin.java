package twisb.combat.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import twisb.combat.TWISBcombat;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Mixin(PotionEntity.class)
public abstract class PotionEntityMixin extends ThrownItemEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger("twisb-combat");
    private static final Predicate<LivingEntity> AFFECTED_BY_WET = entity -> !entity.getType().isIn(TWISBcombat.CANNOT_GET_WET);

    public PotionEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "applyWater",
            at = @At(value="HEAD")
    )
    private void injected(CallbackInfo ci) {
        Box box = this.getBoundingBox().expand(4.0, 2.0, 4.0);
        for (LivingEntity livingEntity : this.getWorld().getEntitiesByClass(LivingEntity.class, box, AFFECTED_BY_WET)) {
            double d = this.squaredDistanceTo(livingEntity);
            if (d < 16.0) {
                livingEntity.addStatusEffect(new StatusEffectInstance(TWISBcombat.WET, 200));
            }
        }
    }

}
