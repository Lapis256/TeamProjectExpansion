package io.github.lapis256.team_project_expansion.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityPowerFlower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;


@Mixin(value = BlockEntityPowerFlower.class, remap = false)
public abstract class BlockEntityPowerFlowerMixin {
    @ModifyExpressionValue(
            method = "tickServer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lcool/furry/mc/forge/projectexpansion/block/entity/BlockEntityPowerFlower;)V",
            at = @At(value = "INVOKE", target = "Ljava/math/BigInteger;add(Ljava/math/BigInteger;)Ljava/math/BigInteger;", ordinal = 1)
    )
    private BigInteger team_project_expansion$tickServerSyncEMC(BigInteger amount) {
        Map<UUID, BigInteger> stored = PowerFlowerCollectorAccessor.getStored();
        UUID owner = ((BlockEntityPowerFlower) (Object) this).owner;
        stored.put(owner, stored.containsKey(owner) ? stored.get(owner).add(amount) : amount);
        return BigInteger.ZERO;
    }
}
