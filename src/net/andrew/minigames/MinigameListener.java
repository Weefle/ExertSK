package net.andrew.minigames;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class MinigameListener implements Listener{
	
	  @EventHandler
	   public void onHungerDeplete(FoodLevelChangeEvent e) {
		  if (MinigameOption.getOptionValue("health") != null){
		  if ((Boolean) MinigameOption.getOptionValue("hunger") == false){
		  
			  if (e.getEntity() instanceof Player){
				  Player p = (Player) e.getEntity();
				  e.setCancelled(true);
				  p.setFoodLevel(20);
			  }
		  }
		  }
	   }
	  @EventHandler
	  public void EntityDamageEvent(EntityDamageEvent e){
		  if (MinigameOption.getOptionValue("health") != null){
			  if ((Boolean) MinigameOption.getOptionValue("health") == false){
				  if (e.getEntity() instanceof Player){
					  Player p = (Player) e.getEntity();
					  e.setCancelled(true);
					  p.setHealth(20);
				  }
			  }
		  }
	  }
}
