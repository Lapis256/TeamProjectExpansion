package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.item.ItemMatterUpgrader;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(value = ItemMatterUpgrader.class, remap = false)
public class ItemMatterUpgraderMixin {
    @ModifyExpressionValue(method = "onItemUseFirst", at = @At(value = "INVOKE", target = "Ljava/util/UUID;equals(Ljava/lang/Object;)Z"))
    private boolean team_project_expansion$onItemUseFirstSyncEMC(boolean original, @Local IKnowledgeProvider provider, @Local Player player) {
        if (!(provider instanceof TeamKnowledgeProvider teamProvider)) {
            return original;
        }
        var team = ((TeamKnowledgeProviderAccessor)teamProvider).invokeGetTeam();
        return team.getAll().contains(player.getUUID());
    }
}
