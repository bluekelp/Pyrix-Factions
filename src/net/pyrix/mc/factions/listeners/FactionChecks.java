package net.pyrix.mc.factions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.pyrix.mc.factions.player.FPlayer;
import net.pyrix.mc.factions.storage.StorageManager;
import net.pyrix.mc.factions.utils.C;

public class FactionChecks implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void friendlyFireCheck(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player attacker = (Player) e.getDamager();
			Player victim = (Player) e.getEntity();
			FPlayer fAttacker = StorageManager.get.FPlayerStorage.get(attacker);
			FPlayer fVictim = StorageManager.get.FPlayerStorage.get(victim);
			if (fAttacker != null && fVictim != null) {
				if (fAttacker.getFaction().equals(fVictim.getFaction())) {
					e.setCancelled(true);
					fAttacker.getPlayer().sendMessage(C.color("&c&oYou can't harm another one of your faction members!"));
				}
			}
		}
	}

}
