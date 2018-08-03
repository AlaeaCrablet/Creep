package org.crabcraft.creep;

import com.google.inject.Inject;
import org.crabcraft.creep.commands.CreepCommand;
import org.slf4j.Logger;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.TabCompleteEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.command.CommandManager;

@Plugin(
        id = "creep",
        name = "Creep",
        description = "Spawns a creeper on your position. Why not?",
        version = "1.0.0",
        authors = {
                "Crablet"
        }
)
public class Creep {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("CrabCraft - Creep: Reporting for duty!");
        Sponge.getCommandManager().register(this, creepCommandSpec, "creep");
    }

    CommandSpec creepCommandSpec = CommandSpec.builder()
            .description(Text.of("Spawns a creeper on your location. Use with caution"))
            .permission("crabcraft.creep.command.creep")
            .arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
            .executor(new CreepCommand())
            .build();
}
