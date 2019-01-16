package com.fizzixnerd.stands

import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

@EventBusSubscriber(Array(Side.SERVER))
object ServerRegistrar {
  @SubscribeEvent
  def onPickup(event: EntityItemPickupEvent): Unit = {
    Stands.logger.info("Server fired on EntityItemPickupEvent")
  }
}
