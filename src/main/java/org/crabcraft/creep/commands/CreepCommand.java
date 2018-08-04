package org.crabcraft.creep.commands;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class CreepCommand implements CommandExecutor {

    private Logger logger;

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player;

        if (args.<Player>getOne("player").orElse(null) == null) {
            player = (Player) src;
        }
        else {
            player = args.<Player>getOne("player").orElse(null);
        }

        Location<World> playerLocation = player.getLocation();

        World world = playerLocation.getExtent();
        Entity creeper = world.createEntity(EntityTypes.CREEPER, playerLocation.getPosition());
        world.spawnEntity(creeper);

        player.sendMessage((Text.of("A creeper has joined forces to aid you!")));

        return CommandResult.success();

    }
}
