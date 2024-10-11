package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityEMCLink;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Debug(export = true)
@Mixin(targets = "cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityEMCLink$FluidHandler", remap = false)
public abstract class BlockEntityEMCLink$FluidHandlerMixin {
    @Shadow
    @Final
    BlockEntityEMCLink this$0;

    @Definition(id = "getPlayer", method = "Lcool/furry/mc/forge/projectexpansion/util/Util;getPlayer(Ljava/util/UUID;)Lnet/minecraft/server/level/ServerPlayer;")
    @Expression("getPlayer(?) != null")
    @ModifyExpressionValue(method = "drain(ILnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)Lnet/minecraftforge/fluids/FluidStack;", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean team_project_expansion$drainPreventPlayerNullCheck(boolean original) {
        return true;
    }

    @Redirect(
        method = "drain(ILnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)Lnet/minecraftforge/fluids/FluidStack;",
        at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;")
    )
    private <T> T team_project_expansion$drainPreventPlayerNullCheck2(T obj) {
        return null;
    }

    @Redirect(
            method = "drain(ILnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)Lnet/minecraftforge/fluids/FluidStack;",
            at = @At(value = "INVOKE", target = "Lmoze_intel/projecte/api/capabilities/IKnowledgeProvider;syncEmc(Lnet/minecraft/server/level/ServerPlayer;)V")
    )
    private void team_project_expansion$drainRedirectSyncEMC(IKnowledgeProvider provider, ServerPlayer serverPlayer) {
        if (provider instanceof TeamKnowledgeProvider teamProvider) {
            TeamProjectExpansion.syncEmc(teamProvider, this$0.owner);
        }
    }
}
