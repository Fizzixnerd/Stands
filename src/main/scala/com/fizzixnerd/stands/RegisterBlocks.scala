package com.fizzixnerd.stands

import com.fizzixnerd.stands.Stands.logger
import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object RegisterBlocks {
  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    logger.debug("Registering block")
    event.getRegistry.register(BlockCool)
  }
}
