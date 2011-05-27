package no.hild1.MakeIce;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class MakeIcePlayerListener extends PlayerListener {

	public static MakeIce plugin;
	//public static final Random RANDOM = new Random();

	public MakeIcePlayerListener(MakeIce instance){
		plugin = instance;
	}
	public void onPlayerInteract(PlayerInteractEvent event){
		if(!event.hasItem()){
			return;
		}

		ItemStack item = event.getItem();
		// INK_SACK and data = 0xF = BONE_MEAL
		if( item.getType() != Material.INK_SACK || item.getData().getData() != 0xF) {
			// Didn't target with BoneMeal
			return;
		}
		
		Player player = event.getPlayer();
		Block targetBlock = player.getTargetBlock(null, 5);

		// Only allow in biomes that normally allow ICE
		if(targetBlock.getBiome() != Biome.TAIGA && targetBlock.getBiome() != Biome.TUNDRA) {
			// In wrong biome
			return;
		}

		// Only stationary, not-running water
		if(targetBlock.getType() != Material.STATIONARY_WATER || targetBlock.getData() != 0x0) {
			return;
		}
		// Only change water into ice if there is a ICE Neighbhour
		if(!hasIceNeighbour(targetBlock)) {
			return;
		}
		
		event.setCancelled(true);

		int amt = item.getAmount();
		if(amt == 1){
			player.getInventory().remove(item);
		} else{
			item.setAmount(amt-1);
		}

		targetBlock.setType(Material.ICE);
	}


	private boolean ice(Block b) {
		return b.getType() == Material.ICE;
	}
	private boolean hasIceNeighbour(Block b) {
		return 	ice(b.getFace(BlockFace.EAST)) ||
		ice(b.getFace(BlockFace.WEST)) ||
		ice(b.getFace(BlockFace.UP)) ||
		ice(b.getFace(BlockFace.DOWN)) ||
		ice(b.getFace(BlockFace.SOUTH)) ||
		ice(b.getFace(BlockFace.SOUTH_EAST)) ||
		ice(b.getFace(BlockFace.SOUTH_WEST)) ||
		ice(b.getFace(BlockFace.NORTH)) ||
		ice(b.getFace(BlockFace.NORTH_EAST)) ||
		ice(b.getFace(BlockFace.NORTH_WEST));
	}
}
