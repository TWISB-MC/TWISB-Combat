package twisb.combat;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TWISBcombat implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("twisb-combat");

	// Game rules
	public static final GameRules.Key<GameRules.BooleanRule> FAST_REGEN_FOOD =
			GameRuleRegistry.register("fastRegenFood", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
	// Status effects
	public static final RegistryEntry<StatusEffect> WET = registerStatusEffect("wet",
			new WetStatusEffect(StatusEffectCategory.NEUTRAL, 1456073, ParticleTypes.FALLING_WATER)); // DRIPPING_WATER
	// Tags
	public static final TagKey<EntityType<?>> CANNOT_GET_WET = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("twisb-combat", "cannot_get_wet"));
	@Override
	public void onInitialize() {
//		Registry.register(Registries.STATUS_EFFECT, Identifier.of("twisb-combat", "wet"), WET);
	}

	private static RegistryEntry<StatusEffect> registerStatusEffect(String id, StatusEffect statusEffect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of("twisb-combat", id), statusEffect);
	}

}