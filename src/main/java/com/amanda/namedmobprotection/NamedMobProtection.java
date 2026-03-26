package com.amanda.namedmobprotection;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(NamedMobProtection.MOD_ID)
public class NamedMobProtection {

    public static final String MOD_ID = "namedmobprotection";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NamedMobProtection(IEventBus modEventBus) {
        LOGGER.info("Named Mob Protection loaded! Any mob with a name tag is now unkillable.");
    }
}
