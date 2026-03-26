package com.amanda.namedmobprotection;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

/**
 * Prevents any mob with a name tag from taking damage or dying.
 * Works against ALL damage sources: players, other mobs, lava, fall damage, etc.
 */
@EventBusSubscriber(modid = NamedMobProtection.MOD_ID)
public class NamedMobEventHandler {

    /**
     * Fires before damage is applied. Sets damage to 0 for any named mob.
     * This stops the hit animation and health loss entirely.
     */
    @SubscribeEvent
    public static void onLivingDamagePre(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();

        // Only protect Mobs (not players) that have a custom name set via name tag
        if (entity instanceof Mob && entity.hasCustomName()) {
            event.setNewDamage(0.0f);
        }
    }

    /**
     * Safety net: if somehow damage still got through and the mob is dying,
     * cancel the death and restore 1 HP so it survives.
     */
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Mob && entity.hasCustomName()) {
            event.setCanceled(true);
            entity.setHealth(1.0f);
        }
    }
}
