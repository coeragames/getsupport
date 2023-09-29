package fr.pr11dev.getsupport.bungeecord.commands;

import fr.pr11dev.getsupport.bungeecord.customevents.BungeeTicketClaimedEvent;
import fr.pr11dev.getsupport.bungeecord.customevents.BungeeTicketClosedEvent;
import fr.pr11dev.getsupport.bungeecord.customevents.BungeeTicketCreatedEvent;
import fr.pr11dev.getsupport.bungeecord.data.CustomProxiedPlayer;
import fr.pr11dev.getsupport.bungeecord.data.Data;
import fr.pr11dev.getsupport.bungeecord.data.BungeeTicket;
import fr.pr11dev.getsupport.bungeecord.utils.AlertStaffBungee;
import fr.pr11dev.getsupport.bungeecord.utils.CustomProxiedPlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static java.lang.Integer.parseInt;

public class TicketCommandBungee extends Command {
    public TicketCommandBungee() {
        super("ticket");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            if(args == null || args.length == 0) {
                sender.sendMessage("§c[§6GetSupport§c] §7Aucune commande n'a été trouvée");
            }
            else if(args != null && args[0].equalsIgnoreCase("list")) {
                if(sender.hasPermission("gs.list.all")) {
                    if(Data.tickets != null && !Data.tickets.isEmpty() && Data.tickets.size() > 0) {
                        sender.sendMessage("§c[§6GetSupport§c] §7Liste des tickets");
                        for(BungeeTicket t : Data.tickets) {
                            sender.sendMessage("§c[§6GetSupport§c] §7Ticket §e" + t.getTicketId() + "§7 Demandeur: §e" + t.getPlayer().getDisplayName()+"§7 Claim: §e"+t.isClaimed()+"§7 Ouverture: §e"+t.getFormattedCaseTime());
                        }
                    }
                    else {
                        sender.sendMessage("§c[§6GetSupport§c] §7Aucun ticket n'a été trouvé");
                    }
                }
                else if(sender.hasPermission("gs.list")) {
                    final CustomProxiedPlayer p = CustomProxiedPlayerManager.getCustomPlayerFromSender(sender);
                    if(p.getTickets() != null && !p.getTickets().isEmpty() && p.getTickets().size() > 0) {
                        sender.sendMessage("§c[§6GetSupport§c] §7Liste des tickets");
                        for (int i = 0; i < p.getTickets().size(); i++) {
                            BungeeTicket t = p.getTickets().get(i);
                            sender.sendMessage("§c[§6GetSupport§c] §7Ticket §e" + t.getTicketId() + "§7 Demandeur: §e" + t.getPlayer().getDisplayName() + "§7 Claim: §e" + t.isClaimed() + "§7 Ouverture: §e" + t.getFormattedCaseTime() + "§7 Numéro de ticket personel: §e" + i + 1);
                        }
                    }
                    else {
                        sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez aucun ticket");
                    }
                }
                else {
                    sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas la permission §egs.list§7.");
                }
            }
            else if(args != null && args[0].equalsIgnoreCase("close")) {
                if(args.length >= 2) {
                    if(sender.hasPermission("gs.close.other")) {
                        if(CustomProxiedPlayerManager.getCustomPlayer(args[1]) != null && CustomProxiedPlayerManager.getCustomPlayer(args[1]).getTickets() != null ) {
                            final CustomProxiedPlayer p = CustomProxiedPlayerManager.getCustomPlayer(args[1]);
                            if(p.getTickets().size() == 1) {
                                sender.sendMessage("§c[§6GetSupport§c] §7Ticket §e" + p.getFirstTicket().getTicketId() + "§7 fermé");
                                ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClosedEvent(p.getFirstTicket()));
                                p.getFirstTicket().close();
                            }
                            else if (p.getTickets().size() >= 2){
                                if(args.length == 3 && args[2].length() == 1 && parseInt(args[2]) != 0) {
                                    final int ticket = parseInt(args[2]) - 1;
                                    sender.sendMessage("§c[§6GetSupport§c] §7Ticket §e" + p.getTickets().get(ticket).getTicketId() + "§7 fermé");
                                    ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClosedEvent(p.getTickets().get(ticket)));
                                    p.getTickets().get(ticket).close();
                                }
                                else {
                                    sender.sendMessage("§c[§6GetSupport§c] §7Veuillez entrer le numéro de ticket à fermer (ou en entrer un)");
                                }
                            }
                        }
                        else {
                            sender.sendMessage("§c[§6GetSupport§c] §7Ce joueur n'a aucun ticket "+args[1]);
                        }
                    }
                    else {
                        sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas la permission de fermer un ticket");
                    }
                }
                else {
                    if(sender.hasPermission("gs.close")) {
                        if(Data.tickets != null && Data.tickets.size() > 0) {
                            if(CustomProxiedPlayerManager.getCustomPlayerFromSender(sender) != null && CustomProxiedPlayerManager.getCustomPlayerFromSender(sender).getTickets() != null) {
                                final CustomProxiedPlayer p = CustomProxiedPlayerManager.getCustomPlayerFromSender(sender);
                                if(p.getTickets().size() == 1) {
                                    sender.sendMessage("§c[§6GetSupport§c] §7Ticket §e" + p.getFirstTicket().getTicketId() + "§7 fermé");
                                    ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClosedEvent(p.getFirstTicket()));
                                    p.getFirstTicket().close();
                                }
                                else if(p.getTickets().size() >= 2) {
                                    if(args.length == 3) {
                                        if(args[2].length() == 1 && parseInt(args[2]) != 0) {
                                            final BungeeTicket t = p.getTickets().get(parseInt(args[2]) - 1);
                                            sender.sendMessage("§c[§6GetSupport§c] §7Ticket §e" + t.getTicketId() + "§7 fermé");
                                            ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClosedEvent(t));
                                            t.close();
                                        }
                                        else {
                                            sender.sendMessage("§c[§6GetSupport§c] §7Veuillez entrer un numéro de ticket valide");
                                        }
                                    }
                                    else {
                                        sender.sendMessage("§c[§6GetSupport§c] §7Vous devez spécifier le ticket à fermer");
                                    }

                                }
                            }
                            else {
                                sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas de ticket ouvert");
                            }
                        }
                        else {
                            sender.sendMessage("§c[§6GetSupport§c] §7Aucun ticket n'est ouvert");
                        }
                    }
                    else {
                        sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas la permission de fermer un ticket");
                    }

                }
            }
            else if(args != null && args[0].equalsIgnoreCase("claim")) {
                if(sender.hasPermission("gs.claim")) {
                    if(args.length >= 2) {
                        if(CustomProxiedPlayerManager.getCustomPlayer(args[1]) != null && CustomProxiedPlayerManager.getCustomPlayer(args[1]).getTickets() != null) {
                            final CustomProxiedPlayer p = CustomProxiedPlayerManager.getCustomPlayer(args[1]);
                            if(args.length >= 3) {
                                if(args[2].length() == 1 && parseInt(args[2]) != 0) {
                                    final BungeeTicket t = p.getTickets().get(parseInt(args[2]) - 1);
                                    if(!t.isClaimed()) {
                                        t.claim((ProxiedPlayer) sender);
                                        sender.sendMessage("§c[§6GetSupport§c] §7Le Ticket §e" + t.getTicketId() + "§7 a été claimé");
                                        sender.sendMessage("§c[§6GetSupport§c] §e" + t.getPlayer().getName() + "§7 est le demandeur de ce ticket");
                                        sender.sendMessage("§c[§6GetSupport§c] §7Message: " + t.getMessage());
                                        ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClaimedEvent(t));
                                    }
                                }
                            }
                            else {
                                final BungeeTicket t = p.getFirstTicket();
                                if(!t.isClaimed()) {
                                    t.claim((ProxiedPlayer) sender);
                                    sender.sendMessage("§c[§6GetSupport§c] §7Le Ticket §e" + t.getTicketId() + "§7 a été claimé");
                                    sender.sendMessage("§c[§6GetSupport§c] §e" + t.getPlayer().getName() + "§7 est le demandeur de ce ticket");
                                    sender.sendMessage("§c[§6GetSupport§c] §7Message: " + t.getMessage());
                                    ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClaimedEvent(t));
                                }
                                else{
                                    sender.sendMessage("§c[§6GetSupport§c] §7Le ticket §e" + t.getTicketId() + "§7 est déjà claimé");
                                }
                            }
                        }
                        else {
                            sender.sendMessage("§c[§6GetSupport§c] §7Aucun ticket n'est ouvert");
                        }
                    }
                    else {
                        if (Data.tickets != null && Data.tickets.size() == 1) {
                            BungeeTicket t = Data.tickets.get(0);
                            if (!t.isClaimed()) {
                                t.claim((ProxiedPlayer) sender);
                                sender.sendMessage("§c[§6GetSupport§c] §7Le Ticket §e" + t.getTicketId() + "§7 a été claimé");
                                sender.sendMessage("§c[§6GetSupport§c] §e" + t.getPlayer().getName() + "§7 est le demandeur de ce ticket");
                                sender.sendMessage("§c[§6GetSupport§c] §7Message: " + t.getMessage());
                                ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketClaimedEvent(t));
                            } else {
                                sender.sendMessage("§c[§6GetSupport§c] §7Le ticket §e" + t.getTicketId() + "§7 est déjà claimé");
                            }
                        } else {
                            sender.sendMessage("§c[§6GetSupport§c] §7Merci de préciser le nom du joueur");
                        }
                    }
                }
                else {
                    sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas la permission de claim un ticket");
                }
            }
            else if(args != null && args[0].equalsIgnoreCase("create")) {
                if(sender.hasPermission("gs.create")) {
                    String message = "";
                    for(int i = 1; i < args.length; ++i) {
                        message = message + args[i] + " ";
                    }
                    BungeeTicket newTicket = new BungeeTicket((ProxiedPlayer) sender, message);
                    sender.sendMessage("§c[§6GetSupport§c] §7Ticket "+newTicket.getTicketId()+"§7 créé");
                    AlertStaffBungee.alert(newTicket);
                    ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketCreatedEvent(newTicket));
                }
                else {
                    sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas la permission de créer un ticket");
                }
            }
            else if(args != null) {
                if(sender.hasPermission("gs.create")) {
                    String message = "";
                    for(int i = 0; i < args.length; ++i) {
                        message = message + args[i] + " ";
                    }
                    BungeeTicket newTicket = new BungeeTicket((ProxiedPlayer) sender, message);
                    sender.sendMessage("§c[§6GetSupport§c] §7Ticket "+newTicket.getTicketId()+"§7 créé");
                    AlertStaffBungee.alert(newTicket);
                    ProxyServer.getInstance().getPluginManager().callEvent(new BungeeTicketCreatedEvent(newTicket));
                }
                else {
                    sender.sendMessage("§c[§6GetSupport§c] §7Vous n'avez pas la permission de créer un ticket");
                }
            }
            else {
                sender.sendMessage("§c[§6GetSupport§c] §7Aucune commande n'a été trouvée");
            }

        }
    }
}
