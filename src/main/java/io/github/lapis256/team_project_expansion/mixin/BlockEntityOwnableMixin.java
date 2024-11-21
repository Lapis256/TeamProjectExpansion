package io.github.lapis256.team_project_expansion.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.block.entity.BlockEntityOwnable;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.UUID;


@Mixin(value = BlockEntityOwnable.class, remap = false)
public class BlockEntityOwnableMixin {
    @Shadow
    public UUID owner;

    @ModifyExpressionValue(method = "handleActivation", at = @At(value = "INVOKE", target = "Ljava/util/UUID;equals(Ljava/lang/Object;)Z"))
    private boolean team_project_expansion$handleActivationCheckMember(boolean original, @Local(argsOnly = true) Player player) {
        return TeamProjectExpansion.isTeamMember(owner, player.getUUID());
    }
}
