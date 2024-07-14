package twisb.combat.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(DrownedEntity.class)
public abstract class DrownedEntityMixin extends MobEntity {

    public final float TRIDENT_DROP_RATE = 0.15F;

    protected DrownedEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method="<init>",
            at = @At("TAIL")
    )
    private void changeHandDropRate(EntityType entityType, World world, CallbackInfo ci) {
        Arrays.fill(this.handDropChances, TRIDENT_DROP_RATE);
    }
}
