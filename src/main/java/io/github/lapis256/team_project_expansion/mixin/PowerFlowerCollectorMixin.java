package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.util.PowerFlowerCollector;
import cool.furry.mc.forge.projectexpansion.util.Util;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;


@Mixin(value = PowerFlowerCollector.class, remap = false)
public abstract class PowerFlowerCollectorMixin {
    @ModifyExpressionValue(
            method = "onTick",
            at = @At(value = "INVOKE", target = "Lcool/furry/mc/forge/projectexpansion/util/Util;getPlayer(Ljava/util/UUID;)Lnet/minecraft/server/level/ServerPlayer;")
    )
    private static ServerPlayer team_project_expansion$onTickSyncEMC(ServerPlayer player, @Local UUID uuid, @Local BigInteger amount, @Local Set<UUID> toRemove) {
        if(player != null) {
            return player;
        }

        IKnowledgeProvider provider = Util.getKnowledgeProvider(uuid);
        if (provider instanceof TeamKnowledgeProvider teamProvider) {
            teamProvider.setEmc(teamProvider.getEmc().add(amount));
            toRemove.add(uuid);
            TeamProjectExpansion.syncEmc(teamProvider, uuid);
        }
        return null;
    }
}
