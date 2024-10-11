package io.github.lapis256.team_project_expansion.mixin;

import cool.furry.mc.forge.projectexpansion.util.PowerFlowerCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;


@Mixin(value = PowerFlowerCollector.class, remap = false)
public interface PowerFlowerCollectorAccessor {
    @Accessor("stored")
    static Map<UUID, BigInteger> getStored() {
        throw new AssertionError();
    }
}
