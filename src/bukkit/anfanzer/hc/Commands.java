package bukkit.anfanzer.hc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * HungerControl commands class:
 *
 * @author Anfanzer
 */
public class Commands implements CommandExecutor
{
    private static HungerControl main = HungerControl.instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof ConsoleCommandSender)
        {
            if(args.length == 0)
            {
                sendConsoleMessage(ChatColor.AQUA + "List of commands for HungerControl:");
                sendConsoleMessage(ChatColor.YELLOW + "> " + ChatColor.GREEN +
                        "/hc add (food) (hunger) (saturation)");
                sendConsoleMessage(ChatColor.YELLOW + "> " + ChatColor.GREEN + "/hc remove (food)");
                sendConsoleMessage(ChatColor.YELLOW + "> " + ChatColor.GREEN + "/hc list");
                return false;
            }
            if(!(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")
                    || args[0].equalsIgnoreCase("list")))
            {
                sendConsoleMessage(ChatColor.RED + "The command '" + args[0] +
                        "' was not recognized! Type /hc for a list of commands.");
                return false;
            }
            if(args[0].equalsIgnoreCase("add") && args.length == 4)
            {
                for(FoodType food : FoodType.values())
                {
                    if(food.getStringValue().replace("_HUNGER", "").equalsIgnoreCase(args[1]))
                    {
                        if(main.getConfig().contains(food.getStringValue()))
                        {
                            if(main.getConfig().get(food.getStringValue()).equals(Integer.parseInt(args[2])) &&
                                    main.getConfig().get(food.getStringValue().replace
                                            ("_HUNGER", "_SATURATION")).equals(Integer.parseInt(args[3])))
                            {
                                sendConsoleMessage(ChatColor.RED + food.getStringValue().replace("_HUNGER", "") +
                                        " has already been set with the same value!");
                                return false;
                            }
                        }
                        main.getConfig().set(food.getStringValue(), Integer.parseInt(args[2]));
                        main.getConfig().set(food.getStringValue().replace
                                ("_HUNGER", "_SATURATION"),Integer.parseInt(args[3]));
                        sendConsoleMessage(ChatColor.GREEN + food.getStringValue().replace("_HUNGER", "") +
                                " has successfully been saved! Hunger Added: " +
                                args[2] + ", Saturation Added: " + args[3]);
                        main.saveConfig();
                        return true;
                    }
                }
            }
            else if(args[0].equalsIgnoreCase("remove") && args.length == 2)
            {
                for(FoodType food : FoodType.values())
                {
                    if(food.getStringValue().replace("_HUNGER", "").equalsIgnoreCase(args[1]))
                    {
                        if(main.getConfig().contains(food.getStringValue()))
                        {
                            main.getConfig().set(food.getStringValue(), null);
                            main.getConfig().set(food.getStringValue().replace("_HUNGER", "_SATURATION"), null);
                            sendConsoleMessage(ChatColor.GREEN + food.getStringValue().replace("_HUNGER", "") +
                                    " has successfully been removed! The item will now behave as normal.");
                            main.saveConfig();
                            return true;
                        }
                        else
                        {
                            sendConsoleMessage(ChatColor.RED + food.getStringValue().replace
                                    ("_HUNGER", "") + " has not yet been set!");
                        }
                    }
                }
            }
            else if(args[0].equalsIgnoreCase("list"))
            {
                sendConsoleMessage(ChatColor.GREEN + "List of food types:");
                for(FoodType food : FoodType.values())
                {
                    sendConsoleMessage(ChatColor.YELLOW + "> " + ChatColor.AQUA +
                            food.getStringValue().replace("_HUNGER" , ""));
                }
            }
            else
            {
                sendConsoleMessage(ChatColor.RED + "Syntax error! Type /hc for a list of commands.");
                return false;
            }
        }
        else
        {
            Player player = (Player) sender;
            if(player.hasPermission("hc.modify"))
            {
                if(args.length == 0)
                {
                    player.sendMessage(ChatColor.AQUA + "List of commands for HungerControl:");
                    player.sendMessage(ChatColor.YELLOW + "> " + ChatColor.GREEN +
                            "/hc add (food) (hunger) (saturation)");
                    player.sendMessage(ChatColor.YELLOW + "> " + ChatColor.GREEN + "/hc remove (food)");
                    player.sendMessage(ChatColor.YELLOW + "> " + ChatColor.GREEN + "/hc list");
                    return false;
                }
                if(!(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")
                        || args[0].equalsIgnoreCase("list")))
                {
                    player.sendMessage(ChatColor.RED + "The command '" + args[0] +
                            "' was not recognized! Type /hc for a list of commands.");
                    return false;
                }
                if(args[0].equalsIgnoreCase("add") && args.length == 4)
                {
                    for(FoodType food : FoodType.values())
                    {
                        if(food.getStringValue().replace("_HUNGER", "").equalsIgnoreCase(args[1]))
                        {
                            if(main.getConfig().contains(food.getStringValue()))
                            {
                                if(main.getConfig().get(food.getStringValue()).equals(Integer.parseInt(args[2])) &&
                                        main.getConfig().get(food.getStringValue().replace
                                                ("_HUNGER", "_SATURATION")).equals(Integer.parseInt(args[3])))
                                {
                                    player.sendMessage(ChatColor.RED + food.getStringValue().replace("_HUNGER", "") +
                                            " has already been set with the same value!");
                                    return false;
                                }
                            }
                            main.getConfig().set(food.getStringValue(), Integer.parseInt(args[2]));
                            main.getConfig().set(food.getStringValue().replace
                            ("_HUNGER", "_SATURATION"), Integer.parseInt(args[3]));
                            player.sendMessage(ChatColor.GREEN + food.getStringValue().replace("_HUNGER", "") +
                                    " has successfully been saved! Hunger Added: " +
                                    args[2] + ", Saturation Added: " + args[3]);
                            main.saveConfig();
                            return true;
                        }
                    }
                }
                else if(args[0].equalsIgnoreCase("remove") && args.length == 2)
                {
                    for(FoodType food : FoodType.values())
                    {
                        if(food.getStringValue().replace("_HUNGER", "").equalsIgnoreCase(args[1]))
                        {
                            if(main.getConfig().contains(food.getStringValue()))
                            {
                                main.getConfig().set(food.getStringValue(), null);
                                main.getConfig().set(food.getStringValue().replace("_HUNGER", "_SATURATION"), null);
                                player.sendMessage(ChatColor.GREEN + food.getStringValue().replace("_HUNGER", "") +
                                        " has successfully been removed! The item will now behave as normal.");
                                main.saveConfig();
                                return true;
                            }
                            else
                            {
                                player.sendMessage(ChatColor.RED + food.getStringValue().replace
                                        ("_HUNGER", "") + " has not yet been set!");
                                return false;
                            }
                        }
                    }
                }
                else if(args[0].equalsIgnoreCase("list"))
                {
                    player.sendMessage(ChatColor.GREEN + "List of food types:");
                    for(FoodType food : FoodType.values())
                    {
                        player.sendMessage(ChatColor.YELLOW + "> " + ChatColor.AQUA +
                                food.getStringValue().replace("_HUNGER" , ""));
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Syntax error! Type /hc for a list of commands.");
                    return false;
                }
            }
            else
            {
                player.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
                return false;
            }
        }
        return false;
    }

    public static void sendConsoleMessage(String message)
    {
        main.getServer().getConsoleSender().sendMessage("[" + main.getDescription().getName() + "] " + message);
    }
}
