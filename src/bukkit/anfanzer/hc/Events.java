package bukkit.anfanzer.hc;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.HashMap;

/**
 * HungerControl events class:
 *
 * @author Anfanzer
 */
public class Events implements Listener
{
    private HungerControl main = HungerControl.instance;
    private HashMap<Player, String> players = new HashMap<Player, String>();

    @EventHandler
    private void onHungerChange(FoodLevelChangeEvent event)
    {
        Entity entity = event.getEntity();
        if(entity instanceof Player)
        {
            Player player = (Player) entity;
            if(event.getFoodLevel() > player.getFoodLevel())
            {
                if(players.containsKey(player))
                {
                    event.setCancelled(true);
                    player.setFoodLevel(player.getFoodLevel() + main.getConfig().getInt(players.get(player)));
                    player.setSaturation(player.getSaturation() + main.getConfig().getInt(
                            players.get(player).replace("_HUNGER", "_SATURATION")));
                    players.remove(player, players.get(player));
                }
                else if(players.containsKey(player))
                {
                    event.setCancelled(true);
                    player.setFoodLevel(player.getFoodLevel() + main.getConfig().getInt(players.get(player)));
                    player.setSaturation(player.getSaturation() + main.getConfig().getInt
                            (players.get(player).replace("_HUNGER", "_SATURATION")));
                    players.remove(player, players.get(player));
                }
            }
        }
    }

    @EventHandler
    private void onItemConsume(PlayerItemConsumeEvent event)
    {
        Player player = event.getPlayer();
        if(player.hasPermission("hc.use"))
        {
            for(FoodType food : FoodType.values())
            {
                if(event.getItem().getType() == food.getMaterialValue() &&
                        main.getConfig().contains(food.getStringValue()))
                {
                    players.put(player, food.getStringValue());
                }
            }
        }
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if(player.hasPermission("hc.use"))
        {
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CAKE_BLOCK)
            {
                for(FoodType food : FoodType.values())
                {
                    if(food.getMaterialValue() == Material.CAKE_BLOCK && main.getConfig().contains(food.getStringValue()))
                    {
                        players.put(player, food.getStringValue());
                    }
                }
            }
        }
    }
}
