package net.pyrix.mc.factions.territories.animations;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

public class TerritorySymbolAnimation extends BukkitRunnable {

	private ArmorStand[] ass;

	public TerritorySymbolAnimation(ArmorStand... ass) {
		this.ass = ass; // ass = a rmor s tand s
	}

	private boolean UPWARD = false;
	private long lastTime = 0;
	private final long COOLDOWN = 1000;
	private boolean stop = false;

	public void stop() {
		stop = true;
	}

	@Override
	public void run() {
		if (stop) {
			cancel();
			for (ArmorStand as : ass) {
				as.remove();
			}
			return;
		}
		for (ArmorStand as : ass) {
			Location loc = as.getLocation();
			loc.setYaw(loc.getYaw() + 0.75f);
			if (System.currentTimeMillis() - lastTime >= COOLDOWN) {
				loc.getWorld().spawnParticle(Particle.LAVA, loc.getX(), loc.getY() + 1.5, loc.getZ(), 8, 0.05, 0.05, 0.05, 0.15);
				this.lastTime = System.currentTimeMillis();
				UPWARD = !UPWARD;
			}
			if (UPWARD) {
				loc.setY(loc.getY() - 0.01);
			} else {
				loc.setY(loc.getY() + 0.01);
			}
			as.teleport(loc, TeleportCause.PLUGIN);
		}
	}

}
