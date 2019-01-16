package com.fizzixnerd.stands

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side

@EventBusSubscriber(Array(Side.CLIENT))
object ClientRegistrar {
  @SubscribeEvent
  def registerModels(event: ModelRegistryEvent): Unit = {
    Stands.logger.info("Registering item models.")
    Stands.ITEMS.foreach((item: Item) => {
      val registeredItem = GameRegistry.findRegistry(classOf[Item]).getValue(item.getRegistryName)
      ModelLoader.setCustomModelResourceLocation(
        registeredItem,
        0,
        new ModelResourceLocation(registeredItem.getRegistryName, "normal")
      )
    })
  }

  @SubscribeEvent
  def onPickup(event: EntityItemPickupEvent): Unit = {
    Stands.logger.info("Client fired on EntityItemPickupEvent")
  }
}
