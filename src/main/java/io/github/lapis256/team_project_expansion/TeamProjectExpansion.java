package io.github.lapis256.team_project_expansion;

import cn.leomc.teamprojecte.TeamKnowledgeProvider;
import cn.leomc.teamprojecte.TeamProjectE;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.network.PacketHandler;
import moze_intel.projecte.network.packets.IPEPacket;
import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncChangePKT;
import moze_intel.projecte.network.packets.to_client.knowledge.KnowledgeSyncEmcPKT;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


@Mod(TeamProjectExpansion.MOD_ID)
public class TeamProjectExpansion {
    public static final String MOD_ID = "team_project_expansion";
    public static final String MOD_NAME = "TeamProjectExpansion";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public TeamProjectExpansion() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    private static void sendTeamPacket(IPEPacket packet, UUID uuid) {
        TeamProjectE.getOnlineTeamMembers(uuid).forEach((p) -> PacketHandler.sendTo(packet, p));
    }

    public static void syncEmc(TeamKnowledgeProvider provider, @NotNull UUID uuid) {
        sendTeamPacket(new KnowledgeSyncEmcPKT(provider.getEmc()), uuid);
    }

    public static void syncKnowledgeChange(@NotNull UUID uuid, ItemInfo change, boolean learned) {
        sendTeamPacket(new KnowledgeSyncChangePKT(change, learned), uuid);
    }
}
