package us.myles.ViaVersion.protocols.protocol1_9to1_8.listeners;

import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import us.myles.ViaVersion.ViaVersionPlugin;
import us.myles.ViaVersion.api.ViaVersion;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.protocols.base.ProtocolInfo;
import us.myles.ViaVersion.protocols.protocol1_9to1_8.Protocol1_9TO1_8;

public class PaperPatch implements Listener {

    /*
    This patch is applied when Paper is detected.
    I'm unsure of what causes this but essentially,
    placing blocks where your standing works?
    If there is a better fix then we'll replace this.
     */

    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {
        if (ViaVersion.getInstance().isPorted(e.getPlayer())) {
            UserConnection userConnection = ((ViaVersionPlugin) ViaVersion.getInstance()).getConnection(e.getPlayer());
            if (userConnection == null) return;
            if (!userConnection.get(ProtocolInfo.class).getPipeline().contains(Protocol1_9TO1_8.class)) return;

            if (e.getPlayer().getLocation().getBlock().equals(e.getBlock())) {
                e.setCancelled(true);
            } else {
                if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).equals(e.getBlock())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}