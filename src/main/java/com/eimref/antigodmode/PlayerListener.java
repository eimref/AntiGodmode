
package com.eimref.antigodmode;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerListener implements Listener {
    private final AntiGodmode plugin; 

    public PlayerListener(AntiGodmode instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
    	if ((!(event.getEntity() instanceof Player)) || event.getEntity().isDead())
    		return;
    	
    	Player player = (Player)event.getEntity();
    	Integer lastTicksLived = (Integer)plugin.getMetadata(player, "lastNDTLivingTicks");
    	
		if (lastTicksLived != null)
		{
			int maxNDT = player.getMaximumNoDamageTicks();
			int tickDiff = (player.getTicksLived() - lastTicksLived);
    		int ndtShouldBe = (maxNDT - tickDiff);
    		
    		if (ndtShouldBe < 0)
    			ndtShouldBe = 0;
    		
    		if (player.getNoDamageTicks() > ndtShouldBe)
    		{
    			player.setNoDamageTicks(ndtShouldBe);
    			
    			// Make sure the player isn't somehow set invulnerable for a long time..
    			if (player.getNoDamageTicks() > maxNDT)
    				player.setNoDamageTicks(maxNDT);
    			
    			// TODO Log here, warn OPS player tried to avoid dmg (Although without checking keepAlive it will have false positives)
    		}
    		if (tickDiff >= player.getMaximumNoDamageTicks() / 2)
    		{
    			plugin.setMetadata(player, "lastNDTLivingTicks", player.getTicksLived());
    		}
		}
		else
		{
	    	plugin.setMetadata(player, "lastNDTLivingTicks", player.getTicksLived());
		}
    	
    }
}
