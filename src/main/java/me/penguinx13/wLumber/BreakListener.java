package me.penguinx13.wLumber;

import me.penguinx13.wLumber.tree.Tree;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class BreakListener implements Listener {
    private final WLumber plugin;

    public BreakListener (WLumber plugin){
       this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (!Tag.LOGS.isTagged(block.getType())) return;

        Tree tree = new Tree(block);
        if (!tree.collect()) return;

        ItemStack tool = player.getInventory().getItemInMainHand();

        event.setDropItems(false);

        for (Block log : tree.getLogs()) {
            log.breakNaturally(tool);
        }
        Collection<Block> leaves = tree.getLeaves();

        new LeafDecayTask(leaves)
                .runTaskTimer(plugin, 1L, 1L);


    }
}
