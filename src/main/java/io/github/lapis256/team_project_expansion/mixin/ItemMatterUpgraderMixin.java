package io.github.lapis256.team_project_expansion.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityOwnable;
import cool.furry.mc.forge.projectexpansion.item.ItemMatterUpgrader;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(value = ItemMatterUpgrader.class, remap = false)
public class ItemMatterUpgraderMixin {
    @ModifyExpressionValue(method = "onItemUseFirst", at = @At(value = "INVOKE", target = "Ljava/util/UUID;equals(Ljava/lang/Object;)Z"))
    private boolean team_project_expansion$onItemUseFirstCheckMember(boolean original, @Local(ordinal = 0) BlockEntity blockEntity, @Local Player player) {
        if (blockEntity instanceof BlockEntityOwnable ownable) {
            return TeamProjectExpansion.isTeamMember(ownable.owner, player.getUUID());
        }
        return original;
    }
}
