package io.github.lapis256.team_project_expansion.mixin;

import cn.leomc.teamprojecte.TPTeam;
import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(value = TeamKnowledgeProvider.class, remap = false)
public interface TeamKnowledgeProviderAccessor {
    @Invoker("getTeam")
    TPTeam invokeGetTeam();
}
