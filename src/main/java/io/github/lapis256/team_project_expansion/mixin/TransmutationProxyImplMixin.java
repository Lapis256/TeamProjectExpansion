package io.github.lapis256.team_project_expansion.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import moze_intel.projecte.impl.TransmutationProxyImpl;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(value = TransmutationProxyImpl.class, remap = false)
public class TransmutationProxyImplMixin {
    @Definition(id = "player", local = @Local(type = Player.class))
    @Expression("player != null")
    @ModifyExpressionValue(method = "getKnowledgeProviderFor", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean team_project_expansion$extractItemSyncEMC(boolean original, @Local Player player) {
        return original && !player.isRemoved();
    }
}
