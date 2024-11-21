package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityTransmutationInterface;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.emc.nbt.NBTManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(targets = "cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityTransmutationInterface$ItemHandler", remap = false)
public class BlockEntityTransmutationInterface$ItemHandlerMixin {
    @SuppressWarnings("target")
    @Shadow
    @Final
    BlockEntityTransmutationInterface this$0;

    @Definition(id = "getPlayer", method = "Lcool/furry/mc/forge/projectexpansion/util/Util;getPlayer(Ljava/util/UUID;)Lnet/minecraft/server/level/ServerPlayer;")
    @Expression("getPlayer(?) != null")
    @ModifyExpressionValue(method = {"getStackInSlot", "insertItem", "extractItem"}, at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 0))
    private boolean team_project_expansion$preventPlayerNullCheck(boolean original) {
        return true;
    }

    @Definition(id = "player", local = @Local(type = ServerPlayer.class))
    @Expression("player != null")
    @ModifyExpressionValue(method = "insertItem", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean team_project_expansion$insertItemSyncEMC(boolean original, @Local(argsOnly = true) ItemStack stack, @Local ItemInfo info, @Local IKnowledgeProvider provider) {
        if(provider instanceof TeamKnowledgeProvider teamProvider) {
            if (teamProvider.addKnowledge(stack)) {
                TeamProjectExpansion.syncKnowledgeChange(this$0.owner, NBTManager.getPersistentInfo(info), true);
            }
            TeamProjectExpansion.syncEmc(teamProvider, this$0.owner);
        }
        return false;
    }

    @Definition(id = "player", local = @Local(type = ServerPlayer.class))
    @Expression("player != null")
    @ModifyExpressionValue(method = "extractItem", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean team_project_expansion$extractItemSyncEMC(boolean original, @Local IKnowledgeProvider provider) {
        if(provider instanceof TeamKnowledgeProvider teamProvider) {
            TeamProjectExpansion.syncEmc(teamProvider, this$0.owner);
        }
        return false;
    }
}
