package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import com.llamalad7.mixinextras.sugar.Local;
import cool.furry.mc.forge.projectexpansion.item.ItemInfiniteFuel;
import io.github.lapis256.team_project_expansion.TeamProjectExpansion;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;


@Mixin(value = ItemInfiniteFuel.class, remap = false)
public class ItemInfiniteFuelMixin {
    @Inject(method = "getCraftingRemainingItem", at = @At(value = "INVOKE", target = "Lmoze_intel/projecte/api/capabilities/IKnowledgeProvider;setEmc(Ljava/math/BigInteger;)V", shift = At.Shift.AFTER))
    private void team_project_expansion$syncEmc(CallbackInfoReturnable<ItemStack> cir, @Local IKnowledgeProvider provider, @Local UUID owner) {
        if (provider instanceof TeamKnowledgeProvider teamProvider) {
            TeamProjectExpansion.syncEmc(teamProvider, owner);
        }
    }
}
