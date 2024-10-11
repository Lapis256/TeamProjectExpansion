package io.github.lapis256.team_project_expansion.mixin;

import cool.furry.mc.forge.projectexpansion.util.Util;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.proxy.ITransmutationProxy;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;


@Mixin(value = Util.class, remap = false)
public abstract class UtilMixin {
    @Inject(method = "getKnowledgeProvider(Ljava/util/UUID;)Lmoze_intel/projecte/api/capabilities/IKnowledgeProvider;", at = @At("HEAD"), cancellable = true)
    private static void team_project_expansion$getKnowledgeProvider(UUID uuid, CallbackInfoReturnable<IKnowledgeProvider> cir) {
        cir.setReturnValue(ITransmutationProxy.INSTANCE.getKnowledgeProviderFor(uuid));
    }

    @Inject(method = "getKnowledgeProvider(Lnet/minecraft/world/entity/player/Player;)Lmoze_intel/projecte/api/capabilities/IKnowledgeProvider;", at = @At("HEAD"), cancellable = true)
    private static void team_project_expansion$getKnowledgeProvider(Player player, CallbackInfoReturnable<IKnowledgeProvider> cir) {
        cir.setReturnValue(ITransmutationProxy.INSTANCE.getKnowledgeProviderFor(player.getUUID()));
    }
}
