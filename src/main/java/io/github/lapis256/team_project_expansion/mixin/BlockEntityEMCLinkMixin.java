package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityEMCLink;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(value = BlockEntityEMCLink.class, remap = false)
public abstract class BlockEntityEMCLinkMixin {
    @Definition(id = "player", local = @Local(type = ServerPlayer.class))
    @Expression("player != null")
    @ModifyExpressionValue(method = "tickServer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lcool/furry/mc/forge/projectexpansion/block/entity/BlockEntityEMCLink;)V", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean team_project_expansion$tickServerSyncEMC(boolean original, @Local IKnowledgeProvider provider) {
        TeamProjectExpansion.syncEmc((TeamKnowledgeProvider)provider, ((BlockEntityEMCLink)(Object)this).owner);
        return false;
    }
}
