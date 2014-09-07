package bukkit.anfanzer.hc;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * HungerControl main class:
 *
 * @author Anfanzer
 */
public class HungerControl extends JavaPlugin
{
    public static HungerControl instance;

    @Override
    public void onEnable()
    {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        getCommand("hc").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Events(), this);
        if(getDescription().getVersion().contains("SNAPSHOT"))
        {
            Commands.sendConsoleMessage(ChatColor.RED + "The current version is a snapshot build and may be unstable!");
        }
    }
}
